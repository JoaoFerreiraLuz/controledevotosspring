package com.jvfl.gestaodecisoes.domain.dto;

import com.jvfl.gestaodecisoes.domain.constantes.Constantes;
import com.jvfl.gestaodecisoes.domain.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoPautaDto implements Serializable {
    private PautaDto pauta;
    private Long qtdVotosPositivos;
    private Long qtdeVotosNegativos;
    private String resultado;

    public ResultadoPautaDto(Pauta pauta, Long qtdVotosPositivos, Long qtdeVotosNegativos) {
        this.pauta = new PautaDto(pauta);
        this.qtdVotosPositivos = qtdVotosPositivos;
        this.qtdeVotosNegativos = qtdeVotosNegativos;
        this.resultado = verificaresultado(qtdVotosPositivos, qtdeVotosNegativos);
    }

    public String verificaresultado(Long qtdVotosPositivos, Long qtdeVotosNegativos){
        return qtdVotosPositivos > qtdeVotosNegativos ? Constantes.APROVADO :
                (qtdeVotosNegativos > qtdVotosPositivos ? Constantes.REPROVADA : Constantes.EMPATE);
    }
}
