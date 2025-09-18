package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Receta;
import java.util.List;

public interface RecetaService {

    Receta createReceta(Integer medicamentoId, Long citaId, String dosis, String indicaciones);

    Receta getRecetaById(Long id);

    List<Receta> getRecetasByCita(Long citaId);
}



