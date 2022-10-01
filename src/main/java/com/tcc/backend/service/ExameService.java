package com.tcc.backend.service;

import com.tcc.backend.model.Exame;
import com.tcc.backend.model.Paciente;
import com.tcc.backend.repository.ExameRepository;
import com.tcc.backend.repository.PacienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Log4j2
@Service
public class ExameService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ExameRepository exameRepository;

    public ResponseEntity<Paciente> addExame(String cpf, Exame[] exameArray) {
        Paciente pacienteData = pacienteRepository.findByCpf(cpf);
        ArrayList<Exame> exameArrayList = new ArrayList<>();
        if (pacienteData.getExame().isEmpty()) {
            for (Exame exame : exameArray) {
                exameArrayList.add(exameRepository.save(new Exame(exame.getNome())));
            }
        } else {
            for (Exame exame : pacienteData.getExame()) {
                exameArrayList.add(exameRepository.save(new Exame(exame.getNome())));
            }
            for (Exame exame : exameArray) {
                exameArrayList.add(exameRepository.save(new Exame(exame.getNome())));
            }
        }

        pacienteData.setExame(exameArrayList);
        pacienteRepository.save(pacienteData);
        log.info("addExame: adicionando exames ao paciente " + pacienteData.getId());
        return new ResponseEntity<>(pacienteData, HttpStatus.CREATED);
    }

    public ResponseEntity<Paciente> substituirExame(String cpf, Exame[] exameArray) {
        Paciente pacienteData = pacienteRepository.findByCpf(cpf);
        ArrayList<Exame> exameArrayList = new ArrayList<>();

        for (Exame exame : exameArray) {
            exameArrayList.add(exameRepository.save(new Exame(exame.getNome())));
        }

        pacienteData.setExame(exameArrayList);
        pacienteRepository.save(pacienteData);
        log.info("substituirExame: substituindo exames do paciente " + pacienteData.getId());
        return new ResponseEntity<>(pacienteData, HttpStatus.CREATED);
    }

}
