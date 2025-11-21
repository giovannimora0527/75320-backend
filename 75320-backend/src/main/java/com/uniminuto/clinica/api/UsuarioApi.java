package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API para la gestión de usuarios del sistema.
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
 * @author Sistema
 */
@Tag(name = "Usuarios", description = "Gestión de usuarios de la plataforma (crear, actualizar, eliminar, consultar)")
@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
public interface UsuarioApi {

    /**
     * Lista todos los usuarios del sistema.
     */
    @Operation(summary = "Listar usuarios", description = "Retorna todos los usuarios registrados en el sistema")
    @GetMapping(
        value = "/listar",
        produces = "application/json"
    )
    ResponseEntity<List<Usuario>> listarUsuarios();

    /**
     * Lista usuarios por rol.
     */
    @Operation(summary = "Listar por rol", description = "Retorna los usuarios filtrados por el rol especificado")
    @GetMapping(
        value = "/listar-rol",
        produces = "application/json"
    )
    ResponseEntity<List<Usuario>> listarUsuariosPorRol(
        @Parameter(description = "Código del rol a filtrar (ej: ADMIN, MEDICO, PACIENTE)") 
        @RequestParam String rol
    );

    /**
     * Busca un usuario por nombre.
     */
    @Operation(summary = "Buscar por nombre", description = "Obtiene un usuario por su nombre de inicio de sesión")
    @GetMapping(
        value = "/buscar-nombre",
        produces = "application/json"
    )
    ResponseEntity<Usuario> buscarUsuarioPorNombre(
        @Parameter(description = "Nombre de usuario (username)") 
        @RequestParam String nombre
    ) throws BadRequestException;

    /**
     * Busca usuarios por estado (activo/inactivo).
     */
    @Operation(summary = "Listar por estado", description = "Lista usuarios filtrados por estado de actividad")
    @GetMapping(
        value = "/buscar-estado",
        produces = "application/json"
    )
    ResponseEntity<List<Usuario>> buscarUsuariosPorEstado(
        @Parameter(description = "1 = activos, 0 = inactivos") 
        @RequestParam Integer activo
    ) throws BadRequestException;

    /**
     * Guarda un nuevo usuario en el sistema.
     */
    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario y envía credenciales temporales por correo electrónico")
    @PostMapping(
        value = "/guardar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<RespuestaRs> guardarUsuario(
        @RequestBody UsuarioRq usuarioRq
    ) throws BadRequestException;

    /**
     * Actualiza un usuario existente.
     */
    @Operation(summary = "Actualizar usuario", description = "Actualiza la información de un usuario existente")
    @PostMapping(
        value = "/actualizar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<RespuestaRs> actualizarUsuario(
        @Parameter(description = "ID del usuario a actualizar") 
        @RequestParam Integer id,
        @RequestBody UsuarioRq usuarioRq
    ) throws BadRequestException;

    /**
     * Elimina un usuario por su ID.
     */
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario del sistema por su identificador")
    @PostMapping(
        value = "/eliminar",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<RespuestaRs> eliminarUsuario(
        @Parameter(description = "ID del usuario a eliminar") 
        @RequestParam Integer id
    ) throws BadRequestException;
}
