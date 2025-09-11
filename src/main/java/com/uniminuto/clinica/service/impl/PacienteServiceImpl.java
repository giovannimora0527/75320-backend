/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.PacienteService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio para la gestión de pacientes.
 *
 * @author lmora
 */
@Service
@Transactional
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> encontrarTodosLosPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> encontrarPacientesActivos() {
        return pacienteRepository.findByActivoTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paciente> encontrarPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paciente> encontrarPacientePorDocumento(String numeroDocumento) {
        return pacienteRepository.findByNumeroDocumento(numeroDocumento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarPacientesPorNombre(String nombre) {
        return pacienteRepository.buscarPorNombre(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarPacientesPorTipoDocumento(String tipoDocumento) {
        return pacienteRepository.findByTipoDocumento(tipoDocumento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarPacientesPorRangoEdad(int edadMinima, int edadMaxima) {
        return pacienteRepository.buscarPorRangoEdad(edadMinima, edadMaxima);
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        // Validar que no exista otro paciente con el mismo número de documento
        if (paciente.getId() == null && existePacientePorDocumento(paciente.getNumeroDocumento())) {
            throw new IllegalArgumentException("Ya existe un paciente con el número de documento: " + paciente.getNumeroDocumento());
        }

        // Establecer fecha de registro si es un nuevo paciente
        if (paciente.getId() == null) {
            paciente.setFechaRegistro(LocalDateTime.now());
        }

        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        // Verificar que el paciente existe
        if (!pacienteRepository.existsById(paciente.getId())) {
            throw new IllegalArgumentException("No se encontró el paciente con ID: " + paciente.getId());
        }

        // Validar que no exista otro paciente con el mismo número de documento (excluyendo el actual)
        Optional<Paciente> pacienteExistente = pacienteRepository.findByNumeroDocumento(paciente.getNumeroDocumento());
        if (pacienteExistente.isPresent() && !pacienteExistente.get().getId().equals(paciente.getId())) {
            throw new IllegalArgumentException("Ya existe otro paciente con el número de documento: " + paciente.getNumeroDocumento());
        }

        return pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró el paciente con ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente desactivarPaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el paciente con ID: " + id));

        paciente.setActivo(false);
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente activarPaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el paciente con ID: " + id));

        paciente.setActivo(true);
        return pacienteRepository.save(paciente);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePacientePorDocumento(String numeroDocumento) {
        return pacienteRepository.existsByNumeroDocumento(numeroDocumento);
    }

    @Override
    @Transactional(readOnly = true)
    public long contarPacientesActivos() {
        return pacienteRepository.countByActivoTrue();
    }
}
