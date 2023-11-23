package com.therioncc.therion.repositories;

import com.therioncc.therion.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoRepositorio extends JpaRepository<Pedido, Long> {
}
