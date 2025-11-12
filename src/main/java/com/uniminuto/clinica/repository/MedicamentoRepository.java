package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad Medicamento.
 * Proporciona operaciones CRUD y consultas personalizadas.
 */
@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

    /**
     * Busca un medicamento por su nombre.
     * @param nombre nombre del medicamento.
     * @return un Optional con el medicamento si existe.
     */
    Optional<Medicamento> findByNombre(String nombre);
}
