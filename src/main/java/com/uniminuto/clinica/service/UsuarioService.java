package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Usuario;
import java.util.List;

import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author lmora
 */
public interface UsuarioService {
    /**
     * Lista todos los usuarios de la bd.
     * @return Lista de usuarios.
     */
    List<Usuario> encontrarTodosLosUsuarios();
    
    /**
     * Buscamos un usuario dado su username.
     * @param username username a buscar.
     * @return Usuario que cumpla con el criterio.
     */
    Usuario encontrarUsuarioPorNombre(String username) throws BadRequestException;

    /**
     * Guarda un usuario nuevo en la bd.
     * @param usuarioNuevo usuario a guardar.
     * @return Respuesta del servicio.
     * @throws BadRequestException excepcion.
     */
    RespuestaRs guardarUsuario(UsuarioRq usuarioNuevo) throws BadRequestException;
    
    /**
    * Actualiza un usuario existente.
    * @param id ID del usuario a actualizar.
    * @param usuarioRq Usuario con los datos actualizados.
    * @return Respuesta del servicio.
    * @throws BadRequestException excepcion.
    */
    RespuestaRs actualizarUsuario(Integer id, UsuarioRq usuarioRq) throws BadRequestException;

    /**
    * Elimina un usuario de la bd.
    * @param id ID del usuario a eliminar.
    * @return Respuesta del servicio.
    * @throws BadRequestException excepcion.
    */
    RespuestaRs eliminarUsuario(Integer id) throws BadRequestException;
}
