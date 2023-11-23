package com.therioncc.therion.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContacto;

    private String CoNombre;
    private String CoTelefono;

    @ManyToOne
    private Negocio negocio;

    public Contacto() {
    }

    public Contacto(String coNombre, String coTelefono, Negocio negocio) {
        CoNombre = coNombre;
        CoTelefono = coTelefono;
        this.negocio = negocio;

    }

    public Contacto(Long idContacto, String coNombre, String coTelefono, Negocio negocio) {
        this.idContacto = idContacto;
        CoNombre = coNombre;
        CoTelefono = coTelefono;
        this.negocio = negocio;
    }


    public Long getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Long idContacto) {
        this.idContacto = idContacto;
    }

    public String getCoNombre() {
        return CoNombre;
    }

    public void setCoNombre(String coNombre) {
        CoNombre = coNombre;
    }

    public String getCoTelefono() {
        return CoTelefono;
    }

    public void setCoTelefono(String coTelefono) {
        CoTelefono = coTelefono;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }
}
