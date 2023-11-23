package com.therioncc.therion.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalTime;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idHorario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idHorario")
    private Long idHorario;

    @ManyToOne
    private Negocio negocio;

    @ManyToOne
    private DiaSemana diaSemana;

    @JsonProperty("horaApertura")
    private LocalTime horaApertura;

    @JsonProperty("horaCierre")
    private LocalTime horaCierre;

    // Getters and setters


    public Horario() {
    }

    public Horario(Negocio negocio, DiaSemana diaSemana, LocalTime horaApertura, LocalTime horaCierre) {
        this.negocio = negocio;
        this.diaSemana = diaSemana;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public Horario(Long idHorario, Negocio negocio, DiaSemana diaSemana, LocalTime horaApertura, LocalTime horaCierre) {
        this.idHorario = idHorario;
        this.negocio = negocio;
        this.diaSemana = diaSemana;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public Long getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }
}