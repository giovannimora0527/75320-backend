package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Paciente.
 * Proporciona operaciones CRUD y consultas personalizadas.
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    /**
     * Busca un paciente por número de documento.
     * @param documento número del documento.
     * @return Optional con el paciente si existe.
     */
    Optional<Paciente> findByNumeroDocumento(String documento);

    /**
     * Obtiene todos los pacientes ordenados por fecha de nacimiento ascendente.
     * @return lista de pacientes ordenados por fecha de nacimiento.
     */
    @Query("SELECT p FROM Paciente p ORDER BY p.fechaNacimiento ASC")
    List<Paciente> findAllByOrderByFechaNacimientoAsc();

    /**
     * Obtiene todos los pacientes ordenados por fecha de nacimiento descendente.
     * @return lista de pacientes ordenados por fecha de nacimiento (más recientes primero).
     */
    @Query("SELECT p FROM Paciente p ORDER BY p.fechaNacimiento DESC")
    List<Paciente> findAllByOrderByFechaNacimientoDesc();
}
