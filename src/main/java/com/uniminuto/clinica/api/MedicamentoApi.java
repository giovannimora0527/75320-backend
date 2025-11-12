package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.model.MedicamentoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * API de Medicamentos
 * Define los endpoints REST disponibles para la gestión de medicamentos.
 * 
 * Endpoints:
 *  - GET /listar
 *  - GET /buscar-por-nombre
 *  - POST /guardar
 *  - POST /actualizar
 *  - POST /eliminar
 *  - POST /actualizar-cantidad
 */
@CrossOrigin(origins = "*")
@RequestMapping("/medicamento")
public interface MedicamentoApi {

    @RequestMapping(
        value = "/listar",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<List<Medicamento>> listarMedicamentos();

    @RequestMapping(
        value = "/buscar-por-nombre",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<Medicamento> buscarPorNombre(@RequestParam String nombre) throws BadRequestException;

    @RequestMapping(
        value = "/guardar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> guardarMedicamento(@RequestBody MedicamentoRq medicamentoRq) throws BadRequestException;

    @RequestMapping(
        value = "/eliminar",
        produces = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> eliminarMedicamento(@RequestParam Integer id) throws BadRequestException;

    @RequestMapping(
        value = "/actualizar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> actualizarMedicamento(
        @RequestParam Integer id,
        @RequestBody MedicamentoRq medicamentoRq
    ) throws BadRequestException;

    @RequestMapping(
        value = "/actualizar-cantidad",
        produces = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> actualizarCantidad(
        @RequestParam Integer id,
        @RequestParam Integer cantidad
    ) throws BadRequestException;
}
