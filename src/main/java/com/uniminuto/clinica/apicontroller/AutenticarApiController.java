package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.AutenticarApi;
import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import com.uniminuto.clinica.model.RecuperarPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.AutenticarService;
import com.uniminuto.clinica.service.RecuperarPasswordService;
import com.uniminuto.clinica.utils.BadRequestException;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class AutenticarApiController implements AutenticarApi {

    @Autowired
    private AutenticarService autenticarService;
    
    @Autowired
    private RecuperarPasswordService recuperarPasswordService;

    @Override
    public ResponseEntity<AutenticatorRs> autenticar(AuthenticatorRq request) throws BadRequestException {
        // Obtener IP del cliente desde la solicitud HTTP
        String ipOrigen = null;
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest httpRequest = attributes.getRequest();
                ipOrigen = obtenerIpCliente(httpRequest);
            }
        } catch (Exception e) {
            // Si no se puede obtener la IP, continuar sin ella
            System.out.println("No se pudo obtener la IP del cliente: " + e.getMessage());
        }
        
        return ResponseEntity.ok(this.autenticarService.autenticar(request, ipOrigen));
    }
    
    @Override
    public ResponseEntity<RespuestaRs> recuperarPassword(RecuperarPasswordRq request) 
            throws BadRequestException {
        try {
            // Obtener HttpServletRequest desde el contexto de Spring
            String ipOrigen = null;
            try {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    HttpServletRequest httpRequest = attributes.getRequest();
                    ipOrigen = obtenerIpCliente(httpRequest);
                }
            } catch (Exception e) {
                // Si no se puede obtener la IP, continuar sin ella
                System.out.println("No se pudo obtener la IP del cliente: " + e.getMessage());
            }
            
            return ResponseEntity.ok(this.recuperarPasswordService.recuperarPassword(request, ipOrigen));
        } catch (MessagingException e) {
            // Por seguridad, no revelar el error específico
            RespuestaRs respuesta = new RespuestaRs();
            respuesta.setStatus(200);
            respuesta.setMensaje("Si el usuario existe y tiene un correo electrónico registrado, se enviará una contraseña temporal.");
            respuesta.setSuccess(true);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            // Manejar cualquier otro error
            RespuestaRs respuesta = new RespuestaRs();
            respuesta.setStatus(200);
            respuesta.setMensaje("Si el usuario existe y tiene un correo electrónico registrado, se enviará una contraseña temporal.");
            respuesta.setSuccess(true);
            return ResponseEntity.ok(respuesta);
        }
    }
    
    /**
     * Obtiene la IP del cliente desde la solicitud HTTP.
     */
    private String obtenerIpCliente(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}

