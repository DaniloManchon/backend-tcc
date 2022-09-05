package com.tcc.backend.repository;

import com.tcc.backend.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByCpf(String cpf);

    @Query(value = "SELECT * FROM (SELECT * FROM PACIENTE WHERE proximo_passo = ?1 ORDER BY criticidade ASC, preferencial DESC, DATA_ENTRADA ASC) WHERE ROWNUM <= 1",
            nativeQuery = true)
    Paciente atendimento(String proximo_passo);
}
