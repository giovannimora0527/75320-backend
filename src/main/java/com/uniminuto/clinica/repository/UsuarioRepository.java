package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de usuario
 */
/**
 * @author Anderson
 */

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    /**
     * Metodo buscar por username de usuario
     */
    Optional<Usuario> findByUsername(String username);
}