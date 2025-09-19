/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio para gestionar operaciones de base de datos para la entidad Cita.
 *
 * @author anago
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    /**
     * Encuentra citas por ID de paciente.
     *
     * @param pacienteId ID del paciente
     * @return Lista de citas del paciente
     */
    List<Cita> findByPacienteId(Long pacienteId);

    /**
     * Encuentra citas por ID de mÃ©dico.
     *
     * @param medicoId ID del mÃ©dico
     * @return Lista de citas del mÃ©dico
     */
    List<Cita> findByMedicoId(Long medicoId);

    /**
     * Encuentra citas por estado.
     *
     * @param estado Estado de la cita
     * @return Lista de citas con el estado especificado
     */
    List<Cita> findByEstado(String estado);

    /**
     * Encuentra citas entre un rango de fechas.
     *
     * @param startFecha Fecha inicial
     * @param endFecha Fecha final
     * @return Lista de citas en el rango de fechas
     */
    @Query("SELECT c FROM Cita c WHERE c.fechaHora BETWEEN :startFecha AND :endFecha ORDER BY c.fechaHora DESC")
    List<Cita> findCitasBetweenDates(@Param("startFecha") LocalDateTime startFecha, 
                                   @Param("endFecha") LocalDateTime endFecha);

    public List<Cita> findAllByOrderByFechaHoraDesc();

    public List<Cita> findByFechaHoraBetweenOrderByFechaHoraDesc(LocalDateTime desde, LocalDateTime hasta);

    public long countByEstado(String estado);
}