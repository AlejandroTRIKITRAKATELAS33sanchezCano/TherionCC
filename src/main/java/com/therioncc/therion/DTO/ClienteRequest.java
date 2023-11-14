package com.therioncc.therion.DTO;

import com.therioncc.therion.models.Direccion;

public class ClienteRequest {
    private String clNombre;
    private String clAppat;
    private String clApmat;
    private String clCorreo;
    private String clContrasenna;
    private String clNumero1;
    private String clNumero2;
    private String esNombre;
    private String alcaldia;
    private String colonia;
    private String codigopostal;

    public ClienteRequest() {
    }

    public ClienteRequest(String clNombre, String clAppat, String clApmat, String clCorreo, String clContrasenna, String clNumero1, String clNumero2, String esNombre, String alcaldia, String colonia, String codigopostal) {
        this.clNombre = clNombre;
        this.clAppat = clAppat;
        this.clApmat = clApmat;
        this.clCorreo = clCorreo;
        this.clContrasenna = clContrasenna;
        this.clNumero1 = clNumero1;
        this.clNumero2 = clNumero2;
        this.esNombre = esNombre;
        this.alcaldia = alcaldia;
        this.colonia = colonia;
        this.codigopostal = codigopostal;
    }

    public String getClNombre() {
        return clNombre;
    }

    public void setClNombre(String clNombre) {
        this.clNombre = clNombre;
    }

    public String getClAppat() {
        return clAppat;
    }

    public void setClAppat(String clAppat) {
        this.clAppat = clAppat;
    }

    public String getClApmat() {
        return clApmat;
    }

    public void setClApmat(String clApmat) {
        this.clApmat = clApmat;
    }

    public String getClCorreo() {
        return clCorreo;
    }

    public void setClCorreo(String clCorreo) {
        this.clCorreo = clCorreo;
    }

    public String getClContrasenna() {
        return clContrasenna;
    }

    public void setClContrasenna(String clContrasenna) {
        this.clContrasenna = clContrasenna;
    }

    public String getClNumero1() {
        return clNumero1;
    }

    public void setClNumero1(String clNumero1) {
        this.clNumero1 = clNumero1;
    }

    public String getClNumero2() {
        return clNumero2;
    }

    public void setClNumero2(String clNumero2) {
        this.clNumero2 = clNumero2;
    }

    public String getEsNombre() {
        return esNombre;
    }

    public void setEsNombre(String esNombre) {
        this.esNombre = esNombre;
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
}
