package com.therioncc.therion.services;

import com.therioncc.therion.models.Producto;
import com.therioncc.therion.repositories.IProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoService {

    private final IProductoRepositorio iProductoRepositorio;

    public ProductoService(IProductoRepositorio iProductoRepositorio) {
        this.iProductoRepositorio = iProductoRepositorio;
    }

    public Producto consultarProducto(Long idProducto){

        Optional<Producto> productoOptional = iProductoRepositorio.findByIdProducto(idProducto);

        return productoOptional.orElse(null);
    }
}
