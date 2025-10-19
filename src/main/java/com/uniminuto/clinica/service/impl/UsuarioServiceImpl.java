package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import com.uniminuto.clinica.repository.UsuarioRepository;
import com.uniminuto.clinica.service.UsuarioService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author lmora
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    /**
     * UsuarioRepository.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> encontrarTodosLosUsuarios() {        
        return this.usuarioRepository.findAll();
    }

    @Override
    public Usuario encontrarUsuarioPorNombre(String username) 
            throws BadRequestException {
        Optional<Usuario> optUser = this.usuarioRepository
                .findByUsername(username);
        if (!optUser.isPresent()) {
            throw new BadRequestException("No se encuentra el usuario.");
        }
        
        return optUser.get();
    }

    @Override
    public RespuestaRs guardarUsuario(UsuarioRq usuarioNuevo)
            throws BadRequestException {
        // Paso 1. Validar que los campos llegue bien
        this.validarCampos(usuarioNuevo);
        // Paso 2. Consulto si existe el usuario por username
        Optional<Usuario> optUser = this.usuarioRepository
                .findByUsername(usuarioNuevo.getUsername().toLowerCase());
        if (optUser.isPresent()) {
            // Paso 3. Si existe lanzo error que ya existe el usuario
            throw new BadRequestException("El usuario ya existe.");
        }
        // Paso 4. Creo el usuario y seteo los campos que lleguen del post
        Usuario nuevo = new Usuario();
        nuevo.setActivo(true);
        nuevo.setFechaCreacion(LocalDateTime.now());
        nuevo.setPassword(this.encriptarPassword(usuarioNuevo.getPassword()));
        nuevo.setRol(usuarioNuevo.getRol().toUpperCase());
        nuevo.setUsername(usuarioNuevo.getUsername().toLowerCase());

        this.usuarioRepository.save(nuevo);

        // Paso 5. Devuelve respuesta ok
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("El usuario se ha guardado correctamente.");
        rta.setStatus(200);
        return rta;
    }

    private void validarCampos(UsuarioRq usuarioNuevo)
            throws BadRequestException {
        if (usuarioNuevo.getUsername() == null
                || usuarioNuevo.getUsername().isBlank()
                || usuarioNuevo.getUsername().isEmpty()) {
            throw new BadRequestException("El campo username es obligatorio.");
        }
        if (usuarioNuevo.getPassword() == null
                || usuarioNuevo.getPassword().isBlank()
                || usuarioNuevo.getPassword().isEmpty()) {
            throw new BadRequestException("El campo pass es obligatorio.");
        }
        if (usuarioNuevo.getRol() == null
                || usuarioNuevo.getRol().isBlank()
                || usuarioNuevo.getRol().isEmpty()) {
            throw new BadRequestException("El campo rol es obligatorio.");
        }
    }

    private String encriptarPassword(String passAEncriptar) {
        String algoritmo = "MD5";
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo); // Ej: "SHA-256", "MD5"
            byte[] hashBytes = md.digest(passAEncriptar.getBytes());

            // Convertir a hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo no soportado: " + algoritmo, e);
        }
    }
    
    @Override
    public RespuestaRs actualizarUsuario(Integer id, UsuarioRq usuarioRq) 
        throws BadRequestException {
    // Validar que el ID no sea nulo
        if (id == null) {
        throw new BadRequestException("Id inválido");
        }
    
    // Validar los campos (username y rol son obligatorios, password es opcional en edición)
        if (usuarioRq.getUsername() == null
            || usuarioRq.getUsername().isBlank()
            || usuarioRq.getUsername().isEmpty()) {
        throw new BadRequestException("El campo username es obligatorio.");
        }
        if (usuarioRq.getRol() == null
            || usuarioRq.getRol().isBlank()
            || usuarioRq.getRol().isEmpty()) {
            throw new BadRequestException("El campo rol es obligatorio.");
        }
    
    // Buscar el usuario existente por ID
        Optional<Usuario> optUsuario = this.usuarioRepository.findById(id);
        if (!optUsuario.isPresent()) {
            throw new BadRequestException("No se encuentra el usuario a actualizar");
        }
    
    // Verificar que no exista otro usuario con el mismo username
        Optional<Usuario> usuarioConMismoUsername = this.usuarioRepository
                .findByUsername(usuarioRq.getUsername().toLowerCase());
        if (usuarioConMismoUsername.isPresent() 
                && !usuarioConMismoUsername.get().getId().equals(id)) {
            throw new BadRequestException("Ya existe otro usuario con ese username");
        }
    
    // Obtener el usuario existente y actualizar sus datos
        Usuario usuarioExistente = optUsuario.get();
        usuarioExistente.setUsername(usuarioRq.getUsername().toLowerCase());
        usuarioExistente.setRol(usuarioRq.getRol().toUpperCase());
    
    // Solo actualizar la contraseña si se proporciona una nueva
        if (usuarioRq.getPassword() != null 
                && !usuarioRq.getPassword().isBlank() 
                && !usuarioRq.getPassword().isEmpty()) {
            usuarioExistente.setPassword(this.encriptarPassword(usuarioRq.getPassword()));
    }
    
    // Actualizar el estado activo si viene en el request
        if (usuarioRq.getActivo() != null) {
            usuarioExistente.setActivo(usuarioRq.getActivo());
        }
    
    // Guardar los cambios
        this.usuarioRepository.save(usuarioExistente);
    
    // Preparar respuesta
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("El usuario se ha actualizado correctamente");
        rta.setStatus(200);
        return rta;
    }

    @Override
    public RespuestaRs eliminarUsuario(Integer id) throws BadRequestException {
        if (id == null) {
            throw new BadRequestException("Id inválido");
        }
    
        Optional<Usuario> optUsuario = this.usuarioRepository.findById(id);
        if (!optUsuario.isPresent()) {
            throw new BadRequestException("No se encuentra el usuario");
        }
    
        this.usuarioRepository.deleteById(id);
    
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("El usuario se ha eliminado correctamente");
        rta.setStatus(200);
        return rta;
    }

}
