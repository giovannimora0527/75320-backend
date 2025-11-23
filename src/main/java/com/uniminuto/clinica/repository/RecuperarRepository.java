/**
 * Repositorio para la gestión de operaciones de base de datos relacionadas con la auditoría de recuperación de contraseñas.
 * Proporciona métodos para acceder y consultar los registros de auditoría almacenados en la base de datos.
 *
 * @author Edwin Morales
 * @author Nahum Dominguez
 * @author Emily Aldana
 * @author Julian Amaya
 * @author Sebastian Paez
 */
package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.RecuperarPasswordAuditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RecuperarRepository extends JpaRepository<RecuperarPasswordAuditoria, Long> {

    /**
     * Cuenta la cantidad de intentos fallidos de login recientes para un usuario específico.
     * Consulta los registros de auditoría donde el tipo es 'LOGIN', la descripción indica un intento fallido
     * y la fecha de transacción está dentro del rango de tiempo especificado.
     *
     * @param username Nombre de usuario para el cual se contarán los intentos fallidos
     * @param fechaLimite Fecha límite a partir de la cual se consideran los intentos como recientes
     * @return Número total de intentos fallidos de login para el usuario dentro del periodo especificado
     */
    @Query("SELECT COUNT(a) FROM RecuperarPasswordAuditoria a WHERE a.username = :username AND a.tipoAuditoria = 'LOGIN' AND a.description LIKE 'Intento fallido%' AND a.transaccionFecha > :fechaLimite")
    long countIntentosFallidosLoginRecientes(@Param("username") String username, @Param("fechaLimite") LocalDateTime fechaLimite);
}