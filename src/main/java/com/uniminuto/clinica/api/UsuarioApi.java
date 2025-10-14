package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Usuario;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
public interface UsuarioApi {

    // ✅ GET sin consumes
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Usuario>> listarUsuarios();

    // ✅ GET sin consumes
    @RequestMapping(value = "/buscar-usuario",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Usuario> buscarUsuarioXUsername(
            @RequestParam String username)
            throws BadRequestException;

    // ✅ POST con consumes (correcto)
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario);
}


