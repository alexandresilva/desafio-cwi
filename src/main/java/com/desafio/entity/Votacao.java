package com.desafio.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Votacao {
	
	@Id
	private Long id;
	
	private String nomePauta;
	
	private Integer sim;
	
	private Integer nao;
	
	private Integer total;
	
}
