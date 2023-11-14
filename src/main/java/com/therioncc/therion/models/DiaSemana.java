package com.therioncc.therion.models;

import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class DiaSemana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDiaSemana;

    private String nombre;

    @OneToMany(mappedBy = "diaSemana")
    private List<Horario> horarios;

    // Getters and setters


    public DiaSemana() {
    }

    public DiaSemana(String nombre, List<Horario> horarios) {
        this.nombre = nombre;
        this.horarios = horarios;
    }

    public DiaSemana(Long idDiaSemana, String nombre, List<Horario> horarios) {
        this.idDiaSemana = idDiaSemana;
        this.nombre = nombre;
        this.horarios = horarios;
    }
}