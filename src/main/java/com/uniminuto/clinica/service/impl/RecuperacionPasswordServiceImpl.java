package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.AuditoriaRecuperacionPassword;
import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.RecuperacionPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.AuditoriaRecuperacionPasswordRepository;
import com.uniminuto.clinica.repository.UsuarioRepository;
import com.uniminuto.clinica.service.CifrarService;
import com.uniminuto.clinica.service.EmailService;
import com.uniminuto.clinica.service.RecuperacionPasswordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Implementación del servicio de recuperación de contraseña.
 * Cumple con los requerimientos de seguridad y auditoría.
 * 
 * @author lmora
 */
@Service
public class RecuperacionPasswordServiceImpl implements RecuperacionPasswordService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private AuditoriaRecuperacionPasswordRepository auditoriaRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private CifrarService cifrarService;

    @Override
    public RespuestaRs recuperarPassword(RecuperacionPasswordRq request, String ipOrigen) 
            throws MessagingException {
        
        // IMPORTANTE: Inicializar auditoría desde el inicio
        AuditoriaRecuperacionPassword auditoria = new AuditoriaRecuperacionPassword();
        auditoria.setUsernameIngresado(
            request.getUsername() != null ? request.getUsername().toLowerCase() : ""
        );
        auditoria.setFechaHora(LocalDateTime.now());
        auditoria.setIpOrigen(ipOrigen);
        auditoria.setExitoso(false); // Por defecto es fallido
        
        try {
            // Paso 1: Validar que el username no sea nulo o vacío
            if (request.getUsername() == null || 
                request.getUsername().trim().isEmpty()) {
                
                auditoria.setDescripcionError("Username no proporcionado o vacío");
                auditoriaRepository.save(auditoria);
                
                // Respuesta genérica (sin dar pistas de seguridad)
                return crearRespuestaGenerica();
            }
            
            // Paso 2: Buscar el usuario en la base de datos
            Optional<Usuario> optUsuario = usuarioRepository
                    .findByUsername(request.getUsername().toLowerCase());
            
            if (optUsuario.isEmpty()) {
                // Usuario NO existe - registrar auditoría de error
                auditoria.setDescripcionError("Usuario no encontrado en el sistema");
                auditoriaRepository.save(auditoria);
                
                // Respuesta genérica (sin confirmar que el usuario no existe)
                return crearRespuestaGenerica();
            }
            
            Usuario usuario = optUsuario.get();
            
            // Paso 3: Validar que el usuario tenga email registrado
            if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
                auditoria.setDescripcionError("Usuario encontrado pero sin email registrado");
                auditoriaRepository.save(auditoria);
                
                // Respuesta genérica
                return crearRespuestaGenerica();
            }
            
            // Paso 4: Validar que el usuario esté activo
            if (!usuario.isActivo()) {
                auditoria.setDescripcionError("Usuario encontrado pero inactivo");
                auditoriaRepository.save(auditoria);
                
                // Respuesta genérica
                return crearRespuestaGenerica();
            }
            
            // Paso 5: Generar contraseña temporal
            String passwordTemporal = generarPasswordTemporal();
            
            // Paso 6: Actualizar contraseña del usuario
            usuario.setPassword(cifrarService.encriptarPassword(passwordTemporal));
            
            // Reiniciar intentos fallidos al recuperar contraseña
            usuario.setIntentosFallidos(0);
            usuario.setCuentaBloqueadaHasta(null);
            
            usuarioRepository.save(usuario);
            
            // Paso 7: Enviar email con contraseña temporal
            String htmlEmail = construirEmailRecuperacion(
                    usuario.getUsername(), 
                    passwordTemporal
            );
            
            emailService.sendHtmlEmail(
                    usuario.getEmail(),
                    "Recuperación de Contraseña - Clínica Uniminuto",
                    htmlEmail,
                    emailService.getTo()
            );
            
            // Paso 8: Registrar auditoría EXITOSA
            auditoria.setExitoso(true);
            auditoria.setEmailDestino(usuario.getEmail());
            auditoria.setDescripcionError(null); // Sin error
            auditoriaRepository.save(auditoria);
            
        } catch (MessagingException e) {
            // Error al enviar el email
            auditoria.setDescripcionError("Error al enviar email: " + e.getMessage());
            auditoriaRepository.save(auditoria);
            
            // Re-lanzar la excepción para que el controller la maneje
            throw e;
            
        } catch (Exception e) {
            // Cualquier otro error inesperado
            auditoria.setDescripcionError("Error inesperado: " + e.getMessage());
            auditoriaRepository.save(auditoria);
            
            // Respuesta genérica (no revelar error técnico al usuario)
            return crearRespuestaGenerica();
        }
        
        // Respuesta genérica en TODOS los casos exitosos (por seguridad)
        return crearRespuestaGenerica();
    }

    /**
     * Crea una respuesta genérica que no revela información de seguridad.
     * Se usa tanto para casos exitosos como fallidos.
     * 
     * @return Respuesta estándar sin detalles
     */
    private RespuestaRs crearRespuestaGenerica() {
        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Se ha enviado contraseña a su correo electrónico");
        return respuesta;
    }

    /**
     * Genera una contraseña temporal aleatoria de 8 caracteres.
     * 
     * @return Contraseña temporal generada
     */
    private String generarPasswordTemporal() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
        StringBuilder password = new StringBuilder();
        int longitudDeseada = 10;

        for (int i = 0; i < longitudDeseada; i++) {
            int indiceAleatorio = (int) (Math.random() * caracteres.length());
            password.append(caracteres.charAt(indiceAleatorio));
        }

        return password.toString();
    }

    /**
     * Construye el HTML del email para recuperación de contraseña.
     * 
     * @param username Nombre del usuario
     * @param passwordTemporal Contraseña temporal generada
     * @return HTML formateado para el email
     */
    private String construirEmailRecuperacion(String username, String passwordTemporal) {
        return String.format("""
                <html>
                <body style="font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f4;">
                    <div style="max-width: 600px; margin: 0 auto; background-color: white; 
                                border: 1px solid #ddd; padding: 30px; border-radius: 8px;">
                        
                        <h2 style="color: #2c3e50; border-bottom: 3px solid #007bff; padding-bottom: 10px;">
                            🔐 Recuperación de Contraseña
                        </h2>
                        
                        <p>Hola <b>%s</b>,</p>
                        
                        <p>Hemos recibido una solicitud para restablecer tu contraseña en el sistema 
                           de la Clínica Uniminuto.</p>
                        
                        <div style="background-color: #e7f3ff; padding: 20px; border-left: 4px solid #007bff; 
                                    margin: 25px 0; border-radius: 4px;">
                            <p style="margin: 0; color: #495057;"><b>Tu contraseña temporal es:</b></p>
                            <p style="font-size: 24px; font-weight: bold; color: #007bff; 
                                      margin: 15px 0; letter-spacing: 2px; font-family: monospace;">
                                %s
                            </p>
                        </div>
                        
                        <div style="background-color: #fff3cd; padding: 15px; border-left: 4px solid #ffc107; 
                                    margin: 20px 0; border-radius: 4px;">
                            <p style="margin: 0;"><b>⚠️ Importante - Por razones de seguridad:</b></p>
                            <ul style="margin: 10px 0; padding-left: 20px;">
                                <li>Esta contraseña es <b>temporal</b></li>
                                <li>Debes cambiarla en tu próximo inicio de sesión</li>
                                <li><b>No compartas</b> esta información con nadie</li>
                                <li>Este enlace es válido por tiempo limitado</li>
                            </ul>
                        </div>
                        
                        <p style="margin-top: 25px;">
                            Si <b>no solicitaste</b> este cambio, por favor contacta al administrador 
                            del sistema inmediatamente.
                        </p>
                        
                        <hr style="border: none; border-top: 1px solid #ddd; margin: 30px 0;">
                        
                        <div style="text-align: center;">
                            <small style="color: #6c757d;">
                                Este es un mensaje automático, por favor no respondas a este correo.
                                <br><br>
                                <b>Clínica Uniminuto</b> - Sistema de Gestión Médica
                                <br>
                                © 2025 Todos los derechos reservados
                            </small>
                        </div>
                    </div>
                </body>
                </html>
                """,
                username,
                passwordTemporal
        );
    }
}