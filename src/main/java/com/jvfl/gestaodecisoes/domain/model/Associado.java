package com.jvfl.gestaodecisoes.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Associado {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

    @Column(length = 30)
    private String nome;

    @Column(length = 30)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "associacao_id", nullable = false)
    private Associacao associacao;
}
