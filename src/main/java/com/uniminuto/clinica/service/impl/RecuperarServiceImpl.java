package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.RecuperarPasswordAuditoria;
import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.RecuperarPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.RecuperarRepository;
import com.uniminuto.clinica.repository.UsuarioRepository;
import com.uniminuto.clinica.service.CifrarService;
import com.uniminuto.clinica.service.EmailService;
import com.uniminuto.clinica.service.RecuperarService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Optional;

@Service
public class RecuperarServiceImpl implements RecuperarService {

    @Autowired
    private RecuperarRepository recuperarRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CifrarService cifrarService;

    @Autowired
    private EmailService emailService;

    @Override
    public RespuestaRs recuperarPassword(RecuperarPasswordRq request) {
        Optional<Usuario> optUser = usuarioRepository.findByUsername(request.getUsername());

        if (!optUser.isPresent()) {
            // Registrar en auditoría - usuario no encontrado
            registrarAuditoriaSafe(request.getUsername(), "Usuario no encontrado en el sistema");
            return crearRespuestaGenerica();
        }

        Usuario usuario = optUser.get();

        if (!usuario.isActivo()) {
            // Registrar en auditoría - usuario inactivo
            registrarAuditoriaSafe(request.getUsername(), "Usuario inactivo");
            return crearRespuestaGenerica();
        }

        try {
            // Generar contraseña temporal
            String passwordTemporal = generarPasswordTemporal();

            // Actualizar contraseña del usuario
            usuario.setPassword(cifrarService.encriptarPassword(passwordTemporal));
            usuarioRepository.save(usuario);

            // Enviar correo con contraseña temporal
            enviarCorreoPasswordTemporal(usuario, passwordTemporal);

            return crearRespuestaExito();

        } catch (BadRequestException | MessagingException e) {
            // Registrar error en auditoría - error en el proceso
            registrarAuditoriaSafe(request.getUsername(), "Error al procesar la recuperación: " + e.getMessage());
            return crearRespuestaGenerica();
        } catch (Exception e) {
            // Registrar error en auditoría - error inesperado
            registrarAuditoriaSafe(request.getUsername(), "Error inesperado al procesar la recuperación: " + e.getMessage());
            return crearRespuestaGenerica();
        }
    }

    /**
     * Método seguro para registrar auditoría que nunca lanza excepción
     */
    private void registrarAuditoriaSafe(String username, String descripcion) {
        try {
            RecuperarPasswordAuditoria auditoria = new RecuperarPasswordAuditoria(username, descripcion);
            recuperarRepository.save(auditoria);
        } catch (Exception e) {
            // Solo log el error pero no propago la excepción para mantener el mensaje genérico al usuario
            System.err.println("Error al guardar auditoría: " + e.getMessage());
            // No se lanza excepción para mantener el flujo de respuesta genérica
        }
    }

    private String generarPasswordTemporal() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        int longitudDeseada = 10;

        for (int i = 0; i < longitudDeseada; i++) {
            int indiceAleatorio = (int) (Math.random() * caracteres.length());
            password.append(caracteres.charAt(indiceAleatorio));
        }

        return password.toString();
    }

    private void enviarCorreoPasswordTemporal(Usuario usuario, String passwordTemporal)
            throws BadRequestException, MessagingException {
        String html = String.format("""
            <html>
            <body>
                <h2>Recuperación de Contraseña - Clínica Uniminuto</h2>
                <p>Hola <b>%s</b>,</p>
                <p>Has solicitado recuperar tu contraseña.</p>
                <p><b>Contraseña temporal:</b> %s</p>
                <p>Por favor, inicia sesión con esta contraseña y cámbiala inmediatamente.</p>
                <br>
                <p><b>Nota de seguridad:</b> Si no solicitaste este cambio, por favor contacta al administrador.</p>
                <hr>
                <small>Este mensaje fue generado automáticamente, por favor no respondas a este correo.</small>
            </body>
            </html>
            """,
                usuario.getUsername(),
                passwordTemporal
        );

        emailService.sendHtmlEmail(
                usuario.getEmail(),
                "Recuperación de Contraseña - Clínica Uniminuto",
                html,
                emailService.getTo()
        );
    }

    private RespuestaRs crearRespuestaGenerica() {
        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Si el usuario existe, recibirá un correo con las instrucciones para recuperar su contraseña.");
        return respuesta;
    }

    private RespuestaRs crearRespuestaExito() {
        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Si el usuario existe, recibirá un correo con las instrucciones para recuperar su contraseña.");
        return respuesta;
    }
}