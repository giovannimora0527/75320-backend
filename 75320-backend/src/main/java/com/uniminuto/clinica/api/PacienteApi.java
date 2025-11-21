package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.model.PacienteRq;
import com.uniminuto.clinica.model.RespuestaRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * API de Pacientes.
 * Define los endpoints REST disponibles para la gestión de pacientes.
 * 
 * Endpoints:
 *  - GET /listar → Lista todos los pacientes
 *  - GET /buscar-paciente-documento → Busca paciente por documento
 *  - POST /guardar → Crea un paciente
 *  - POST /actualizar → Actualiza un paciente
 *  - POST /eliminar → Elimina un paciente
 *  - GET /por-edad → Lista pacientes ordenados por edad
 *  - GET /listar-orden-fecha-nacimiento → Lista pacientes según orden (asc/desc)
 * 
 * @author Sistema
 */
@Tag(name = "Pacientes", description = "Operaciones de gestión de pacientes (CRUD y consultas)")
@CrossOrigin(origins = "*")
@RequestMapping("/paciente")
public interface PacienteApi {

    @Operation(summary = "Listar pacientes", description = "Retorna todos los pacientes registrados en el sistema")
    @RequestMapping(
        value = "/listar",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<List<Paciente>> listarPacientes();

    @Operation(summary = "Buscar por documento", description = "Busca un paciente por su número de documento de identidad")
    @RequestMapping(
        value = "/buscar-paciente-documento",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<Paciente> buscarPacienteXIdentificacion(
        @Parameter(description = "Número de documento del paciente") 
        @RequestParam String numeroDocumento
    ) throws BadRequestException;

    @Operation(summary = "Crear paciente", description = "Registra un nuevo paciente en la base de datos")
    @RequestMapping(
        value = "/guardar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> guardarPaciente(
        @RequestBody PacienteRq pacienteRq
    ) throws BadRequestException;

    @Operation(summary = "Actualizar paciente", description = "Actualiza los datos de un paciente existente")
    @RequestMapping(
        value = "/actualizar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> actualizarPaciente(
        @Parameter(description = "ID del paciente a actualizar") 
        @RequestParam Integer id,
        @RequestBody PacienteRq pacienteRq
    ) throws BadRequestException;

    @Operation(summary = "Eliminar paciente", description = "Elimina un paciente por su identificador")
    @RequestMapping(
        value = "/eliminar",
        produces = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> eliminarPaciente(
        @Parameter(description = "ID del paciente a eliminar") 
        @RequestParam Integer id
    ) throws BadRequestException;

    @Operation(summary = "Listar por edad", description = "Retorna pacientes ordenados por edad")
    @RequestMapping(
        value = "/por-edad",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<List<Paciente>> listarPacientesPorEdad();

    @Operation(summary = "Listar ordenados por fecha de nacimiento", description = "Retorna pacientes ordenados por fecha de nacimiento (ascendente o descendente)")
    @RequestMapping(
        value = "/listar-orden-fecha-nacimiento",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<List<Paciente>> listarPacientesXOrden(
        @Parameter(description = "Orden: 'asc' para ascendente, 'desc' para descendente") 
        @RequestParam String orden
    );
}
