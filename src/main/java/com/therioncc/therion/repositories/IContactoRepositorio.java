package com.therioncc.therion.repositories;

import com.therioncc.therion.models.Contacto;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactoRepositorio extends JpaRepository<Contacto, Long> {

}
