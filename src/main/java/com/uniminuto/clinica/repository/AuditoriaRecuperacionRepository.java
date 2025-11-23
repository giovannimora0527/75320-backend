package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.AuditoriaRecuperacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio para la entidad de auditoría de recuperación de contraseña.
 */
@Repository
public interface AuditoriaRecuperacionRepository extends JpaRepository<AuditoriaRecuperacion, Long> {
    
    /**
     * Busca todos los registros de auditoría por nombre de usuario.
     * @param username Nombre de usuario a buscar.
     * @return Lista de registros de auditoría.
     */
    List<AuditoriaRecuperacion> findByUsernameIngresadoOrderByFechaHoraDesc(String username);
    
    // Métodos para consultas con paginación y filtros
    Page<AuditoriaRecuperacion> findByUsernameIngresado(String username, Pageable pageable);
    
    Page<AuditoriaRecuperacion> findByExitoso(Boolean exitoso, Pageable pageable);
    
    Page<AuditoriaRecuperacion> findByUsernameIngresadoAndExitoso(String username, Boolean exitoso, Pageable pageable);
    
    Page<AuditoriaRecuperacion> findByFechaHoraBetween(LocalDateTime fechaDesde, LocalDateTime fechaHasta, Pageable pageable);
    
    Page<AuditoriaRecuperacion> findByFechaHoraAfter(LocalDateTime fechaDesde, Pageable pageable);
    
    Page<AuditoriaRecuperacion> findByExitosoAndFechaHoraBetween(Boolean exitoso, LocalDateTime fechaDesde, LocalDateTime fechaHasta, Pageable pageable);
    
    Page<AuditoriaRecuperacion> findByExitosoAndFechaHoraAfter(Boolean exitoso, LocalDateTime fechaDesde, Pageable pageable);
    
    Page<AuditoriaRecuperacion> findByUsernameIngresadoAndFechaHoraBetween(String username, LocalDateTime fechaDesde, LocalDateTime fechaHasta, Pageable pageable);
    
    Page<AuditoriaRecuperacion> findByUsernameIngresadoAndFechaHoraAfter(String username, LocalDateTime fechaDesde, Pageable pageable);
    
    Page<AuditoriaRecuperacion> findByUsernameIngresadoAndExitosoAndFechaHoraBetween(String username, Boolean exitoso, LocalDateTime fechaDesde, LocalDateTime fechaHasta, Pageable pageable);
    
    Page<AuditoriaRecuperacion> findByUsernameIngresadoAndExitosoAndFechaHoraAfter(String username, Boolean exitoso, LocalDateTime fechaDesde, Pageable pageable);
}
