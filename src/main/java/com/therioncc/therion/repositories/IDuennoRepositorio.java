package com.therioncc.therion.repositories;

import com.therioncc.therion.models.Duenno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDuennoRepositorio extends JpaRepository<Duenno, Long> {
    Optional<Duenno> findByDuCorreo(String duCorreo);
}
