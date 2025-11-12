package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import com.uniminuto.clinica.repository.UsuarioRepository;
import com.uniminuto.clinica.service.CifrarService;
import com.uniminuto.clinica.service.EmailService;
import com.uniminuto.clinica.service.UsuarioService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import jakarta.mail.MessagingException;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de gestión de usuarios.
 * Contiene operaciones CRUD y envío de notificaciones por correo.
 * 
 * @author lmora
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CifrarService cifrarService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarTodosLosUsuarios() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> encontrarPorRol(String rol) {
        return this.usuarioRepository.findByRol(rol);
    }

    @Override
    public Usuario encontrarPorNombre(String nombreUsuario) throws BadRequestException {
        return this.usuarioRepository.findByUsername(nombreUsuario.toLowerCase())
                .orElseThrow(() -> new BadRequestException("No existe el usuario"));
    }

    @Override
    public List<Usuario> buscarPorEstado(Integer estado) {
        boolean activo = estado == 1;
        return this.usuarioRepository.findByActivo(activo);
    }

    @Override
    public RespuestaRs guardarUsuario(UsuarioRq usuarioNuevo)
            throws BadRequestException, MessagingException {
        validarCampos(usuarioNuevo);

        // Validar que no exista el usuario
        if (usuarioRepository.findByUsername(usuarioNuevo.getUsername().toLowerCase()).isPresent()) {
            throw new BadRequestException("El usuario ya existe.");
        }

        // Crear el usuario
        Usuario nuevo = new Usuario();
        nuevo.setActivo(true);
        nuevo.setFechaCreacion(LocalDateTime.now());
        nuevo.setRol(usuarioNuevo.getRol().toUpperCase());
        nuevo.setUsername(usuarioNuevo.getUsername().toLowerCase());
        nuevo.setEmail(usuarioNuevo.getEmail());

        // Generar contraseña aleatoria y cifrar
        String password = generarPass();
        nuevo.setPassword(this.cifrarService.encriptarPassword(password));
        this.usuarioRepository.save(nuevo);

        // Enviar correo con credenciales
        String html = String.format("""
            <html>
            <body>
                <h2>¡Bienvenido a la Clínica Uniminuto!</h2>
                <p>Hola <b>%s</b>,</p>
                <p>Tu cuenta ha sido creada exitosamente.</p>
                <p><b>Usuario:</b> %s</p>
                <p><b>Correo:</b> %s</p>
                <p><b>Contraseña temporal:</b> %s</p>
                <p>Por favor, inicia sesión y cambia tu contraseña lo antes posible.</p>
                <br>
                <hr>
                <small>Este mensaje fue generado automáticamente, por favor no respondas a este correo.</small>
            </body>
            </html>
            """, nuevo.getUsername(), nuevo.getUsername(), nuevo.getEmail(), password);

        this.emailService.sendHtmlEmail(
                nuevo.getEmail(),
                "Credenciales de acceso - Clínica Uniminuto",
                html,
                emailService.getTo()
        );

        // Respuesta
        RespuestaRs rta = new RespuestaRs();
        rta.setMensaje("El usuario se ha guardado correctamente.");
        rta.setStatus(201);
        rta.setData(nuevo);
        return rta;
    }

    @Override
    public RespuestaRs actualizarUsuario(Integer id, UsuarioRq usuarioRq)
            throws BadRequestException {
        if (id == null) throw new BadRequestException("Id inválido");

        validarCampos(usuarioRq);

        Usuario usuarioExistente = usuarioRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new BadRequestException("No se encuentra el usuario a actualizar"));

        // Verificar que no haya otro usuario con el mismo username
        Optional<Usuario> usuarioConMismoUsername =
                usuarioRepository.findByUsername(usuarioRq.getUsername().toLowerCase());
        if (usuarioConMismoUsername.isPresent()
                && !usuarioConMismoUsername.get().getId().equals(id)) {
            throw new BadRequestException("Ya existe otro usuario con ese username");
        }

        // Actualizar campos
        usuarioExistente.setUsername(usuarioRq.getUsername().toLowerCase());
        usuarioExistente.setRol(usuarioRq.getRol().toUpperCase());
        usuarioExistente.setEmail(usuarioRq.getEmail());

        if (usuarioRq.getPassword() != null && !usuarioRq.getPassword().isBlank()) {
            usuarioExistente.setPassword(this.encriptarPassword(usuarioRq.getPassword()));
        }

        usuarioExistente.setActivo(usuarioRq.isActivo());

        usuarioRepository.save(usuarioExistente);

        RespuestaRs rta = new RespuestaRs();
        rta.setMensaje("El usuario se ha actualizado correctamente.");
        rta.setStatus(200);
        rta.setData(usuarioExistente);
        return rta;
    }

    @Override
    public RespuestaRs eliminarUsuario(Integer id) throws BadRequestException {
        if (id == null) throw new BadRequestException("Id inválido");

        Usuario usuario = usuarioRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new BadRequestException("No se encuentra el usuario"));

        usuarioRepository.delete(usuario);

        RespuestaRs rta = new RespuestaRs();
        rta.setMensaje("El usuario se ha eliminado correctamente.");
        rta.setStatus(200);
        return rta;
    }

    /**
     * Genera una contraseña aleatoria de 8 caracteres.
     */
    private String generarPass() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int indiceAleatorio = (int) (Math.random() * caracteres.length());
            password.append(caracteres.charAt(indiceAleatorio));
        }
        return password.toString();
    }

    private void validarCampos(UsuarioRq usuarioNuevo) throws BadRequestException {
        if (usuarioNuevo.getUsername() == null || usuarioNuevo.getUsername().isBlank()) {
            throw new BadRequestException("El campo username es obligatorio.");
        }
        if (usuarioNuevo.getEmail() == null || usuarioNuevo.getEmail().isBlank()) {
            throw new BadRequestException("El campo email es obligatorio.");
        }
        if (usuarioNuevo.getRol() == null || usuarioNuevo.getRol().isBlank()) {
            throw new BadRequestException("El campo rol es obligatorio.");
        }
    }

    private String encriptarPassword(String passAEncriptar) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(passAEncriptar.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo no soportado: MD5", e);
        }
    }
}
