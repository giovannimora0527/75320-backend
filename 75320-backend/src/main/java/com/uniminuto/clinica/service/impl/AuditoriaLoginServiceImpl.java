package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.model.AuditoriaLoginRq;
import com.uniminuto.clinica.model.AuditoriaLoginRs;
import com.uniminuto.clinica.repository.AuditoriaLoginRepository;
import com.uniminuto.clinica.service.AuditoriaLoginService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Implementación del servicio de auditoría de login.
 * Proporciona consultas paginadas con filtros por usuario, fecha y tipo de evento.
 * 
 * @author Sistema
 */
@Service
public class AuditoriaLoginServiceImpl implements AuditoriaLoginService {
    
    private final AuditoriaLoginRepository auditoriaLoginRepository;
    
    public AuditoriaLoginServiceImpl(AuditoriaLoginRepository auditoriaLoginRepository) {
        this.auditoriaLoginRepository = auditoriaLoginRepository;
    }
    
    @Override
    public AuditoriaLoginRs consultarAuditoria(AuditoriaLoginRq request) {
        // Validar y normalizar parámetros de paginación
        int pagina = request.getPagina() != null && request.getPagina() >= 0 ? request.getPagina() : 0;
        int tamano = request.getTamano() != null && request.getTamano() > 0 && request.getTamano() <= 100 
                    ? request.getTamano() : 20;
        
        // Crear objeto de ordenamiento
        Sort.Direction direccion = "ASC".equalsIgnoreCase(request.getDireccion()) 
                                   ? Sort.Direction.ASC 
                                   : Sort.Direction.DESC;
        
        String campoOrden = request.getOrdenarPor() != null && !request.getOrdenarPor().isEmpty()
                           ? request.getOrdenarPor()
                           : "fechaHora";
        
        Sort sort = Sort.by(direccion, campoOrden);
        Pageable pageable = PageRequest.of(pagina, tamano, sort);
        
        // Normalizar filtros
        String username = request.getUsername() != null && !request.getUsername().trim().isEmpty()
                         ? request.getUsername().trim()
                         : null;
        
        LocalDateTime fechaDesde = request.getFechaDesde();
        LocalDateTime fechaHasta = request.getFechaHasta();
        
        // Si se especifica fechaHasta, agregar un día completo para incluir todo el día
        if (fechaHasta != null) {
            fechaHasta = fechaHasta.plusDays(1).minusSeconds(1);
        }
        
        Boolean exitoso = request.getExitoso();
        
        // Ejecutar consulta paginada
        Page<com.uniminuto.clinica.entity.AuditoriaLogin> paginaResultado = 
            auditoriaLoginRepository.buscarConFiltros(username, fechaDesde, fechaHasta, exitoso, pageable);
        
        // Construir respuesta
        AuditoriaLoginRs respuesta = new AuditoriaLoginRs();
        respuesta.setContenido(paginaResultado.getContent());
        respuesta.setTotalElementos(paginaResultado.getTotalElements());
        respuesta.setTotalPaginas(paginaResultado.getTotalPages());
        respuesta.setPaginaActual(paginaResultado.getNumber());
        respuesta.setTamanoPagina(paginaResultado.getSize());
        respuesta.setTieneSiguiente(paginaResultado.hasNext());
        respuesta.setTieneAnterior(paginaResultado.hasPrevious());
        
        return respuesta;
    }
}

