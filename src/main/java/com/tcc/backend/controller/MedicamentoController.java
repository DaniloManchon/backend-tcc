package com.tcc.backend.controller;

import com.tcc.backend.model.Medicamento;
import com.tcc.backend.model.Paciente;
import com.tcc.backend.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class MedicamentoController {

    @Autowired
    MedicamentoService medicamentoService;

    @PostMapping("/new/medicamento/{cpf}")
    public ResponseEntity<Paciente> createMedicamento(@PathVariable("cpf") String cpf, @RequestBody Medicamento[] medicamentoArray) {
        try {
            return medicamentoService.addMedicamento(cpf, medicamentoArray);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/medicamento/{cpf}")
    public ResponseEntity<Paciente> substituirMedicamento(@PathVariable("cpf") String cpf, @RequestBody Medicamento[] medicamentoArray) {
        try {
            return medicamentoService.substituirMedicamento(cpf, medicamentoArray);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
