package com.therioncc.therion.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDiaSemana")
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

    public Long getIdDiaSemana() {
        return idDiaSemana;
    }

    public void setIdDiaSemana(Long idDiaSemana) {
        this.idDiaSemana = idDiaSemana;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}