package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.AuditoriaLogin;
import com.uniminuto.clinica.entity.AuditoriaRecuperacion;
import com.uniminuto.clinica.model.LogAuditoriaRs;
import com.uniminuto.clinica.model.LogsAuditoriaResponseRs;
import com.uniminuto.clinica.repository.AuditoriaLoginRepository;
import com.uniminuto.clinica.repository.AuditoriaRecuperacionRepository;
import com.uniminuto.clinica.service.AuditoriaService;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de gestión de logs de auditoría.
 * Combina datos de las tablas de auditoría de login y recuperación de contraseña.
 * 
 * @author Sistema
 */
@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    @Autowired
    private AuditoriaLoginRepository auditoriaLoginRepository;

    @Autowired
    private AuditoriaRecuperacionRepository auditoriaRecuperacionRepository;

    @Override
    public LogsAuditoriaResponseRs listarLogs(
            Integer pagina,
            Integer tamanoPagina,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            String usuario,
            String tipoEvento
    ) throws BadRequestException {
        
        try {
            // Validar parámetros de paginación
            if (pagina == null || pagina < 1) {
                pagina = 1;
            }
            if (tamanoPagina == null || tamanoPagina < 1) {
                tamanoPagina = 20;
            }
            if (tamanoPagina > 100) {
                tamanoPagina = 100; // Limitar tamaño máximo de página
            }

            // Obtener todos los logs de login (con manejo de null)
            List<AuditoriaLogin> logsLogin = new ArrayList<>();
            try {
                logsLogin = auditoriaLoginRepository.findAll();
                if (logsLogin == null) {
                    logsLogin = new ArrayList<>();
                }
            } catch (Exception e) {
                System.err.println("Error al obtener logs de login: " + e.getMessage());
                logsLogin = new ArrayList<>();
            }
            
            // Obtener todos los logs de recuperación (con manejo de null)
            List<AuditoriaRecuperacion> logsRecuperacion = new ArrayList<>();
            try {
                logsRecuperacion = auditoriaRecuperacionRepository.findAll();
                if (logsRecuperacion == null) {
                    logsRecuperacion = new ArrayList<>();
                }
            } catch (Exception e) {
                System.err.println("Error al obtener logs de recuperación: " + e.getMessage());
                logsRecuperacion = new ArrayList<>();
            }

            // Convertir a LogAuditoriaRs y combinar
            List<LogAuditoriaRs> todosLosLogs = new ArrayList<>();
            
            // Convertir logs de login
            for (AuditoriaLogin log : logsLogin) {
                if (log != null) {
                    try {
                        LogAuditoriaRs logRs = convertirLoginALogRs(log);
                        if (logRs != null) {
                            todosLosLogs.add(logRs);
                        }
                    } catch (Exception e) {
                        System.err.println("Error al convertir log de login: " + e.getMessage());
                        // Continuar con el siguiente log
                    }
                }
            }
            
            // Convertir logs de recuperación
            for (AuditoriaRecuperacion log : logsRecuperacion) {
                if (log != null) {
                    try {
                        LogAuditoriaRs logRs = convertirRecuperacionALogRs(log);
                        if (logRs != null) {
                            todosLosLogs.add(logRs);
                        }
                    } catch (Exception e) {
                        System.err.println("Error al convertir log de recuperación: " + e.getMessage());
                        // Continuar con el siguiente log
                    }
                }
            }

            // Aplicar filtros
            List<LogAuditoriaRs> logsFiltrados = aplicarFiltros(
                    todosLosLogs,
                    fechaInicio,
                    fechaFin,
                    usuario,
                    tipoEvento
            );

            // Ordenar por fecha descendente (más recientes primero) - con manejo de null
            logsFiltrados.sort((a, b) -> {
                if (a == null || a.getFechaHora() == null) return 1;
                if (b == null || b.getFechaHora() == null) return -1;
                return b.getFechaHora().compareTo(a.getFechaHora());
            });

            // Calcular paginación
            Long total = (long) logsFiltrados.size();
            int totalPaginas = (int) Math.ceil((double) total / tamanoPagina);
            if (totalPaginas < 1) {
                totalPaginas = 1;
            }
            
            // Aplicar paginación
            int inicio = (pagina - 1) * tamanoPagina;
            int fin = Math.min(inicio + tamanoPagina, logsFiltrados.size());
            
            List<LogAuditoriaRs> logsPaginados = new ArrayList<>();
            if (inicio < logsFiltrados.size() && inicio >= 0) {
                logsPaginados = logsFiltrados.subList(inicio, fin);
            }

            // Construir respuesta
            return LogsAuditoriaResponseRs.builder()
                    .data(logsPaginados != null ? logsPaginados : new ArrayList<>())
                    .total(total != null ? total : 0L)
                    .pagina(pagina)
                    .tamanoPagina(tamanoPagina)
                    .totalPaginas(totalPaginas)
                    .build();
        } catch (Exception e) {
            System.err.println("Error en listarLogs: " + e.getMessage());
            e.printStackTrace();
            // Retornar respuesta vacía en caso de error
            return LogsAuditoriaResponseRs.builder()
                    .data(new ArrayList<>())
                    .total(0L)
                    .pagina(pagina != null && pagina > 0 ? pagina : 1)
                    .tamanoPagina(tamanoPagina != null && tamanoPagina > 0 ? tamanoPagina : 20)
                    .totalPaginas(0)
                    .build();
        }
    }

    /**
     * Convierte un registro de AuditoriaLogin a LogAuditoriaRs.
     */
    private LogAuditoriaRs convertirLoginALogRs(AuditoriaLogin log) {
        if (log == null) {
            return null;
        }
        
        String tipoEvento = log.isExitoso() ? "LOGIN_EXITOSO" : "LOGIN_FALLIDO";
        
        return LogAuditoriaRs.builder()
                .id(log.getId())
                .fechaHora(log.getFechaHora() != null ? log.getFechaHora() : java.time.LocalDateTime.now())
                .usuario(log.getUsernameIngresado() != null ? log.getUsernameIngresado() : "")
                .tipoEvento(tipoEvento)
                .descripcion(log.getDescripcion())
                .direccionIp(log.getIpOrigen())
                .metadata(null)
                .build();
    }

    /**
     * Convierte un registro de AuditoriaRecuperacion a LogAuditoriaRs.
     */
    private LogAuditoriaRs convertirRecuperacionALogRs(AuditoriaRecuperacion log) {
        if (log == null) {
            return null;
        }
        
        String tipoEvento = log.isExitoso() ? "RECUPERACION_PASSWORD" : "RECUPERACION_PASSWORD_FALLIDA";
        
        return LogAuditoriaRs.builder()
                .id(log.getId())
                .fechaHora(log.getFechaHora() != null ? log.getFechaHora() : java.time.LocalDateTime.now())
                .usuario(log.getUsernameIngresado() != null ? log.getUsernameIngresado() : "")
                .tipoEvento(tipoEvento)
                .descripcion(log.getDescripcion())
                .direccionIp(log.getIpOrigen())
                .metadata(null)
                .build();
    }

    /**
     * Aplica los filtros a la lista de logs.
     */
    private List<LogAuditoriaRs> aplicarFiltros(
            List<LogAuditoriaRs> logs,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            String usuario,
            String tipoEvento
    ) {
        if (logs == null || logs.isEmpty()) {
            return new ArrayList<>();
        }
        
        return logs.stream()
                .filter(log -> {
                    if (log == null) {
                        return false;
                    }
                    
                    // Filtro por fecha de inicio
                    if (fechaInicio != null && log.getFechaHora() != null) {
                        LocalDate fechaLog = log.getFechaHora().toLocalDate();
                        if (fechaLog.isBefore(fechaInicio)) {
                            return false;
                        }
                    }
                    
                    // Filtro por fecha de fin
                    if (fechaFin != null && log.getFechaHora() != null) {
                        LocalDate fechaLog = log.getFechaHora().toLocalDate();
                        if (fechaLog.isAfter(fechaFin)) {
                            return false;
                        }
                    }
                    
                    // Filtro por usuario
                    if (usuario != null && !usuario.trim().isEmpty()) {
                        if (log.getUsuario() == null || 
                            !log.getUsuario().toLowerCase().contains(usuario.toLowerCase().trim())) {
                            return false;
                        }
                    }
                    
                    // Filtro por tipo de evento
                    if (tipoEvento != null && !tipoEvento.trim().isEmpty()) {
                        if (log.getTipoEvento() == null || 
                            !log.getTipoEvento().equalsIgnoreCase(tipoEvento.trim())) {
                            return false;
                        }
                    }
                    
                    return true;
                })
                .collect(Collectors.toList());
    }
}

