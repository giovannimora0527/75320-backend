package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import com.uniminuto.clinica.utils.BadRequestException;

import jakarta.mail.MessagingException;
import java.util.List;

/**
 * Servicio para la gestión de usuarios.
 * Define las operaciones CRUD y consultas específicas.
 */
public interface UsuarioService {

    /**
     * Lista todos los usuarios registrados en el sistema.
     *
     * @return lista de usuarios.
     */
    List<Usuario> listarTodosLosUsuarios();

    /**
     * Busca usuarios por su rol.
     *
     * @param rol rol a buscar.
     * @return lista de usuarios con el rol especificado.
     */
    List<Usuario> encontrarPorRol(String rol);

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param nombreUsuario nombre de usuario a buscar.
     * @return el usuario encontrado.
     * @throws BadRequestException si no se encuentra el usuario.
     */
    Usuario encontrarPorNombre(String nombreUsuario) throws BadRequestException;

    /**
     * Busca usuarios según su estado (activo/inactivo).
     *
     * @param estado estado del usuario (1 activo, 0 inactivo, etc.).
     * @return lista de usuarios que coincidan con el estado.
     */
    List<Usuario> buscarPorEstado(Integer estado);

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param usuarioNuevo datos del nuevo usuario.
     * @return respuesta del servicio.
     * @throws BadRequestException si los datos son inválidos.
     * @throws MessagingException si ocurre un error al enviar notificación por correo.
     */
    RespuestaRs guardarUsuario(UsuarioRq usuarioNuevo) throws BadRequestException, MessagingException;

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id identificador del usuario.
     * @param usuarioRq datos actualizados del usuario.
     * @return respuesta del servicio.
     * @throws BadRequestException si los datos son inválidos o el usuario no existe.
     */
    RespuestaRs actualizarUsuario(Integer id, UsuarioRq usuarioRq) throws BadRequestException;

    /**
     * Elimina un usuario por su identificador.
     *
     * @param id identificador del usuario a eliminar.
     * @return respuesta del servicio.
     * @throws BadRequestException si el usuario no existe.
     */
    RespuestaRs eliminarUsuario(Integer id) throws BadRequestException;
}
