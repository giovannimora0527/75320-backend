package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Usuario;
import java.util.List;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import org.apache.coyote.BadRequestException;



public interface UsuarioService {
    /**
     * Servicio para listar los usuarios
     */
    List<Usuario> encontrarTodosLosUsuarios();
    /**
     * Servicio para buscar usuario por username
     */
    Usuario encontrarUsuarioPorNombre(String username) throws BadRequestException;
    /**
     * Servicio para guardar usuario
     */
    RespuestaRs guardarUsuario(UsuarioRq usuarioNuevo) throws BadRequestException;

    /**
     * Actualiza un usuario registrado en la bd.
     * @param usuarioRq usuario a actualizar.
     * @return Respuesta del servicio.
     * @throws BadRequestException excepcion.
     */
    RespuestaRs actualizarUsuario(UsuarioRq usuarioRq) throws BadRequestException;
}
