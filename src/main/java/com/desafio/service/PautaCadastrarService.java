package com.desafio.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.entity.Pauta;
import com.desafio.exception.ApiException;
import com.desafio.repository.PautaRepository;

@Service
public class PautaCadastrarService {
	
	private static final Logger log = LoggerFactory.getLogger (PautaCadastrarService.class);
	
	@Autowired
	private PautaRepository pautaRepository;

	public void cadastrar(Pauta pauta) {
		
		validaRegrasDeNegocio(pauta);
		
		pautaRepository.save(pauta);
		
		log.info("Cadastrando Pauta -> [ID "+pauta.getId()+"]");
	}
	
	private void validaRegrasDeNegocio(Pauta pauta) {
		
		if(pauta.getDescricao() == null)
			throw new ApiException("Campo 'Descrição' é obrigatório");
		
		if(pauta.getDescricao().split(" ").length <= 1)
			throw new ApiException("Campo 'Descrição' deve possuir mais de uma palavra");
		
		if(pauta.getDataCadastro() == null)
			throw new ApiException("Data de Cadatro é obrigatória");
		
	}
}