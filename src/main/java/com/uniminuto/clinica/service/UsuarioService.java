package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.repository.UsuarioRepository;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ✅ Guardar usuario
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // ✅ Listar todos los usuarios
    public List<Usuario> encontrarTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    // ✅ Buscar usuario por nombre
    public Usuario encontrarUsuarioPorNombre(String username) throws BadRequestException {
        return usuarioRepository.findByUsername(username).orElse(null);
    }
}
