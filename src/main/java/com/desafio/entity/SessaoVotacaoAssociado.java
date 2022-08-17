package com.desafio.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "sessao_votacao_associado")
@Data
@Builder
public class SessaoVotacaoAssociado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "id_sessao_votacao")
	private Long idSessaoVotacao;
	
	@Column(name = "id_associado")
	private Long idAssociado;
	
	@Column(name = "voto")
	private Boolean voto;
	
	@Column(name = "data_cadastro")
	private LocalDateTime dataCadastro;
	
	public SessaoVotacaoAssociado() {
		super();
	}
		

	public SessaoVotacaoAssociado(Long idSessaoVotacao, Long idAssociado, Boolean voto) {
		
		if( idSessaoVotacao == null){
			throw new IllegalArgumentException("É necessário informar uma Sessão de Votação.");
		}
		
		if(idAssociado == null ){
			throw new IllegalArgumentException("Informe um Associado para votar.");
		}
		
		if(voto == null ){
			throw new IllegalArgumentException("O voto não pode ser nulo.");
		}
		
		
		this.idSessaoVotacao = idSessaoVotacao;
		this.idAssociado = idAssociado;
		this.voto = voto;
	}

	
	public SessaoVotacaoAssociado(Long id, Long idSessaoVotacao, Long idAssociado, Boolean voto,
			LocalDateTime dataCadastro) {
		super();
		this.id = id;
		this.idSessaoVotacao = idSessaoVotacao;
		this.idAssociado = idAssociado;
		this.voto = voto;
		this.dataCadastro = dataCadastro;
	}

}
