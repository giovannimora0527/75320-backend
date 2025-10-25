package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.UsuarioApi;
import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import com.uniminuto.clinica.service.UsuarioService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de la Api de usuario
 */
/**
 * @author Anderson
 */

@RestController
public class UsuarioApiController implements UsuarioApi {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.encontrarTodosLosUsuarios());
    }

    @Override
    public ResponseEntity<Usuario> buscarUsuarioXUsername(String username)
            throws BadRequestException {
        return ResponseEntity.ok(usuarioService
                .encontrarUsuarioPorNombre(username));
    }

    @Override
    public ResponseEntity<RespuestaRs> guardarUsuario(UsuarioRq usuarioRq) throws BadRequestException {
        return ResponseEntity.ok(usuarioService.guardarUsuario(usuarioRq));
    }
    
    @Override
    public ResponseEntity<RespuestaRs> actualizarUsuario(UsuarioRq usuarioRq) throws BadRequestException {
        return ResponseEntity.ok(this.usuarioService.actualizarUsuario(usuarioRq));
    }
}
