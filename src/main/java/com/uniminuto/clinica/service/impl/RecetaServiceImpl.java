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
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<Receta> listarRecetasOrdenadas() {
        return this.recetaRepository.findAllByOrderByFechaCreacionRegistroDesc();
    }

    @Override
    public RespuestaRs guardarReceta(RecetaRq recetaRq) throws BadRequestException {
        Optional<Medicamento> optMedicamento = this
                .medicamentoRepository.findById(recetaRq.getMedicamentoId());
        if (optMedicamento.isEmpty()) {
            throw new BadRequestException("El medicamento con ID " + recetaRq.getMedicamentoId() + " no existe.");
        }

        Optional<Cita> optCita = this.citaRepository.findById(recetaRq.getCitaId());
        if (optCita.isEmpty()) {
            throw new BadRequestException("La cita con ID " + recetaRq.getCitaId() + " no existe.");
        }

        Receta recetaGuardar = this.convertRecetaRqToReceta(
                recetaRq,
                optCita.get(),
                optMedicamento.get()
        );
        this.recetaRepository.save(recetaGuardar);
        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Receta guardada con éxito.");
        return rta;
    }

    @Override
    public RespuestaRs actualizarReceta(RecetaRq recetaRq) throws BadRequestException {
        if (recetaRq.getId() == null) {
            throw new BadRequestException("El ID de la receta es obligatorio para actualizar.");
        }
        Optional<Receta> optReceta = this.recetaRepository.findById(recetaRq.getId());
        if (optReceta.isEmpty()) {
            throw new BadRequestException("La receta con ID " + recetaRq.getId() + " no existe.");
        }
        Receta recetaActual = optReceta.get();
        if (recetaRq.getMedicamentoId() == null) {
            throw new BadRequestException("El ID del medicamento es obligatorio para actualizar.");
        }

        /**
         * El medicamento no puede ser cambiado en la actualización de la receta
         */
        if (!recetaActual.getMedicamento().getId().equals(recetaRq.getMedicamentoId())) {
            // Busco si el medicamento existe por el id.
            Optional<Medicamento> optMedicamento = this.medicamentoRepository
                    .findById(recetaRq.getMedicamentoId());
            if (optMedicamento.isEmpty()) {
                throw new BadRequestException("El medicamento con ID " + recetaRq.getMedicamentoId() + " no existe.");
            }
            //Cambio el medicamento
            recetaActual.setMedicamento(optMedicamento.get());
        }

        recetaActual.setIndicaciones(recetaRq.getIndicaciones());
        recetaActual.setDosis(recetaRq.getDosis());
        recetaActual.setFechaActualizacionRegistro(LocalDateTime.now());
        this.recetaRepository.save(recetaActual);

        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Receta actualizada con éxito.");
        return rta;
    }

    private Receta convertRecetaRqToReceta(RecetaRq recetaRq, Cita cita, Medicamento medicamento) {
        Receta receta = new Receta();
        receta.setCita(cita);
        receta.setMedicamento(medicamento);
        receta.setDosis(recetaRq.getDosis());
        receta.setIndicaciones(recetaRq.getIndicaciones());
        return receta;
    }



}
