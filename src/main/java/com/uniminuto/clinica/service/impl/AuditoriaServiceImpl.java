package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.AuditoriaLogin;
import com.uniminuto.clinica.entity.AuditoriaRecuperacion;
import com.uniminuto.clinica.model.AuditoriaFiltroRq;
import com.uniminuto.clinica.model.AuditoriaListaRs;
import com.uniminuto.clinica.model.AuditoriaRs;
import com.uniminuto.clinica.repository.AuditoriaLoginRepository;
import com.uniminuto.clinica.repository.AuditoriaRecuperacionRepository;
import com.uniminuto.clinica.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de auditoría.
 * Combina registros de login y recuperación de contraseña.
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
    public AuditoriaListaRs listarAuditoria(AuditoriaFiltroRq filtro) {
        List<AuditoriaRs> todosLosRegistros = new ArrayList<>();
        
        // Obtener registros de login si el filtro lo permite
        if (filtro.getTipoEvento() == null || "login".equalsIgnoreCase(filtro.getTipoEvento())) {
            List<AuditoriaLogin> loginRegistros = filtrarLogin(filtro);
            todosLosRegistros.addAll(convertirLoginARs(loginRegistros));
        }
        
        // Obtener registros de recuperación si el filtro lo permite
        if (filtro.getTipoEvento() == null || "recuperacion".equalsIgnoreCase(filtro.getTipoEvento())) {
            List<AuditoriaRecuperacion> recuperacionRegistros = filtrarRecuperacion(filtro);
            todosLosRegistros.addAll(convertirRecuperacionARs(recuperacionRegistros));
        }
        
        // Ordenar por fecha descendente
        todosLosRegistros.sort((a, b) -> b.getFechaHora().compareTo(a.getFechaHora()));
        
        // Aplicar paginación manual
        int pagina = filtro.getPagina() != null ? filtro.getPagina() : 0;
        int tamano = filtro.getTamano() != null ? filtro.getTamano() : 10;
        int inicio = pagina * tamano;
        int fin = Math.min(inicio + tamano, todosLosRegistros.size());
        
        List<AuditoriaRs> registrosPaginados = todosLosRegistros.subList(
            Math.min(inicio, todosLosRegistros.size()),
            fin
        );
        
        // Construir respuesta
        AuditoriaListaRs respuesta = new AuditoriaListaRs();
        respuesta.setRegistros(registrosPaginados);
        respuesta.setTotalRegistros((long) todosLosRegistros.size());
        respuesta.setPaginaActual(pagina);
        respuesta.setTamanoPagina(tamano);
        respuesta.setTotalPaginas((int) Math.ceil((double) todosLosRegistros.size() / tamano));
        
        return respuesta;
    }
    
    /**
     * Filtra registros de login según los criterios especificados.
     */
    private List<AuditoriaLogin> filtrarLogin(AuditoriaFiltroRq filtro) {
        List<AuditoriaLogin> todos = auditoriaLoginRepository.findAll(
            Sort.by(Sort.Direction.DESC, "fechaHora")
        );
        
        return todos.stream()
            .filter(reg -> {
                // Filtro por username
                if (filtro.getUsername() != null && !filtro.getUsername().trim().isEmpty()) {
                    if (!reg.getUsernameIngresado().toLowerCase()
                        .contains(filtro.getUsername().toLowerCase().trim())) {
                        return false;
                    }
                }
                
                // Filtro por fecha
                if (filtro.getFechaDesde() != null) {
                    if (reg.getFechaHora().isBefore(filtro.getFechaDesde())) {
                        return false;
                    }
                }
                if (filtro.getFechaHasta() != null) {
                    if (reg.getFechaHora().isAfter(filtro.getFechaHasta())) {
                        return false;
                    }
                }
                
                // Filtro por exitoso
                if (filtro.getExitoso() != null) {
                    if (reg.isExitoso() != filtro.getExitoso()) {
                        return false;
                    }
                }
                
                return true;
            })
            .collect(Collectors.toList());
    }
    
    /**
     * Filtra registros de recuperación según los criterios especificados.
     */
    private List<AuditoriaRecuperacion> filtrarRecuperacion(AuditoriaFiltroRq filtro) {
        List<AuditoriaRecuperacion> todos = auditoriaRecuperacionRepository.findAll(
            Sort.by(Sort.Direction.DESC, "fechaHora")
        );
        
        return todos.stream()
            .filter(reg -> {
                // Filtro por username
                if (filtro.getUsername() != null && !filtro.getUsername().trim().isEmpty()) {
                    if (!reg.getUsernameIngresado().toLowerCase()
                        .contains(filtro.getUsername().toLowerCase().trim())) {
                        return false;
                    }
                }
                
                // Filtro por fecha
                if (filtro.getFechaDesde() != null) {
                    if (reg.getFechaHora().isBefore(filtro.getFechaDesde())) {
                        return false;
                    }
                }
                if (filtro.getFechaHasta() != null) {
                    if (reg.getFechaHora().isAfter(filtro.getFechaHasta())) {
                        return false;
                    }
                }
                
                // Filtro por exitoso
                if (filtro.getExitoso() != null) {
                    if (reg.isExitoso() != filtro.getExitoso()) {
                        return false;
                    }
                }
                
                return true;
            })
            .collect(Collectors.toList());
    }
    
    /**
     * Convierte entidades de AuditoriaLogin a AuditoriaRs.
     */
    private List<AuditoriaRs> convertirLoginARs(List<AuditoriaLogin> loginRegistros) {
        return loginRegistros.stream().map(reg -> {
            AuditoriaRs rs = new AuditoriaRs();
            rs.setId(reg.getId());
            rs.setUsernameIngresado(reg.getUsernameIngresado());
            rs.setFechaHora(reg.getFechaHora());
            rs.setExitoso(reg.isExitoso());
            rs.setDescripcion(reg.getDescripcion());
            rs.setIpOrigen(reg.getIpOrigen());
            rs.setUsuarioId(reg.getUsuarioId());
            rs.setTipoEvento("LOGIN");
            return rs;
        }).collect(Collectors.toList());
    }
    
    /**
     * Convierte entidades de AuditoriaRecuperacion a AuditoriaRs.
     */
    private List<AuditoriaRs> convertirRecuperacionARs(List<AuditoriaRecuperacion> recuperacionRegistros) {
        return recuperacionRegistros.stream().map(reg -> {
            AuditoriaRs rs = new AuditoriaRs();
            rs.setId(reg.getId());
            rs.setUsernameIngresado(reg.getUsernameIngresado());
            rs.setFechaHora(reg.getFechaHora());
            rs.setExitoso(reg.isExitoso());
            rs.setDescripcion(reg.getDescripcion());
            rs.setIpOrigen(reg.getIpOrigen());
            rs.setEmailUsuario(reg.getEmailUsuario());
            rs.setTipoEvento("RECUPERACION");
            return rs;
        }).collect(Collectors.toList());
    }
}

