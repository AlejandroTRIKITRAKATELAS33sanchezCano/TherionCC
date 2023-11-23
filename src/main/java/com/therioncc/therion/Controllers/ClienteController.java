package com.therioncc.therion.Controllers;

import com.therioncc.therion.DTO.MenuRequest;
import com.therioncc.therion.DTO.PedidoRequest;
import com.therioncc.therion.models.Menu;
import com.therioncc.therion.models.Negocio;
import com.therioncc.therion.models.Pedido;
import com.therioncc.therion.models.Producto;
import com.therioncc.therion.services.ClienteService;
import com.therioncc.therion.services.NegocioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ClienteController {

    //Servicios
    private final ClienteService clienteService;
    private final NegocioService negocioService;

    public ClienteController(ClienteService clienteService, NegocioService negocioService) {
        this.clienteService = clienteService;
        this.negocioService = negocioService;
    }

    //Iniciar Pedido
    @PostMapping("/Pedido/Iniciar")
    public Pedido realizarPedido(@RequestBody PedidoRequest pedidoRequest){

        return clienteService.registrarPedido(pedidoRequest);
    }

    //Visualizar Negocios
    @GetMapping("/Negocios/ConsultarALL")
    public List<Negocio> consultarNegocio(){

        List<Negocio> negocios = negocioService.consultarNegocioALL();

        return negocios;
    }

    //Visualizar Menus por Negocio
    @GetMapping("/Negocios/Menu/ConsultarXNegocio")
    public List<Menu> consultarMenuNegocio(@RequestBody MenuRequest menuRequest){

        List<Menu> menus = negocioService.consultarMenuNegocio(menuRequest);

        return menus;
    }

    //Visualizar Productos por Menus
//    @GetMapping("/Negocios/Menu/Productos")
//    public List<Producto> constultarProductosMenu(@RequestBody ){
//
//    }
}
