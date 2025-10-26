package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.model.MedicamentoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;
import java.util.List;



public interface MedicamentoService {
    /**
     * Servicio para listar medicamento
     */
    List<Medicamento> listarAllMedicamentos();
    /**
     * Servicio para guardar medicamento
     */
    RespuestaRs guardarMedicamento(MedicamentoRq medicamento) throws BadRequestException;
    /**
     * Servicio para buscar por id de medicamento
     */
    Medicamento buscarPorId(Integer id) throws BadRequestException;
    /**
     * Servicio para actualizar medicamento
     */
    RespuestaRs actualizarMedicamento(MedicamentoRq medicamento) throws BadRequestException;
}
