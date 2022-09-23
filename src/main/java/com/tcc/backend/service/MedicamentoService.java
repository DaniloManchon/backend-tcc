package com.tcc.backend.service;

import com.tcc.backend.model.Medicamento;
import com.tcc.backend.model.Paciente;
import com.tcc.backend.repository.MedicamentoRepository;
import com.tcc.backend.repository.PacienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Log4j2
@Service
public class MedicamentoService {

    @Autowired
    MedicamentoRepository medicamentoRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    public ResponseEntity<Paciente> addMedicamento(String cpf, Medicamento[] medicamentoArray) {
        Paciente pacienteData = pacienteRepository.findByCpf(cpf);
        ArrayList<Medicamento> medicamentoArrayList = new ArrayList<>();

        if (pacienteData.getMedicamento().isEmpty()) {
            for (Medicamento medicamento : medicamentoArray) {
                medicamentoArrayList.add(medicamentoRepository.save(new Medicamento(medicamento.getNome(), medicamento.getDosagem())));
            }
        } else {
            for (Medicamento medicamento : pacienteData.getMedicamento()) {
                medicamentoArrayList.add(medicamentoRepository.save(new Medicamento(medicamento.getNome(), medicamento.getDosagem())));
            }
            for (Medicamento medicamento : medicamentoArray) {
                medicamentoArrayList.add(medicamentoRepository.save(new Medicamento(medicamento.getNome(), medicamento.getDosagem())));
            }
        }
        pacienteData.setMedicamento(medicamentoArrayList);
        pacienteRepository.save(pacienteData);
        log.info("addMedicamento: adicionando medicamentos ao paciente " + pacienteData.getId());
        return new ResponseEntity<>(pacienteData, HttpStatus.CREATED);
    }

    public ResponseEntity<Paciente> substituirMedicamento(String cpf, Medicamento[] medicamentoArray) {
        Paciente pacienteData = pacienteRepository.findByCpf(cpf);
        ArrayList<Medicamento> medicamentoArrayList = new ArrayList<>();

        for (Medicamento medicamento : medicamentoArray) {
            medicamentoArrayList.add(medicamentoRepository.save(new Medicamento(medicamento.getNome(), medicamento.getDosagem())));
        }

        pacienteData.setMedicamento(medicamentoArrayList);
        pacienteRepository.save(pacienteData);
        log.info("substituirMedicamento: substituindo medicamentos ao paciente " + pacienteData.getId());
        return new ResponseEntity<>(pacienteData, HttpStatus.CREATED);
    }

}
