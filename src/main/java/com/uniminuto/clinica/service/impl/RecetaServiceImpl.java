package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.MedicamentoRepository;
import com.uniminuto.clinica.repository.RecetaRepository;
import com.uniminuto.clinica.service.RecetaService;

import lombok.RequiredArgsConstructor;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de recetas médicas.
 * Combina validaciones, control de errores y respuesta estandarizada.
 */
@Service
@RequiredArgsConstructor
public class RecetaServiceImpl implements RecetaService {

    private final CitaRepository citaRepository;
    private final RecetaRepository recetaRepository;
    private final MedicamentoRepository medicamentoRepository;

    @Override
    public List<Receta> listarRecetas() {
        try {
            List<Receta> recetas = recetaRepository.findAll();
            if (recetas == null) {
                return new java.util.ArrayList<>();
            }
            return recetas;
        } catch (Exception e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }

    @Override
    public RespuestaRs guardarReceta(RecetaRq recetaRq) throws BadRequestException {
        if (recetaRq.getCitaId() == null || recetaRq.getMedicamentoId() == null) {
            throw new BadRequestException("CitaId y MedicamentoId son obligatorios.");
        }

        Optional<Cita> citaOpt = citaRepository.findById(recetaRq.getCitaId());
        Optional<Medicamento> medicamentoOpt = medicamentoRepository.findById(recetaRq.getMedicamentoId());

        if (citaOpt.isEmpty()) {
            throw new BadRequestException("La cita con ID " + recetaRq.getCitaId() + " no existe.");
        }
        if (medicamentoOpt.isEmpty()) {
            throw new BadRequestException("El medicamento con ID " + recetaRq.getMedicamentoId() + " no existe.");
        }

        Cita cita = citaOpt.get();
        Medicamento medicamento = medicamentoOpt.get();

        // Evita duplicar recetas para la misma cita y medicamento
        List<Receta> duplicadas = recetaRepository.findByCitaAndMedicamento(cita, medicamento);
        if (!duplicadas.isEmpty()) {
            throw new BadRequestException("Ya existe una receta para esta cita y medicamento.");
        }

        Receta receta = new Receta();
        receta.setCita(cita);
        receta.setMedicamento(medicamento);
        receta.setDosis(recetaRq.getDosis());
        receta.setIndicaciones(recetaRq.getIndicaciones());
        receta.setFechaCreacion(LocalDateTime.now());

        recetaRepository.save(receta);

        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(201);
        respuesta.setMensaje("Receta creada correctamente.");
        respuesta.setData(receta);
        return respuesta;
    }

    @Override
    public Receta obtenerPorId(Integer id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + id));
    }

    @Override
    public RespuestaRs actualizarReceta(Integer id, RecetaRq recetaRq) throws BadRequestException {
        Optional<Receta> recetaExistente = recetaRepository.findById(id);
        if (recetaExistente.isEmpty()) {
            throw new BadRequestException("No existe la receta con ID " + id);
        }

        Receta receta = recetaExistente.get();

        if (recetaRq.getCitaId() != null) {
            citaRepository.findById(recetaRq.getCitaId()).ifPresent(receta::setCita);
        }

        if (recetaRq.getMedicamentoId() != null) {
            medicamentoRepository.findById(recetaRq.getMedicamentoId()).ifPresent(receta::setMedicamento);
        }

        receta.setDosis(recetaRq.getDosis());
        receta.setIndicaciones(recetaRq.getIndicaciones());
        receta.setFechaCreacion(LocalDateTime.now());

        recetaRepository.save(receta);

        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Receta actualizada correctamente.");
        respuesta.setData(receta);
        return respuesta;
    }

    @Override
    public RespuestaRs eliminarReceta(Integer id) throws BadRequestException {
        Optional<Receta> receta = recetaRepository.findById(id);
        if (receta.isEmpty()) {
            throw new BadRequestException("No se encuentra la receta con ID " + id);
        }

        recetaRepository.delete(receta.get());

        RespuestaRs respuesta = new RespuestaRs();
        respuesta.setStatus(200);
        respuesta.setMensaje("Receta eliminada correctamente.");
        return respuesta;
    }
}
