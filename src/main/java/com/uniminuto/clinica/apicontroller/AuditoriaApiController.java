package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.AuditoriaApi;
import com.uniminuto.clinica.model.AuditoriaFiltroRq;
import com.uniminuto.clinica.model.AuditoriaListaRs;
import com.uniminuto.clinica.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST que implementa la API de Auditoría.
 * 
 * Endpoints:
 *  - POST /auditoria/listar → Lista registros de auditoría con filtros y paginación.
 * 
 * Permite consultar logs de login y recuperación de contraseña.
 * 
 * @author Sistema
 */
@RestController
@CrossOrigin(origins = "*")
public class AuditoriaApiController implements AuditoriaApi {
    
    @Autowired
    private AuditoriaService auditoriaService;
    
    /**
     * Lista registros de auditoría aplicando los filtros especificados.
     * 
     * @param filtro Filtros de búsqueda (fecha, usuario, tipo de evento, etc.) y paginación
     * @return Lista paginada de registros de auditoría
     */
    @Override
    @PostMapping("/auditoria/listar")
    public ResponseEntity<AuditoriaListaRs> listarAuditoria(@RequestBody AuditoriaFiltroRq filtro) {
        // Si no se especifica filtro, crear uno por defecto
        if (filtro == null) {
            filtro = new AuditoriaFiltroRq();
        }
        
        AuditoriaListaRs resultado = auditoriaService.listarAuditoria(filtro);
        return ResponseEntity.ok(resultado);
    }
}

