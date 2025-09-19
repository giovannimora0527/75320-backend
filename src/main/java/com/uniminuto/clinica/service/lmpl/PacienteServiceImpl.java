/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.service.Impl;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.repository.PacienteRepository;
import com.uniminuto.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> encontrarTodosLosPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public List<Paciente> encontrarPacientesActivos() {
        return pacienteRepository.findByActivoTrue();
    }

    @Override
    public Optional<Paciente> encontrarPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Optional<Paciente> encontrarPacientePorDocumento(String numeroDocumento) {
        return pacienteRepository.findByNumeroDocumento(numeroDocumento);
    }

    @Override
    public List<Paciente> buscarPacientesPorNombre(String nombre) {
        return pacienteRepository.buscarPorNombre(nombre);
    }

    @Override
    public List<Paciente> buscarPacientesPorTipoDocumento(String tipoDocumento) {
        return pacienteRepository.findByTipoDocumento(tipoDocumento);
    }

    @Override
    public List<Paciente> buscarPacientesPorRangoEdad(int edadMinima, int edadMaxima) {
        return pacienteRepository.buscarPorRangoEdad(edadMinima, edadMaxima);
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        // Aquí deberías agregar validaciones antes de guardar
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        // Verificar que el paciente existe antes de actualizar
        if (!pacienteRepository.existsById(paciente.getId())) {
            throw new IllegalArgumentException("Paciente no encontrado con ID: " + paciente.getId());
        }
        return pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Paciente no encontrado con ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente desactivarPaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado con ID: " + id));
        paciente.setActivo(false);
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente activarPaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado con ID: " + id));
        paciente.setActivo(true);
        return pacienteRepository.save(paciente);
    }

    @Override
    public boolean existePacientePorDocumento(String numeroDocumento) {
        return pacienteRepository.existsByNumeroDocumento(numeroDocumento);
    }

    @Override
    public long contarPacientesActivos() {
        return pacienteRepository.countByActivoTrue();
    }
    
    @Override
    public List<Paciente> encontrarPacientesOrdenadosPorEdadAsc() {
        return pacienteRepository.findAllByOrderByFechaNacimientoAsc();
    }

    @Override
    public List<Paciente> encontrarPacientesOrdenadosPorEdadDesc() {
        return pacienteRepository.findAllByOrderByFechaNacimientoDesc();
    }
}