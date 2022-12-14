package com.jvfl.gestaodecisoes.api.repository;

import com.jvfl.gestaodecisoes.domain.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer> {
    List<Voto> findVotosByPauta_Id(Integer pautaId);

    Optional<Voto> findByAssociado_IdAndAndPauta_Id(Integer associadoId, Integer pautaId);
}
