package com.desafio.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.entity.Votacao;
import com.desafio.service.VotacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Votacao")
@RequestMapping("/votacao/v1")
public class VotacaoController {
	
	@Autowired
	private VotacaoService votacaoService;
	
	@ApiOperation(value = "Retorna o resultado da Votação por Pauta")
	@GetMapping("/resultado/{idPauta}")
	public Votacao resultado(@PathVariable Long idPauta) {		
		return votacaoService.resultado(idPauta);
	}

}
