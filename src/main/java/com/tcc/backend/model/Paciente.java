package com.tcc.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String cpf;
    @Column
    private String nome;
    @Column
    private int preferencial;

    // azul < verde < amarelo < laranja < vermelho
    //  5       4       3          2         1
    @Column
    private int criticidade;
    @Column
    private LocalDateTime data_entrada;
    @Column
    private String proximo_passo;
    //relacionamento de muitos medicamento para um paciente
    @Nullable
    @Column
    private String sala_atendimento;
    @Nullable
    @OneToMany
    private List<Medicamento> medicamento;
    //relacionamento de muitos exames para um paciente
    @Nullable
    @OneToMany
    private List<Exame> exame;

    public Paciente(String cpf, String nome, int preferencial, int criticidade, LocalDateTime data_entrada, String proximo_passo, @Nullable String sala_atendimento, @Nullable List<Medicamento> medicamento, @Nullable List<Exame> exame) {
        this.cpf = cpf;
        this.nome = nome;
        this.preferencial = preferencial;
        this.criticidade = criticidade;
        this.data_entrada = data_entrada;
        this.proximo_passo = proximo_passo;
        this.sala_atendimento = sala_atendimento;
        this.medicamento = medicamento;
        this.exame = exame;
    }
}

