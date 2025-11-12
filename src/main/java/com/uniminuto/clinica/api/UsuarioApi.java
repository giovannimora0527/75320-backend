package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import java.util.List;
import jakarta.mail.MessagingException;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API para la gestión de usuarios.
 * 
 * Endpoints:
 *  - GET  /usuario/listar → Lista todos los usuarios.
 *  - GET  /usuario/listar-rol → Lista usuarios por rol.
 *  - GET  /usuario/buscar-nombre → Busca usuario por nombre.
 *  - GET  /usuario/buscar-estado → Lista usuarios por estado.
 *  - POST /usuario/guardar → Crea un nuevo usuario.
 *  - POST /usuario/actualizar → Actualiza un usuario existente.
 *  - POST /usuario/eliminar → Elimina un usuario.
 * 
 * @author 
 */
@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
public interface UsuarioApi {

    /**
     * Lista todos los usuarios del sistema.
     */
    @GetMapping(
        value = "/listar",
        produces = "application/json"
    )
    ResponseEntity<List<Usuario>> listarUsuarios();

    /**
     * Lista usuarios por rol.
     */
    @GetMapping(
        value = "/listar-rol",
        produces = "application/json"
    )
    ResponseEntity<List<Usuario>> listarUsuariosPorRol(
        @RequestParam String rol
    );

    /**
     * Busca un usuario por nombre.
     */
    @GetMapping(
        value = "/buscar-nombre",
        produces = "application/json"
    )
    ResponseEntity<Usuario> buscarUsuarioPorNombre(
        @RequestParam String nombre
    ) throws BadRequestException;

    /**
     * Busca usuarios por estado (activo/inactivo).
     */
    @GetMapping(
        value = "/buscar-estado",
        produces = "application/json"
    )
    ResponseEntity<List<Usuario>> buscarUsuariosPorEstado(
        @RequestParam Integer activo
    ) throws BadRequestException;

    /**
     * Guarda un nuevo usuario en el sistema.
     */
    @PostMapping(
        value = "/guardar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<RespuestaRs> guardarUsuario(
        @RequestBody UsuarioRq usuarioRq
    ) throws BadRequestException, MessagingException;

    /**
     * Actualiza un usuario existente.
     */
    @PostMapping(
        value = "/actualizar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<RespuestaRs> actualizarUsuario(
        @RequestParam Integer id,
        @RequestBody UsuarioRq usuarioRq
    ) throws BadRequestException;

    /**
     * Elimina un usuario por su ID.
     */
    @PostMapping(
        value = "/eliminar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<RespuestaRs> eliminarUsuario(
        @RequestParam Integer id
    ) throws BadRequestException;
}
