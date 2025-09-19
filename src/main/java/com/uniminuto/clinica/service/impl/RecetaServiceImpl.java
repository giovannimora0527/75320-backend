package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.RecetaRepository;
import com.uniminuto.clinica.service.RecetaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public Receta crear(Receta receta) {
        if (receta.getCita() == null || receta.getCita().getId() == null) {
            throw new IllegalArgumentException("La receta debe estar asociada a una cita válida");
        }
        Cita cita = citaRepository.findById(receta.getCita().getId())
                .orElseThrow(() -> new IllegalArgumentException("La cita asociada no existe"));
        receta.setCita(cita);
        return recetaRepository.save(receta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Receta> listarRecetas() {
        return recetaRepository.findAllByOrderByFechaCreacionRegistroDesc();
    }
}










