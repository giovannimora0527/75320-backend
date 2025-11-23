package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Medico;

import java.util.List;

import com.uniminuto.clinica.model.MedicoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "*")
@Tag(
    name = "Médicos",
    description = "Gestión de médicos"
)
@RequestMapping("/medico")
public interface MedicoApi {

    @Operation(
            summary = "Listar médicos",
            description = "Retorna todos los médicos registrados.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado obtenido correctamente",
                            content = @Content(schema = @Schema(implementation = Medico.class))
                    )
            }
    )
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Medico>> listarMedicos();


    @Operation(
            summary = "Listar médicos por especialización",
            description = "Devuelve los médicos filtrados por el código de especialización.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado obtenido",
                            content = @Content(schema = @Schema(implementation = Medico.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Código inválido",
                            content = @Content(schema = @Schema(implementation = RespuestaRs.class))
                    )
            }
    )
    @RequestMapping(value = "/listar-x-cod-esp",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Medico>> listarMedicosporEspecialidad(
            @RequestParam String codigo
    ) throws BadRequestException;


    @Operation(
            summary = "Guardar médico",
            description = "Registra un nuevo médico.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Médico guardado correctamente",
                            content = @Content(schema = @Schema(implementation = RespuestaRs.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos",
                            content = @Content(schema = @Schema(implementation = RespuestaRs.class))
                    )
            }
    )
    @RequestMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> guardarMedico(
            @RequestBody @Valid MedicoRq medicoRq
    ) throws BadRequestException;

}
