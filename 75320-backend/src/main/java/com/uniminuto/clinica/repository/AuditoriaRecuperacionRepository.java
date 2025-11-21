package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.AuditoriaRecuperacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}

