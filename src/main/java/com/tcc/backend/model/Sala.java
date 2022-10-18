package com.tcc.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int numero;
    @Column
    private String especialidade;
    @Column
    private String responsavel;

    public Sala(int numero, String especialidade, String responsavel) {
        this.numero = numero;
        this.especialidade = especialidade;
        this.responsavel = responsavel;
    }
}
