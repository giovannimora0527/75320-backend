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

    private Receta convertRecetaRqToReceta(RecetaRq recetaRq, Cita cita, Medicamento medicamento) {
        Receta receta = new Receta();
        receta.setCita(cita);
        receta.setMedicamento(medicamento);
        receta.setDosis(recetaRq.getDosis());
        receta.setIndicaciones(recetaRq.getIndicaciones());
        return receta;
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

        Optional<Medicamento> optMedicamento = this.medicamentoRepository.findById(recetaRq.getMedicamentoId());
        if (optMedicamento.isEmpty()) {
            throw new BadRequestException("El medicamento con ID " + recetaRq.getMedicamentoId() + " no existe.");
        }

        Optional<Cita> optCita = this.citaRepository.findById(recetaRq.getCitaId());
        if (optCita.isEmpty()) {
            throw new BadRequestException("La cita con ID " + recetaRq.getCitaId() + " no existe.");
        }

        Receta recetaActualizar = optReceta.get();
        recetaActualizar.setCita(optCita.get());
        recetaActualizar.setMedicamento(optMedicamento.get());
        recetaActualizar.setDosis(recetaRq.getDosis());
        recetaActualizar.setIndicaciones(recetaRq.getIndicaciones());

        this.recetaRepository.save(recetaActualizar);

        RespuestaRs rta = new RespuestaRs();
        rta.setStatus(200);
        rta.setMessage("Receta actualizada con éxito.");
        return rta;
    }


}
