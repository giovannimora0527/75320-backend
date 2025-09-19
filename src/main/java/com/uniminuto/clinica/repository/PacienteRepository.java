/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de datos de pacientes.
 *
 * @author lmora
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    /**
     * Busca un paciente por número de documento.
     *
     * @param numeroDocumento Número de documento del paciente
     * @return Optional con el paciente encontrado
     */
    Optional<Paciente> findByNumeroDocumento(String numeroDocumento);

    /**
     * Busca pacientes por tipo de documento.
     *
     * @param tipoDocumento Tipo de documento
     * @return Lista de pacientes con el tipo de documento especificado
     */
    List<Paciente> findByTipoDocumento(String tipoDocumento);

    /**
     * Busca pacientes activos.
     *
     * @return Lista de pacientes activos
     */
    List<Paciente> findByActivoTrue();

    /**
     * Busca pacientes por nombre (nombres o apellidos).
     *
     * @param nombre Nombre a buscar
     * @return Lista de pacientes que coinciden con el nombre
     */
    @Query("SELECT p FROM Paciente p WHERE "
            + "LOWER(p.nombres) LIKE LOWER(CONCAT('%', :nombre, '%')) OR "
            + "LOWER(p.apellidos) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Paciente> buscarPorNombre(@Param("nombre") String nombre);

    /**
     * Busca pacientes por rango de edad.
     *
     * @param edadMinima Edad mínima
     * @param edadMaxima Edad máxima
     * @return Lista de pacientes en el rango de edad especificado
     */
    @Query("SELECT p FROM Paciente p WHERE "
            + "YEAR(CURRENT_DATE) - YEAR(p.fechaNacimiento) BETWEEN :edadMinima AND :edadMaxima")
    List<Paciente> buscarPorRangoEdad(@Param("edadMinima") int edadMinima,
            @Param("edadMaxima") int edadMaxima);

    /**
     * Verifica si existe un paciente con el número de documento especificado.
     *
     * @param numeroDocumento Número de documento
     * @return true si existe, false en caso contrario
     */
    boolean existsByNumeroDocumento(String numeroDocumento);

    /**
     * Cuenta el número total de pacientes activos.
     *
     * @return Número de pacientes activos
     */
    long countByActivoTrue();

    /**
     * Encuentra todos los pacientes ordenados por fecha de nacimiento ascendente (de mayor a menor edad).
     * Fechas más antiguas primero = pacientes de mayor edad.
     */
    List<Paciente> findAllByOrderByFechaNacimientoAsc();

    /**
     * Encuentra todos los pacientes ordenados por fecha de nacimiento descendente (de menor a mayor edad).
     * Fechas más recientes primero = pacientes de menor edad.
     */
    List<Paciente> findAllByOrderByFechaNacimientoDesc();
    
}

