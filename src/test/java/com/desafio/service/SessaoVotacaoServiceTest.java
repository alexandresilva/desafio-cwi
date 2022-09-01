package com.desafio.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.desafio.entity.Pauta;
import com.desafio.entity.SessaoVotacao;
import com.desafio.repository.SessaoVotacaoRepository;

@RunWith(MockitoJUnitRunner.class)
public class SessaoVotacaoServiceTest {

	@InjectMocks
	private SessaoVotacaoService sessaoVotacaoService;

	@Mock
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	SessaoVotacao sessaoVotacao = new SessaoVotacao();
	Pauta p = new Pauta();
	
	@Before
	public void setup() {
		
		p = Pauta
				.builder()
				.id(1L)
				.descricao("Teste cadastro pauta")
				.dataCadastro(LocalDateTime.now())
				.build();
		
		sessaoVotacao = SessaoVotacao
				.builder()
				.id(1L)
				.dataCadastro(LocalDateTime.now())
				.dataFinalSessao(LocalDateTime.now().plusMinutes(30))
				.build();
		
	}

	@Test
	public void deveAbrirSessaoVotacao(){		
		sessaoVotacaoService.abrirSessaoVotacao(sessaoVotacao);
		verify(sessaoVotacaoRepository).save(any(SessaoVotacao.class));
	}

}
