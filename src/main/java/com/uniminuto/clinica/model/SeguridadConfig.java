package com.uniminuto.clinica.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuración parametrizable de seguridad.
 */
@Component
@Data
public class SeguridadConfig {

    @Value("${seguridad.intentos.maximos:3}")
    private Integer intentosMaximos;

    @Value("${seguridad.bloqueo.minutos:5}")
    private Integer minutosBloqueo;

    @Value("${seguridad.password.temporal.horas:24}")
    private Integer horasExpiracionPasswordTemporal;
}