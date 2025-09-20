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
 * @author Andre
 */
@Repository
public interface CitaRepository extends JpaRepository <Cita, Long>{
    Optional<Cita> findByMedico_IdAndFechaHora(Long medicoId, LocalDateTime fechaHora);
    
    
    /**
     * Metodo listar por fecha
     */
    List<Cita>findAllByOrderByFechaHoraDesc();
}
