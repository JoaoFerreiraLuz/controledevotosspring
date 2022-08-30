package com.jvfl.gestaodecisoes.domain.dto;

import com.jvfl.gestaodecisoes.domain.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PautaDto implements Serializable {
    private Integer id;
    private String descricao;

    public PautaDto(Pauta pauta) {
        this.id = pauta.getId();
        this.descricao = pauta.getDescricao();
    }

}
