package com.therioncc.therion.services;

import com.therioncc.therion.models.Menu;
import com.therioncc.therion.repositories.IMenuRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private final IMenuRepositorio menuRepositorio;

    public MenuService(IMenuRepositorio menuRepositorio) {
        this.menuRepositorio = menuRepositorio;
    }

    public Menu consultarMenu(Long idMenu){
        Optional<Menu> menuOptional = menuRepositorio.findByIdMenu(idMenu);
        return menuOptional.orElse(null);
    }
}
