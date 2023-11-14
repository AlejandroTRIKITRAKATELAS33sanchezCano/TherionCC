package com.therioncc.therion.services;

import com.therioncc.therion.DTO.ClienteRequest;
import com.therioncc.therion.models.Estado;
import com.therioncc.therion.repositories.IEstadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private final IEstadoRepositorio estadoRepositorio;

    public EstadoService(IEstadoRepositorio estadoRepositorio) {
        this.estadoRepositorio = estadoRepositorio;
    }

    public Estado consultarEstado(String name){

        Optional<Estado> estadoOptional = estadoRepositorio.findByEsNombre(name);
        return estadoOptional.orElse(null);
    }
}
