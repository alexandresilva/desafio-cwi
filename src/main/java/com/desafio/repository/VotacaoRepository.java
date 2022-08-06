package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.entity.Votacao;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {
	
	@Transactional
	@Query(value="SELECT p.id, p.descricao AS pauta, "
			+ "		(SELECT COUNT(*) as totalVotos "
			+ "		FROM SESSAO_VOTACAO_ASSOCIADO  sva2 "
			+ "		JOIN SESSAO_VOTACAO sv2 ON sva2.ID_SESSAO_VOTACAO = sv2.ID "
			+ "		WHERE ID_SESSAO_VOTACAO = sv.id AND sva2.voto = true) as sim, "

			+ "		(SELECT COUNT(*) as totalVotos "
			+ "		FROM SESSAO_VOTACAO_ASSOCIADO  sva2 "
			+ "		JOIN SESSAO_VOTACAO sv2 ON sva2.ID_SESSAO_VOTACAO = sv2.ID "
			+ "		WHERE ID_SESSAO_VOTACAO = sv.id AND sva2.voto = false) as nao, "

			+ "		(SELECT COUNT(*) as totalVotos "
			+ "		FROM SESSAO_VOTACAO_ASSOCIADO  sva2 "
			+ "		JOIN SESSAO_VOTACAO sv2 ON sva2.ID_SESSAO_VOTACAO = sv2.ID "
			+ "		WHERE ID_SESSAO_VOTACAO = sv.id) as total "
			+ "FROM SESSAO_VOTACAO sv "
			+ "JOIN PAUTA			p 	ON (sv.id_pauta = p.id) "
			+ "WHERE p.id = :idPauta", nativeQuery=true)
	Votacao resultado(@Param("idPauta") Long idPauta);

}
