/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.service;
import java.util.List;
import com.uniminuto.clinica.entity.Paciente;

/**
 *
 * @author Alkri
 */

public interface PacienteService {
    List<Paciente> listarPacientes();
    Paciente buscarPorDocumento(String numeroDocumento);
}