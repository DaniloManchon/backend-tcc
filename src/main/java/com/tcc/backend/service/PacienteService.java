package com.tcc.backend.service;

import com.tcc.backend.dto.PacienteCriticidadeUpdateDto;
import com.tcc.backend.model.Paciente;
import com.tcc.backend.repository.PacienteRepository;
import com.tcc.backend.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    SalaRepository salaRepository;


    public ResponseEntity<Paciente> criarPaciente(Paciente paciente) {
        Optional<Paciente> pacienteData = Optional.ofNullable(pacienteRepository.findByCpf(paciente.getCpf()));
        if (pacienteData.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.FOUND);
        } else {
            if (paciente.getCriticidade() == 0) {
                paciente.setCriticidade(5);
            }
            Paciente _paciente = pacienteRepository.save(new Paciente(
                            paciente.getCpf(),
                            paciente.getNome(),
                            paciente.getPreferencial(),
                            paciente.getCriticidade(),
                            java.time.LocalDateTime.now(),
                            "triagem",
                            null,
                            null,
                            null
                    )
            );
            return new ResponseEntity<>(_paciente, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Paciente> findPacienteByCpf(String cpf) {
        Optional<Paciente> pacienteData = Optional.ofNullable(pacienteRepository.findByCpf(cpf));
        if (pacienteData.isPresent()) {
            return new ResponseEntity<>(pacienteData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Paciente> updateCriticidadePaciente(String cpf, PacienteCriticidadeUpdateDto pacienteCriticidadeUpdateDto) {
        Optional<Paciente> pacienteData = Optional.ofNullable(pacienteRepository.findByCpf(cpf));
        if (pacienteData.isPresent()) {
            Paciente _paciente = pacienteData.get();
            _paciente.setCriticidade(pacienteCriticidadeUpdateDto.getCriticidade());
            return new ResponseEntity<>(pacienteRepository.save(_paciente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
