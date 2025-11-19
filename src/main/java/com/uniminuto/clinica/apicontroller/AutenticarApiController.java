package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.AutenticarApi;
import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import com.uniminuto.clinica.model.RecuperacionPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.AutenticarService;
import com.uniminuto.clinica.service.RecuperacionPasswordService;
import javax.mail.MessagingException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AutenticarApiController implements AutenticarApi {
    
    @Autowired
    private AutenticarService autenticarService;
    
    @Autowired 
    private RecuperacionPasswordService recuperacionPasswordService;
    
    @Override
    public ResponseEntity<AutenticatorRs> autenticar(
            AuthenticatorRq request, 
            HttpServletRequest httpRequest) throws BadRequestException {
        
        // Obtener IP del cliente
        String ip = obtenerIpCliente(httpRequest);
        
        // Llamar al servicio pasando la IP
        return ResponseEntity.ok(this.autenticarService.autenticar(request, ip));
    }

    @Override
    public ResponseEntity<RespuestaRs> recuperarPassword(
            RecuperacionPasswordRq request,
            HttpServletRequest httpRequest) throws MessagingException {
        
        // Obtener IP del cliente
        String ip = obtenerIpCliente(httpRequest);
        
        // ✅ CORREGIDO: Usar la instancia inyectada (minúscula) y no la clase (mayúscula)
        RespuestaRs respuesta = recuperacionPasswordService.recuperarPassword(request, ip);
        
        return ResponseEntity.ok(respuesta);
    } 

        
    
    /**
     * Obtiene la IP del cliente desde la solicitud HTTP
     * Verifica los headers más comunes usados por proxies
     */
    private String obtenerIpCliente(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        
        // Si hay múltiples IPs separadas por coma, tomar la primera
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        
        return ip != null ? ip : "DESCONOCIDA";
    }

    
}