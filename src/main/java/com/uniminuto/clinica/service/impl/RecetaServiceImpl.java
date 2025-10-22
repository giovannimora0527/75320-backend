package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.MedicamentoRepository;
import com.uniminuto.clinica.repository.RecetaRepository;
import com.uniminuto.clinica.service.RecetaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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
    public Receta creacionReceta(Receta receta) {
        // 🔍 Validar que tenga cita y medicamento
        if (receta.getCita() == null || receta.getMedicamento() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe tener cita y medicamento válidos");
        }

        // ⚠️ Verificar que la cita exista
        Cita cita = citaRepository.findById(receta.getCita().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cita no existe"));

        // ⚠️ Verificar que el medicamento exista
        Medicamento medicamento = medicamentoRepository.findById(receta.getMedicamento().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El medicamento no existe"));

        receta.setCita(cita);
        receta.setMedicamento(medicamento);
        receta.setFechaRegistro(LocalDateTime.now()); // fecha actual al crear

        return recetaRepository.save(receta);
    }

    @Override
    public List<Receta> listadeReceta() {
        return recetaRepository.findAll();
    }

    @Override
    public Receta actualizarReceta(Long id, Receta receta) {
        Receta existente = recetaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receta no encontrada"));

        if (receta.getCita() != null) {
            Cita cita = citaRepository.findById(receta.getCita().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cita no existe"));
            existente.setCita(cita);
        }

        if (receta.getMedicamento() != null) {
            Medicamento medicamento = medicamentoRepository.findById(receta.getMedicamento().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El medicamento no existe"));
            existente.setMedicamento(medicamento);
        }

        existente.setDosis(receta.getDosis());
        existente.setIndicaciones(receta.getIndicaciones());
        existente.setFechaRegistro(LocalDateTime.now()); // actualizar fecha

        return recetaRepository.save(existente);
    }

    @Override
    public void eliminarReceta(Long id) {
        if (!recetaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receta no encontrada");
        }
        recetaRepository.deleteById(id);
    }
}
