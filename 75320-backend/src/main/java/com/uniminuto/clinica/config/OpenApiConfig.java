package com.uniminuto.clinica.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de OpenAPI/Swagger para documentación automática de la API REST.
 * 
 * Una vez iniciada la aplicación, la documentación interactiva estará disponible en:
 * http://localhost:8000/clinica/v1/swagger-ui/index.html
 * 
 * El descriptor OpenAPI en formato JSON estará en:
 * http://localhost:8000/clinica/v1/v3/api-docs
 * 
 * @author Sistema
 */
@Configuration
public class OpenApiConfig {

    /**
     * Construye el descriptor OpenAPI con metadatos del proyecto.
     *
     * @return instancia de {@link OpenAPI} configurada con información del proyecto.
     */
    @Bean
    public OpenAPI clinicaOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Clínica Uniminuto API")
                        .description("API REST para la gestión integral de una clínica universitaria. " +
                                "Incluye módulos de autenticación con JWT, gestión de usuarios, pacientes, " +
                                "citas médicas, recetas y medicamentos. El sistema implementa control de " +
                                "intentos de login, bloqueo temporal de usuarios, recuperación de contraseñas " +
                                "y auditoría completa de operaciones de seguridad.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Equipo 827766 - Anyi Natkary Gómez")
                                .email("anyig8652@gmail.com"))
                        .license(new License()
                                .name("GPL-3.0")
                                .url("https://www.gnu.org/licenses/gpl-3.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio del proyecto")
                        .url("https://github.com/giovannimora0527/75320-backend"));
    }
}

