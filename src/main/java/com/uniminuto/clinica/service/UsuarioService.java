package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Usuario;
import java.util.List;
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
}
