package com.tcc.backend.controller;

import com.tcc.backend.model.Exame;
import com.tcc.backend.model.Paciente;
import com.tcc.backend.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ExameController {

    @Autowired
    ExameService exameService;

    @PostMapping("/new/exame/{cpf}")
    public ResponseEntity<Paciente> createExame(@PathVariable("cpf") String cpf, @RequestBody Exame[] exameArray) {
        try {
            return exameService.addExame(cpf, exameArray);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/exame/{cpf}")
    public ResponseEntity<Paciente> substituirExame(@PathVariable("cpf") String cpf, @RequestBody Exame[] exameArray) {
        try {
            return exameService.substituirExame(cpf, exameArray);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
