/**
 * Implementación del servicio para la recuperación de contraseñas en el sistema clínico.
 * Gestiona el proceso completo de recuperación incluyendo validación de usuarios,
 * generación de contraseñas temporales, envío de correos electrónicos y auditoría.
 *
 * @author Edwin Morales
 * @author Nahum Dominguez
 * @author Emily Aldana
 * @author Julian Amaya
 * @author Sebastian Paez
 */
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
import java.util.List;
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

    /**
     * Recupera todos los registros de auditoría de recuperación de contraseñas.
     *
     * @return Lista con todos los registros de auditoría almacenados en el sistema
     */
    @Override
    public List<RecuperarPasswordAuditoria> listarTodosLosRegistros() {
        return this.recuperarRepository.findAll();
    }

    /**
     * Procesa la solicitud de recuperación de contraseña para un usuario.
     * Valida la existencia y estado del usuario, genera una contraseña temporal,
     * la envía por correo electrónico y registra la auditoría del proceso.
     *
     * @param request Solicitud de recuperación de contraseña con el nombre de usuario
     * @return Respuesta genérica que indica el resultado del proceso
     */
    @Override
    public RespuestaRs recuperarPassword(RecuperarPasswordRq request) {
        Optional<Usuario> optUser = usuarioRepository.findByUsername(request.getUsername());

        if (!optUser.isPresent()) {
            registrarAuditoriaSafe(request.getUsername(), "Usuario no encontrado en el sistema");
            return crearRespuestaGenerica();
        }

        Usuario usuario = optUser.get();

        if (!usuario.isActivo()) {
            registrarAuditoriaSafe(request.getUsername(), "Usuario inactivo");
            return crearRespuestaGenerica();
        }

        try {
            String passwordTemporal = generarPasswordTemporal();

            usuario.setPassword(cifrarService.encriptarPassword(passwordTemporal));
            usuarioRepository.save(usuario);

            enviarCorreoPasswordTemporal(usuario, passwordTemporal);

            return crearRespuestaExito();

        } catch (BadRequestException | MessagingException e) {
            registrarAuditoriaSafe(request.getUsername(), "Error al procesar la recuperación: " + e.getMessage());
            return crearRespuestaGenerica();
        } catch (Exception e) {
            registrarAuditoriaSafe(request.getUsername(), "Error inesperado al procesar la recuperación: " + e.getMessage());
            return crearRespuestaGenerica();
        }
    }

    /**
     * Registra un evento de auditoría de forma segura, capturando cualquier excepción
     * para evitar que interrumpa el flujo principal del proceso.
     *
     * @param username Nombre de usuario relacionado con el evento
     * @param descripcion Descripción del evento a registrar
     */
    private void registrarAuditoriaSafe(String username, String descripcion) {
        try {
            RecuperarPasswordAuditoria auditoria = new RecuperarPasswordAuditoria(username, descripcion);
            auditoria.setTipoAuditoria("RECUPERACION");
            recuperarRepository.save(auditoria);
        } catch (Exception e) {
            System.err.println("Error al guardar auditoría: " + e.getMessage());
        }
    }

    /**
     * Genera una contraseña temporal aleatoria de 10 caracteres.
     * La contraseña incluye letras mayúsculas, minúsculas y números.
     *
     * @return Contraseña temporal generada aleatoriamente
     */
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

    /**
     * Envía un correo electrónico al usuario con la contraseña temporal generada.
     * El correo incluye instrucciones para el uso seguro de la contraseña temporal.
     *
     * @param usuario Usuario al que se enviará el correo
     * @param passwordTemporal Contraseña temporal generada
     * @throws BadRequestException Si hay un error en la solicitud de envío
     * @throws MessagingException Si hay un error en el envío del correo
     */
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

    /**
     * Crea una respuesta genérica para mantener la consistencia en las respuestas
     * y evitar revelar información sensible sobre la existencia de usuarios.
     *
     * @return Respuesta genérica con estado 200 y mensaje estándar
     */
    private RespuestaRs crearRespuestaGenerica() {
        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Si el usuario existe, recibirá un correo con las instrucciones para recuperar su contraseña.");
        return respuesta;
    }

    /**
     * Crea una respuesta de éxito para operaciones completadas correctamente.
     * Utiliza el mismo mensaje genérico por seguridad.
     *
     * @return Respuesta exitosa con estado 200 y mensaje estándar
     */
    private RespuestaRs crearRespuestaExito() {
        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Si el usuario existe, recibirá un correo con las instrucciones para recuperar su contraseña.");
        return respuesta;
    }
}