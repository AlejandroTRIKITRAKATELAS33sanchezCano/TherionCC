package com.therioncc.therion.services;

import com.therioncc.therion.Auth.AuthResponse;
import com.therioncc.therion.DTO.ClienteRequest;
import com.therioncc.therion.DTO.LoginRequest;
import com.therioncc.therion.DTO.PedidoRequest;
import com.therioncc.therion.models.*;
import com.therioncc.therion.repositories.IClienteRepositorio;
import com.therioncc.therion.repositories.IDireccionRepositorio;
import com.therioncc.therion.repositories.IPedidoRepositorio;
import com.therioncc.therion.repositories.IProductoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private final IClienteRepositorio clienteRepository;

    @Autowired
    private final IPedidoRepositorio pedidoRepositorio;

    @Autowired
    private final IProductoRepositorio productoRepositorio;

    //Servicio
    @Autowired
    private final NegocioService negocioService;

    @Autowired
    private final EstadoService estadoService;

    @Autowired
    private final ProductoService productoService;

    private final JwtService jwtService;

    @Autowired
    private final IDireccionRepositorio direccionRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public ClienteService(IClienteRepositorio clienteRepository, IPedidoRepositorio pedidoRepositorio, IProductoRepositorio productoRepositorio, NegocioService negocioService, EstadoService estadoService, ProductoService productoService, JwtService jwtService, IDireccionRepositorio direccionRepositorio, AuthenticationManager authenticationManager) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepositorio = pedidoRepositorio;
        this.productoRepositorio = productoRepositorio;
        this.negocioService = negocioService;
        this.estadoService = estadoService;
        this.productoService = productoService;
        this.jwtService = jwtService;
        this.direccionRepositorio = direccionRepositorio;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthResponse registrarCliente(ClienteRequest clienteRequest) {

        // Validar que no haya valores nulos

        //Encontrar Estado
        Estado estado = estadoService.consultarEstado(clienteRequest.getEsNombre());

        // Crear una instancia de Cliente
        Cliente cliente = new Cliente();
        cliente.setClNombre(clienteRequest.getClNombre());
        cliente.setClApmat(clienteRequest.getClApmat());
        cliente.setClAppat(clienteRequest.getClAppat());
        cliente.setClCorreo(clienteRequest.getClCorreo());
        cliente.setClNumero1(clienteRequest.getClNumero1());
        cliente.setClNumero2(clienteRequest.getClNumero2());
        String encodedPassword = passwordEncoder.encode(clienteRequest.getClContrasenna());
        cliente.setClContrasenna(encodedPassword);

        clienteRepository.save(cliente);

        AuthResponse authResponse = AuthResponse.builder()
                .token(jwtService.getToken(cliente))
                .build();

        System.out.println("Valor del token en AuthResponse: " + authResponse.getToken());

        return authResponse;
    }

    public AuthResponse loginCliente(LoginRequest loginrequest) {
        System.out.println("Se está iniciando sesion");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginrequest.getEmail(), loginrequest.getContrasenna()));

        UserDetails cliente = clienteRepository.findByClCorreo(loginrequest.getEmail()).orElseThrow();


        String token = jwtService.getToken(cliente);

        AuthResponse authResponse = AuthResponse.builder()
                .token(token)
                .build();

        return authResponse;
    }

    public Cliente consultarCliente(Long idCliente){
        Optional<Cliente> cliente = clienteRepository.findByIdCliente(idCliente);

        return cliente.orElse(null);
    }

    public Pedido registrarPedido(PedidoRequest pedidoRequest){

        //Validar Campos

        //Consultar Negocio

        Negocio negocio = negocioService.consultarNegocio(pedidoRequest.getIdNegocio());

        //Consultar Cliente

        Cliente cliente = consultarCliente(pedidoRequest.getIdCliente());

        //Consultar Productos



        //Crear Pedido

        Pedido pedido = new Pedido();

        pedido.setPeActivo(pedidoRequest.isPeActivo()); //Activo
        pedido.setPeCancelado(pedido.isPeCancelado()); //Cancelado

        pedido.setPeDireccion(pedidoRequest.getPeDireccion()); //Direccion
        pedido.setPeFecha(pedidoRequest.getPeFecha()); //Fecha
        pedido.setPePrecio(pedidoRequest.getPePrecio()); //Precio

        pedido.setIdNegocio(negocio.getIdNegocio()); //Negocio
        pedido.setCliente(cliente); //Cliente

        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(productoService.consultarProducto(pedidoRequest.getIdProducto()));
        pedido.setProductos(listaProductos); //Productos

        pedidoRepositorio.save(pedido);

        return pedido;
    }

    // Otros métodos según las operaciones que necesites
}