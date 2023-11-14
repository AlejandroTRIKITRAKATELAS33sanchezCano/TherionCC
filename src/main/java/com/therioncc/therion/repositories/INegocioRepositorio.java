package com.therioncc.therion.repositories;

import com.therioncc.therion.models.Negocio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface INegocioRepositorio extends JpaRepository<Negocio, Long> {
    Optional<Negocio> findByIdNegocio(Long idNegocio);
}
