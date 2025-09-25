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

    //Dependencias a los repositorios, inyectadas para interactuar con la base de datos
    private final RecetaRepository recetaRepository;
    private final CitaRepository citaRepository;
    private final MedicamentoRepository medicamentoRepository;

    // Constructor que recibe los repositorios como parámetros.
    // Spring se encarga de inyectar automáticamente estas dependencias.
    public RecetaServiceImpl(RecetaRepository recetaRepository,
                             CitaRepository citaRepository,
                             MedicamentoRepository medicamentoRepository) {
        this.recetaRepository = recetaRepository;
        this.citaRepository = citaRepository;
        this.medicamentoRepository = medicamentoRepository;
    }
    @Override
    public Receta createReceta(Integer medicamentoId, Long citaId, String dosis, String indicaciones) {
        
        // 1. Busca la entidad Cita por su ID.
        // '.orElseThrow' lanza una excepción si la cita no se encuentra, deteniendo la ejecución.
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id " + citaId));

        // 2. Busca la entidad Medicamento por su ID, con el mismo manejo de errores.
        Medicamento medicamento = medicamentoRepository.findById(medicamentoId)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado con id " + medicamentoId));

        // 3. Crea una nueva instancia de la entidad Receta.
        Receta receta = new Receta();
        
        // 4. Establece las relaciones y los datos de la nueva entidad.
        receta.setCita(cita);
        receta.setMedicamento(medicamento);
        receta.setDosis(dosis);
        receta.setIndicaciones(indicaciones);
        
        // 5. Asigna la fecha y hora de creación si aún no ha sido establecida.
        if(receta.getFechaCreacionRegistro() == null) {
            receta.setFechaCreacionRegistro(LocalDateTime.now());
        }

        // 6. Guarda la nueva entidad en la base de datos a través del repositorio y la devuelve.
        return recetaRepository.save(receta);
    }

    @Override
    public List<Receta> obtenerRecetasOrdenadasPorFechaDesc() {
        
        // Delega la lógica de búsqueda y ordenación al repositorio.
        // Spring Data JPA implementa este método automáticamente basándose en su nombre.
        return recetaRepository.findAllByOrderByFechaCreacionRegistroDesc();
    }
}
