package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.AuditoriaLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio para la entidad de auditoría de inicio de sesión.
 * 
 * @author Sistema
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
    
    /**
     * Consulta paginada con filtros dinámicos.
     * 
     * @param username Filtro por nombre de usuario (opcional, puede ser null).
     * @param fechaDesde Fecha de inicio del rango (opcional, puede ser null).
     * @param fechaHasta Fecha de fin del rango (opcional, puede ser null).
     * @param exitoso Filtro por tipo de evento (opcional, puede ser null).
     * @param pageable Objeto de paginación.
     * @return Página de registros de auditoría.
     */
    @Query("SELECT a FROM AuditoriaLogin a WHERE " +
           "(:username IS NULL OR LOWER(a.usernameIngresado) LIKE LOWER(CONCAT('%', :username, '%'))) AND " +
           "(:fechaDesde IS NULL OR a.fechaHora >= :fechaDesde) AND " +
           "(:fechaHasta IS NULL OR a.fechaHora <= :fechaHasta) AND " +
           "(:exitoso IS NULL OR a.exitoso = :exitoso) " +
           "ORDER BY a.fechaHora DESC")
    Page<AuditoriaLogin> buscarConFiltros(
        @Param("username") String username,
        @Param("fechaDesde") LocalDateTime fechaDesde,
        @Param("fechaHasta") LocalDateTime fechaHasta,
        @Param("exitoso") Boolean exitoso,
        Pageable pageable
    );
}

