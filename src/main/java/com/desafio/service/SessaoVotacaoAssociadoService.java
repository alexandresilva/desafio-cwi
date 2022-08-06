package com.desafio.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.entity.Associado;
import com.desafio.entity.SessaoVotacao;
import com.desafio.entity.SessaoVotacaoAssociado;
import com.desafio.entity.Votacao;
import com.desafio.repository.AssociadoRepository;
import com.desafio.repository.SessaoVotacaoAssociadoRepository;
import com.desafio.repository.SessaoVotacaoRepository;
import com.desafio.repository.VotacaoRepository;

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

	public void enviarVoto(Long idAssociado, Long idSessaoVotacao, Boolean voto) {

		SessaoVotacaoAssociado sva = new SessaoVotacaoAssociado();
		sva.setIdAssociado(idAssociado);
		sva.setIdSessaoVotacao(idSessaoVotacao);
		sva.setVoto(voto);
		sva.setDataCadastro(LocalDateTime.now());

		Optional<Associado> associado = associadoRepository.findById(idAssociado);
		Optional<SessaoVotacao> verificaSessao = sessaoVotacaoRepository.findById(idSessaoVotacao);
		Optional<SessaoVotacaoAssociado> verificaVoto = sessaoVotacaoAssociadoRepository
				.findByIdSessaoVotacaoAndIdAssociado(idSessaoVotacao, idAssociado);

		verificaDados(sva, associado, verificaSessao, verificaVoto);

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