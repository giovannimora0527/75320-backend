package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.UsuarioApi;
import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import com.uniminuto.clinica.service.UsuarioService;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para la gestión de usuarios.
 * Implementa la interfaz UsuarioApi.
 *
 * Endpoints:
 *  - GET  /usuario/listar → Lista todos los usuarios
 *  - GET  /usuario/listar-rol?rol= → Lista usuarios por rol
 *  - GET  /usuario/buscar-nombre?nombre= → Busca usuario por nombre
 *  - GET  /usuario/buscar-estado?activo= → Busca usuarios por estado
 *  - POST /usuario/guardar → Crea un nuevo usuario
 *  - POST /usuario/actualizar?id= → Actualiza un usuario
 *  - POST /usuario/eliminar?id= → Elimina un usuario
 *
 * @author lmora
 */
@RestController
public class UsuarioApiController implements UsuarioApi {

    private final UsuarioService usuarioService;

    // ✅ Inyección por constructor (mejor práctica)
    public UsuarioApiController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Lista todos los usuarios registrados.
     */
    @Override
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodosLosUsuarios());
    }

    /**
     * Lista los usuarios filtrados por rol.
     */
    @Override
    public ResponseEntity<List<Usuario>> listarUsuariosPorRol(String rol) {
        return ResponseEntity.ok(usuarioService.encontrarPorRol(rol));
    }

    /**
     * Busca un usuario por su nombre.
     */
    @Override
    public ResponseEntity<Usuario> buscarUsuarioPorNombre(String nombre)
            throws BadRequestException {
        return ResponseEntity.ok(usuarioService.encontrarPorNombre(nombre));
    }

    /**
     * Busca los usuarios por su estado (activo/inactivo).
     */
    @Override
    public ResponseEntity<List<Usuario>> buscarUsuariosPorEstado(Integer activo)
            throws BadRequestException {
        return ResponseEntity.ok(usuarioService.buscarPorEstado(activo));
    }

    /**
     * Guarda un nuevo usuario.
     */
    @Override
    public ResponseEntity<RespuestaRs> guardarUsuario(UsuarioRq usuarioRq)
            throws BadRequestException {
        return ResponseEntity.ok(usuarioService.guardarUsuario(usuarioRq));
    }

    /**
     * Actualiza los datos de un usuario existente por su ID.
     */
    @Override
    public ResponseEntity<RespuestaRs> actualizarUsuario(Integer id, UsuarioRq usuarioRq)
            throws BadRequestException {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuarioRq));
    }

    /**
     * Elimina un usuario existente.
     */
    @Override
    public ResponseEntity<RespuestaRs> eliminarUsuario(Integer id)
            throws BadRequestException {
        return ResponseEntity.ok(usuarioService.eliminarUsuario(id));
    }
}
