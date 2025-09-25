package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.model.MedicamentoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface MedicamentoService {

    //método diseñado para obtener una lista de todos los medicamentos disponibles.
    List<Medicamento> listarAllMedicamentos();

    //método para guardar un nuevo medicamento. 
    RespuestaRs guardarMedicamento(MedicamentoRq medicamento) throws BadRequestException;

    //método que se encarga de buscar un medicamento específico por su ID
    Medicamento buscarPorId(Integer id) throws BadRequestException;

    //método que se usa para actualizar los datos de un medicamento existente. 
    RespuestaRs actualizarMedicamento(MedicamentoRq medicamento) throws BadRequestException;
}
