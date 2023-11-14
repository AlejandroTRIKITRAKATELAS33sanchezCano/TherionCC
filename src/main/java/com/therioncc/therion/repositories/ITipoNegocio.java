package com.therioncc.therion.repositories;


import com.therioncc.therion.models.Estado;
import com.therioncc.therion.models.TipoNegocio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITipoNegocio extends JpaRepository<TipoNegocio, Long> {
    Optional<TipoNegocio> findByIdTipoNegocio(Long idTipoNegocio);
}
