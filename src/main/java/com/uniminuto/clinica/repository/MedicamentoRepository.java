package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositorio de medicamento
 */
/**
 * @author Anderson
 */

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {
    /**
     * Metodo listar por nombre del medicmanto
     */
    Optional<Medicamento> findByNombre(String nombre);
}