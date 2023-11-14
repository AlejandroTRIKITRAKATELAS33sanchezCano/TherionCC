package com.therioncc.therion.Controllers;

import com.therioncc.therion.DTO.NegocioRequest;
import com.therioncc.therion.models.Negocio;
import com.therioncc.therion.services.NegocioService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class DuennoController {

    private final NegocioService negocioService;

    public DuennoController(NegocioService negocioService) {
        this.negocioService = negocioService;
    }

    //Negocio
    @PostMapping(value = "/Negocio/Registro" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Negocio> RegistrarNegocio(@RequestBody MultiValueMap<String, String> negocioFormData, @RequestPart  MultipartFile archivo){

        // Crear un objeto NegocioRequest y asignar los valores manualmente
        NegocioRequest negocioRequest = new NegocioRequest();

//        negocioRequest.setNeNombre(negocioFormData.getFirst("neNombre"));
//        negocioRequest.setNeTarjeta(Boolean.parseBoolean(negocioFormData.getFirst("neTarjeta")));

        negocioRequest.setImagen(archivo);  // Asigna el archivo al DTO

        return ResponseEntity.ok(negocioService.registrarNegocio(negocioRequest));
    }
}
