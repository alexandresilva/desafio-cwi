package com.desafio.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "sessao_votacao")
@Data
public class SessaoVotacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String descricao;
	
	@Column(name = "data_cadastro")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime dataCadastro;
	
	@Column(name = "id_pauta")
	private Long idPauta;
	
	@Column(name = "tempo_abertura")
	private Integer tempoAbertura;
	
	public SessaoVotacao() {
		super();
	}

	public SessaoVotacao(String descricao, Long idPauta, Integer tempoAbertura) {
		
		if( descricao == null){
			throw new IllegalArgumentException("A Sessão não pode ser criado sem DESCRIÇÃO");
		}
		
		if(idPauta == null ){
			throw new IllegalArgumentException("A Sessão não pode ser criado sem PAUTA");
		}
		
		if(tempoAbertura == null ){
			this.tempoAbertura = 1;
		}else {
			this.tempoAbertura = tempoAbertura;
		}
		
		this.descricao = descricao;
		this.idPauta = idPauta;
	}

}
