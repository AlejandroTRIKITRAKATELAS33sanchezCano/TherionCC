package com.therioncc.therion.DTO;

import com.therioncc.therion.models.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NegocioRequest {

    //token
    private String token;

    //Negocio

    private String neNombre;
    private boolean neAbierto;
    private boolean neActivo;
    private boolean neBorrado;
    private String neImagen;

    //Imagen

    private MultipartFile imagen;


    private Duenno duenno;

    private boolean neTarjeta;
    private boolean neDomicilio;

    //Dirrecion
    private String esNombre;
    private String alcaldia;
    private String colonia;
    private String codigopostal;

    //Contacto

    private String coNombre;
    private String coTelefono;

    //TipoNegocio

    private Long idTipoNegocio;
}
