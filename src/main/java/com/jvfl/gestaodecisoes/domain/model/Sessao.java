package com.jvfl.gestaodecisoes.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sessao {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "associacao_id", nullable = false)
    private Associacao associacao;

    @ManyToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pauta;

    @Column(name = "data_sessao", nullable = false, columnDefinition = "datetime")
    private Date dataSessao;

    @Column(name = "inicio_sessao", nullable = false, columnDefinition = "datetime")
    private Date inicioSessao;

    @Column(name = "termino_sessao", nullable = false, columnDefinition = "datetime")
    private Date terminoSessao;
}