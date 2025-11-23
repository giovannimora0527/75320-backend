/**
 * Clase de configuración para los parámetros de seguridad relacionados con el login.
 * Define las propiedades configurables para el manejo de intentos fallidos, bloqueos y ventanas de tiempo
 * en el proceso de autenticación del sistema clínico.
 *
 * @author Edwin Morales
 * @author Nahum Dominguez
 * @author Emily Aldana
 * @author Julian Amaya
 * @author Sebastian Paez
 */
package com.uniminuto.clinica.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "seguridad.login")
public class LoginConfig {

    /**
     * Número máximo de intentos fallidos de login permitidos antes de bloquear el usuario.
     * Valor por defecto: 3 intentos
     */
    private int maxIntentosFallidos = 3;

    /**
     * Tiempo de bloqueo del usuario en minutos después de exceder el máximo de intentos fallidos.
     * Valor por defecto: 5 minutos
     */
    private int minutosBloqueo = 5;

    /**
     * Ventana de tiempo en minutos durante la cual se cuentan los intentos fallidos consecutivos.
     * Valor por defecto: 5 minutos
     */
    private int ventanaTiempoMinutos = 5;
}