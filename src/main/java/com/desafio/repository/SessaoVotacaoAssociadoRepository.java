package com.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.entity.SessaoVotacaoAssociado;

public interface SessaoVotacaoAssociadoRepository extends JpaRepository<SessaoVotacaoAssociado, Long> {
	
	Optional<SessaoVotacaoAssociado> findByIdSessaoVotacaoAndIdAssociado(Long idSessaoVotacao, Long idAssembleia);

}
