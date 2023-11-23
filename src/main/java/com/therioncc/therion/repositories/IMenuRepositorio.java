package com.therioncc.therion.repositories;

import com.therioncc.therion.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IMenuRepositorio extends JpaRepository<Menu, Long> {
    Optional<Menu> findByIdMenu(Long idMenu);
    List<Menu> findAllByNegocio_IdNegocio(Long idNegocio);
}
