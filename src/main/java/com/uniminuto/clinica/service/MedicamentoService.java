package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Medicamento;
import java.util.List;

public interface MedicamentoService {
    List<Medicamento> listar();
    Medicamento crear(Medicamento medicamento);
    Medicamento obtenerPorId(Long id);
    Medicamento actualizar(Long id, Medicamento medicamento);
    void eliminar(Long id);
}

