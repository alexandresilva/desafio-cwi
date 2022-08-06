package com.desafio.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.entity.SessaoVotacao;
import com.desafio.service.SessaoVotacaoService;

@RestController
@RequestMapping("/sessao-votacao")
public class SessaoVotacaoController {
	
	@Autowired
	private SessaoVotacaoService sessaoVotacaoService;
	
	@PostMapping("/v1/abrir-sessao")
	public ResponseEntity<SessaoVotacao> abrirSessaoVotacao(@RequestBody SessaoVotacao sv) {
		
		sessaoVotacaoService.abrirSessaoVotacao(sv);
		URI location = URI.create(String.format("/v1/abrir-sessao/%s", sv.getId()));
	    return ResponseEntity.created(location).build();
		        
//		sessaoVotacaoService.abrirSessaoVotacao(s);
//		return ResponseEntity.ok().build();
	}

}
