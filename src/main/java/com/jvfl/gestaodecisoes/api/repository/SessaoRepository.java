package com.jvfl.gestaodecisoes.api.repository;

import com.jvfl.gestaodecisoes.domain.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer> {
    List<Sessao> findSessaoByAssociacao_Id(Integer id);
//    List<Sessao> findSessaoByPauta_Id(Integer id);
//    List<Sessao> findSessaoByData_sessao(Date date);
    List<Sessao> findSessaosByPauta_Id(Integer id);
//    List<Sessao> findSessaosByData_sessao(Date date);
}
