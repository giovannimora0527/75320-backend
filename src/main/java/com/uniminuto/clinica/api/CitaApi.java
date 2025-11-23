package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@CrossOrigin(origins = "*")
@Tag(name = "Citas", description = "Gestión de las citas médicas")
@RequestMapping("/cita")
public interface CitaApi {

    @Operation(
        summary = "Listar todas las citas",
        description = "Devuelve la lista completa de citas."
    )
    @RequestMapping(
        value = "/listar",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<List<Cita>> listarCitas();

    @Operation(
        summary = "Guardar nueva cita",
        description = "Crea una nueva cita con los datos proporcionados.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Guardado OK",
                content = @Content(schema = @Schema(implementation = RespuestaRs.class))),
            @ApiResponse(responseCode = "400", description = "Petición inválida",
                content = @Content(schema = @Schema(implementation = RespuestaRs.class)))
        }
    )
    @RequestMapping(
        value = "/guardar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> guardarCita(
            @RequestBody @Valid CitaRq citaRq
    ) throws BadRequestException;


    @Operation(
        summary = "Listar citas por paciente",
        description = "Retorna las citas asociadas al paciente indicado por ID."
    )
    @RequestMapping(
        value = "/listar-citas-paciente",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<List<Cita>> listarCitasPorPaciente(
            @RequestParam Integer pacienteIds
    ) throws BadRequestException;
}
