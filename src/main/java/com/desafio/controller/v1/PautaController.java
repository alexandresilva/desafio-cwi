package com.desafio.controller.v1;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.dto.PautaDTO;
import com.desafio.service.PautaCadastrarService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pauta/v1")
public class PautaController {
	
	@Autowired
	private PautaCadastrarService pautaCadastrarService;
	
	@ApiOperation(value = "Cadastra uma Pauta para Votação")
	@PostMapping("/cadastrar")
	public ResponseEntity<PautaDTO> cadastrar(@RequestBody PautaDTO pautaDTO) {
		pautaCadastrarService.cadastrar(pautaDTO);
		URI location = URI.create(String.format("/cadastrar/%s", pautaDTO.getId()));
	    return ResponseEntity.created(location).build();
	}

}
