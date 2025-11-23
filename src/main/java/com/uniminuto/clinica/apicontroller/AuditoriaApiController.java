/**
 * Controlador REST que implementa la interfaz de auditoría para el sistema clínico.
 * Gestiona las operaciones relacionadas con la consulta de registros de auditoría
 * de recuperación de contraseñas.
 *
 * @author Edwin Morales
 * @author Nahum Dominguez
 * @author Emily Aldana
 * @author Julian Amaya
 * @author Sebastian Paez
 */
package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.AuditoriaApi;
import com.uniminuto.clinica.entity.RecuperarPasswordAuditoria;
import com.uniminuto.clinica.service.RecuperarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuditoriaApiController implements AuditoriaApi {

    @Autowired
    private RecuperarService recuperarService;

    /**
     * Implementación del endpoint para listar todos los registros de auditoría
     * de recuperación de contraseñas. Recupera la información completa del historial
     * de solicitudes de recuperación desde el servicio correspondiente.
     *
     * @return ResponseEntity con una lista de todos los registros de auditoría
     *         encontrados en el sistema. Retorna estado HTTP 200 con los datos
     *         o una lista vacía si no existen registros.
     */
    @Override
    public ResponseEntity<List<RecuperarPasswordAuditoria>> listarTodosLosRegistros() {
        return ResponseEntity.ok(recuperarService.listarTodosLosRegistros());
    }
}