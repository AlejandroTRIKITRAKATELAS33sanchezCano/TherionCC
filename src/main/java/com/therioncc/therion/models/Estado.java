package com.therioncc.therion.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEstado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstado;

    private String esNombre;

    // Constructor por defecto
    public Estado() {
    }

    //Constructor Sobrecargado


    public Estado(Long idEstado, String esNombre) {
        this.idEstado = idEstado;
        this.esNombre = esNombre;
    }

    // Constructor con todos los campos excepto el idEstado
    public Estado(String esNombre) {
        this.esNombre = esNombre;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getEsNombre() {
        return esNombre;
    }

    public void setEsNombre(String esNombre) {

        this.esNombre = esNombre;
    }
}
