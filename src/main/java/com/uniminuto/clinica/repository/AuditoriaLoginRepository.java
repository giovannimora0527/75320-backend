package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.AuditoriaLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio para la entidad de auditoría de inicio de sesión.
 */
@Repository
public interface AuditoriaLoginRepository extends JpaRepository<AuditoriaLogin, Long> {
    
    /**
     * Busca todos los registros de auditoría por nombre de usuario, ordenados por fecha descendente.
     * @param username Nombre de usuario a buscar.
     * @return Lista de registros de auditoría.
     */
    List<AuditoriaLogin> findByUsernameIngresadoOrderByFechaHoraDesc(String username);
    
    /**
     * Cuenta los intentos fallidos consecutivos recientes para un usuario.
     * @param username Nombre de usuario.
     * @param fechaDesde Fecha desde la cual contar intentos.
     * @return Número de intentos fallidos consecutivos.
     */
    long countByUsernameIngresadoAndExitosoFalseAndFechaHoraAfter(String username, LocalDateTime fechaDesde);
    
    // Métodos para consultas con paginación y filtros
    Page<AuditoriaLogin> findByUsernameIngresado(String username, Pageable pageable);
    
    Page<AuditoriaLogin> findByExitoso(Boolean exitoso, Pageable pageable);
    
    Page<AuditoriaLogin> findByUsernameIngresadoAndExitoso(String username, Boolean exitoso, Pageable pageable);
    
    Page<AuditoriaLogin> findByFechaHoraBetween(LocalDateTime fechaDesde, LocalDateTime fechaHasta, Pageable pageable);
    
    Page<AuditoriaLogin> findByFechaHoraAfter(LocalDateTime fechaDesde, Pageable pageable);
    
    Page<AuditoriaLogin> findByExitosoAndFechaHoraBetween(Boolean exitoso, LocalDateTime fechaDesde, LocalDateTime fechaHasta, Pageable pageable);
    
    Page<AuditoriaLogin> findByExitosoAndFechaHoraAfter(Boolean exitoso, LocalDateTime fechaDesde, Pageable pageable);
    
    Page<AuditoriaLogin> findByUsernameIngresadoAndFechaHoraBetween(String username, LocalDateTime fechaDesde, LocalDateTime fechaHasta, Pageable pageable);
    
    Page<AuditoriaLogin> findByUsernameIngresadoAndFechaHoraAfter(String username, LocalDateTime fechaDesde, Pageable pageable);
    
    Page<AuditoriaLogin> findByUsernameIngresadoAndExitosoAndFechaHoraBetween(String username, Boolean exitoso, LocalDateTime fechaDesde, LocalDateTime fechaHasta, Pageable pageable);
    
    Page<AuditoriaLogin> findByUsernameIngresadoAndExitosoAndFechaHoraAfter(String username, Boolean exitoso, LocalDateTime fechaDesde, Pageable pageable);
}
