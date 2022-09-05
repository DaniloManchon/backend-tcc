package com.tcc.backend.service;

import com.tcc.backend.dto.SalaUpdateDto;
import com.tcc.backend.model.Paciente;
import com.tcc.backend.model.Sala;
import com.tcc.backend.repository.PacienteRepository;
import com.tcc.backend.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    SalaRepository salaRepository;
    @Autowired
    PacienteRepository pacienteRepository;


    public ResponseEntity<Sala> criarSala(Sala sala) {
        Optional<Sala> salaData = Optional.ofNullable(salaRepository.findByNumero(sala.getNumero()));
        if (salaData.isPresent()) {
            return new ResponseEntity<>(salaData.get(), HttpStatus.FOUND);
        } else {
            salaRepository.save(new Sala(sala.getNumero(), sala.getEspecialidade(), sala.getResponsavel()));
            return new ResponseEntity<>(sala, HttpStatus.CREATED);
        }

    }

    public ResponseEntity<List<Sala>> findAll() {
        List<Sala> salas = new ArrayList<>(salaRepository.findAll());
        if (salas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(salas, HttpStatus.OK);
        }
    }

    public ResponseEntity<Sala> findByNumero(String numero) {

        Optional<Sala> salaData = Optional.ofNullable(salaRepository.findByNumero(numero));
        if (salaData.isPresent()) {
            return new ResponseEntity<>(salaData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Sala> atualizarSala(SalaUpdateDto salaUpdateDto, String numero) {
        Optional<Sala> salaData = Optional.ofNullable(salaRepository.findByNumero(numero));
        if (salaData.isPresent()) {
            Sala _sala = salaData.get();
            _sala.setEspecialidade(salaUpdateDto.getEspecialidade());
            _sala.setResponsavel(salaUpdateDto.getResponsavel());
            return new ResponseEntity<>(salaRepository.save(_sala), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Sala> deletarSala(String numero) {

        Optional<Sala> salaData = Optional.ofNullable(salaRepository.findByNumero(numero));
        if (salaData.isPresent()) {
            salaRepository.deleteById(salaData.get().getId());
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Paciente> atenderPaciente(String numero) {
        Optional<Sala> salaData = Optional.ofNullable(salaRepository.findByNumero(numero));
        if (salaData.get().getEspecialidade().equals("triagem")) {
            Optional<Paciente> pacienteData = Optional.ofNullable(pacienteRepository.atendimento(salaData.get().getEspecialidade()));
            Paciente _pacienteData = pacienteData.get();
            _pacienteData.setSala_atendimento(numero);
            _pacienteData.setProximo_passo("atendimento");
            pacienteRepository.save(_pacienteData);
            return new ResponseEntity<>(pacienteData.get(), HttpStatus.OK);
        } else {
            Optional<Paciente> pacienteData = Optional.ofNullable(pacienteRepository.atendimento(salaData.get().getEspecialidade()));
            Paciente _pacienteData = pacienteData.get();
            _pacienteData.setSala_atendimento(numero);
            _pacienteData.setProximo_passo(null);
            pacienteRepository.save(_pacienteData);
            return new ResponseEntity<>(pacienteData.get(), HttpStatus.OK);
        }
    }

}
