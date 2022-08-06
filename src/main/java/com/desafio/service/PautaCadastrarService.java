package com.desafio.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.entity.Pauta;
import com.desafio.repository.PautaRepository;

@Service
public class PautaCadastrarService {
	
	private static final Logger log = LoggerFactory.getLogger (PautaCadastrarService.class);
	
	@Autowired
	private PautaRepository pautaRepository;

	public void cadastrar(Pauta pauta) {
		
		pautaRepository.save(pauta);
		
		log.info("Cadastrando Pauta -> [ID "+pauta.getId()+"]");
	}
	
}