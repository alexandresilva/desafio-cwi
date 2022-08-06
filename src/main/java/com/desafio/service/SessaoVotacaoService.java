package com.desafio.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.entity.SessaoVotacao;
import com.desafio.repository.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoService {
	
	private static final Logger log = LoggerFactory.getLogger (SessaoVotacaoService.class);
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;

	public StringBuilder abrirSessaoVotacao(SessaoVotacao sv) {
		
		/*
		 * Caso não seja informado o tempo de abertura ou informado ZERO, será setado 1 minuto por default 
		 */
		if(sv.getTempoAbertura() == null || sv.getTempoAbertura() == 0) {
			sv.setTempoAbertura(1);
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String inicioSessao = LocalDateTime.now().format(formatter);
        String encerramentoSessao = LocalDateTime.now().plusMinutes(sv.getTempoAbertura()).format(formatter);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Início da Sessão: " + inicioSessao + " ---- ");
		sb.append("Encerramento da Sesão: " + encerramentoSessao);
		
		sv.setDataCadastro(LocalDateTime.now());
		sessaoVotacaoRepository.save(sv);
		
		log.info("Abertura de Sessão -> [ID "+sv.getId()+"]");
		log.info(sb.toString());

		return sb;
		
	}
	
}