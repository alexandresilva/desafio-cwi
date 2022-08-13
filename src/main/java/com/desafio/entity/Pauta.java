package com.desafio.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "pauta")
@Data
@Builder
public class Pauta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String descricao;
	
	@Column(name = "data_cadastro")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime dataCadastro;
			
	public Pauta() {
		super();
	}
	
	public Pauta(Long id, String descricao, LocalDateTime dataCadastro) {
		this.id = id;
		this.dataCadastro = LocalDateTime.now();
		this.descricao = descricao;
	}

	public Pauta(String descricao) {
		
		if( descricao == null){
			throw new IllegalArgumentException("A descrição da Pauta não pode ser nula ou vazia");
		}
		
		this.dataCadastro = LocalDateTime.now();
		this.descricao = descricao;
	}
	

}
