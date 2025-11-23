/**
 * Controlador REST que implementa la interfaz para la recuperación de contraseñas en el sistema clínico.
 * Gestiona las solicitudes de recuperación de credenciales de usuario y coordina el proceso
 * con el servicio correspondiente.
 *
 * @author Edwin Morales
 * @author Nahum Dominguez
 * @author Emily Aldana
 * @author Julian Amaya
 * @author Sebastian Paez
 */
package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.RecuperarPasswordApi;
import com.uniminuto.clinica.model.RecuperarPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.RecuperarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecuperarPasswordApiController implements RecuperarPasswordApi {

    @Autowired
    private RecuperarService recuperarService;

    /**
     * Implementación del endpoint para procesar solicitudes de recuperación de contraseña.
     * Recibe la solicitud del usuario y la delega al servicio correspondiente para su procesamiento,
     * que incluye validación, generación de tokens y notificación al usuario.
     *
     * @param request Objeto RecuperarPasswordRq que contiene los datos de la solicitud (email, usuario, etc.)
     * @return ResponseEntity con un objeto RespuestaRs que contiene el resultado de la operación,
     *         incluyendo estado, mensajes y cualquier información relevante para el usuario.
     */
    @Override
    public ResponseEntity<RespuestaRs> recuperarPassword(RecuperarPasswordRq request) {
        return ResponseEntity.ok(recuperarService.recuperarPassword(request));
    }
}