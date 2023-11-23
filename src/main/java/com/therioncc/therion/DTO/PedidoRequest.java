package com.therioncc.therion.DTO;

import com.therioncc.therion.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {

    private String peDireccion;
    private int pePrecio;
    private Date peFecha;
    private boolean peActivo;
    private boolean peCancelado;
    private Long idCliente; //Cliente
    private Long idProducto; //Producto
    private Long idNegocio; //Negocio
}
