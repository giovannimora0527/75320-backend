package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.AuditoriaLog;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

public interface AuditoriaApi {

    // 🔥 CORRECCIÓN IMPORTANTE:
    // Antes estaba: @GetMapping("/auditoria/buscar")
    // Eso generaba /auditoria/auditoria/buscar → 404
    // Ahora queda solo "/buscar", porque el controlador ya define "/auditoria"
    @GetMapping("/buscar")
    Page<AuditoriaLog> buscarAuditoria(
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
    );
}
