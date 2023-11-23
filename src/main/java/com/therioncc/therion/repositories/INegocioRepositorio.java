package com.therioncc.therion.repositories;

import com.therioncc.therion.models.Duenno;
import com.therioncc.therion.models.Negocio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface INegocioRepositorio extends JpaRepository<Negocio, Long> {
    Optional<Negocio> findByIdNegocio(Long idNegocio); //Consultar Un Negocio
    List<Negocio> findAll(); //Consultar Todos Los Negocios
    @Query("SELECT n FROM Negocio n WHERE n.duenno.idDuenno = :id")
    List<Negocio> findAllByDuennoId(@Param("id") Long id);


}
