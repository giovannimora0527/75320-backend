package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.AuditoriaLogin;
import com.uniminuto.clinica.entity.AuditoriaRecuperacion;
import com.uniminuto.clinica.model.AuditoriaRs;
import com.uniminuto.clinica.repository.AuditoriaLoginRepository;
import com.uniminuto.clinica.repository.AuditoriaRecuperacionRepository;
import com.uniminuto.clinica.service.AuditoriaService;
import com.uniminuto.clinica.utils.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de auditoría.
 * Proporciona métodos para consultar logs de seguridad con filtros y paginación.
 */
@Service
@RequiredArgsConstructor
public class AuditoriaServiceImpl implements AuditoriaService {
    
    private final AuditoriaLoginRepository auditoriaLoginRepository;
    private final AuditoriaRecuperacionRepository auditoriaRecuperacionRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Page<AuditoriaRs> listarAuditoriaLogin(
            String username,
            Boolean exitoso,
            LocalDateTime fechaDesde,
            LocalDateTime fechaHasta,
            Pageable pageable
    ) throws BadRequestException {
        try {
            // Validar fechas
            if (fechaDesde != null && fechaHasta != null && fechaDesde.isAfter(fechaHasta)) {
                throw new BadRequestException("La fecha desde no puede ser posterior a la fecha hasta");
            }
            
            Page<AuditoriaLogin> page;
            
            // Aplicar filtros
            if (username != null && !username.trim().isEmpty()) {
                if (exitoso != null) {
                    if (fechaDesde != null && fechaHasta != null) {
                        // Todos los filtros
                        page = auditoriaLoginRepository.findByUsernameIngresadoAndExitosoAndFechaHoraBetween(
                                username.trim(), exitoso, fechaDesde, fechaHasta, pageable);
                    } else if (fechaDesde != null) {
                        page = auditoriaLoginRepository.findByUsernameIngresadoAndExitosoAndFechaHoraAfter(
                                username.trim(), exitoso, fechaDesde, pageable);
                    } else {
                        page = auditoriaLoginRepository.findByUsernameIngresadoAndExitoso(
                                username.trim(), exitoso, pageable);
                    }
                } else {
                    if (fechaDesde != null && fechaHasta != null) {
                        page = auditoriaLoginRepository.findByUsernameIngresadoAndFechaHoraBetween(
                                username.trim(), fechaDesde, fechaHasta, pageable);
                    } else if (fechaDesde != null) {
                        page = auditoriaLoginRepository.findByUsernameIngresadoAndFechaHoraAfter(
                                username.trim(), fechaDesde, pageable);
                    } else {
                        page = auditoriaLoginRepository.findByUsernameIngresado(
                                username.trim(), pageable);
                    }
                }
            } else if (exitoso != null) {
                if (fechaDesde != null && fechaHasta != null) {
                    page = auditoriaLoginRepository.findByExitosoAndFechaHoraBetween(
                            exitoso, fechaDesde, fechaHasta, pageable);
                } else if (fechaDesde != null) {
                    page = auditoriaLoginRepository.findByExitosoAndFechaHoraAfter(
                            exitoso, fechaDesde, pageable);
                } else {
                    page = auditoriaLoginRepository.findByExitoso(exitoso, pageable);
                }
            } else if (fechaDesde != null && fechaHasta != null) {
                page = auditoriaLoginRepository.findByFechaHoraBetween(fechaDesde, fechaHasta, pageable);
            } else if (fechaDesde != null) {
                page = auditoriaLoginRepository.findByFechaHoraAfter(fechaDesde, pageable);
            } else {
                page = auditoriaLoginRepository.findAll(pageable);
            }
            
            // Convertir a DTO
            List<AuditoriaRs> content = page.getContent().stream()
                    .map(this::convertirLoginToRs)
                    .collect(Collectors.toList());
            
            return new PageImpl<>(content, pageable, page.getTotalElements());
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Error al consultar auditoría de login: " + e.getMessage());
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<AuditoriaRs> listarAuditoriaRecuperacion(
            String username,
            Boolean exitoso,
            LocalDateTime fechaDesde,
            LocalDateTime fechaHasta,
            Pageable pageable
    ) throws BadRequestException {
        try {
            // Validar fechas
            if (fechaDesde != null && fechaHasta != null && fechaDesde.isAfter(fechaHasta)) {
                throw new BadRequestException("La fecha desde no puede ser posterior a la fecha hasta");
            }
            
            Page<AuditoriaRecuperacion> page;
            
            // Aplicar filtros (similar a login pero con repositorio de recuperación)
            if (username != null && !username.trim().isEmpty()) {
                if (exitoso != null) {
                    if (fechaDesde != null && fechaHasta != null) {
                        page = auditoriaRecuperacionRepository.findByUsernameIngresadoAndExitosoAndFechaHoraBetween(
                                username.trim(), exitoso, fechaDesde, fechaHasta, pageable);
                    } else if (fechaDesde != null) {
                        page = auditoriaRecuperacionRepository.findByUsernameIngresadoAndExitosoAndFechaHoraAfter(
                                username.trim(), exitoso, fechaDesde, pageable);
                    } else {
                        page = auditoriaRecuperacionRepository.findByUsernameIngresadoAndExitoso(
                                username.trim(), exitoso, pageable);
                    }
                } else {
                    if (fechaDesde != null && fechaHasta != null) {
                        page = auditoriaRecuperacionRepository.findByUsernameIngresadoAndFechaHoraBetween(
                                username.trim(), fechaDesde, fechaHasta, pageable);
                    } else if (fechaDesde != null) {
                        page = auditoriaRecuperacionRepository.findByUsernameIngresadoAndFechaHoraAfter(
                                username.trim(), fechaDesde, pageable);
                    } else {
                        page = auditoriaRecuperacionRepository.findByUsernameIngresado(
                                username.trim(), pageable);
                    }
                }
            } else if (exitoso != null) {
                if (fechaDesde != null && fechaHasta != null) {
                    page = auditoriaRecuperacionRepository.findByExitosoAndFechaHoraBetween(
                            exitoso, fechaDesde, fechaHasta, pageable);
                } else if (fechaDesde != null) {
                    page = auditoriaRecuperacionRepository.findByExitosoAndFechaHoraAfter(
                            exitoso, fechaDesde, pageable);
                } else {
                    page = auditoriaRecuperacionRepository.findByExitoso(exitoso, pageable);
                }
            } else if (fechaDesde != null && fechaHasta != null) {
                page = auditoriaRecuperacionRepository.findByFechaHoraBetween(fechaDesde, fechaHasta, pageable);
            } else if (fechaDesde != null) {
                page = auditoriaRecuperacionRepository.findByFechaHoraAfter(fechaDesde, pageable);
            } else {
                page = auditoriaRecuperacionRepository.findAll(pageable);
            }
            
            // Convertir a DTO
            List<AuditoriaRs> content = page.getContent().stream()
                    .map(this::convertirRecuperacionToRs)
                    .collect(Collectors.toList());
            
            return new PageImpl<>(content, pageable, page.getTotalElements());
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Error al consultar auditoría de recuperación: " + e.getMessage());
        }
    }
    
    /**
     * Convierte una entidad AuditoriaLogin a DTO AuditoriaRs.
     */
    private AuditoriaRs convertirLoginToRs(AuditoriaLogin auditoria) {
        AuditoriaRs rs = new AuditoriaRs();
        rs.setId(auditoria.getId());
        rs.setUsernameIngresado(auditoria.getUsernameIngresado());
        rs.setFechaHora(auditoria.getFechaHora());
        rs.setExitoso(auditoria.isExitoso());
        rs.setDescripcion(auditoria.getDescripcion());
        rs.setIpOrigen(auditoria.getIpOrigen());
        rs.setUsuarioId(auditoria.getUsuarioId());
        rs.setTipo("LOGIN");
        return rs;
    }
    
    /**
     * Convierte una entidad AuditoriaRecuperacion a DTO AuditoriaRs.
     */
    private AuditoriaRs convertirRecuperacionToRs(AuditoriaRecuperacion auditoria) {
        AuditoriaRs rs = new AuditoriaRs();
        rs.setId(auditoria.getId());
        rs.setUsernameIngresado(auditoria.getUsernameIngresado());
        rs.setFechaHora(auditoria.getFechaHora());
        rs.setExitoso(auditoria.isExitoso());
        rs.setDescripcion(auditoria.getDescripcion());
        rs.setIpOrigen(auditoria.getIpOrigen());
        rs.setEmailUsuario(auditoria.getEmailUsuario());
        rs.setTipo("RECUPERACION");
        return rs;
    }
}

