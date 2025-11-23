package com.uniminuto.clinica.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Configuración de seguridad para la aplicación.
 * Controla las rutas públicas, el uso de JWT y las políticas CORS.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    /**
     * Define las reglas de seguridad HTTP.
     *
     * @param http configuración HTTP de Spring Security.
     * @return cadena de filtros de seguridad.
     * @throws Exception en caso de error de configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(requests -> requests
                // Endpoints públicos
                .requestMatchers(
                    "/auth/login",
                    "/auth/recuperar-contrasena",
                    "/test/**",
                    "/email/test/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
                // Endpoints de auditoría requieren autenticación (solo ADMIN)
                .requestMatchers("/auditoria/**").hasRole("ADMIN")
                // El resto requieren autenticación JWT
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
            .logout(logout -> logout.permitAll());

        return http.build();
    }

    /**
     * Configuración CORS para permitir peticiones desde orígenes válidos.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
            "http://localhost:4200",
            "http://localhost:8080",
            "http://127.0.0.1:4200",
            "http://127.0.0.1:8080",
            "http://10.0.5.50:4200",
            "http://10.0.5.50:8080"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
