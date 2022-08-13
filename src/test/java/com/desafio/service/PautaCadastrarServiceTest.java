package com.desafio.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.desafio.client.CPFClient;
import com.desafio.entity.Pauta;
import com.desafio.exception.ApiException;
import com.desafio.repository.PautaRepository;

@RunWith(MockitoJUnitRunner.class)
public class PautaCadastrarServiceTest {

	@InjectMocks
	private PautaCadastrarService pautaCadastrarService;

	@Mock
	private PautaRepository pautaRepository;

	@Mock
	private CPFClient cpfClient;

	@Test
	public void deveCadastrarPauta(){
		
		Pauta pauta = Pauta
				.builder()
				.id(1L)
				.descricao("Pauta para aumento salarial")
				.dataCadastro(LocalDateTime.now())
				.build();

		pautaCadastrarService.cadastrar(pauta);

		verify(pautaRepository).save(any(Pauta.class));
	}

	@Test(expected = ApiException.class)
	public void naoCadastrarPautaSemNome(){
		
		Pauta pauta = Pauta
				.builder()
				.id(1L)
				.descricao(null)
				.dataCadastro(LocalDateTime.now())
				.build();

		pautaCadastrarService.cadastrar(pauta);

		verify(pautaRepository).save(any(Pauta.class));
	}

	@Test(expected = ApiException.class)
	public void naoCadastrarPautaSemData(){
		
		Pauta pauta = Pauta
				.builder()
				.id(1L)
				.descricao("Pauta para aumento salarial")
				.dataCadastro(LocalDateTime.now())
				.build();
		pauta.setDataCadastro(null);

		pautaCadastrarService.cadastrar(pauta);

		verify(pautaRepository).save(any(Pauta.class));
	}

}
