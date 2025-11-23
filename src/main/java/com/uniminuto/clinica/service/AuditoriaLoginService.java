/**
 * Interfaz de servicio para la gestión de auditoría de login.
 * Define los métodos necesarios para registrar intentos de acceso, verificar bloqueos
 * y reiniciar contadores de intentos fallidos en el sistema clínico.
 *
 * @author Edwin Morales
 * @author Nahum Dominguez
 * @author Emily Aldana
 * @author Julian Amaya
 * @author Sebastian Paez
 */
package com.uniminuto.clinica.service;

public interface AuditoriaLoginService {

    /**
     * Registra un intento de login en el sistema de auditoría.
     * Almacena información sobre el intento de acceso incluyendo el resultado
     * y detalles relevantes para seguimiento de seguridad.
     *
     * @param username Nombre de usuario que intentó el acceso
     * @param ipAddress Dirección IP desde donde se realizó el intento
     * @param exitoso Indica si el intento de login fue exitoso o fallido
     * @param descripcion Descripción detallada del resultado del intento
     */
    void registrarIntentoLogin(String username, String ipAddress, boolean exitoso, String descripcion);

    /**
     * Verifica si un usuario se encuentra actualmente bloqueado por exceder
     * el número máximo de intentos fallidos de login en un periodo de tiempo.
     *
     * @param username Nombre de usuario a verificar
     * @return true si el usuario está bloqueado, false en caso contrario
     */
    boolean usuarioEstaBloqueado(String username);

    /**
     * Reinicia el contador de intentos fallidos para un usuario específico.
     * Este metodo se utiliza cuando un usuario realiza un login exitoso o cuando
     * se desbloquea manualmente su cuenta.
     *
     * @param username Nombre de usuario al que se le reiniciarán los intentos fallidos
     */
    void reiniciarIntentosFallidos(String username);
}