/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.MedicamentoApi;
import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.model.MedicamentoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.MedicamentoService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Controlador de la Api de medicamento
 */

@RestController
public class MedicamentoApiController implements MedicamentoApi {

    @Autowired
    private MedicamentoService medicamentoService;

    @Override
    public ResponseEntity<List<Medicamento>> listarMedicamentos() {
        return ResponseEntity.ok(this.medicamentoService.listarAllMedicamentos());
    }

    @Override
    public ResponseEntity<RespuestaRs> guardarMedicamento(MedicamentoRq medicamentoRq)
            throws BadRequestException {
        return ResponseEntity.ok(this.medicamentoService.guardarMedicamento(medicamentoRq));
    }

    @Override
    public ResponseEntity<Medicamento> buscarMedicamentoPorId(Integer id) throws BadRequestException {
        return ResponseEntity.ok(this.medicamentoService.buscarPorId(id));
    }

    @Override
    public ResponseEntity<RespuestaRs> actualizarMedicamento(MedicamentoRq medicamentoRq) throws BadRequestException {
        return ResponseEntity.ok(this.medicamentoService.actualizarMedicamento(medicamentoRq));
    }
}