package com.jvfl.gestaodecisoes.domain.dto;

import com.jvfl.gestaodecisoes.domain.model.Associacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociacaoDto implements Serializable {
    private Integer id;
    private String descricao;

    public AssociacaoDto(Associacao associacao) {
        this.id = associacao.getId();
        this.descricao = associacao.getDescricao();
    }

    public List<AssociacaoDto> convertEntitytoDto(List<Associacao> associacaos){
        return associacaos.stream().map(AssociacaoDto::new).collect(Collectors.toList());
    }
}
