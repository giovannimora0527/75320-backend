package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.MedicoApi;
import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.model.MedicoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.MedicoService;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST que implementa la API de Médicos.
 *
 * Endpoints:
 *  - GET  /medico/listar → Lista todos los médicos.
 *  - GET  /medico/especialidad → Lista médicos por código de especialidad.
 *  - POST /medico/guardar → Registra un nuevo médico.
 *  - PUT  /medico/actualizar/{id} → Actualiza un médico existente.
 *  - DELETE /medico/eliminar/{id} → Elimina un médico.
 *  - GET  /medico/test → Endpoint de prueba (opcional).
 * 
 * @author 
 */
@RestController
public class MedicoApiController implements MedicoApi {

    private final MedicoService medicoService;

    // ✅ Inyección de dependencias por constructor (recomendada)
    public MedicoApiController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    /**
     * Lista todos los médicos registrados.
     */
    @Override
    public ResponseEntity<List<Medico>> listarMedicos() {
        return ResponseEntity.ok(medicoService.listarMedicos());
    }

    /**
     * Lista los médicos filtrados por código de especialidad.
     */
    @Override
    public ResponseEntity<List<Medico>> listarMedicosPorEspecializacion(String codigo)
            throws BadRequestException {
        return ResponseEntity.ok(medicoService.buscarPorEspecialidad(codigo));
    }

    /**
     * Guarda un nuevo médico en la base de datos.
     */
    @Override
    public ResponseEntity<RespuestaRs> guardarMedico(MedicoRq medicoRq)
            throws BadRequestException {
        return ResponseEntity.ok(medicoService.guardarMedico(medicoRq));
    }

    /**
     * Actualiza la información de un médico existente.
     */
    @Override
    public ResponseEntity<RespuestaRs> actualizarMedico(Integer id, MedicoRq medicoRq)
            throws BadRequestException {
        return ResponseEntity.ok(medicoService.actualizarMedico(id, medicoRq));
    }

    /**
     * Elimina un médico por su ID.
     */
    @Override
    public ResponseEntity<RespuestaRs> eliminarMedico(Integer id)
            throws BadRequestException {
        return ResponseEntity.ok(medicoService.eliminarMedico(id));
    }

    /**
     * Endpoint de prueba para verificar funcionamiento.
     */
    @Override
    public ResponseEntity<String> testMedico() {
        return ResponseEntity.ok("✅ Servicio de Médico funcionando correctamente");
    }
}
