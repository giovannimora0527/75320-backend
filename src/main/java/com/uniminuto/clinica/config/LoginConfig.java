package com.uniminuto.clinica.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "seguridad.login")
public class LoginConfig {
    private int maxIntentosFallidos = 3;
    private int minutosBloqueo = 5;
    private int ventanaTiempoMinutos = 5;
}