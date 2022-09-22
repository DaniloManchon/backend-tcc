package com.tcc.backend.controller;

import com.tcc.backend.dto.SalaUpdateDto;
import com.tcc.backend.model.Paciente;
import com.tcc.backend.model.Sala;
import com.tcc.backend.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SalaController {
    @Autowired
    SalaService salaService;

    @PostMapping("/new/sala")
    public ResponseEntity<Sala> createSala(@RequestBody Sala sala) {
        try {
            return salaService.criarSala(sala);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/salas")
    public ResponseEntity<List<Sala>> findAll() {
        try {
            return salaService.findAll();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/sala/{numero}")
    public ResponseEntity<Sala> findSalaByNumero(@PathVariable("numero") int numero) {
        try {
            return salaService.findByNumero(numero);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/sala/{numero}/update")
    public ResponseEntity<Sala> atualizarSala(@PathVariable("numero") int numero, @RequestBody SalaUpdateDto salaUpdateDto) {
        try {
            return salaService.atualizarSala(salaUpdateDto, numero);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/sala/{numero}")
    public ResponseEntity<Sala> deleteSala(@PathVariable("numero") int numero) {
        try {
            return salaService.deletarSala(numero);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sala/{numero}/atendimento")
    public ResponseEntity<Paciente> atenderPaciente(@PathVariable("numero") int numero) {
        try {
            return salaService.atenderPaciente(numero);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
