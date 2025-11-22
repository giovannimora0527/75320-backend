package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.AuditoriaLog;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface AuditoriaService {

    void registrarIntentoFallidoLogin(String username, String descripcion, String ip);

    void registrarRecuperacionFallida(String username, String descripcion, String ip);

    void registrarBloqueo(String username, String descripcion, String ip);

    void registrarRecuperacionExitosa(String username, String ip);

    Page<AuditoriaLog> buscarAuditoria(
            String usuario,
            String tipo,
            LocalDateTime fechaDesde,
            LocalDateTime fechaHasta,
            int page,
            int size);
}
