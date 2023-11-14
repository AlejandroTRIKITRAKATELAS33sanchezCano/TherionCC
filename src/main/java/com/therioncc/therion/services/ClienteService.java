package com.therioncc.therion.services;

import com.therioncc.therion.Auth.AuthResponse;
import com.therioncc.therion.DTO.ClienteRequest;
import com.therioncc.therion.DTO.LoginRequest;
import com.therioncc.therion.models.Cliente;
import com.therioncc.therion.models.Direccion;
import com.therioncc.therion.models.Estado;
import com.therioncc.therion.repositories.IClienteRepositorio;
import com.therioncc.therion.repositories.IDireccionRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private final IClienteRepositorio clienteRepository;

    @Autowired
    private final EstadoService estadoService;

    private final JwtService jwtService;

    @Autowired
    private final IDireccionRepositorio direccionRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public ClienteService(IClienteRepositorio clienteRepository, EstadoService estadoService, JwtService jwtService, IDireccionRepositorio direccionRepositorio, AuthenticationManager authenticationManager) {
        this.clienteRepository = clienteRepository;
        this.estadoService = estadoService;
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

        // Crear una instancia de Direccion
        Direccion direccion = new Direccion();
        direccion.setAlcaldia(clienteRequest.getAlcaldia());
        direccion.setCodigopostal(clienteRequest.getCodigopostal());
        direccion.setColonia(clienteRequest.getColonia());
        direccion.setEstado(estado);
        direccion.setCliente(cliente);

        // Guardar la dirección en la base de datos
        direccionRepositorio.save(direccion);


        cliente.setDireccion(direccion);
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

    // Otros métodos según las operaciones que necesites
}