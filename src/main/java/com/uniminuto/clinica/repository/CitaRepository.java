/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Cita;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    /**
     * 
     * @param pacienteId
     * @param fechaHora
     * @return 
     */
    Optional<Cita> findByPacienteIdAndFechaHora(Integer pacienteId, LocalDateTime fechaHora);

    /**
     * 
     * @param medicoId
     * @param fechaHora
     * @return 
     */     
    Optional<Cita> findByMedicoIdAndFechaHora(Long medicoId, LocalDateTime fechaHora);

    /**
     * 
     * @return 
     */
    List<Cita> findAllByOrderByFechaHoraDesc();
}
