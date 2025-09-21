package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.model.MedicamentoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* Api de medicamento
*/
/**
 * @author Anderson
 */

@CrossOrigin(origins = "*")
@RequestMapping("/medicamento")
public interface MedicamentoApi {
    /**
     * Endpoint para listar los medicamentos
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Medicamento>> listarMedicamentos();
    /**
     * Endpoint para guardar los medicamentos
     */
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> guardarMedicamento(
            @RequestBody MedicamentoRq medicamentoRq
    ) throws BadRequestException;
    /**
     * Endpoint para buscar los medicamentos por Id
     */
    @RequestMapping(value = "/buscar-por-id",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Medicamento> buscarMedicamentoPorId(
            @RequestParam Integer id
    ) throws BadRequestException;
    /**
     * Endpoint para actualizar la entidad medicamentos
     */
    @RequestMapping(value = "/actualizar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> actualizarMedicamento(
            @RequestBody MedicamentoRq medicamentoRq
    ) throws BadRequestException;
}
