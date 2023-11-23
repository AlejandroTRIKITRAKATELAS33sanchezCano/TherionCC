package com.therioncc.therion.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequest {

    private String prNombre;
    private int prPrecio;
    private String prImagen;
    private String prDescripcion;

    //Imagen
    private MultipartFile imagen;

    //Menu
    private Long idMenu;
}
