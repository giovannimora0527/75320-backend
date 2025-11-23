/**
 * Interfaz API REST para manejar operaciones de autenticación y recuperación de contraseñas en el sistema clínico.
 * Proporciona endpoints para la gestión de recuperación de credenciales de usuario.
 *
 * @author Edwin Morales
 * @author Nahum Dominguez
 * @author Emily Aldana
 * @author Julian Amaya
 * @author Sebastian Paez
 */
package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.RecuperarPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public interface RecuperarPasswordApi {

    /**
     * Procesa la solicitud de recuperación de contraseña para un usuario.
     * Este endpoint recibe las credenciales del usuario y gestiona el proceso de recuperación de contraseña,
     * enviando instrucciones o un enlace de restablecimiento al correo electrónico asociado.
     *
     * @param request Objeto RecuperarPasswordRq que contiene la información necesaria para la recuperación (email, usuario, etc.)
     * @return ResponseEntity con un objeto RespuestaRs que indica el resultado de la operación,
     *         incluyendo estado de éxito/error y mensajes descriptivos.
     */
    @RequestMapping(value = "/recuperar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> recuperarPassword(@Valid @RequestBody RecuperarPasswordRq request);
}