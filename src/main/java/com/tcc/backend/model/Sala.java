package com.tcc.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String numero;
    @Column
    private String especialidade;
    @Column
    private String responsavel;

    public Sala(String numero, String especialidade, String responsavel) {
        this.numero = numero;
        this.especialidade = especialidade;
        this.responsavel = responsavel;
    }
}
