package com.uniminuto.clinica.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuración de OpenAPI/Swagger para documentación de la API REST.
 * 
 * @author lmora
 */
@Configuration
public class OpenApiConfig {

    @Value("${server.servlet.contextPath}")
    private String contextPath;

    @Value("${server.port}")
    private String serverPort;

    /**
     * Configuración personalizada de OpenAPI.
     * 
     * @return Objeto OpenAPI configurado
     */
    @Bean
    public OpenAPI customOpenAPI() {
        // Definir el servidor
        Server server = new Server();
        server.setUrl("http://localhost:" + serverPort + contextPath);
        server.setDescription("Servidor de desarrollo");

        // Definir el esquema de seguridad JWT
        final String securitySchemeName = "Bearer Authentication";
        
        return new OpenAPI()
                .servers(List.of(server))
                .info(new Info()
                        .title("API REST Clínica - UNIMINUTO")
                        .description("Documentación de la API REST para el sistema de gestión de clínica. "
                                + "Esta API proporciona endpoints para la gestión de pacientes, médicos, "
                                + "citas médicas, historias clínicas y autenticación de usuarios.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("programacionwebuniminuto1@gmail.com")
                                .url("https://uniminuto.edu"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Ingrese el token JWT en el formato: Bearer {token}")));
    }
}