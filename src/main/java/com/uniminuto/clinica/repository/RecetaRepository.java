/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para gestionar operaciones de base de datos para la entidad Receta.
 *
 * @author anago
 */
@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    /**
     * Encuentra todas las recetas ordenadas por fecha de creación descendente.
     *
     * @return Lista de recetas ordenadas por fecha de creación descendente
     */
    @Query("SELECT r FROM Receta r ORDER BY r.fechaCreacionRegistro DESC")
    List<Receta> findAllByOrderByFechaCreacionRegistroDesc();

    /**
     * Encuentra recetas por ID de cita.
     *
     * @param citaId ID de la cita
     * @return Lista de recetas asociadas a la cita
     */
    @Query("SELECT r FROM Receta r WHERE r.cita.id = :citaId ORDER BY r.fechaCreacionRegistro DESC")
    List<Receta> findByCitaId(@Param("citaId") Long citaId);

    /**
     * Encuentra recetas por ID de medicamento.
     *
     * @param medicamentoId ID del medicamento
     * @return Lista de recetas con el medicamento especificado
     */
    @Query("SELECT r FROM Receta r WHERE r.medicamento.id = :medicamentoId")
    List<Receta> findByMedicamentoId(@Param("medicamentoId") Long medicamentoId);

    /**
     * Cuenta el número de recetas por cita.
     *
     * @param citaId ID de la cita
     * @return Número de recetas para la cita
     */
    @Query("SELECT COUNT(r) FROM Receta r WHERE r.cita.id = :citaId")
    Long countByCitaId(@Param("citaId") Long citaId);

    public long countByPacienteId(Long pacienteId);

    public List<Receta> findByMedicoId(Long medicoId);

    public List<Receta> findByPacienteId(Long pacienteId);
}