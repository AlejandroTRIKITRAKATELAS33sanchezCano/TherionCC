package com.therioncc.therion.DTO;

import com.therioncc.therion.models.Negocio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HorarioRequest{

    private Long idDiaSemana;

    private Long idNegocio;
    private LocalTime horaApertura;
    private LocalTime horaCierre;

}
