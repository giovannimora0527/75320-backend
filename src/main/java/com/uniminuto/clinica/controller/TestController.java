package com.uniminuto.clinica.controller;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public String test() {
        return "¡Funciona!";
    }

    @GetMapping("/db")
    public String testDatabase() {
        try {
            long count = pacienteRepository.count();
            return "Conexión a la base de datos exitosa. Total de pacientes: " + count;
        } catch (Exception e) {
            return "Error en la base de datos: " + e.getMessage();
        }
    }

    @GetMapping("/pacientes")
    public String testPacientes() {
        try {
            var pacientes = pacienteRepository.findAll();
            return "Pacientes encontrados: " + pacientes.size() + " - " + pacientes.toString();
        } catch (Exception e) {
            return "Error al obtener pacientes: " + e.getMessage();
        }
    }
}
