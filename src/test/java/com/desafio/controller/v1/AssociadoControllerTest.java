package com.desafio.controller.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.desafio.repository.AssociadoRepository;
import com.desafio.utils.Utilitarios;

@RunWith(MockitoJUnitRunner.class)
public class AssociadoControllerTest {

	@Mock
	private AssociadoRepository associadoRepository;
	
	String cpfAssociado = "00011122233";
	String cpfAssociadoValido = "23902294094";
	
	
	@Test
	public void verificaTamanhoCpf() {
		assertEquals(11, cpfAssociado.length(), "CPF com tamanho correto");
	}
	
	@Test
	public void verificaCpfInvalido() {
		assertEquals(false, Utilitarios.validaCpf(cpfAssociado), "CPF Inválido");
	}
	
	@Test
	public void verificaCpfValido() {
		assertEquals(true, Utilitarios.validaCpf(cpfAssociadoValido), "CPF Válido");
	}

}
