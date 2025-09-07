/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Paciente;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para lógica de negocio de Pacientes.
 */
public interface PacienteService {
    List<Paciente> listarTodos();
    Optional<Paciente> buscarPorDocumento(String numeroDocumento);
}

