package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;

/**
 *
 * @author PC
 */
public interface RecetaService {
    RespuestaRs guardarReceta(RecetaRq recetaRq);
    
    List<Receta> listarRecetas();
}
