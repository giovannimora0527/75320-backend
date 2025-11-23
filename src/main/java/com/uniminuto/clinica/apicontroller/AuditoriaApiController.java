package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.AuditoriaApi;
import com.uniminuto.clinica.model.AuditoriaRs;
import com.uniminuto.clinica.service.AuditoriaService;
import com.uniminuto.clinica.utils.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Controlador REST para la gestión de auditoría.
 * Expone endpoints para consultar logs de seguridad con filtros y paginación.
 */
@RestController
@RequiredArgsConstructor
public class AuditoriaApiController implements AuditoriaApi {
    
    private final AuditoriaService auditoriaService;
    
    @Override
    public ResponseEntity<Page<AuditoriaRs>> listarAuditoriaLogin(
            String username,
            Boolean exitoso,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaDesde,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHasta,
            Pageable pageable
    ) throws BadRequestException {
        // Si no se especifica orden, ordenar por fecha descendente
        if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(Sort.Direction.DESC, "fechaHora")
            );
        }
        
        Page<AuditoriaRs> resultado = auditoriaService.listarAuditoriaLogin(
                username, exitoso, fechaDesde, fechaHasta, pageable
        );
        
        return ResponseEntity.ok(resultado);
    }
    
    @Override
    public ResponseEntity<Page<AuditoriaRs>> listarAuditoriaRecuperacion(
            String username,
            Boolean exitoso,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaDesde,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHasta,
            Pageable pageable
    ) throws BadRequestException {
        // Si no se especifica orden, ordenar por fecha descendente
        if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(Sort.Direction.DESC, "fechaHora")
            );
        }
        
        Page<AuditoriaRs> resultado = auditoriaService.listarAuditoriaRecuperacion(
                username, exitoso, fechaDesde, fechaHasta, pageable
        );
        
        return ResponseEntity.ok(resultado);
    }
}

