package com.therioncc.therion.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "TipoNegocio")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTipoNegocio")
public class TipoNegocio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoNegocio;

    private String TiNombre;
    private Boolean TiAdultos;
    private Boolean TiEntretenimiento;
    private Boolean TiRestaurante;
    private Boolean TiBar;

    @ManyToMany(mappedBy = "tiposNegocio")
    private List<Negocio> negocios;

    // Constructores, getters y setters


    public TipoNegocio() {
    }

    public TipoNegocio(Long idTipoNegocio, String tiNombre, Boolean tiAdultos, Boolean tiEntretenimiento, Boolean tiRestaurante, Boolean tiBar, List<Negocio> negocios) {
        this.idTipoNegocio = idTipoNegocio;
        TiNombre = tiNombre;
        TiAdultos = tiAdultos;
        TiEntretenimiento = tiEntretenimiento;
        TiRestaurante = tiRestaurante;
        TiBar = tiBar;
        this.negocios = negocios;
    }

    public TipoNegocio(String tiNombre, Boolean tiAdultos, Boolean tiEntretenimiento, Boolean tiRestaurante, Boolean tiBar, List<Negocio> negocios) {
        TiNombre = tiNombre;
        TiAdultos = tiAdultos;
        TiEntretenimiento = tiEntretenimiento;
        TiRestaurante = tiRestaurante;
        TiBar = tiBar;
        this.negocios = negocios;
    }

    public Long getIdTipoNegocio() {
        return idTipoNegocio;
    }

    public void setIdTipoNegocio(Long idTipoNegocio) {
        this.idTipoNegocio = idTipoNegocio;
    }

    public String getTiNombre() {
        return TiNombre;
    }

    public void setTiNombre(String tiNombre) {
        TiNombre = tiNombre;
    }

    public Boolean getTiAdultos() {
        return TiAdultos;
    }

    public void setTiAdultos(Boolean tiAdultos) {
        TiAdultos = tiAdultos;
    }

    public Boolean getTiEntretenimiento() {
        return TiEntretenimiento;
    }

    public void setTiEntretenimiento(Boolean tiEntretenimiento) {
        TiEntretenimiento = tiEntretenimiento;
    }

    public Boolean getTiRestaurante() {
        return TiRestaurante;
    }

    public void setTiRestaurante(Boolean tiRestaurante) {
        TiRestaurante = tiRestaurante;
    }

    public Boolean getTiBar() {
        return TiBar;
    }

    public void setTiBar(Boolean tiBar) {
        TiBar = tiBar;
    }

    public List<Negocio> getNegocios() {
        return negocios;
    }

    public void setNegocios(List<Negocio> negocios) {
        this.negocios = negocios;
    }
}