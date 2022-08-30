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
public class VotoDto implements Serializable {
    private Integer id;
    private PautaDto pauta;
    private Boolean favoravel;
    private AssociadoDto associado;
    private SessaoDto sessao;

    public VotoDto(Voto voto) {
        this.id = voto.getId();
        this.pauta = new PautaDto(voto.getPauta());
        this.favoravel = voto.getFavoravel();
        this.associado = new AssociadoDto(voto.getAssociado());
        this.sessao = new SessaoDto(voto.getSessao());
    }

}
