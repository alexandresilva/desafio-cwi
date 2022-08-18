package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.entity.Votacao;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {
	
	@Transactional
	@Query(value="SELECT * FROM VW_VOTACAO vw WHERE vw.id = :idPauta", nativeQuery=true)
	Votacao resultado(@Param("idPauta") Long idPauta);

}
