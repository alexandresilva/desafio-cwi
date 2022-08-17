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

import com.desafio.entity.SessaoVotacao;
import com.desafio.repository.SessaoVotacaoRepository;

@RunWith(MockitoJUnitRunner.class)
public class SessaoVotacaoServiceTest {

	@InjectMocks
	private SessaoVotacaoService sessaoVotacaoService;

	@Mock
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	SessaoVotacao sv = new SessaoVotacao();
	
	@Before
	public void setup() {
		
		sv = SessaoVotacao
				.builder()
				.id(1L)
				.descricao("Sessão Test")
				.dataCadastro(LocalDateTime.now())
				.idPauta(1L)
				.tempoAbertura(30)
				.build();
		
	}

	@Test
	public void deveAbrirSessaoVotacao(){		
		sessaoVotacaoService.abrirSessaoVotacao(sv);
		verify(sessaoVotacaoRepository).save(any(SessaoVotacao.class));
	}

}
