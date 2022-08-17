package com.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.entity.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
	
	Optional<Associado> findById(Long idAssociado);
	
	Associado findByCpf(String cpf);

}
