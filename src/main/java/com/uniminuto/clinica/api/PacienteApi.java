/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.uniminuto.clinica.entity.Paciente;

/**
 *
 * @author Alkri
 */

@RequestMapping("/paciente")
public interface PacienteApi {

    @GetMapping("/EncontrarPacientes")
    ResponseEntity<List<Paciente>> listarPacientes();

    @GetMapping("/usuarios/{documento}")
    ResponseEntity<Paciente> buscarPorDocumento(@PathVariable String documento);
}
