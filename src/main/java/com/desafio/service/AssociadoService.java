package com.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.entity.Associado;
import com.desafio.entity.Pauta;
import com.desafio.exception.ApiException;
import com.desafio.repository.AssociadoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AssociadoService {

	@Autowired
	private AssociadoRepository associadoRepository;

	/**
		Método para cadastrar uma Pauta
		@author Alexandre Oliveira
		@version 1.0
	*/
	public void cadastrar(Associado associado) {
		
		validaRegrasDeNegocio(associado);
		associadoRepository.save(associado);
		
		log.info("Associado cadastrado com sucesso.");
	}
	
	public List<Associado> listarTodos() {
		log.info("Listando todos os Associado");
		return associadoRepository.findAll();
	}
	
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

}
