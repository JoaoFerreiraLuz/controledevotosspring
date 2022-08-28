package com.jvfl.gestaodecisoes.domain.dto;

import com.jvfl.gestaodecisoes.domain.model.Sessao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessaoDto implements Serializable {
    private Integer id;
    private AssociacaoDto associacao;
    private PautaDto pauta;
    private Date data_sessao;
    private Date inicio_sessao;
    private Date termino_sessao;

    public SessaoDto(Sessao sessao) {
        this.id = sessao.getId();
        this.associacao = new AssociacaoDto(sessao.getAssociacao());
        this.pauta = new PautaDto(sessao.getPauta());
        this.data_sessao = sessao.getData_sessao();
        this.inicio_sessao = sessao.getInicio_sessao();
        this.termino_sessao = sessao.getData_sessao();
    }

    public List<SessaoDto> convertEntitytoDto(List<Sessao> sessoes){
        return sessoes.stream().map(SessaoDto::new).collect(Collectors.toList());
    }
}