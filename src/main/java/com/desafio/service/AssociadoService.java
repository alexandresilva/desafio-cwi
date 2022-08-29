package com.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.entity.Associado;
import com.desafio.repository.AssociadoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AssociadoService {

	@Autowired
	private AssociadoRepository associadoRepository;
	
	public List<Associado> listarTodos() {
		log.info("Listando todos os Associado");
		return associadoRepository.findAll();
	}
	
	public Optional<Associado> listarPorId(Long id) {
		log.info("Listando Associado por ID");
		return associadoRepository.findById(id);
	}

}
