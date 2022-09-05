package com.tcc.backend.controller;

import com.tcc.backend.dto.PacienteCriticidadeUpdateDto;
import com.tcc.backend.model.Paciente;
import com.tcc.backend.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping("/new/paciente")
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        try {
            return pacienteService.criarPaciente(paciente);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paciente/{cpf}")
    public ResponseEntity<Paciente> findPacienteByCpf(@PathVariable("cpf") String cpf) {
        try {
            return pacienteService.findPacienteByCpf(cpf);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/paciente/{cpf}/criticidade")
    public ResponseEntity<Paciente> updateCridicidade(@PathVariable("cpf") String cpf, @RequestBody PacienteCriticidadeUpdateDto pacienteCriticidadeUpdateDto) {
        try {
            return pacienteService.updateCriticidadePaciente(cpf, pacienteCriticidadeUpdateDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
