/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Paciente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrato API para gestionar pacientes.
 */
@RequestMapping("/pacientes")
public interface PacienteApi {

    /**
     * Lista todos los pacientes.
     * @return lista de pacientes
     */
    @GetMapping("/listarPaciente")
    ResponseEntity<List<Paciente>> listarPacientes();

    /**
     * Busca un paciente por número de documento.
     * @param numeroDocumento número de documento del paciente
     * @return paciente encontrado o 404
     */
    @GetMapping("/numeroDocumento/{numeroDocumento}")
    ResponseEntity<Paciente> buscarPorDocumento(@PathVariable String numeroDocumento);

}
