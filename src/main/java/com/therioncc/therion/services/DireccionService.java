package com.therioncc.therion.services;

import com.therioncc.therion.DTO.ClienteRequest;
import com.therioncc.therion.models.Cliente;
import com.therioncc.therion.models.Direccion;
import com.therioncc.therion.models.Estado;
import com.therioncc.therion.repositories.IDireccionRepositorio;
import com.therioncc.therion.repositories.IEstadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionService {

    @Autowired
    private final IDireccionRepositorio direccionRepositorio;

    public DireccionService(IDireccionRepositorio direccionRepositorio) {
        this.direccionRepositorio = direccionRepositorio;
    }

    public void registrarDireccion(Direccion direccion) {
        // Aquí puedes realizar validaciones, transformaciones, etc.

        direccionRepositorio.save(direccion);

    }
}
