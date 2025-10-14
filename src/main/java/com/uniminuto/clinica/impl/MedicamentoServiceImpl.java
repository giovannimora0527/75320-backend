package com.uniminuto.clinica.impl;

import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.repository.MedicamentoRepository;
import com.uniminuto.clinica.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<Medicamento> listar() {
        return medicamentoRepository.findAll();
    }

    @Override
    public Medicamento crear(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public Medicamento obtenerPorId(Long id) {
        return medicamentoRepository.findById(id).orElse(null);
    }

    @Override
    public Medicamento actualizar(Long id, Medicamento medicamento) {
        Medicamento existente = obtenerPorId(id);
        if (existente != null) {
            existente.setNombre(medicamento.getNombre());
            existente.setDescripcion(medicamento.getDescripcion());
            existente.setPrecio(medicamento.getPrecio());
            existente.setStock(medicamento.getStock());
            return medicamentoRepository.save(existente);
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        medicamentoRepository.deleteById(id);
    }
}

