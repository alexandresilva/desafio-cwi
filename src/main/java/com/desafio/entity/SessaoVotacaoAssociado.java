package com.desafio.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
	
}
