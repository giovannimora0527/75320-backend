package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.repository.UsuarioRepository;
import com.uniminuto.clinica.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lmora
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    /**
     * UsuarioRepository.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> encontrarTodosLosUsuarios() {        
        return this.usuarioRepository.findAll();
    }

    @Override
    public Usuario encontrarUsuarioPorNombre(String username) 
            throws BadRequestException {
        Optional<Usuario> optUser = this.usuarioRepository
                .findByUsername(username);
        if (!optUser.isPresent()) {
            throw new BadRequestException("No se encuentra el usuario.");
        }
        
        return optUser.get();
    }
    
}
