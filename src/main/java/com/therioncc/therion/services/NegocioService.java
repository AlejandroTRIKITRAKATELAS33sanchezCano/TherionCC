package com.therioncc.therion.services;

import com.therioncc.therion.DTO.HorarioRequest;
import com.therioncc.therion.DTO.MenuRequest;
import com.therioncc.therion.DTO.NegocioRequest;
import com.therioncc.therion.DTO.ProductoRequest;
import com.therioncc.therion.models.*;
import com.therioncc.therion.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //Servicio Imagen
    @Autowired
    private final MenuService menuService;

    //Tipo De Negocio Servicio
    @Autowired
    private final TipoNegocioService tipoNegocioService;

    private final AuthenticationManager authenticationManager;

    //Producto Repositorio
    @Autowired
    private final IProductoRepositorio productoRepositorio;

    //Direccion Repositorio
    @Autowired
    private final IDireccionRepositorio direccionRepositorio;

    //Horario Repositorio
    @Autowired
    private final IHorarioRepositorio horarioRepositorio;

    //Menu Repositorio
    @Autowired
    private final IMenuRepositorio menuRepositorio;

    //Dia de la semana Servicio
    @Autowired
    private final DiaSemanaService diaSemanaService;


    public NegocioService(IDuennoRepositorio duennoRepositorio, INegocioRepositorio iNegocioRepositorio, EstadoService estadoService, JwtService jwtService, ContactoService contactoService, CloudinaryService cloudinaryService, MenuService menuService, TipoNegocioService tipoNegocioService, AuthenticationManager authenticationManager, IProductoRepositorio productoRepositorio, IDireccionRepositorio direccionRepositorio, IDiaSemana iDiaSemana, IHorarioRepositorio horarioRepositorio, IMenuRepositorio menuRepositorio, DiaSemanaService diaSemanaService) {
        this.duennoRepositorio = duennoRepositorio;
        this.iNegocioRepositorio = iNegocioRepositorio;
        this.estadoService = estadoService;
        this.jwtService = jwtService;
        this.contactoService = contactoService;
        this.cloudinaryService = cloudinaryService;
        this.menuService = menuService;
        this.tipoNegocioService = tipoNegocioService;
        this.authenticationManager = authenticationManager;
        this.productoRepositorio = productoRepositorio;
        this.direccionRepositorio = direccionRepositorio;
        this.horarioRepositorio = horarioRepositorio;
        this.menuRepositorio = menuRepositorio;
        this.diaSemanaService = diaSemanaService;
    }

    public List<Negocio> consultarNegocioxDuenno(Long id){

        List<Negocio> negocios = iNegocioRepositorio.findAllByDuennoId(id);
        //System.out.println(negocios.get(0));
        return negocios;
    }

    public List<Negocio> consultarNegocioALL(){

        List<Negocio> negocios = iNegocioRepositorio.findAll();

        return negocios;
    }
    public List<Menu> consultarMenuNegocio(MenuRequest menuRequest){

        List<Menu> menus = menuRepositorio.findAllByNegocio_IdNegocio(menuRequest.getIdNegocio());

        return menus;
    }
    public Negocio consultarNegocio(Long idNegocio){
        Optional<Negocio> negocio = iNegocioRepositorio.findByIdNegocio(idNegocio);
        return negocio.orElse(null);
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

        System.out.println("El Dueño Del Negocio Es " + duennoFromToken);

        negocio.setDuenno(duennoFromToken);

        // Crear Contacto
        Contacto contacto = new Contacto();
        contacto.setCoNombre(negocioRequest.getCoNombre());
        contacto.setCoTelefono(negocioRequest.getCoTelefono());
        contacto.setNegocio(negocio);

        List<Contacto> listaContactos = new ArrayList<>();
        listaContactos.add(contacto);

        negocio.setContactos(listaContactos);

        // Guardar Negocio en la base de datos (esto también guardará automáticamente el Contacto debido a la cascada)
        Negocio negocioGuardado = iNegocioRepositorio.save(negocio);

        // Crear Direccion
        Direccion direccion = new Direccion();
        direccion.setEstado(estado);
        direccion.setAlcaldia(negocioRequest.getAlcaldia());
        direccion.setColonia(negocioRequest.getColonia());
        direccion.setCodigopostal(negocioRequest.getCodigopostal());
        direccion.setNegocio(negocioGuardado);

        // Guardar Direccion en la base de datos
        direccionRepositorio.save(direccion);


        // Guardar Negocio en la base de datos
        return negocio;
    }

    public Horario registrarHorario(HorarioRequest horarioRequest){

        //Validar

        //Encontrar Dia De La Semana

        DiaSemana diaSemana = diaSemanaService.consultarDia(horarioRequest.getIdDiaSemana());

        //Inciar Horario

        Horario horario = new Horario();

        horario.setDiaSemana(diaSemana);

        horario.setHoraApertura(horarioRequest.getHoraApertura());
        horario.setHoraCierre(horarioRequest.getHoraCierre());

        horario.setNegocio(consultarNegocio(horarioRequest.getIdNegocio()));

        horarioRepositorio.save(horario);

        return horario;
    }

    public Menu registrarMenu(MenuRequest menuRequest) {

        //Validar Campos

        Menu menu = new Menu();

        //Buscar negocio

        menu.setNegocio(consultarNegocio(menuRequest.getIdNegocio())); //ID Negocio
        menu.setMeActivo(menuRequest.isMeActivo()); //Activo
        menu.setMeDisponible(menuRequest.isMeDisponible()); //Disponible

        System.out.println("El Nombre es: " + menuRequest.getMeNombre());
        menu.setMeNombre(menuRequest.getMeNombre()); //Nombre

        // Imagen
        String url = cloudinaryService.uploadImage(menuRequest.getImagen());
        menu.setMeImagen(url);

        System.out.println("La imagen Es :" + url);

        menuRepositorio.save(menu);

        return menu;
    }

    public Producto registrarProducto(ProductoRequest productoRequest){

        //Validar Campos

        //Buscar menu

        Menu menu = menuService.consultarMenu(productoRequest.getIdMenu());

        //Crear Producto

        Producto producto = new Producto();

        //Añadir Menu
        List<Menu> listaMenus = new ArrayList<>();
        listaMenus.add(menu);
        producto.setMenus(listaMenus);

        //Atributos de Productos

        producto.setPrNombre(productoRequest.getPrNombre());
        producto.setPrPrecio(productoRequest.getPrPrecio());
        producto.setPrDescripcion(productoRequest.getPrDescripcion());

        // Imagen
        String url = cloudinaryService.uploadImage(productoRequest.getImagen());
        producto.setPrImagen(url);

        productoRepositorio.save(producto);

        return producto;
    }
}
