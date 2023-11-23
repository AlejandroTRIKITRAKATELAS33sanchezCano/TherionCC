package com.therioncc.therion.services;

import com.therioncc.therion.models.DiaSemana;
import com.therioncc.therion.repositories.IDiaSemana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiaSemanaService {

    @Autowired
    private final IDiaSemana diaSemana;

    public DiaSemanaService(IDiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public DiaSemana consultarDia(Long idDiaSemana){

        Optional<DiaSemana> diaSemanaOpcional = diaSemana.findByIdDiaSemana(idDiaSemana);

        return diaSemanaOpcional.orElse(null);
    }
}
