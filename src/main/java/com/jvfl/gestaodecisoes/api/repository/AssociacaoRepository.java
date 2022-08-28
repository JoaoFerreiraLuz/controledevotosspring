package com.jvfl.gestaodecisoes.api.repository;


import com.jvfl.gestaodecisoes.domain.model.Associacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociacaoRepository extends JpaRepository<Associacao, Integer> {
    List<Associacao> findAllByDescricaoContaining(String descriacao);
}