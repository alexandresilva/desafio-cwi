package com.desafio.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.desafio.response.CPFResponse;

@FeignClient(url = "${client.url.cpf}", name="valida-cpf")
public interface CPFClient {

	@GetMapping("/{cpf}/")
	CPFResponse getCpf(@PathVariable("cpf") long cpf);

}
