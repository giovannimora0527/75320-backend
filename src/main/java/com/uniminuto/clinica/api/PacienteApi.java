package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.model.PacienteRq;
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
 * API de Pacientes
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
 * @author 
 */
@CrossOrigin(origins = "*")
@RequestMapping("/paciente")
public interface PacienteApi {

    @RequestMapping(
        value = "/listar",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<List<Paciente>> listarPacientes();

    @RequestMapping(
        value = "/buscar-paciente-documento",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<Paciente> buscarPacienteXIdentificacion(
        @RequestParam String numeroDocumento
    ) throws BadRequestException;

    @RequestMapping(
        value = "/guardar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> guardarPaciente(
        @RequestBody PacienteRq pacienteRq
    ) throws BadRequestException;

    @RequestMapping(
        value = "/actualizar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> actualizarPaciente(
        @RequestParam Integer id,
        @RequestBody PacienteRq pacienteRq
    ) throws BadRequestException;

    @RequestMapping(
        value = "/eliminar",
        produces = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> eliminarPaciente(
        @RequestParam Integer id
    ) throws BadRequestException;

    @RequestMapping(
        value = "/por-edad",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<List<Paciente>> listarPacientesPorEdad();

    @RequestMapping(
        value = "/listar-orden-fecha-nacimiento",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<List<Paciente>> listarPacientesXOrden(
        @RequestParam String orden
    );
}
