package com.jvfl.gestaodecisoes.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotarDto implements Serializable {
    private Integer pauta;
    private Boolean favoravel;
    private Integer associado;
    private Integer sessao;

}
