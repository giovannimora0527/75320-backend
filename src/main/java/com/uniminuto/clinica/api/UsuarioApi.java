package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Usuario;
import java.util.List;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
public interface UsuarioApi {
    /**
     * Endpoint para listar los usuarios
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Usuario>> listarUsuarios();
    /**
     * Endpoint para buscar los usuarios por username
     */
    @RequestMapping(value = "/buscar-usuario",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Usuario> buscarUsuarioXUsername(
            @RequestParam String username)
            throws BadRequestException;
    /**
     * Endpoint para guardar usuario en la bd
     */
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> guardarUsuario(
            @RequestBody UsuarioRq usuarioRq)
            throws BadRequestException;

    /**
     * Guarda un usuario nuevo en la bd.
     * @param usuarioRq Usuario de entrada.
     * @return respuesta del servicio.
     * @throws BadRequestException excepcion.
     */
    @RequestMapping(value = "/actualizar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> actualizarUsuario(
            @RequestBody UsuarioRq usuarioRq)
            throws BadRequestException;
}
