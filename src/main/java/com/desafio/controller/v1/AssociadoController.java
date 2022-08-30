package com.desafio.controller.v1;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.entity.Associado;
import com.desafio.service.AssociadoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Associado")
@RequestMapping("/associado/v1")
public class AssociadoController {
	
	@Autowired
	private AssociadoService associadoService;
	
	@ApiOperation(value = "Cadastra uma Associado para Votação")
	@PostMapping("/cadastrar")
	public ResponseEntity<Associado> cadastrar(@RequestBody Associado associado) {
		associadoService.cadastrar(associado);
		URI location = URI.create(String.format("/cadastrar/%s", associado.getId()));
	    return ResponseEntity.created(location).build();
	}
	
	@ApiOperation(value = "Listar todos os Associados")
	@GetMapping("/listarTodos")
	public List<Associado> listarTodos() {		
		return associadoService.listarTodos();
	}
	
	@ApiOperation(value = "Listar Associado por ID")
	@GetMapping("/listarPorId/{id}")
	public Optional<Associado> listarPorId(@PathVariable Long id) {		
		return associadoService.listarPorId(id);
	}

}
