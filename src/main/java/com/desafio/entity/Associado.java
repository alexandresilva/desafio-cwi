package com.desafio.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "associado")
@Data
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
	private String cpf;
	
	@Column(nullable = false)
	private Boolean apto;
	
	@Column(name = "data_cadastro", nullable = false)
	private LocalDateTime dataCadastro;

	public Associado() {
		super();
	}

	public Associado(String nome, String email, String cpf) {
		
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

}
