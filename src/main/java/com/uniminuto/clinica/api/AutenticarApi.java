package com.uniminuto.clinica.api;

import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import com.uniminuto.clinica.model.RecuperacionPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import javax.mail.MessagingException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;                  
import io.swagger.v3.oas.annotations.tags.Tag;                    
//import io.swagger.v3.oas.annotations.parameters.RequestBody;     
import io.swagger.v3.oas.annotations.media.Content;              
import io.swagger.v3.oas.annotations.media.Schema;               
import io.swagger.v3.oas.annotations.responses.ApiResponse;      

@Tag(name = "Autenticación", description = "Endpoints para login y recuperación de contraseña")   
@RequestMapping("/auth")

public interface AutenticarApi {
    @Operation(
            summary = "Autenticar usuario",                                           
            description = "Valida usuario y contraseña y genera token JWT.",          
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autenticación exitosa",
                            content = @Content(schema = @Schema(implementation = AutenticatorRs.class)) 
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error en la petición",
                            content = @Content(schema = @Schema(implementation = RespuestaRs.class))      
                    )
            }
    )
    @PostMapping("/login")
    ResponseEntity<AutenticatorRs> autenticar(
            @Valid
            @org.springframework.web.bind.annotation.RequestBody
            AuthenticatorRq request,
            HttpServletRequest httpRequest
    ) throws BadRequestException;


    @Operation(
            summary = "Recuperar contraseña",                                        
            description = "Envía una contraseña temporal al correo del usuario.",    
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Solicitud procesada (respuesta genérica)",
                            content = @Content(schema = @Schema(implementation = RespuestaRs.class))    
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error al enviar correo",
                            content = @Content(schema = @Schema(implementation = RespuestaRs.class))     
                    )
            }
    )
    @PostMapping("/recuperar-contrasena")
    ResponseEntity<RespuestaRs> recuperarPassword(
            @Valid
            @org.springframework.web.bind.annotation.RequestBody
            RecuperacionPasswordRq request,
            HttpServletRequest httpRequest
    ) throws MessagingException;
}
