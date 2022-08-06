package com.desafio.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Votacao {
	
	@Id
	private Long id;
	
	private String pauta;
	
	private Integer sim;
	
	private Integer nao;
	
	private Integer total;
	
}
