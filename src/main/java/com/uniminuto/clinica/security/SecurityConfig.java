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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests((requests) -> requests

                        // ==== SWAGGER PERMITIDO ====
                        .antMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()

                        // ==== ENDPOINTS PÚBLICOS QUE TÚ DEFINISTE ====
                        .antMatchers("/auth/login", "/auth/recuperar-contrasena").permitAll()

                        .antMatchers("/auth/**").permitAll()
                        .antMatchers("/cita/**").permitAll()
                        .antMatchers("/clinica/**").permitAll()
                        .antMatchers("/email/**").permitAll()
                        .antMatchers("/especializacion/**").permitAll()
                        .antMatchers("/medicamento/**").permitAll()
                        .antMatchers("/medico/**").permitAll()
                        .antMatchers("/paciente/**").permitAll()
                        .antMatchers("/receta/**").permitAll()
                        .antMatchers("/usuario/**").permitAll()

                        // ==== CUALQUIER OTRO ====
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(
                "http://localhost:4200",
                "http://localhost:8080",
                "http://127.0.0.1:8080",
                "http://127.0.0.1:4200"
        ));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Headers necesarios para Swagger
        config.addAllowedHeader("*");
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("Content-Type");

        config.addExposedHeader("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
