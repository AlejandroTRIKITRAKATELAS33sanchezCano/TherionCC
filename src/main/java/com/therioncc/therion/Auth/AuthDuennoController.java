package com.therioncc.therion.Auth;

import com.therioncc.therion.DTO.DuennoRequest;
import com.therioncc.therion.DTO.LoginRequest;
import com.therioncc.therion.services.DuennoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/duenno")
public class AuthDuennoController {

    private final DuennoService duennoService;

    @Autowired
    public AuthDuennoController(DuennoService duennoService) {
        this.duennoService = duennoService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginDuenno(@RequestBody LoginRequest loginrequest){
        return ResponseEntity.ok(duennoService.loginDuenno(loginrequest));
    }

    @PostMapping("/registrar")
    public ResponseEntity<AuthResponse> registrarDuenno(@RequestBody DuennoRequest duennoRequest) {

        return ResponseEntity.ok(duennoService.registrarDuenno(duennoRequest));
    }

    // Otros métodos según las operaciones que necesites
}
