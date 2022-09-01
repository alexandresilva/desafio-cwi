package com.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.entity.Votacao;
import com.desafio.repository.VotacaoRepository;

@Service
public class VotacaoService {

	@Autowired
	private VotacaoRepository votacaoRepository;

	
	/**
		Método para ver o Resultado de uma votação por Pauta. Será exibido o nome da pauta, os votos sim, não e o total.
		@author Alexandre Oliveira
		@version 1.0
	*/
	public Votacao resultado(Long idPauta) {
		return votacaoRepository.resultado(idPauta);
	}
}
