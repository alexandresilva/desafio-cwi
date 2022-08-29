package com.desafio.controller.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
