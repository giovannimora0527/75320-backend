package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Receta;
import java.util.List;

public interface RecetaService {

    //método que se encarga de crear una nueva receta. 
    Receta createReceta(Integer medicamentoId, Long citaId, String dosis, String indicaciones);

    //método diseñado para obtener una lista de todas las recetas, ordenadas por fecha en orden descendente.
    List<Receta> obtenerRecetasOrdenadasPorFechaDesc();
}



