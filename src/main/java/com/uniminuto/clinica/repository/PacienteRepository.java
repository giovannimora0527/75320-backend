/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alkri
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByNombres(String nombres);
    Optional<Paciente> findByNumeroDocumento(String numeroDocumento);
    
    /**
     * se enlistan los pacientes de mayor a menor o manera Ascendente
     * @return 
     */
    List<Paciente> findAllByOrderByFechaNacimientoAsc();
    
}
