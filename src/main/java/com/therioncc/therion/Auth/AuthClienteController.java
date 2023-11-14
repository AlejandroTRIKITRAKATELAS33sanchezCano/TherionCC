package com.therioncc.therion.Auth;

import com.therioncc.therion.DTO.ClienteRequest;
import com.therioncc.therion.DTO.LoginRequest;
import com.therioncc.therion.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/cliente")
public class AuthClienteController {

    private final ClienteService clienteService;

    @Autowired
    public AuthClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginDuenno(@RequestBody LoginRequest loginrequest){
        return ResponseEntity.ok(clienteService.loginCliente(loginrequest));
    }

    @PostMapping("/registrar")
    public ResponseEntity<AuthResponse> registrarCliente(@RequestBody ClienteRequest clienteRequest) {

        return ResponseEntity.ok(clienteService.registrarCliente(clienteRequest));
    }

    // Otros métodos según las operaciones que necesites
}