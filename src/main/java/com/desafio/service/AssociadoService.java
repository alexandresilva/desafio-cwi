package com.desafio.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.client.CPFClient;
import com.desafio.entity.Associado;
import com.desafio.entity.SessaoVotacao;
import com.desafio.entity.Voto;
import com.desafio.exception.ApiException;
import com.desafio.repository.AssociadoRepository;
import com.desafio.repository.SessaoVotacaoRepository;
import com.desafio.repository.VotoRepository;
import com.desafio.response.CPFResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AssociadoService {

	@Autowired
	private AssociadoRepository associadoRepository;

	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;

	@Autowired
	private VotoRepository votoRepository;

	@Autowired
	private CPFClient cpfClient;

	/**
		Método para cadastrar um Associado
		@author Alexandre Oliveira
		@version 1.0
	*/
	public void cadastrar(Associado associado) {
		
		validaRegrasDeNegocio(associado);
		associadoRepository.save(associado);
		
		log.info("Associado cadastrado com sucesso.");
	}

	/**
		Método listar todos os Associados
		@author Alexandre Oliveira
		@version 1.0
	*/	
	public List<Associado> listarTodos() {
		log.info("Listando todos os Associado");
		return associadoRepository.findAll();
	}

	/**
		Método listar o Associado por ID
		@author Alexandre Oliveira
		@version 1.0
	*/	
	public Optional<Associado> listarPorId(Long id) {
		log.info("Listando Associado por ID");
		return associadoRepository.findById(id);
	}
	
	private void validaRegrasDeNegocio(Associado associado) {
		
		if(associado.getNome() == null)
			throw new ApiException("Campo 'Nome' é obrigatório");
		
		if(associado.getNome().split(" ").length <= 1)
			throw new ApiException("Campo 'Nome' deve possuir mais de uma palavra");
		
		if(associado.getEmail() == null)
			throw new ApiException("Campo 'Email' é obrigatório");
		
		if(associado.getCpf() == null)
			throw new ApiException("Campo 'CPF' é obrigatório");
		
	}

	/**
		Método para enviar o voto do Associado
		@author Alexandre Oliveira
		@version 1.0
	*/
	public void enviarVoto(Voto voto) {

		voto.setDataCadastro(LocalDateTime.now());

		Optional<SessaoVotacao> verificaSessao = sessaoVotacaoRepository.findById(voto.getPauta().getSessaoVotacao().getId());

		if(verificaSessao.isPresent()) {
			
			verificaVoto(voto);
			
		}else {
			log.info("Sessão não existente.");
		}

	}


	private void verificaVoto(Voto voto) {
		
		Optional<Voto> verificaVoto = votoRepository.findByAssociadoIdAndPautaId(voto.getAssociado().getId(), voto.getPauta().getId());
		
		if(verificaVoto.isPresent()) {
			log.info("Associado já votou nesta Sessão.");
		}else {
			verificaCpfEAptidao(voto);
		}
	}


	private void verificaCpfEAptidao(Voto voto) {
		
		CPFResponse cpfResponse = cpfClient.getCpf(voto.getAssociado().getCpf());
		cpfResponse.setCpf(voto.getAssociado().getCpf());
		
		if(cpfResponse.getStatus().equals("ABLE_TO_VOTE") && voto.getAssociado().getApto()) {
			
			if (verificaEncerramentoSessao(voto)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				String dataEncerramento = voto.getPauta().getSessaoVotacao().getDataCadastro().format(formatter);
				log.info("Sessão Encerrada -> [Horário encerramento: " + dataEncerramento + "]");
			} else {
				votoRepository.save(voto);
				log.info("Voto inserido com sucesso.");
			}
			
		}else {
			log.info("O CPF " + cpfResponse.getCpf() + " NÃO ESTÁ APTO para votar");
		}
	}
	
	private boolean verificaEncerramentoSessao(Voto voto) {
		boolean flag = voto.getPauta().getSessaoVotacao().getDataCadastro().isBefore(voto.getPauta().getSessaoVotacao().getDataFinalSessao());
		return flag;
	}

}
