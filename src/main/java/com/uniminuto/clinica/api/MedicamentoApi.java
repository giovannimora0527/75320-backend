package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Medicamento;
import com.uniminuto.clinica.model.MedicamentoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;             
import io.swagger.v3.oas.annotations.tags.Tag;              
import io.swagger.v3.oas.annotations.media.Content;         
import io.swagger.v3.oas.annotations.media.Schema;         
import io.swagger.v3.oas.annotations.responses.ApiResponse; 

@CrossOrigin(origins = "*")
@Tag(name = "Medicamentos", description = "CRUD de medicamentos")   
@RequestMapping("/medicamento")
public interface MedicamentoApi {

    @Operation(
            summary = "Listar medicamentos",
            description = "Retorna todos los medicamentos registrados.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado obtenido correctamente",
                            content = @Content(schema = @Schema(implementation = Medicamento.class))
                    )
            }
    )
    @RequestMapping(
            value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET   // ← CONSUMES QUITADO
    )
    ResponseEntity<List<Medicamento>> listarMedicamentos();


    @Operation(
            summary = "Guardar medicamento",
            description = "Crea un nuevo medicamento.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Medicamento guardado",
                            content = @Content(schema = @Schema(implementation = RespuestaRs.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos",
                            content = @Content(schema = @Schema(implementation = RespuestaRs.class))
                    )
            }
    )
    @RequestMapping(
            value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},  // POST → ok
            method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> guardarMedicamento(
            @RequestBody MedicamentoRq medicamentoRq
    ) throws BadRequestException;


    @Operation(
            summary = "Actualizar medicamento",
            description = "Actualiza un medicamento existente.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Actualización exitosa",
                            content = @Content(schema = @Schema(implementation = RespuestaRs.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error en los datos",
                            content = @Content(schema = @Schema(implementation = RespuestaRs.class))
                    )
            }
    )
    @RequestMapping(
            value = "/actualizar",
            produces = {"application/json"},
            consumes = {"application/json"},  // POST → ok
            method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> actualizarMedicamento(
            @RequestBody MedicamentoRq medicamentoRq
    ) throws BadRequestException;
}
