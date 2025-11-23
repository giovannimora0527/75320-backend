package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;           
import io.swagger.v3.oas.annotations.tags.Tag;            
import io.swagger.v3.oas.annotations.media.Content;       
import io.swagger.v3.oas.annotations.media.Schema;        
import io.swagger.v3.oas.annotations.responses.ApiResponse; 

@CrossOrigin(origins = "*")
@Tag(name = "Pacientes", description = "Gestión de pacientes del sistema")
@RequestMapping("/paciente")
public interface PacienteApi {

    /**
     * Lista los usuarios de la bd.
     *
     * @return
     */
    @Operation(
            summary = "Listar pacientes",
            description = "Retorna todos los pacientes registrados.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado exitoso",
                            content = @Content(schema = @Schema(implementation = Paciente.class))
                    )
            }
    )
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> listarPacientes();


    @Operation(
            summary = "Buscar paciente por documento",
            description = "Permite buscar un paciente por su número de identificación.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Paciente encontrado",
                            content = @Content(schema = @Schema(implementation = Paciente.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Documento inválido",
                            content = @Content(schema = @Schema(implementation = Paciente.class))
                    )
            }
    )
    @RequestMapping(value = "/buscar-paciente-documento",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Paciente> buscarPacienteXIdentificacion(
            @RequestParam String numeroDocumento)
            throws BadRequestException;


    /**
     * Lista los usuarios de la bd.
     *
     * @return
     */
    @Operation(
            summary = "Listar pacientes por orden de fecha nacimiento",
            description = "Permite ordenar los pacientes por fecha de nacimiento asc o desc.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado ordenado correctamente",
                            content = @Content(schema = @Schema(implementation = Paciente.class))
                    )
            }
    )
    @RequestMapping(value = "/listar-orden-fecha-nacimiento",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> listarPacientesXOrden(
            @RequestParam String orden
    );
}
