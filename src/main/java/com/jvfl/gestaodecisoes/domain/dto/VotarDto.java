package com.jvfl.gestaodecisoes.domain.dto;

import com.jvfl.gestaodecisoes.domain.model.Voto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotarDto implements Serializable {
    private Integer pauta;
    private Boolean favoravel;
    private Integer associado;
    private Integer sessao;

}
