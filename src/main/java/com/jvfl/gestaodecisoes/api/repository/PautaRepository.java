package com.jvfl.gestaodecisoes.api.repository;

import com.jvfl.gestaodecisoes.domain.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PautaRepository extends JpaRepository<Pauta, Integer> {
    List<Pauta> findPautaByDescricao(String descricao);

//    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
//    List<Topico> carregarPorNomeDoCurso(String nomeCurso);
//    List<ResultadoPautaDto> findPautaByResultaVotacao(Integer id);

}
