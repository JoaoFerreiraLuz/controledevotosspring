package com.jvfl.gestaodecisoes.api.repository;


import com.jvfl.gestaodecisoes.domain.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Integer> {
    List<Associado> findAssociadoByNome(String nome);
}
