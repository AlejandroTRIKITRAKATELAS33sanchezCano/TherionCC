package com.therioncc.therion.repositories;

import com.therioncc.therion.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductoRepositorio extends JpaRepository<Producto, Long> {

    Optional<Producto> findByIdProducto(Long idProducto);
}
