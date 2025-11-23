/**
 * Interfaz de servicio para la gestión de recuperación de contraseñas y auditoría.
 * Define los métodos necesarios para procesar solicitudes de recuperación de contraseñas
 * y consultar registros de auditoría en el sistema clínico.
 *
 * @author Edwin Morales
 * @author Nahum Dominguez
 * @author Emily Aldana
 * @author Julian Amaya
 * @author Sebastian Paez
 */
package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.RecuperarPasswordAuditoria;
import com.uniminuto.clinica.model.RecuperarPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;

import java.util.List;

public interface RecuperarService {

    /**
     * Procesa la solicitud de recuperación de contraseña para un usuario.
     * Incluye validación del usuario, generación de contraseña temporal,
     * envío de correo electrónico y registro de auditoría.
     *
     * @param request Solicitud de recuperación que contiene el nombre de usuario
     * @return Respuesta con el resultado del proceso de recuperación
     */
    RespuestaRs recuperarPassword(RecuperarPasswordRq request);

    /**
     * Recupera todos los registros de auditoría de recuperación de contraseñas.
     * Proporciona acceso al historial completo de solicitudes de recuperación
     * para fines de monitoreo y análisis de seguridad.
     *
     * @return Lista de todos los registros de auditoría almacenados en el sistema
     */
    List<RecuperarPasswordAuditoria> listarTodosLosRegistros();
}