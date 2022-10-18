package com.tcc.backend.service;

import com.tcc.backend.dto.SalaUpdateDto;
import com.tcc.backend.model.Paciente;
import com.tcc.backend.model.Sala;
import com.tcc.backend.repository.PacienteRepository;
import com.tcc.backend.repository.SalaRepository;
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
public class SalaService {

    @Autowired
    SalaRepository salaRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    public ResponseEntity<Sala> criarSala(Sala sala) {
        Optional<Sala> salaData = Optional.ofNullable(salaRepository.findByNumero(sala.getNumero()));
        if (salaData.isPresent()) {
            log.warn("criarSala: sala: " + sala.getNumero() + " encontrada");
            return new ResponseEntity<>(salaData.get(), HttpStatus.CONFLICT);
        } else {
            salaRepository.save(new Sala(sala.getNumero(), sala.getEspecialidade(), sala.getResponsavel()));
            log.info("criarSala: criando sala: " + sala.getNumero());
            return new ResponseEntity<>(sala, HttpStatus.OK);
        }
    }

    public ResponseEntity<List<Sala>> findAll() {
        List<Sala> salas = new ArrayList<>(salaRepository.findAll());
        if (salas.isEmpty()) {
            log.info("findAll: nenhuma sala cadastrada");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("findAll: retornando todas as salas");
            return new ResponseEntity<>(salas, HttpStatus.OK);
        }
    }

    public ResponseEntity<Sala> findByNumero(int numero) {
        Optional<Sala> salaData = Optional.ofNullable(salaRepository.findByNumero(numero));
        if (salaData.isPresent()) {
            log.info("findByNumero: sala " + numero + " encontrada");
            return new ResponseEntity<>(salaData.get(), HttpStatus.OK);
        } else {
            log.warn("findByNumero: sala " + numero + " nao encontrada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Sala> atualizarSala(SalaUpdateDto salaUpdateDto, int numero) {
        Optional<Sala> salaData = Optional.ofNullable(salaRepository.findByNumero(numero));
        if (salaData.isPresent()) {
            Sala _sala = salaData.get();
            _sala.setEspecialidade(salaUpdateDto.getEspecialidade());
            _sala.setResponsavel(salaUpdateDto.getResponsavel());
            log.info("atualizarSala: sala " + numero + " atualizada");
            return new ResponseEntity<>(salaRepository.save(_sala), HttpStatus.OK);
        } else {
            log.warn("atualizarSala: sala " + numero + " nao encontrada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Sala> deletarSala(int numero) {
        Optional<Sala> salaData = Optional.ofNullable(salaRepository.findByNumero(numero));
        if (salaData.isPresent()) {
            salaRepository.deleteById(salaData.get().getId());
            log.info("deletarSala: sala " + numero + " deletada");
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            log.warn("deletarSala: sala " + numero + " nao encontrada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Paciente> atenderPaciente(int numero) {
        Optional<Sala> salaData = Optional.ofNullable(salaRepository.findByNumero(numero));
        if (salaData.get().getEspecialidade().equals("triagem")) {
            Optional<Paciente> pacienteData = Optional.ofNullable(pacienteRepository.atendimento(salaData.get().getEspecialidade()));
            Paciente _pacienteData = pacienteData.get();
            _pacienteData.setSala_atendimento(numero);
            _pacienteData.setProximo_passo("atendimento");
            _pacienteData.setData_triagem(java.time.LocalDateTime.now());
            pacienteRepository.save(_pacienteData);
            log.info("atenderPaciente: sala " + numero + " chamando paciente: " + _pacienteData.getId());
            return new ResponseEntity<>(pacienteData.get(), HttpStatus.OK);
        } else {
            Optional<Paciente> pacienteData = Optional.ofNullable(pacienteRepository.atendimento(salaData.get().getEspecialidade()));
            Paciente _pacienteData = pacienteData.get();
            _pacienteData.setSala_atendimento(numero);
            _pacienteData.setProximo_passo(null);
            _pacienteData.setData_atendimento(java.time.LocalDateTime.now());
            pacienteRepository.save(_pacienteData);
            log.info("atenderPaciente: sala " + numero + " chamando paciente: " + _pacienteData.getId());
            return new ResponseEntity<>(pacienteData.get(), HttpStatus.OK);
        }
    }
}
