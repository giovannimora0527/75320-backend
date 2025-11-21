package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.RecetaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.utils.BadRequestException;

import java.util.List;

/**
 * Servicio para la gestión de recetas médicas.
 * Define las operaciones CRUD y consultas relacionadas.
 */
public interface RecetaService {

    /**
     * Lista todas las recetas médicas registradas.
     *
     * @return lista de recetas.
     */
    List<Receta> listarRecetas();

    /**
     * Guarda una nueva receta médica.
     *
     * @param recetaRq datos de la receta.
     * @return respuesta del servicio.
     * @throws BadRequestException si los datos son inválidos.
     */
    RespuestaRs guardarReceta(RecetaRq recetaRq) throws BadRequestException;

    /**
     * Obtiene una receta por su identificador.
     *
     * @param id identificador de la receta.
     * @return la receta encontrada.
     * @throws BadRequestException si la receta no existe.
     */
    Receta obtenerPorId(Integer id) throws BadRequestException;

    /**
     * Actualiza los datos de una receta existente.
     *
     * @param id identificador de la receta.
     * @param recetaRq datos actualizados.
     * @return respuesta del servicio.
     * @throws BadRequestException si la receta no existe o los datos son inválidos.
     */
    RespuestaRs actualizarReceta(Integer id, RecetaRq recetaRq) throws BadRequestException;

    /**
     * Elimina una receta por su identificador.
     *
     * @param id identificador de la receta.
     * @return respuesta del servicio.
     * @throws BadRequestException si la receta no existe.
     */
    RespuestaRs eliminarReceta(Integer id) throws BadRequestException;
}
