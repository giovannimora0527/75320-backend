package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.EspecializacionApi;
import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.model.EspecializacionRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.EspecializacionService;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Controlador REST que implementa la API de Especialización.
 * 
 * Endpoints:
 *  - GET  /especializacion/listar → Lista todas las especializaciones.
 *  - POST /especializacion/guardar → Crea una nueva especialización.
 *  - POST /especializacion/actualizar → Actualiza una especialización existente.
 *  - POST /especializacion/eliminar → Elimina una especialización.
 * 
 * @author 
 */
@RestController
public class EspecializacionApiController implements EspecializacionApi {

    private final EspecializacionService especializacionService;

    // ✅ Constructor para inyección de dependencias
    public EspecializacionApiController(EspecializacionService especializacionService) {
        this.especializacionService = especializacionService;
    }

    /**
     * Lista todas las especializaciones.
     */
    @Override
    public ResponseEntity<List<Especializacion>> listarEspecializaciones() {
        return ResponseEntity.ok(especializacionService.listarTodo());
    }

    /**
     * Guarda una nueva especialización.
     */
    @Override
    public ResponseEntity<RespuestaRs> guardarEspecializacion(EspecializacionRq especializacionRq)
            throws BadRequestException {
        return ResponseEntity.ok(especializacionService.guardarEspecializacion(especializacionRq));
    }

    /**
     * Actualiza una especialización existente.
     */
    @Override
    public ResponseEntity<RespuestaRs> actualizarEspecializacion(Integer id, EspecializacionRq especializacionRq)
            throws BadRequestException {
        return ResponseEntity.ok(especializacionService.actualizarEspecializacion(id, especializacionRq));
    }

    /**
     * Elimina una especialización por ID.
     */
    @Override
    public ResponseEntity<RespuestaRs> eliminarEspecializacion(Integer id) throws BadRequestException {
        return ResponseEntity.ok(especializacionService.eliminarEspecializacion(id));
    }

    /**
     * Busca una especialización por su código.
     */
    @Override
    public ResponseEntity<Especializacion> buscarPorCodigo(String codigo) throws BadRequestException {
        return ResponseEntity.ok(especializacionService.buscarEspecializacionPorCod(codigo));
    }
}
