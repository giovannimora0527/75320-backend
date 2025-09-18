/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;
import java.time.LocalDate;
import java.util.List;
import org.apache.coyote.BadRequestException;

/**
 *
 * @author Alkri
 */
public interface PacienteService {
 /**
     * Lista todos los pacientes de la bd.
     * @return Lista de pacientes.
     */
    List<Paciente> encontrarTodosLosPacientes(); 
    Paciente encontrarPacientePorNombre(String nombres) throws BadRequestException;
    Paciente buscarPorNumeroDocumento(String numeroDocumento) throws BadRequestException;
    /**
     * se listan los pacientes por fecha de mayor a menor
     * @return
     * @throws BadRequestException 
     */
    List<Paciente>listarPorFecha()throws BadRequestException;
}