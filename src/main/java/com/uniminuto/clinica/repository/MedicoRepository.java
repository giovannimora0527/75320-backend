package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.entity.Medico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de medico
 */
/**
 * @author Anderson
 */

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    /**
     * Metodo listar por especializacion de medico
     */
    List<Medico> findByEspecializacion(Especializacion espec);
}