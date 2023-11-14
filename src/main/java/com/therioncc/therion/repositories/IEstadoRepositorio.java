package com.therioncc.therion.repositories;

import com.therioncc.therion.models.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEstadoRepositorio extends JpaRepository<Estado, Long> {
    Optional<Estado> findByEsNombre(String name);
}
