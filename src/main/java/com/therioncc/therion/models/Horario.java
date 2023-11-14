package com.therioncc.therion.models;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalTime;

@Entity
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHorario;

    @ManyToOne
    private Negocio negocio;

    @ManyToOne
    private DiaSemana diaSemana;

    private LocalTime horaApertura;
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