package com.therioncc.therion.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.therioncc.therion.DTO.HorarioRequest;
import com.therioncc.therion.DTO.MenuRequest;
import com.therioncc.therion.DTO.NegocioRequest;
import com.therioncc.therion.DTO.ProductoRequest;
import com.therioncc.therion.models.*;
import com.therioncc.therion.services.JwtService;
import com.therioncc.therion.services.NegocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DuennoController {

    private final NegocioService negocioService;

    //Servicio JwtService
    @Autowired
    private final JwtService jwtService;

    public DuennoController(NegocioService negocioService, JwtService jwtService) {

        this.negocioService = negocioService;
        this.jwtService = jwtService;
    }

    //Negocio

    //Obtener Negocios
    @GetMapping("/Negocio/ConsultarxDuenno")
    public ResponseEntity<List<Negocio>> consultarNegocioxDuenno(@RequestHeader("Authorization") String token){

        try{

            System.out.println(token);
        String tokenCortado = token.substring("Bearer ".length());
        System.out.println("El token es: " + tokenCortado);

        Duenno duennoFromToken = jwtService.getDuennoFromToken(tokenCortado);

        System.out.println("El Dueño Del Negocio Es " + duennoFromToken);

        Long id = duennoFromToken.getIdDuenno();

        List<Negocio> negocios = negocioService.consultarNegocioxDuenno(id);

            for (Negocio negocio : negocios) {
                System.out.println("Su nombre es: " + negocio.getNeNombre());
                System.out.println("Su Imagen es: " + negocio.getNeImagen());
                System.out.println("Su Activo es: " + negocio.isNeActivo());
            }

        System.out.println(negocios);
        return ResponseEntity.ok(negocios);
        }catch (Exception e){

            System.out.println("Falló " + token);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/Negocio/Registro")
    public ResponseEntity<Negocio> RegistrarNegocio(
            @RequestParam("negocioRequest") String negocioRequestJson,
            @RequestParam("archivo") MultipartFile archivo) throws JsonProcessingException {

        NegocioRequest negocioRequest = new ObjectMapper().readValue(negocioRequestJson, NegocioRequest.class);
        negocioRequest.setImagen(archivo);
        Negocio negocio = negocioService.registrarNegocio(negocioRequest);

        return ResponseEntity.ok(negocio);

    }

    @PostMapping("/Negocio/Horario/Registro")
    public Horario RegistrarHorario(@RequestBody HorarioRequest horarioRequest) {

        Horario horario = negocioService.registrarHorario(horarioRequest);

        return horario;

    }

    @PostMapping("/Negocio/Menu/Registro")
    public Menu RegistrarMenu(
            @RequestParam("menuRequest") String menuRequestJson,
            @RequestParam("archivo") MultipartFile archivo) throws JsonProcessingException {

        MenuRequest menuRequest = new ObjectMapper().readValue(menuRequestJson, MenuRequest.class);
        menuRequest.setImagen(archivo);
        Menu menu = negocioService.registrarMenu(menuRequest);

        return menu;
    }

    @PostMapping("/Negocio/Menu/Producto/Registro")
    public Producto RegistrarProducto(@RequestParam("productoRequest") String productoRequestJson,
                                                      @RequestParam("archivo") MultipartFile archivo) throws JsonProcessingException{

        ProductoRequest productoRequest = new ObjectMapper().readValue(productoRequestJson, ProductoRequest.class);
        productoRequest.setImagen(archivo);
        Producto producto = negocioService.registrarProducto(productoRequest);

        return producto;
    }
}
