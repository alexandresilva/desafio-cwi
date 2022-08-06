package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.entity.SessaoVotacao;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {
	
	SessaoVotacao findById(Integer idSessaoVotacao);

}
