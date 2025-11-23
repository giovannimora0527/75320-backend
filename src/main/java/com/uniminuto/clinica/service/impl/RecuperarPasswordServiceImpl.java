package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.AuditoriaRecuperacion;
import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.RecuperarPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.AuditoriaRecuperacionRepository;
import com.uniminuto.clinica.repository.UsuarioRepository;
import com.uniminuto.clinica.service.CifrarService;
import com.uniminuto.clinica.service.EmailService;
import com.uniminuto.clinica.service.RecuperarPasswordService;
import com.uniminuto.clinica.utils.BadRequestException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Implementación del servicio de recuperación de contraseña.
 * Incluye auditoría de seguridad y envío de contraseñas temporales.
 */
@Service
public class RecuperarPasswordServiceImpl implements RecuperarPasswordService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private AuditoriaRecuperacionRepository auditoriaRepository;
    
    @Autowired
    private CifrarService cifrarService;
    
    @Autowired
    private EmailService emailService;
    
    /**
     * Genera una contraseña temporal aleatoria de 8 caracteres.
     */
    private String generarPasswordTemporal() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int indiceAleatorio = (int) (Math.random() * caracteres.length());
            password.append(caracteres.charAt(indiceAleatorio));
        }
        return password.toString();
    }
    
    /**
     * Registra un intento de recuperación en la auditoría.
     */
    private void registrarAuditoria(String username, boolean exitoso, String descripcion, String email, String ipOrigen) {
        AuditoriaRecuperacion auditoria = new AuditoriaRecuperacion();
        auditoria.setUsernameIngresado(username);
        auditoria.setFechaHora(LocalDateTime.now());
        auditoria.setExitoso(exitoso);
        auditoria.setDescripcion(descripcion);
        auditoria.setEmailUsuario(email);
        auditoria.setIpOrigen(ipOrigen);
        auditoriaRepository.save(auditoria);
        
        System.out.println("==========================================");
        System.out.println("AUDITORÍA DE RECUPERACIÓN DE CONTRASEÑA");
        System.out.println("Username ingresado: " + username);
        System.out.println("Fecha/Hora: " + auditoria.getFechaHora());
        System.out.println("Exitoso: " + (exitoso ? "Sí" : "No"));
        System.out.println("Descripción: " + descripcion);
        if (ipOrigen != null) {
            System.out.println("IP Origen: " + ipOrigen);
        }
        System.out.println("==========================================");
    }
    
    @Override
    public RespuestaRs recuperarPassword(RecuperarPasswordRq request, String ipOrigen) 
            throws BadRequestException, MessagingException {
        
        String username = request.getUsername();
        
        // Validar que el username no esté vacío
        if (username == null || username.isBlank()) {
            registrarAuditoria(username, false, "Intento de recuperación con username vacío o nulo", null, ipOrigen);
            // Por seguridad, devolver el mismo mensaje genérico
            return crearRespuestaGenerica();
        }
        
        // Buscar el usuario (sin distinguir entre existencia o no por seguridad)
        System.out.println("==========================================");
        System.out.println("BUSCANDO USUARIO PARA RECUPERACIÓN");
        System.out.println("Username ingresado: '" + username + "'");
        System.out.println("Username ingresado (longitud): " + (username != null ? username.length() : "NULL"));
        
        String usernameNormalizado = username != null ? username.trim().toLowerCase() : "";
        System.out.println("Username normalizado: '" + usernameNormalizado + "'");
        
        // Intentar buscar primero con el username tal cual viene
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username.trim());
        System.out.println("Búsqueda 1 - Por username original: " + (usuarioOpt.isPresent() ? "✓ Encontrado" : "✗ No encontrado"));
        
        // Si no se encontró, intentar con minúsculas
        if (usuarioOpt.isEmpty() && !username.trim().equals(usernameNormalizado)) {
            System.out.println("Búsqueda 2 - Por username en minúsculas: '" + usernameNormalizado + "'");
            usuarioOpt = usuarioRepository.findByUsername(usernameNormalizado);
            System.out.println("Búsqueda 2 - Resultado: " + (usuarioOpt.isPresent() ? "✓ Encontrado" : "✗ No encontrado"));
        }
        
        // Si no se encontró por username, intentar buscar por email (por si el usuario ingresó su email como username)
        if (usuarioOpt.isEmpty() && username.contains("@")) {
            System.out.println("Búsqueda 3 - El input parece ser un email, buscando por email: '" + username.trim() + "'");
            usuarioOpt = usuarioRepository.findByEmail(username.trim());
            System.out.println("Búsqueda 3 - Por email: " + (usuarioOpt.isPresent() ? "✓ Encontrado" : "✗ No encontrado"));
            
            // También intentar con minúsculas
            if (usuarioOpt.isEmpty() && !username.trim().equals(usernameNormalizado)) {
                System.out.println("Búsqueda 4 - Por email en minúsculas: '" + usernameNormalizado + "'");
                usuarioOpt = usuarioRepository.findByEmail(usernameNormalizado);
                System.out.println("Búsqueda 4 - Resultado: " + (usuarioOpt.isPresent() ? "✓ Encontrado" : "✗ No encontrado"));
            }
        }
        
        // Si aún no se encontró, listar todos los usuarios para debugging (solo en desarrollo)
        if (usuarioOpt.isEmpty()) {
            System.err.println("⚠ Usuario no encontrado. Listando todos los usuarios disponibles:");
            usuarioRepository.findAll().forEach(u -> {
                System.err.println("  - ID: " + u.getId() + ", Username: '" + u.getUsername() + "', Email: " + u.getEmail());
            });
        }
        
        if (usuarioOpt.isPresent()) {
            // Usuario válido encontrado
            Usuario usuario = usuarioOpt.get();
            System.out.println("✓ Usuario encontrado:");
            System.out.println("  ID: " + usuario.getId());
            System.out.println("  Username: " + usuario.getUsername());
            System.out.println("  Email: " + (usuario.getEmail() != null ? usuario.getEmail() : "NULL"));
            System.out.println("  Activo: " + usuario.isActivo());
            
            // Verificar que el usuario tenga email
            if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
                System.err.println("✗ Usuario encontrado pero NO tiene email registrado");
                registrarAuditoria(username, false, 
                    "Usuario encontrado pero no tiene email registrado", 
                    null, ipOrigen);
                return crearRespuestaGenerica();
            }
            
            System.out.println("✓ Usuario tiene email registrado: " + usuario.getEmail());
            
            // Generar contraseña temporal
            String passwordTemporal = generarPasswordTemporal();
            
            // Actualizar la contraseña del usuario
            usuario.setPassword(cifrarService.encriptarPassword(passwordTemporal));
            usuarioRepository.save(usuario);
            
            System.out.println("✓ Contraseña temporal generada: " + passwordTemporal);
            System.out.println("✓ Contraseña actualizada en la base de datos");
            
            // Enviar correo con la contraseña temporal
            boolean correoEnviado = false;
            String errorCorreo = null;
            
            try {
                if (this.emailService == null) {
                    System.err.println("✗ ERROR CRÍTICO: EmailService es NULL");
                    errorCorreo = "EmailService no está disponible (null)";
                } else {
                    System.out.println("✓ EmailService está disponible");
                    System.out.println("==========================================");
                    System.out.println("INTENTANDO ENVIAR CORREO DE RECUPERACIÓN");
                    System.out.println("Destinatario: " + usuario.getEmail());
                    System.out.println("Username: " + usuario.getUsername());
                    System.out.println("Contraseña temporal: " + passwordTemporal);
                    
                    String html = String.format("""
                        <html>
                        <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                            <div style="max-width: 600px; margin: 0 auto; padding: 20px;">
                                <h2 style="color: #2c3e50;">Recuperación de Contraseña - Clínica Uniminuto</h2>
                                <p>Hola <b>%s</b>,</p>
                                <p>Has solicitado la recuperación de tu contraseña.</p>
                                <div style="background-color: #f8f9fa; border-left: 4px solid #007bff; padding: 15px; margin: 20px 0;">
                                    <p style="margin: 0;"><strong>Tu nueva contraseña temporal es:</strong></p>
                                    <p style="font-size: 24px; font-weight: bold; color: #007bff; margin: 10px 0; letter-spacing: 2px;">
                                        %s
                                    </p>
                                </div>
                                <p><strong>Importante:</strong></p>
                                <ul>
                                    <li>Esta es una contraseña temporal generada automáticamente.</li>
                                    <li>Por seguridad, te recomendamos cambiar esta contraseña después de iniciar sesión.</li>
                                    <li>Si no solicitaste esta recuperación, contacta al administrador del sistema.</li>
                                </ul>
                                <hr style="border: none; border-top: 1px solid #eee; margin: 20px 0;">
                                <p style="font-size: 12px; color: #999;">
                                    Este mensaje fue generado automáticamente. Por favor, no respondas a este correo.
                                </p>
                            </div>
                        </body>
                        </html>
                        """, usuario.getUsername(), passwordTemporal);
                    
                    // Usar el correo remitente configurado (dragnovith0111@gmail.com)
                    String remitente = emailService.getFrom();
                    System.out.println("Remitente (From) configurado: " + remitente);
                    System.out.println("Destinatario (To): " + usuario.getEmail());
                    
                    this.emailService.sendHtmlEmail(
                        usuario.getEmail(),
                        "Recuperación de Contraseña - Clínica Uniminuto",
                        html,
                        remitente
                    );
                    
                    System.out.println("✓ Correo de recuperación enviado exitosamente a: " + usuario.getEmail());
                    System.out.println("==========================================");
                    correoEnviado = true;
                }
            } catch (MessagingException e) {
                // Log detallado del error de mensajería
                System.err.println("==========================================");
                System.err.println("ERROR AL ENVIAR CORREO DE RECUPERACIÓN");
                System.err.println("Destinatario: " + usuario.getEmail());
                System.err.println("Error: " + e.getMessage());
                System.err.println("Causa: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A"));
                if (e.getCause() != null && e.getCause().getCause() != null) {
                    System.err.println("Causa raíz: " + e.getCause().getCause().getMessage());
                }
                System.err.println("==========================================");
                e.printStackTrace();
                
                errorCorreo = "Error al enviar correo: " + e.getMessage();
                // Aún así devolver mensaje genérico por seguridad
            } catch (BadRequestException e) {
                // Log del error de solicitud incorrecta
                System.err.println("==========================================");
                System.err.println("ERROR DE CONFIGURACIÓN DE CORREO");
                System.err.println("Destinatario: " + usuario.getEmail());
                System.err.println("Error: " + e.getMessage());
                System.err.println("==========================================");
                e.printStackTrace();
                
                errorCorreo = "Error de configuración de correo: " + e.getMessage();
            } catch (Exception e) {
                // Log de cualquier otro error
                System.err.println("==========================================");
                System.err.println("ERROR INESPERADO AL ENVIAR CORREO DE RECUPERACIÓN");
                System.err.println("Destinatario: " + usuario.getEmail());
                System.err.println("Tipo de error: " + e.getClass().getSimpleName());
                System.err.println("Error: " + e.getMessage());
                if (e.getCause() != null) {
                    System.err.println("Causa: " + e.getCause().getMessage());
                }
                System.err.println("==========================================");
                e.printStackTrace();
                
                errorCorreo = "Error inesperado: " + e.getMessage();
            } finally {
                // Registrar auditoría con el resultado final
                if (correoEnviado) {
                    registrarAuditoria(username, true, 
                        "Contraseña temporal generada y enviada por correo electrónico", 
                        usuario.getEmail(), ipOrigen);
                } else {
                    registrarAuditoria(username, false, 
                        errorCorreo != null ? errorCorreo : "Error desconocido al enviar correo", 
                        usuario.getEmail(), ipOrigen);
                }
            }
            
            // Siempre devolver el mismo mensaje genérico (por seguridad)
            return crearRespuestaGenerica();
            
        } else {
            // Usuario no encontrado - registrar en auditoría pero no revelar
            registrarAuditoria(username, false, 
                "Intento de recuperación con username inválido o no existente", 
                null, ipOrigen);
            
            // Devolver el mismo mensaje genérico (por seguridad)
            return crearRespuestaGenerica();
        }
    }
    
    /**
     * Crea una respuesta genérica que no revela si el usuario existe o no.
     * Esto es por seguridad para evitar enumeración de usuarios.
     */
    private RespuestaRs crearRespuestaGenerica() {
        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Si el usuario existe y tiene un correo electrónico registrado, se enviará una contraseña temporal.");
        respuesta.setSuccess(true);
        return respuesta;
    }
}

