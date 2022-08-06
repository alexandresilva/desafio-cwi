package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.entity.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
	
	Associado findById(Integer idAssociado);
	
	Associado findByCpf(String cpf);

}
