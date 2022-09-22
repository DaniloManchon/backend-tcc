package com.tcc.backend.repository;

import com.tcc.backend.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
    Sala findByNumero(int numero);

}