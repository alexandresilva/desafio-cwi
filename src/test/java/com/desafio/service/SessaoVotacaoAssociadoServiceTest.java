package com.desafio.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.desafio.client.CPFClient;
import com.desafio.entity.Associado;
import com.desafio.entity.SessaoVotacao;
import com.desafio.entity.SessaoVotacaoAssociado;
import com.desafio.repository.AssociadoRepository;
import com.desafio.repository.SessaoVotacaoAssociadoRepository;
import com.desafio.repository.SessaoVotacaoRepository;
import com.desafio.response.CPFResponse;

@RunWith(MockitoJUnitRunner.class)
public class SessaoVotacaoAssociadoServiceTest {

	@InjectMocks
	private SessaoVotacaoAssociadoService sessaoVotacaoAssociadoService;

	@Mock
	private SessaoVotacaoAssociadoRepository sessaoVotacaoAssociadoRepository;

	@Mock
	private SessaoVotacaoRepository sessaoVotacaoRepository;

	@Mock
	private AssociadoRepository associadoRepository;

	@Mock
	private CPFClient cpfClient;
	
	SessaoVotacaoAssociado sva = new SessaoVotacaoAssociado();
	Associado associado = new Associado();
	CPFResponse cpfResponse = new CPFResponse();
	
	@Before
	public void setup() {
		
		sva = SessaoVotacaoAssociado
				.builder()
				.idSessaoVotacao(3L)
				.idAssociado(2L)
				.voto(true)
				.dataCadastro(LocalDateTime.now())
				.build();
		
		associado = Associado
				.builder()
				.id(1L)
				.nome("Alexandre Teste")
				.email("alexandreteste@gmail.com")
				.cpf(29834198000L)
				.apto(true)
				.dataCadastro(LocalDateTime.now())
				.build();
				
		cpfResponse.setCpf(associado.getCpf());
		cpfResponse.setStatus("ABLE_TO_VOTE");
		
		Optional<SessaoVotacao> verificaSessao = Optional.of(new SessaoVotacao());
		verificaSessao.get().setDescricao("Sessão para expulsão de colaborador");
		verificaSessao.get().setDataCadastro(LocalDateTime.now());		
		verificaSessao.get().setTempoAbertura(30);
		when(sessaoVotacaoRepository.findById(sva.getIdSessaoVotacao())).thenReturn(verificaSessao);
		
	}


	@Test
	public void deveEnviarVoto(){
		
		Optional<Associado> ass = Optional.of(new Associado());
		ass.get().setId(associado.getId());
		ass.get().setNome(associado.getNome());
		ass.get().setEmail(associado.getEmail());
		ass.get().setApto(associado.getApto());
		ass.get().setCpf(associado.getCpf());
		when(associadoRepository.findById(sva.getIdAssociado())).thenReturn(ass);
		when(cpfClient.getCpf(ass.get().getCpf())).thenReturn(cpfResponse);
		
        sessaoVotacaoAssociadoService.enviarVoto(sva);

		verify(sessaoVotacaoAssociadoRepository).save(any(SessaoVotacaoAssociado.class));
	}


	@Test
	public void verificaSeAssociadoJaVotou(){
		
        Optional<SessaoVotacaoAssociado> verificaVoto = Optional.of(new SessaoVotacaoAssociado());
		verificaVoto.get().setIdSessaoVotacao(3L);
		verificaVoto.get().setIdAssociado(2L);
		verificaVoto.get().setVoto(true);		
		when(sessaoVotacaoAssociadoRepository.findByIdSessaoVotacaoAndIdAssociado(sva.getIdSessaoVotacao(), sva.getIdAssociado())).thenReturn(verificaVoto);
		
		Optional<Associado> ass = Optional.of(new Associado());
		ass.get().setId(associado.getId());
		ass.get().setNome(associado.getNome());
		ass.get().setEmail(associado.getEmail());
		ass.get().setApto(associado.getApto());
		ass.get().setCpf(associado.getCpf());
		when(associadoRepository.findById(sva.getIdAssociado())).thenReturn(ass);
		when(cpfClient.getCpf(ass.get().getCpf())).thenReturn(cpfResponse);
		
        sessaoVotacaoAssociadoService.enviarVoto(sva);

		verify(sessaoVotacaoAssociadoRepository, never()).save(any(SessaoVotacaoAssociado.class));
	}

	
}