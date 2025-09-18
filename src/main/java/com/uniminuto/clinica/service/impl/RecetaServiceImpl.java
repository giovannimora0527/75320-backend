package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.entity.Receta;

import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.MedicamentoRepository;
import com.uniminuto.clinica.repository.RecetaRepository;

import com.uniminuto.clinica.service.RecetaService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecetaServiceImpl implements RecetaService {

    private final RecetaRepository recetaRepository;
    private final CitaRepository citaRepository;
    private final MedicamentoRepository medicamentoRepository;

    public RecetaServiceImpl(RecetaRepository recetaRepository,
                             CitaRepository citaRepository,
                             MedicamentoRepository medicamentoRepository) {
        this.recetaRepository = recetaRepository;
        this.citaRepository = citaRepository;
        this.medicamentoRepository = medicamentoRepository;
    }
    @Override
    public Receta createReceta(Integer medicamentoId, Long citaId, String dosis, String indicaciones) {
        // Buscar la cita
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id " + citaId));

        Medicamento medicamento = medicamentoRepository.findById(medicamentoId)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado con id " + medicamentoId));

        Receta receta = new Receta();
        receta.setCita(cita);
        receta.setMedicamento(medicamento);
        receta.setDosis(dosis);
        receta.setIndicaciones(indicaciones);
        if(receta.getFechaCreacionRegistro() == null) {
            receta.setFechaCreacionRegistro(LocalDateTime.now());
        }

        return recetaRepository.save(receta);
    }

    @Override
    public List<Receta> obtenerRecetasOrdenadasPorFechaDesc() {
        return recetaRepository.findAllByOrderByFechaCreacionRegistroDesc();
    }
}
