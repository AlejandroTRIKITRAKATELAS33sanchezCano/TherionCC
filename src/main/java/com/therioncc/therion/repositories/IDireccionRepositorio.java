package com.therioncc.therion.repositories;

import com.therioncc.therion.models.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDireccionRepositorio extends JpaRepository<Direccion, Long> {

}
