package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.EspecializacionApi;
import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.service.EspecializacionService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para la gestión de especializaciones médicas
 * 
 * @author JulianLopez
 */
@RestController
public class EspecializacionApiController implements EspecializacionApi {
    
    @Autowired
    private EspecializacionService especializacionService;
    
    @Override
    public ResponseEntity<List<Especializacion>> listarEspecializaciones() {
        return ResponseEntity.ok(especializacionService.encontrarTodasLasEspecializaciones());
    }
    
    @Override
    public ResponseEntity<Especializacion> buscarEspecializacionPorId(Integer id) 
            throws BadRequestException {
        return ResponseEntity.ok(especializacionService.buscarEspecializacionPorId(id));
    }
    
    @Override
    public ResponseEntity<Especializacion> crearEspecializacion(Especializacion especializacion) 
            throws BadRequestException {
        Especializacion nuevaEspecializacion = especializacionService.crearEspecializacion(especializacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEspecializacion);
    }
    
    @Override
    public ResponseEntity<Especializacion> actualizarEspecializacion(Integer id, Especializacion especializacion) 
            throws BadRequestException {
        Especializacion especializacionActualizada = especializacionService.actualizarEspecializacion(id, especializacion);
        return ResponseEntity.ok(especializacionActualizada);
    }
    
    @Override
    public ResponseEntity<Void> eliminarEspecializacion(Integer id) 
            throws BadRequestException {
        especializacionService.eliminarEspecializacion(id);
        return ResponseEntity.noContent().build();
    }
}