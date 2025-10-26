package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.apache.coyote.BadRequestException;



public interface RecetaService {
    /**
     * Servicio para listar las recetas
     */
    List<Receta> listarRecetas();
    /**
     * Servicio para guardar receta
     */
    RespuestaRs guardarReceta(RecetaRq recetaRp) throws BadRequestException;

    RespuestaRs actualizarReceta(RecetaRq recetaRq) throws BadRequestException;
}
