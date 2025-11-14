package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.AuditoriaLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
    long countByUsernameIngresadoAndExitosoFalseAndFechaHoraAfter(String username, java.time.LocalDateTime fechaDesde);
}

