package com.therioncc.therion.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDireccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;

    @ManyToOne
    @JoinColumn(name = "estado_id_estado")
    private Estado estado;
    private String alcaldia;
    private String colonia;

    private String codigopostal;

    @OneToOne
    @JoinColumn(name = "negocio_id", unique = true)
    private Negocio negocio;


    // Constructores, getters y setters

    public Direccion() {
    }

    public Direccion(Estado estado, String alcaldia, String colonia, String codigopostal, Cliente cliente, Negocio negocio) {
        this.estado = estado;
        this.alcaldia = alcaldia;
        this.colonia = colonia;
        this.codigopostal = codigopostal;
        this.negocio = negocio;
    }

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Estado getEstado(Estado estado) {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getAlcaldia() {
        return alcaldia;
    }

    public void setAlcaldia(String alcaldia) {
        this.alcaldia = alcaldia;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    public Negocio getNegocio(){ return negocio; }

    public void setNegocio(Negocio negocio){ this.negocio = negocio; }
}