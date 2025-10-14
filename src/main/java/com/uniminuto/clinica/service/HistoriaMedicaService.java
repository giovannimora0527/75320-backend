package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.HistoriaMedica;
import com.uniminuto.clinica.repository.HistoriaMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriaMedicaService {

    @Autowired
    private HistoriaMedicaRepository repository;

    public List<HistoriaMedica> listar() {
        return repository.findAll();
    }

    public HistoriaMedica crear(HistoriaMedica historia) {
        return repository.save(historia);
    }

    public Optional<HistoriaMedica> actualizar(Long id, HistoriaMedica historia) {
        return repository.findById(id).map(existente -> {
            existente.setDiagnostico(historia.getDiagnostico());
            existente.setTratamiento(historia.getTratamiento());
            existente.setFecha(historia.getFecha());
            existente.setPaciente(historia.getPaciente());
            existente.setMedico(historia.getMedico());
            return repository.save(existente);
        });
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}

