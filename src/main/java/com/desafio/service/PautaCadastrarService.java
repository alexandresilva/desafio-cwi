package com.desafio.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.entity.Pauta;
import com.desafio.exception.ApiException;
import com.desafio.repository.PautaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PautaCadastrarService {
	
	@Autowired
	private PautaRepository pautaRepository;

	/**
		Método para cadastrar uma Pauta
		@author Alexandre Oliveira
		@version 1.0
	*/
	public void cadastrar(Pauta pauta) {
		
		validaRegrasDeNegocio(pauta);
		pauta.setDescricao(pauta.getDescricao());
		pauta.setDataCadastro(LocalDateTime.now());
		pautaRepository.save(pauta);
		
		log.info("Pauta cadastrada com sucesso.");
	}
	
	private void validaRegrasDeNegocio(Pauta pauta) {
		
		if(pauta.getDescricao() == null)
			throw new ApiException("Campo 'Descrição' é obrigatório");
		
		if(pauta.getDescricao().split(" ").length <= 1)
			throw new ApiException("Campo 'Descrição' deve possuir mais de uma palavra");
		
	}
}