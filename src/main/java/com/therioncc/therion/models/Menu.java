package com.therioncc.therion.models;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.List;

@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMenu;

    private String MeNombre;
    private boolean MeDisponible;

    private boolean MeActivo;

    private  String MeImagen;

    @ManyToOne
    @JoinColumn(name = "negocio_id")
    private Negocio negocio;

    @ManyToMany
    @JoinTable(
            name = "menu_producto",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;

    // Constructores, getters y setters


    public Menu(Long idMenu, String meNombre, boolean meDisponible, boolean meActivo, String meImagen, Negocio negocio, List<Producto> productos) {
        this.idMenu = idMenu;
        MeNombre = meNombre;
        MeDisponible = meDisponible;
        MeActivo = meActivo;
        MeImagen = meImagen;
        this.negocio = negocio;
        this.productos = productos;
    }

    public Menu() {
    }

    public Menu(String meNombre, boolean meDisponible, boolean meActivo, String meImagen, Negocio negocio, List<Producto> productos) {
        MeNombre = meNombre;
        MeDisponible = meDisponible;
        MeActivo = meActivo;
        MeImagen = meImagen;
        this.negocio = negocio;
        this.productos = productos;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public String getMeNombre() {
        return MeNombre;
    }

    public void setMeNombre(String meNombre) {
        MeNombre = meNombre;
    }

    public boolean isMeDisponible() {
        return MeDisponible;
    }

    public void setMeDisponible(boolean meDisponible) {
        MeDisponible = meDisponible;
    }

    public boolean isMeActivo() {
        return MeActivo;
    }

    public void setMeActivo(boolean meActivo) {
        MeActivo = meActivo;
    }

    public String getMeImagen() {
        return MeImagen;
    }

    public void setMeImagen(String meImagen) {
        MeImagen = meImagen;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}