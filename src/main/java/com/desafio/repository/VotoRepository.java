package com.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.entity.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {
	
	Optional<Voto> findById(Long id);
	
	Optional<Voto> findByAssociadoIdAndPautaId(Long associadoId, Long pautaId);

}
