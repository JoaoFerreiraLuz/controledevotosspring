package com.jvfl.gestaodecisoes.api.repository;

import com.jvfl.gestaodecisoes.domain.model.Sessao;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer> {
    List<Sessao> findAllByAssociacao_Id(Integer id);

    List<Sessao> findAllByPauta_Id(Integer id);

    List<Sessao> findAllByDataSessaoEquals(Date date);

    Optional<Sessao> findById(Integer id);
}
