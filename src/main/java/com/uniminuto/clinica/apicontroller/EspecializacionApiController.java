package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.EspecializacionApi;
import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.model.EspecializacionRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.EspecializacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EspecializacionApiController implements EspecializacionApi {

    @Autowired
    private EspecializacionService especializacionService;

    @Override
    public ResponseEntity<List<Especializacion>> listarEspecializaciones() {
        return ResponseEntity.ok(this.especializacionService.listarEspecializaciones());
    }

    @Override
    public ResponseEntity<RespuestaRs> guardarEspecializacion(@Valid @RequestBody EspecializacionRq especializacionRq) throws BadRequestException {
       return ResponseEntity.ok(this.especializacionService.guardarEspecializacion(especializacionRq)) ; 
    }

    @Override
    public ResponseEntity<RespuestaRs> actualizarEspecializacion(@Valid @RequestBody EspecializacionRq especializacionRq) throws BadRequestException {
        return ResponseEntity.ok(this.especializacionService.actualizarEspecializacion(especializacionRq)); 
    }
}