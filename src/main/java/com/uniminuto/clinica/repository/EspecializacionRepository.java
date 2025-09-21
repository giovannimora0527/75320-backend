package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Especializacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de especializacion
 */
/**
 * @author Anderson
 */

@Repository
public interface EspecializacionRepository extends JpaRepository<Especializacion, Integer> {
    /**
     * Metodo listar por especializacion
     */
    Optional<Especializacion> findByCodigoEspecializacion(String codigo);
}