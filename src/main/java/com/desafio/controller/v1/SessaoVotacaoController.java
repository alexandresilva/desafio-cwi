package com.desafio.controller.v1;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.entity.SessaoVotacao;
import com.desafio.service.SessaoVotacaoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sessao-votacao/v1")
public class SessaoVotacaoController {
	
	@Autowired
	private SessaoVotacaoService sessaoVotacaoService;
	
	@ApiOperation(value = "Abre uam Sessão para Votação")
	@PostMapping("/abrir-sessao")
	public ResponseEntity<SessaoVotacao> abrirSessaoVotacao(@RequestBody SessaoVotacao sv) {
		
		sessaoVotacaoService.abrirSessaoVotacao(sv);
		URI location = URI.create(String.format("/abrir-sessao/%s", sv.getId()));
	    return ResponseEntity.created(location).build();

	}

}