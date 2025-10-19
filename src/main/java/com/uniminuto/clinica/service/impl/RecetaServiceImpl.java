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

import java.util.List;

/**
 *
 * @author PC
 */

@Service
@RequiredArgsConstructor
public class RecetaServiceImpl implements RecetaService {
    
    private final CitaRepository citaRepository;
    private final RecetaRepository recetaRepository;
    private final MedicamentoRepository medicamentoRepository;
    
    public RespuestaRs guardarReceta(RecetaRq recetaRq) {
        var respuesta = new RespuestaRs();

        var cita = citaRepository.findById(recetaRq.getCitaId());
        var medicamento = medicamentoRepository.findById(recetaRq.getMedicamentoId());



        if (cita.isEmpty() || medicamento.isEmpty()) {
            respuesta.setStatus(400);
            respuesta.setMessage("Cita o medicamento no encontrado");
            return respuesta;
        }

        Receta receta = new Receta();
        receta.setCita(cita.get());
        receta.setMedicamento(medicamento.get());
        receta.setDosis(recetaRq.getDosis());
        receta.setIndicaciones(recetaRq.getIndicaciones());

        recetaRepository.save(receta);

        respuesta.setStatus(200);
        respuesta.setMessage("Receta creada correctamente");
        return respuesta;
    }
    
    @Override
    public List<Receta> listarRecetas() {
        return this.recetaRepository.findAllOrderByCitaFechaHoraDesc();
    }
    
}
