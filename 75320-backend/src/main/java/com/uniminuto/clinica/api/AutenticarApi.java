package com.uniminuto.clinica.api;

import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import com.uniminuto.clinica.model.RecuperarPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * API de autenticación y recuperación de contraseñas.
 * 
 * Endpoints:
 *  - POST /auth/login → Autentica un usuario y genera un token JWT
 *  - POST /auth/recuperar-contrasena → Envía una contraseña temporal al correo del usuario
 * 
 * @author Sistema
 */
@Tag(name = "Autenticación", description = "Endpoints para inicio de sesión, generación de tokens JWT y recuperación de contraseñas")
@RequestMapping("/auth")
public interface AutenticarApi {
    
    /**
     * Autentica un usuario con sus credenciales y genera un token JWT.
     * 
     * El sistema registra cada intento (exitoso o fallido) en la auditoría.
     * Si un usuario falla 3 veces consecutivas, será bloqueado temporalmente por 5 minutos.
     * 
     * @param request Credenciales del usuario (username y password)
     * @return Token JWT si las credenciales son válidas
     * @throws BadRequestException Si las credenciales son incorrectas o el usuario está bloqueado
     */
    @Operation(
        summary = "Autenticar usuario",
        description = "Valida las credenciales del usuario y genera un token JWT si son correctas. " +
                     "Registra el intento en la auditoría y aplica bloqueo temporal tras 3 intentos fallidos."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Autenticación exitosa",
            content = @Content(schema = @Schema(implementation = AutenticatorRs.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Credenciales incorrectas o usuario bloqueado"
        )
    })
    @PostMapping("/login")
    ResponseEntity<AutenticatorRs> autenticar(@Valid @RequestBody AuthenticatorRq request) throws BadRequestException;
    
    /**
     * Inicia el proceso de recuperación de contraseña.
     * 
     * El usuario ingresa su nombre de usuario y el sistema envía una contraseña temporal
     * al correo electrónico registrado. Por seguridad, siempre devuelve un mensaje genérico
     * sin revelar si el usuario existe o no.
     * 
     * @param request Solicitud con el nombre de usuario
     * @return Respuesta genérica (por seguridad, no revela si el usuario existe)
     * @throws BadRequestException Si hay un error en la solicitud
     */
    @Operation(
        summary = "Recuperar contraseña",
        description = "Envía una contraseña temporal al correo electrónico registrado del usuario. " +
                     "Por motivos de seguridad, siempre devuelve un mensaje genérico sin revelar si el usuario existe."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Solicitud procesada (mensaje genérico por seguridad)",
            content = @Content(schema = @Schema(implementation = RespuestaRs.class))
        )
    })
    @PostMapping("/recuperar-contrasena")
    ResponseEntity<RespuestaRs> recuperarPassword(@Valid @RequestBody RecuperarPasswordRq request) throws BadRequestException;
}
