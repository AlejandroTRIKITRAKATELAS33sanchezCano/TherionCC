package com.therioncc.therion.repositories;

import com.therioncc.therion.models.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDiaSemana extends JpaRepository <DiaSemana, Long> {

    Optional<DiaSemana> findByIdDiaSemana(Long idDiaSemana);
}
