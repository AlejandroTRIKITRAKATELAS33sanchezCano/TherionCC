package com.therioncc.therion.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idNegocio")
public class Negocio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idNegocio")
    private Long idNegocio;

    @JsonProperty("neNombre")
    private String NeNombre;

    @JsonProperty("neAbierto")
    private boolean NeAbierto;

    @JsonProperty("neActivo")
    private boolean NeActivo;

    @JsonProperty("neBorrado")
    private boolean NeBorrado;

    @JsonProperty("neImagen")
    private String NeImagen;

    @ManyToOne
    @JsonIgnore
    private Duenno duenno;

    @JsonProperty("neTarjeta")
    private boolean NeTarjeta;

    @JsonProperty("neDomicilio")
    private boolean NeDomicilio;

    @OneToMany(mappedBy = "negocio")
    @JsonIgnore
    private List<Menu> menus;

    @OneToMany(mappedBy = "negocio")
    @JsonIgnore
    private List<Horario> horarios;

    @ManyToMany
    @JoinTable(
            name = "negocio_tipo_negocio",
            joinColumns = @JoinColumn(name = "negocio_idNegocio"),
            inverseJoinColumns = @JoinColumn(name = "tipo_negocio_idTipoNegocio"))
    @JsonIgnore
    private List<TipoNegocio> tiposNegocio;

    @OneToMany(mappedBy = "negocio", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contacto> contactos;

    // Constructor and Getters and setters


    public Negocio() {
    }

    public Negocio(String neNombre, boolean neAbierto, boolean neActivo, boolean neBorrado, String neImagen, Duenno duenno, boolean neTarjeta, boolean neDomicilio, List<Menu> menus, List<Horario> horarios, List<TipoNegocio> tiposNegocio, List<Contacto> contactos) {
        NeNombre = neNombre;
        NeAbierto = neAbierto;
        NeActivo = neActivo;
        NeBorrado = neBorrado;
        NeImagen = neImagen;
        this.duenno = duenno;
        NeTarjeta = neTarjeta;
        NeDomicilio = neDomicilio;
        this.menus = menus;
        this.horarios = horarios;
        this.tiposNegocio = tiposNegocio;
        this.contactos = contactos;
    }

    public Negocio(Long idNegocio, String neNombre, boolean neAbierto, boolean neActivo, boolean neBorrado, String neImagen, Duenno duenno, boolean neTarjeta, boolean neDomicilio, List<Menu> menus, List<Horario> horarios, List<TipoNegocio> tiposNegocio, List<Contacto> contactos) {
        this.idNegocio = idNegocio;
        NeNombre = neNombre;
        NeAbierto = neAbierto;
        NeActivo = neActivo;
        NeBorrado = neBorrado;
        NeImagen = neImagen;
        this.duenno = duenno;
        NeTarjeta = neTarjeta;
        NeDomicilio = neDomicilio;
        this.menus = menus;
        this.horarios = horarios;
        this.tiposNegocio = tiposNegocio;
        this.contactos = contactos;
    }

    public Long getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(Long idNegocio) {
        this.idNegocio = idNegocio;
    }

    public String getNeNombre() {
        return NeNombre;
    }

    public void setNeNombre(String neNombre) {
        NeNombre = neNombre;
    }

    public boolean isNeAbierto() {
        return NeAbierto;
    }

    public void setNeAbierto(boolean neAbierto) {
        NeAbierto = neAbierto;
    }

    public boolean isNeActivo() {
        return NeActivo;
    }

    public void setNeActivo(boolean neActivo) {
        NeActivo = neActivo;
    }

    public boolean isNeBorrado() {
        return NeBorrado;
    }

    public void setNeBorrado(boolean neBorrado) {
        NeBorrado = neBorrado;
    }

    public String getNeImagen() {
        return NeImagen;
    }

    public void setNeImagen(String neImagen) {
        NeImagen = neImagen;
    }

    public Duenno getDuenno() {
        return duenno;
    }

    public void setDuenno(Duenno duenno) {
        this.duenno = duenno;
    }

    public boolean isNeTarjeta() {
        return NeTarjeta;
    }

    public void setNeTarjeta(boolean neTarjeta) {
        NeTarjeta = neTarjeta;
    }

    public boolean isNeDomicilio() {
        return NeDomicilio;
    }

    public void setNeDomicilio(boolean neDomicilio) {
        NeDomicilio = neDomicilio;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public List<TipoNegocio> getTiposNegocio() {
        return tiposNegocio;
    }

    public void setTiposNegocio(List<TipoNegocio> tiposNegocio) {
        this.tiposNegocio = tiposNegocio;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }
}