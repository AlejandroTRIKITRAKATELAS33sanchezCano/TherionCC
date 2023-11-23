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
public class MenuRequest {

    private String meNombre;

    private boolean meDisponible;

    private boolean meActivo;

    private MultipartFile imagen;

    private Long idNegocio;
}
