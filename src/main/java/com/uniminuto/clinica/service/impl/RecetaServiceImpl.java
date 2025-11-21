package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.MedicamentoRepository;
import com.uniminuto.clinica.repository.RecetaRepository;
import com.uniminuto.clinica.service.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecetaServiceImpl implements RecetaService {

    private final CitaRepository citaRepository;
    private final RecetaRepository recetaRepository;
    private final MedicamentoRepository medicamentoRepository;

    @Override
    public RespuestaRs guardarReceta(RecetaRq recetaRq) {
        var respuesta = new RespuestaRs();

        Integer citaId = recetaRq.getCitaId();
        Integer medicamentoId = recetaRq.getMedicamentoId();

        if (citaId == null || medicamentoId == null) {
            respuesta.setStatus(400);
            respuesta.setMessage("CitaId o MedicamentoId no pueden ser nulos");
            return respuesta;
        }

        var cita = citaRepository.findById(citaId);
        var medicamento = medicamentoRepository.findById(medicamentoId);

        if (cita.isEmpty() || medicamento.isEmpty()) {
            respuesta.setStatus(404);
            respuesta.setMessage("Cita o medicamento no encontrados");
            return respuesta;
        }

        Receta receta = new Receta();
        receta.setCita(cita.get());
        receta.setMedicamento(medicamento.get());
        receta.setDosis(recetaRq.getDosis());
        receta.setIndicaciones(recetaRq.getIndicaciones());
        receta.setFechaHora(LocalDateTime.now());

        recetaRepository.save(receta);

        respuesta.setStatus(201);
        respuesta.setMessage("Receta creada correctamente");
        respuesta.setData(receta);

        return respuesta;
    }

    @Override
    public List<Receta> listarRecetas() {
        return recetaRepository.findAllByOrderByCita_FechaHoraDesc();
    }

    @Override
    public Receta obtenerPorId(Integer id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + id));
    }

    @Override
    public RespuestaRs actualizarReceta(Integer id, RecetaRq recetaRq) {
        var respuesta = new RespuestaRs();

        Optional<Receta> recetaExistente = recetaRepository.findById(id);
        if (recetaExistente.isEmpty()) {
            respuesta.setStatus(404);
            respuesta.setMessage("Receta no encontrada con ID: " + id);
            return respuesta;
        }

        Receta receta = recetaExistente.get();

        // Actualizamos los datos solo si vienen en el request
        if (recetaRq.getCitaId() != null) {
            citaRepository.findById(recetaRq.getCitaId()).ifPresent(receta::setCita);
        }

        if (recetaRq.getMedicamentoId() != null) {
            medicamentoRepository.findById(recetaRq.getMedicamentoId()).ifPresent(receta::setMedicamento);
        }

        receta.setDosis(recetaRq.getDosis());
        receta.setIndicaciones(recetaRq.getIndicaciones());
        receta.setFechaHora(LocalDateTime.now());

        recetaRepository.save(receta);

        respuesta.setStatus(200);
        respuesta.setMessage("Receta actualizada correctamente");
        respuesta.setData(receta);

        return respuesta;
    }

    @Override
    public RespuestaRs eliminarReceta(Integer id) {
        var respuesta = new RespuestaRs();

        Optional<Receta> receta = recetaRepository.findById(id);
        if (receta.isEmpty()) {
            respuesta.setStatus(404);
            respuesta.setMessage("Receta no encontrada con ID: " + id);
            return respuesta;
        }

        recetaRepository.delete(receta.get());

        respuesta.setStatus(200);
        respuesta.setMessage("Receta eliminada correctamente");

        return respuesta;
    }
}
