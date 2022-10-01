package com.tcc.backend.service;

import com.tcc.backend.dto.PacienteCriticidadeUpdateDto;
import com.tcc.backend.model.Paciente;
import com.tcc.backend.repository.PacienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;

    public ResponseEntity<Paciente> criarPaciente(Paciente paciente) {
        Optional<Paciente> pacienteData = Optional.ofNullable(pacienteRepository.findByCpf(paciente.getCpf()));
        if (pacienteData.isPresent()) {
            log.warn("criarPaciente: paciente: " + pacienteData.get().getId() + " encontrado");
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
                            null,
                            null,
                            "triagem",
                            0,
                            null,
                            null
                    )
            );
            log.info("criarPaciente: paciente: " + _paciente.getId() + " criado");
            return new ResponseEntity<>(_paciente, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Paciente> findPacienteByCpf(String cpf) {
        Optional<Paciente> pacienteData = Optional.ofNullable(pacienteRepository.findByCpf(cpf));
        if (pacienteData.isPresent()) {
            log.info("findPacienteByCpf: paciente " + pacienteData.get().getId() + " encontrado");
            return new ResponseEntity<>(pacienteData.get(), HttpStatus.OK);
        } else {
            log.warn("findPacienteByCpf: paciente " + cpf + " nao encontrado");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Paciente> updateCriticidadePaciente(String cpf, PacienteCriticidadeUpdateDto pacienteCriticidadeUpdateDto) {
        Optional<Paciente> pacienteData = Optional.ofNullable(pacienteRepository.findByCpf(cpf));
        if (pacienteData.isPresent()) {
            Paciente _paciente = pacienteData.get();
            _paciente.setCriticidade(pacienteCriticidadeUpdateDto.getCriticidade());
            log.info("updateCriticidadePaciente: criticidade paciente " + pacienteData.get().getId() + " atualizado para " + pacienteCriticidadeUpdateDto.getCriticidade());
            return new ResponseEntity<>(pacienteRepository.save(_paciente), HttpStatus.OK);
        } else {
            log.warn("updateCriticidadePaciente: paciente nao encontrado");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Paciente> deletarPaciente(long id) {
        if (pacienteRepository.findById(id).isPresent()) {
            pacienteRepository.deleteById(id);
            log.info("deletarPaciente: paciente " + id + " deletado");
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else{
            log.warn("deletarPaciente: paciente " + id + " nao encontrado");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Paciente>> findAllPacientes() {
        List<Paciente> pacientes = new ArrayList<>(pacienteRepository.findAll());
        if (pacientes.isEmpty()) {
            log.info("findAll: nenhum paciente cadastrado");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("findAll: retornando todos os pacientes");
            return new ResponseEntity<>(pacientes, HttpStatus.OK);
        }
    }
}
