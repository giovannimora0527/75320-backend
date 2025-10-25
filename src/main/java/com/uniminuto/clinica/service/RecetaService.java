package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.apache.coyote.BadRequestException;

public interface RecetaService {

    // Crear receta
    RespuestaRs guardarReceta(RecetaRq recetaRq);

    // Listar todas las recetas
    List<Receta> listarRecetas();

    // Obtener receta por ID
    Receta obtenerPorId(Integer id);

    // Eliminar receta
    RespuestaRs eliminarReceta(Integer id) throws BadRequestException;
    
    // Actualizar receta
    RespuestaRs actualizarReceta(Integer id, RecetaRq recetaRq) throws BadRequestException;
}
