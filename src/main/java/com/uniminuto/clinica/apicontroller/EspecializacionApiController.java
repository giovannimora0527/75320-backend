package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.EspecializacionApi;
import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.model.EspecializacionRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.EspecializacionService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PC
 */

@RestController
public class EspecializacionApiController implements EspecializacionApi {
    
    @Autowired
    private EspecializacionService especializacionService;

    @Override
    public ResponseEntity<List<Especializacion>> listarEspecializaciones() {
        return ResponseEntity.ok(especializacionService.buscarEspecializacion());
    }
    
    @Override
    public ResponseEntity<RespuestaRs> guardarEspecializacion(EspecializacionRq especializacionRq) throws BadRequestException {
        return ResponseEntity.ok(especializacionService.guardarEspecializacion(especializacionRq));
    }

    @Override
    public ResponseEntity<RespuestaRs> eliminarEspecializacion(Integer id) throws BadRequestException {
        return ResponseEntity.ok(especializacionService.eliminarEspecializacion(id));
    }

    @Override
    public ResponseEntity<RespuestaRs> actualizarEspecializacion(Integer id, EspecializacionRq especializacionRq) throws BadRequestException {
        return ResponseEntity.ok(especializacionService.actualizarEspecializacion(id, especializacionRq));
    
    }
}