package com.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.dto.PautaDTO;
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
	public void cadastrar(PautaDTO pautaDTO) {
		
		validaRegrasDeNegocio(pautaDTO);
		Pauta pauta = new Pauta();
		pauta.setDescricao(pautaDTO.getDescricao());
		pauta.setDataCadastro(pautaDTO.getDataCadastro());
		pautaRepository.save(pauta);
		
		pautaDTO.setId(pauta.getId());
		
		log.info("Pauta cadastrada com sucesso.");
	}
	
	private void validaRegrasDeNegocio(PautaDTO pautaDTO) {
		
		if(pautaDTO.getDescricao() == null)
			throw new ApiException("Campo 'Descrição' é obrigatório");
		
		if(pautaDTO.getDescricao().split(" ").length <= 1)
			throw new ApiException("Campo 'Descrição' deve possuir mais de uma palavra");
		
		if(pautaDTO.getDataCadastro() == null)
			throw new ApiException("Data de Cadastro é obrigatória");
		
	}
}