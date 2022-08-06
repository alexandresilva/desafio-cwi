package com.desafio.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.entity.Pauta;
import com.desafio.service.PautaCadastrarService;

@RestController
@RequestMapping("/pauta")
public class PautaController {
	
	@Autowired
	private PautaCadastrarService pautaCadastrarService;
	
	@PostMapping("/v1/cadastrar")
	public ResponseEntity<Pauta> cadastrar(@RequestBody Pauta pauta) {
		pautaCadastrarService.cadastrar(pauta);
		URI location = URI.create(String.format("/v1/cadastrar/%s", pauta.getId()));
	    return ResponseEntity.created(location).build();
	}

}
