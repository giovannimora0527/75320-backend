package com.uniminuto.clinica.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;

@Component
public class JwtTokenFilter extends GenericFilterBean {

    private static final Logger LOG = Logger.getLogger(JwtTokenFilter.class.getName());

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String authHeader = request.getHeader("Authorization");
        String requestURI = request.getRequestURI();

        LOG.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        // Rutas públicas que NO requieren token JWT
        if (isPublicEndpoint(requestURI)) {
            try {
                filterChain.doFilter(req, res);
            } catch (BadRequestException e) {
                LOG.log(Level.WARNING, "Error en la petición: {0}", e.getMessage());
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"error\": \"Bad Request\","
                        + " \"message\": \"" + e.getMessage() + "\"}");
                return;
            }
            LOG.info("Petición a endpoint público, se permite sin token.");
            return;
        }

        // Para rutas protegidas, validar el token JWT
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                if (isTokenExpired(token)) {
                    sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED,
                            "Token expirado. Por favor, inicie sesión nuevamente.");
                    return;
                }

                // Si el token es válido, establecer autenticación en el contexto de Spring
                DecodedJWT jwt = JWT.decode(token);
                String username = jwt.getSubject();
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            username, null, Collections.singletonList(new SimpleGrantedAuthority("USER"))
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

                // Continuar con la cadena de filtros
                filterChain.doFilter(req, res);

            } catch (ExpiredJwtException eje) {
                sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED,
                        "Token expirado. Por favor, inicie sesión nuevamente.");
                return;
            } catch (Exception e) {
                sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Token inválido.");
                return;
            }
        } else {
            // No hay token en una ruta protegida
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED,
                    "Token JWT requerido. Por favor, incluya el header Authorization: Bearer <token>");
            return;
        }
    }

    /**
     * Verifica si la URI es un endpoint público
     */
    private boolean isPublicEndpoint(String requestURI) {
        return requestURI != null && (
                requestURI.contains("/auth/login") ||
                        requestURI.contains("/auth/recuperar-contrasena") ||
                        requestURI.contains("/recuperar/password")  // NUEVO: Endpoint de recuperación
        );
    }

    /**
     * Valida si el token JWT está expirado.
     */
    private boolean isTokenExpired(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            java.util.Date fechaFinSesion = jwt.getClaim("fecha_fin_sesion").asDate();
            if (fechaFinSesion == null) {
                return false; // Si no tiene expiración, se considera válido
            }
            return fechaFinSesion.before(new java.util.Date());
        } catch (Exception e) {
            return true; // Si hay error al decodificar, considerar como expirado
        }
    }

    /**
     * Envía una respuesta de error JSON
     */
    private void sendErrorResponse(HttpServletResponse response, int status, String message)
            throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"error\":\"" + message + "\"}");
        response.flushBuffer();
    }
}