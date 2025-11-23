package com.uniminuto.clinica.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Swagger/OpenAPI para la documentación de la API REST.
 * Proporciona una interfaz interactiva para probar los endpoints.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configura la información de la API y la autenticación JWT.
     * 
     * @return Configuración de OpenAPI.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Clínica - Sistema de Gestión Médica")
                        .version("1.0.0")
                        .description("API REST para el sistema de gestión de clínica médica. " +
                                "Incluye gestión de pacientes, médicos, citas, medicamentos, " +
                                "fórmulas médicas y auditoría de seguridad.")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("programacionwebuniminuto1@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()));
    }

    /**
     * Crea el esquema de seguridad para JWT.
     * 
     * @return Esquema de seguridad JWT.
     */
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer")
                .description("Ingrese el token JWT obtenido del endpoint /auth/login. " +
                        "Formato: Bearer {token}");
    }
}



