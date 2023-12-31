package com.therioncc.therion.repositories;

import com.therioncc.therion.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClienteRepositorio extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByClCorreo(String clCorreo);
    Optional<Cliente> findByIdCliente(Long idCliente);
}
