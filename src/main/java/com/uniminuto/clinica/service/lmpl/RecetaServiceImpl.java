package com.uniminuto.clinica.service.Impl;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.repository.RecetaRepository;
import com.uniminuto.clinica.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    public List<Receta> encontrarTodasLasRecetas() {
        return recetaRepository.findAll();
    }

    public Optional<Receta> encontrarRecetaPorId(Long id) {
        return recetaRepository.findById(id);
    }

    public List<Receta> encontrarRecetasPorPaciente(Long pacienteId) {
        return recetaRepository.findByPacienteId(pacienteId);
    }

    public List<Receta> encontrarRecetasPorMedico(Long medicoId) {
        return recetaRepository.findByMedicoId(medicoId);
    }

    public Receta guardarReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    public Receta actualizarReceta(Receta receta) {
        if (!recetaRepository.existsById(receta.getId())) {
            throw new IllegalArgumentException("Receta no encontrada con ID: " + receta.getId());
        }
        return recetaRepository.save(receta);
    }

    @Override
    public void eliminarReceta(Long id) {
        if (!recetaRepository.existsById(id)) {
            throw new IllegalArgumentException("Receta no encontrada con ID: " + id);
        }
        recetaRepository.deleteById(id);
    }

    public long contarRecetasPorPaciente(Long pacienteId) {
        return recetaRepository.countByPacienteId(pacienteId);
    }

    @Override
    public List<Receta> getAllRecetasOrdenadas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Receta> getRecetasPorCita(Long citaId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Receta crearReceta(Receta receta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Receta getRecetaPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Receta actualizarReceta(Long id, Receta receta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
