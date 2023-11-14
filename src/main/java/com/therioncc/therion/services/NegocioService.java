package com.therioncc.therion.services;

import com.therioncc.therion.DTO.NegocioRequest;
import com.therioncc.therion.models.*;
import com.therioncc.therion.repositories.IDireccionRepositorio;
import com.therioncc.therion.repositories.IDuennoRepositorio;
import com.therioncc.therion.repositories.INegocioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NegocioService {

    //Duenno Repositorio

    @Autowired
    private final IDuennoRepositorio duennoRepositorio;

    //Negocio Repositorio
    @Autowired
    private final INegocioRepositorio iNegocioRepositorio;

    //Servicio Del Estado
    @Autowired
    private final EstadoService estadoService;

    //Servicio JwtService
    @Autowired
    private final JwtService jwtService;

    //Servicio de Contacto
    @Autowired
    private final ContactoService contactoService;

    //Servicio Imagen
    @Autowired
    private final CloudinaryService cloudinaryService;

    //Tipo De Negocio Servicio
    @Autowired
    private final TipoNegocioService tipoNegocioService;

    private final AuthenticationManager authenticationManager;

    //Direccion Repositorio
    @Autowired
    private final IDireccionRepositorio direccionRepositorio;


    public NegocioService(IDuennoRepositorio duennoRepositorio, INegocioRepositorio iNegocioRepositorio, EstadoService estadoService, JwtService jwtService, ContactoService contactoService, CloudinaryService cloudinaryService, TipoNegocioService tipoNegocioService, AuthenticationManager authenticationManager, IDireccionRepositorio direccionRepositorio) {
        this.duennoRepositorio = duennoRepositorio;
        this.iNegocioRepositorio = iNegocioRepositorio;
        this.estadoService = estadoService;
        this.jwtService = jwtService;
        this.contactoService = contactoService;
        this.cloudinaryService = cloudinaryService;
        this.tipoNegocioService = tipoNegocioService;
        this.authenticationManager = authenticationManager;
        this.direccionRepositorio = direccionRepositorio;
    }

    public Negocio registrarNegocio(NegocioRequest negocioRequest) {

        // Validar que no haya valores nulos

        // Encontrar Estado
        Estado estado = estadoService.consultarEstado(negocioRequest.getEsNombre());

        System.out.println("el request manda: " + negocioRequest.getEsNombre());
        System.out.println("El servicio manda: " + estado);

        // Crear Negocio
        Negocio negocio = new Negocio();
        negocio.setNeNombre(negocioRequest.getNeNombre());
        negocio.setNeAbierto(true);
        negocio.setNeActivo(true);
        negocio.setNeBorrado(true);
        negocio.setNeTarjeta(true);
        negocio.setNeDomicilio(true);

        // Imagen
        String url = cloudinaryService.uploadImage(negocioRequest.getImagen());
        negocio.setNeImagen(url);

        // Consultar y asociar TipoNegocio
        TipoNegocio tipoNegocio = tipoNegocioService.consultarTipoNegocio(negocioRequest.getIdTipoNegocio());

        System.out.println("el request manda: " + negocioRequest.getEsNombre());
        System.out.println("El servicio manda: " + tipoNegocio);

        List<TipoNegocio> listaTipoNegocios = new ArrayList<>();
        listaTipoNegocios.add(tipoNegocio);
        negocio.setTiposNegocio(listaTipoNegocios);

        // Obtener el dueño asociado al token

        Duenno duennoFromToken = jwtService.getDuennoFromToken(negocioRequest.getToken());

        negocio.setDuenno(duennoFromToken);

        // Crear Contacto
        Contacto contacto = new Contacto();
        contacto.setCoNombre(negocioRequest.getCoNombre());
        contacto.setCoTelefono(negocioRequest.getCoTelefono());
        contacto.setNegocio(negocio);

        // Guardar Contacto en la base de datos
        contactoService.registrarContacto(contacto);

        List<Contacto> listaContactos = new ArrayList<>();
        listaContactos.add(contacto);

        negocio.setContactos(listaContactos);

        //Guardar Negocio
        iNegocioRepositorio.save(negocio);

        // Crear Direccion
        Direccion direccion = new Direccion();
        direccion.setEstado(estado);
        direccion.setAlcaldia(negocioRequest.getAlcaldia());
        direccion.setColonia(negocioRequest.getColonia());
        direccion.setCodigopostal(negocioRequest.getCodigopostal());
        direccion.setNegocio(negocio);

        // Guardar Direccion en la base de datos
        direccionRepositorio.save(direccion);


        // Guardar Negocio en la base de datos
        return negocio;
    }
}
