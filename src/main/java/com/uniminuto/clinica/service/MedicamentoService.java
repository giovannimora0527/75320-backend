package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.model.MedicamentoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.apache.coyote.BadRequestException;

public interface MedicamentoService {
    List<Medicamento> listarMedicamentos();
    Medicamento buscarPorNombre(String nombre) throws BadRequestException;
    RespuestaRs guardarMedicamento(MedicamentoRq medicamentoRq) throws BadRequestException;
    RespuestaRs eliminarMedicamento(Integer id) throws BadRequestException;
    RespuestaRs actualizarMedicamento(Integer id, MedicamentoRq medicamentoRq) throws BadRequestException;
    RespuestaRs actualizarCantidad(Integer id, Integer cantidad) throws BadRequestException;
}
