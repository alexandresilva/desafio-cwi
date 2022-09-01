package com.desafio.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.entity.SessaoVotacao;
import com.desafio.repository.SessaoVotacaoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SessaoVotacaoService {
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;

	/**
		Método para abrir uma sessão de votação
		@author Alexandre Oliveira
		@version 1.0
	*/
	public void abrirSessaoVotacao(SessaoVotacao sessaoVotacao) {
		
		/*
		 * Caso não seja informado o tempo de abertura ou informado ZERO, será setado 1 minuto por default 
		 */
		if(sessaoVotacao.getTempoAbertura() == null || sessaoVotacao.getTempoAbertura() == 0) {
			sessaoVotacao.setTempoAbertura(1);
		}
		
		sessaoVotacao.setDataCadastro(LocalDateTime.now());
		sessaoVotacaoRepository.save(sessaoVotacao);
		
		log.info("ID de Abertura da Sessão: " + sessaoVotacao.getId());

	}
	
}