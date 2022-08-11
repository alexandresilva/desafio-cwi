package com.desafio.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class SessaoVotacaoAssociadoService {

	private static final Logger log = LoggerFactory.getLogger(SessaoVotacaoAssociadoService.class);

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

	public void enviarVoto(SessaoVotacaoAssociado sva) {

		sva.setDataCadastro(LocalDateTime.now());

		Optional<Associado> associado = associadoRepository.findById(sva.getIdAssociado());
		Optional<SessaoVotacao> verificaSessao = sessaoVotacaoRepository.findById(sva.getIdSessaoVotacao());
		Optional<SessaoVotacaoAssociado> verificaVoto = sessaoVotacaoAssociadoRepository
				.findByIdSessaoVotacaoAndIdAssociado(sva.getIdSessaoVotacao(), sva.getIdAssociado());
		

		if(verificaSessao.isPresent()) {
			CPFResponse cpfResponse = cpfClient.getCpf(Long.parseLong(associado.get().getCpf()));
			cpfResponse.setCpf(associado.get().getCpf());
			if(cpfResponse.getStatus().equals("ABLE_TO_VOTE")) {
				verificaDados(sva, associado, verificaSessao, verificaVoto);
			}else {
				log.info("O CPF " + cpfResponse.getCpf() + " NÃO ESTÁ APTO para votar");
			}
		}else {
			log.info("Sessão não existente.");
		}

	}

	private void verificaDados(SessaoVotacaoAssociado sva, Optional<Associado> associado,
			Optional<SessaoVotacao> verificaSessao, Optional<SessaoVotacaoAssociado> verificaVoto) {

		if (associado.get().getApto()) {
			if (verificaSessao.get().getDataCadastro().plusMinutes(verificaSessao.get().getTempoAbertura())
					.isBefore(LocalDateTime.now())) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				String dataEncerramento = verificaSessao.get().getDataCadastro().format(formatter);
				log.info("Sessão Encerrada -> [Horário encerramento: " + dataEncerramento + "]");
			} else if (verificaVoto.isPresent()) {
				log.info("Associado já votou nesta Sessão.");
			} else {
				sessaoVotacaoAssociadoRepository.save(sva);
				log.info("Votação inserida com sucesso.");
			}
		} else {
			log.info("Associado não está apto para votar.");
		}
	}
	
	public Votacao resultado(Long idPauta) {
		Votacao res = votacaoRepository.resultado(idPauta);
		return res;
	}

}