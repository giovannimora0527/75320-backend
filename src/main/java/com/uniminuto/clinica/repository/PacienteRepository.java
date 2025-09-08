/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.uniminuto.clinica.entity.Paciente;

/**
 *
 * @author Alkri
 */

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByNumeroDocumento(String numeroDocumento);
}
