package com.therioncc.therion.DTO;

import com.therioncc.therion.models.Contacto;
import com.therioncc.therion.models.Negocio;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public class DuennoRequest {

    private String dunNombre;
    private String duAppat;
    private String duApmat;
    private String duCorreo;
    private String duContrasenna;
    private String coNombre;
    private String coTelefono;

    @ManyToOne
    private Negocio negocio;

    public DuennoRequest() {
    }

    public DuennoRequest(String dunNombre, String duAppat, String duApmat, String duCorreo, String duContrasenna, String coNombre, String coTelefono, Negocio negocio) {
        this.dunNombre = dunNombre;
        this.duAppat = duAppat;
        this.duApmat = duApmat;
        this.duCorreo = duCorreo;
        this.duContrasenna = duContrasenna;
        this.coNombre = coNombre;
        this.coTelefono = coTelefono;
        this.negocio = negocio;
    }

    //Getters and Setters


    public String getDunNombre() {
        return dunNombre;
    }

    public void setDunNombre(String dunNombre) {
        this.dunNombre = dunNombre;
    }

    public String getDuAppat() {
        return duAppat;
    }

    public void setDuAppat(String duAppat) {
        this.duAppat = duAppat;
    }

    public String getDuApmat() {
        return duApmat;
    }

    public void setDuApmat(String duApmat) {
        this.duApmat = duApmat;
    }

    public String getDuCorreo() {
        return duCorreo;
    }

    public void setDuCorreo(String duCorreo) {
        this.duCorreo = duCorreo;
    }

    public String getDuContrasenna() {
        return duContrasenna;
    }

    public void setDuContrasenna(String duContrasenna) {
        this.duContrasenna = duContrasenna;
    }

    public String getCoNombre() {
        return coNombre;
    }

    public void setCoNombre(String coNombre) {
        this.coNombre = coNombre;
    }

    public String getCoTelefono() {
        return coTelefono;
    }

    public void setCoTelefono(String coTelefono) {
        this.coTelefono = coTelefono;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }
}
