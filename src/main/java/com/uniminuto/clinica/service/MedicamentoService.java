package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.model.MedicamentoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.utils.BadRequestException;

import java.util.List;

/**
 * Servicio que define las operaciones para la gestión de medicamentos.
 */
public interface MedicamentoService {

    /**
     * Lista todos los medicamentos registrados.
     *
     * @return lista de medicamentos.
     */
    List<Medicamento> listarMedicamentos();

    /**
     * Busca un medicamento por su nombre.
     *
     * @param nombre nombre del medicamento.
     * @return medicamento encontrado.
     * @throws BadRequestException si no se encuentra el medicamento.
     */
    Medicamento buscarPorNombre(String nombre) throws BadRequestException;

    /**
     * Guarda un nuevo medicamento.
     *
     * @param medicamentoRq datos del medicamento.
     * @return respuesta del servicio.
     * @throws BadRequestException si los datos son inválidos.
     */
    RespuestaRs guardarMedicamento(MedicamentoRq medicamentoRq) throws BadRequestException;

    /**
     * Actualiza los datos de un medicamento existente.
     *
     * @param id identificador del medicamento.
     * @param medicamentoRq datos actualizados.
     * @return respuesta del servicio.
     * @throws BadRequestException si el medicamento no existe o los datos son inválidos.
     */
    RespuestaRs actualizarMedicamento(Integer id, MedicamentoRq medicamentoRq) throws BadRequestException;

    /**
     * Elimina un medicamento por su ID.
     *
     * @param id identificador del medicamento.
     * @return respuesta del servicio.
     * @throws BadRequestException si el medicamento no existe.
     */
    RespuestaRs eliminarMedicamento(Integer id) throws BadRequestException;

    /**
     * Actualiza la cantidad disponible de un medicamento.
     *
     * @param id identificador del medicamento.
     * @param cantidad nueva cantidad.
     * @return respuesta del servicio.
     * @throws BadRequestException si el medicamento no existe o la cantidad es inválida.
     */
    RespuestaRs actualizarCantidad(Integer id, Integer cantidad) throws BadRequestException;
}
