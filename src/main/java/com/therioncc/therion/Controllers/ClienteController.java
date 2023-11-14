package com.therioncc.therion.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ClienteController {
    @PostMapping("/Pedido/Iniciar")
    public String welcome(){
        return "Has hecho un pedido";
    }
}
