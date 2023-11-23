package com.therioncc.therion.services;

import com.therioncc.therion.Auth.AuthResponse;
import com.therioncc.therion.DTO.DuennoRequest;
import com.therioncc.therion.DTO.LoginRequest;
import com.therioncc.therion.models.Contacto;
import com.therioncc.therion.models.Negocio;
import com.therioncc.therion.repositories.IContactoRepositorio;
import com.therioncc.therion.repositories.IDuennoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.therioncc.therion.models.Duenno;

import java.util.ArrayList;
import java.util.List;

@Service
public class DuennoService {

    @Autowired
    private final IDuennoRepositorio duennoRepositorio;
    private final JwtService jwtService;
    private IContactoRepositorio contactoRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public DuennoService(IDuennoRepositorio duennoRepositorio, JwtService jwtService, IContactoRepositorio contactoRepositorio, AuthenticationManager authenticationManager) {
        this.duennoRepositorio = duennoRepositorio;
        this.jwtService = jwtService;
        this.contactoRepositorio = contactoRepositorio;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthResponse registrarDuenno(DuennoRequest duennoRequest) {

        //Crear Dueño

        Duenno duenno = new Duenno();

        duenno.setDunNombre(duennoRequest.getDunNombre());
        duenno.setDuAppat(duennoRequest.getDuAppat());
        duenno.setDuApmat(duennoRequest.getDuApmat());
        duenno.setDuCorreo(duennoRequest.getDuCorreo());
        String encodedPassword = passwordEncoder.encode(duennoRequest.getDuContrasenna());
        duenno.setDuContrasenna(encodedPassword);

        //Guardar Contacto

        System.out.println(duenno);

        duennoRepositorio.save(duenno);

        AuthResponse authResponse = AuthResponse.builder()
                .token(jwtService.getToken(duenno))
                .build();

        System.out.println("Valor del token en AuthResponse: " + authResponse.getToken());

        return authResponse;


    }

    public AuthResponse loginDuenno(LoginRequest loginrequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginrequest.getEmail(), loginrequest.getContrasenna()));

        UserDetails duenno = duennoRepositorio.findByDuCorreo(loginrequest.getEmail()).orElseThrow();
        String token = jwtService.getToken(duenno);

        AuthResponse authResponse = AuthResponse.builder()
                                        .token(token)
                                        .build();

        System.out.println("Valor del token en AuthResponse: " + authResponse.getToken());

        return authResponse;
    }

}
