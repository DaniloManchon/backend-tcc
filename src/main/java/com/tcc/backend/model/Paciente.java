package com.tcc.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @Nullable
    @Column
    private LocalDateTime data_triagem;
    @Nullable
    @Column
    private LocalDateTime data_atendimento;
    @Column
    private String proximo_passo;
    //relacionamento de muitos medicamento para um paciente
    @Nullable
    @Column
    private int sala_atendimento;
    @Nullable
    @OneToMany
    private List<Medicamento> medicamento;
    //relacionamento de muitos exames para um paciente
    @Nullable
    @OneToMany
    private List<Exame> exame;

    public Paciente(String cpf, String nome, int preferencial, int criticidade, LocalDateTime data_entrada, @Nullable LocalDateTime data_triagem, @Nullable LocalDateTime data_atendimento, String proximo_passo, int sala_atendimento, @Nullable List<Medicamento> medicamento, @Nullable List<Exame> exame) {
        this.cpf = cpf;
        this.nome = nome;
        this.preferencial = preferencial;
        this.criticidade = criticidade;
        this.data_entrada = data_entrada;
        this.data_triagem = data_triagem;
        this.data_atendimento = data_atendimento;
        this.proximo_passo = proximo_passo;
        this.sala_atendimento = sala_atendimento;
        this.medicamento = medicamento;
        this.exame = exame;
    }
}

