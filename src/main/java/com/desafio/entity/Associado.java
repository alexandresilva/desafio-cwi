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
@Table(name = "associado")
@Data
@Builder
public class Associado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false, length=11)
	private Long cpf;
	
	@Column(nullable = false)
	private Boolean apto;
	
	@Column(name = "data_cadastro", nullable = false)
	private LocalDateTime dataCadastro;

	public Associado() {
		super();
	}

	public Associado(String nome, String email, Long cpf) {
		
		if( nome == null){
			throw new IllegalArgumentException("O Associado não pode ser criado sem NOME");
		}
		
		if(email == null ){
			throw new IllegalArgumentException("O Associado não pode ser criado sem EMAIL");
		}
		
		if(cpf == null ){
			throw new IllegalArgumentException("O Associado não pode ser criado sem CPF");
		}
		
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}

	public Associado(Long id, String nome, String email, Long cpf, Boolean apto, LocalDateTime dataCadastro) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.apto = apto;
		this.dataCadastro = dataCadastro;
	}

}
