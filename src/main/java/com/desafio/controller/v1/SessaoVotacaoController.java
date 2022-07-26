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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Sessão Votação")
@RequestMapping("/sessao-votacao/v1")
public class SessaoVotacaoController {
	
	@Autowired
	private SessaoVotacaoService sessaoVotacaoService;
	
	@ApiOperation(value = "Abre uam Sessão para Votação")
	@PostMapping("/abrir-sessao")
	public ResponseEntity<SessaoVotacao> abrirSessaoVotacao(@RequestBody SessaoVotacao sessaoVotacao) {		
		sessaoVotacaoService.abrirSessaoVotacao(sessaoVotacao);
		URI location = URI.create(String.format("/abrir-sessao/%s", sessaoVotacao.getId()));
	    return ResponseEntity.created(location).build();

	}

}