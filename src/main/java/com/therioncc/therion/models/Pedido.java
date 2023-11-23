package com.therioncc.therion.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPedido")
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

    private Long idNegocio;

    @ManyToMany
    @JoinTable(
            name = "pedido_productos",
            joinColumns = @JoinColumn(name = "pedido_idPedido"),
            inverseJoinColumns = @JoinColumn(name = "producto_idProducto"))
    private List<Producto> productos;

    // Constructor, getters y setters


    public Pedido() {
    }

    public Pedido(Long idPedido, String peDireccion, int pePrecio, Date peFecha, boolean peActivo, boolean peCancelado, Cliente cliente, Long idNegocio, List<Producto> productos) {
        this.idPedido = idPedido;
        PeDireccion = peDireccion;
        PePrecio = pePrecio;
        PeFecha = peFecha;
        PeActivo = peActivo;
        PeCancelado = peCancelado;
        this.cliente = cliente;
        this.idNegocio = idNegocio;
        this.productos = productos;
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

    public Long getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(Long idNegocio) {
        this.idNegocio = idNegocio;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}