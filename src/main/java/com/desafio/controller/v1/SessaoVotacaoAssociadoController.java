package com.desafio.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.entity.SessaoVotacao;
import com.desafio.entity.SessaoVotacaoAssociado;
import com.desafio.entity.Votacao;
import com.desafio.service.SessaoVotacaoAssociadoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sessao-votacao-associado/v1")
public class SessaoVotacaoAssociadoController {
	
	@Autowired
	private SessaoVotacaoAssociadoService sessaoVotacaoAssociadoService;
	
	@ApiOperation(value = "Inserir apenas um voto por Associado")
	@PostMapping("/votar")
	public ResponseEntity<SessaoVotacao> enviarVoto(@RequestBody SessaoVotacaoAssociado sva) {
		sessaoVotacaoAssociadoService.enviarVoto(sva);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Retorna o resultado da Votação por Pauta")
	@GetMapping("/resultado/{idPauta}")
	public Votacao resultado(@PathVariable Long idPauta) {		
		return sessaoVotacaoAssociadoService.resultado(idPauta);
	}

}
