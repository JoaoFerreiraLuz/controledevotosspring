package com.jvfl.gestaodecisoes.domain.dto;

import com.jvfl.gestaodecisoes.domain.model.Associado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoDto implements Serializable {
    private Integer id;
    private String nome;
    private String cpf;
    private AssociacaoDto associacao;

    public AssociadoDto(Associado associado) {
        this.id = associado.getId();
        this.nome = associado.getNome();
        this.cpf = associado.getCpf();
        this.associacao = new AssociacaoDto(associado.getAssociacao());
    }

    public List<AssociadoDto> convertEntitytoDto(List<Associado> associados){
        return associados.stream().map(AssociadoDto::new).collect(Collectors.toList());
    }
}
