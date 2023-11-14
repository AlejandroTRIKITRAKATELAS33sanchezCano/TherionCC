package com.therioncc.therion.services;

import com.therioncc.therion.models.TipoNegocio;
import com.therioncc.therion.repositories.ITipoNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoNegocioService {

    @Autowired
    private final ITipoNegocio iTipoNegocio;

    public TipoNegocioService(ITipoNegocio iTipoNegocio) {
        this.iTipoNegocio = iTipoNegocio;
    }

    public TipoNegocio consultarTipoNegocio(Long idTipoNegocio){
        Optional<TipoNegocio> tipoNegocioOptional = iTipoNegocio.findByIdTipoNegocio(idTipoNegocio);
        return tipoNegocioOptional.orElse(null);
    }


}
