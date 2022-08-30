package com.desafio.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.client.CPFClient;
import com.desafio.entity.Associado;
import com.desafio.entity.SessaoVotacao;
import com.desafio.entity.SessaoVotacaoAssociado;
import com.desafio.entity.Votacao;
import com.desafio.repository.AssociadoRepository;
import com.desafio.repository.SessaoVotacaoAssociadoRepository;
import com.desafio.repository.SessaoVotacaoRepository;
import com.desafio.repository.VotacaoRepository;
import com.desafio.response.CPFResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SessaoVotacaoAssociadoService {

	@Autowired
	private SessaoVotacaoAssociadoRepository sessaoVotacaoAssociadoRepository;

	@Autowired
	private VotacaoRepository votacaoRepository;

	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;

	@Autowired
	private AssociadoRepository associadoRepository;

	@Autowired
	private CPFClient cpfClient;

	/**
		Método para enviar o voto do Associado
		@author Alexandre Oliveira
		@version 1.0
	*/
	public void enviarVoto(SessaoVotacaoAssociado sva) {

		sva.setDataCadastro(LocalDateTime.now());

		Optional<SessaoVotacao> verificaSessao = sessaoVotacaoRepository.findById(sva.getIdSessaoVotacao());

		if(verificaSessao.isPresent()) {
			
			verificaVoto(sva, verificaSessao);
			
		}else {
			log.info("Sessão não existente.");
		}

	}


	private void verificaVoto(SessaoVotacaoAssociado sva, Optional<SessaoVotacao> verificaSessao) {
		
		Optional<SessaoVotacaoAssociado> verificaVoto = sessaoVotacaoAssociadoRepository.findByIdSessaoVotacaoAndIdAssociado(sva.getIdSessaoVotacao(), sva.getIdAssociado());
		
		if(verificaVoto.isPresent()) {
			
			verificaCpfEAptidao(sva, verificaSessao);
			
		}else {
			log.info("Associado já votou nesta Sessão.");
		}
	}


	private void verificaCpfEAptidao(SessaoVotacaoAssociado sva, Optional<SessaoVotacao> verificaSessao) {
		
		Optional<Associado> associado = associadoRepository.findById(sva.getIdAssociado());
		CPFResponse cpfResponse = cpfClient.getCpf(associado.get().getCpf());
		cpfResponse.setCpf(associado.get().getCpf());
		
		if(cpfResponse.getStatus().equals("ABLE_TO_VOTE") && associado.get().getApto()) {
			
			if (verificaEncerramentoSessao(verificaSessao)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				String dataEncerramento = verificaSessao.get().getDataCadastro().format(formatter);
				log.info("Sessão Encerrada -> [Horário encerramento: " + dataEncerramento + "]");
			} else {
				sessaoVotacaoAssociadoRepository.save(sva);
				log.info("Votação inserida com sucesso.");
			}
			
		}else {
			log.info("O CPF " + cpfResponse.getCpf() + " NÃO ESTÁ APTO para votar");
		}
	}

	
	/**
		Método para ver o Resultado de uma votação por Pauta. Será exibido o nome da pautam, os votos sim, não e o total.
		@author Alexandre Oliveira
		@version 1.0
	*/
	public Votacao resultado(Long idPauta) {
		return votacaoRepository.resultado(idPauta);
	}
	
	private boolean verificaEncerramentoSessao(Optional<SessaoVotacao> verificaSessao) {
		boolean f = verificaSessao.get().getDataCadastro().plusMinutes(verificaSessao.get().getTempoAbertura()).isBefore(LocalDateTime.now());
		return f;
	}

}