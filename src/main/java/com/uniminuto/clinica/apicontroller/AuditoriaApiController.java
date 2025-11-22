package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.AuditoriaLog;
import com.uniminuto.clinica.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auditoria")
@CrossOrigin(origins = "*")
public class AuditoriaApiController implements AuditoriaApi {

    @Autowired
    private AuditoriaService auditoriaService;

    @Override
    @GetMapping("/buscar")
    public Page<AuditoriaLog> buscarAuditoria(
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime fechaDesde,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime fechaHasta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return auditoriaService.buscarAuditoria(
                usuario, tipo, fechaDesde, fechaHasta, page, size
        );
    }
}
