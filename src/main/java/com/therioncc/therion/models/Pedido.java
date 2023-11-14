package com.therioncc.therion.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private String PeDireccion;
    private int PePrecio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date PeFecha;
    private boolean PeActivo;
    private boolean PeCancelado;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idNegocio")
    private Negocio negocio;

    // Constructor, getters y setters


    public Pedido() {
    }

    public Pedido(String peDireccion, int pePrecio, Date peFecha, boolean peActivo, boolean peCancelado, Cliente cliente, Negocio negocio) {
        PeDireccion = peDireccion;
        PePrecio = pePrecio;
        PeFecha = peFecha;
        PeActivo = peActivo;
        PeCancelado = peCancelado;
        this.cliente = cliente;
        this.negocio = negocio;
    }

    public Pedido(Long idPedido, String peDireccion, int pePrecio, Date peFecha, boolean peActivo, boolean peCancelado, Cliente cliente, Negocio negocio) {
        this.idPedido = idPedido;
        PeDireccion = peDireccion;
        PePrecio = pePrecio;
        PeFecha = peFecha;
        PeActivo = peActivo;
        PeCancelado = peCancelado;
        this.cliente = cliente;
        this.negocio = negocio;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getPeDireccion() {
        return PeDireccion;
    }

    public void setPeDireccion(String peDireccion) {
        PeDireccion = peDireccion;
    }

    public int getPePrecio() {
        return PePrecio;
    }

    public void setPePrecio(int pePrecio) {
        PePrecio = pePrecio;
    }

    public Date getPeFecha() {
        return PeFecha;
    }

    public void setPeFecha(Date peFecha) {
        PeFecha = peFecha;
    }

    public boolean isPeActivo() {
        return PeActivo;
    }

    public void setPeActivo(boolean peActivo) {
        PeActivo = peActivo;
    }

    public boolean isPeCancelado() {
        return PeCancelado;
    }

    public void setPeCancelado(boolean peCancelado) {
        PeCancelado = peCancelado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }
}