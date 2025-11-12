package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.model.MedicoRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import jakarta.validation.Valid;
import com.uniminuto.clinica.utils.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * API de Médicos
 * Define los endpoints REST disponibles para la gestión de médicos.
 * 
 * Endpoints:
 *  - GET /listar → Lista todos los médicos
 *  - GET /test → Endpoint de prueba
 *  - GET /listar-por-especializacion → Lista médicos filtrados por especialización
 *  - POST /guardar → Guarda un nuevo médico
 * 
 * @author 
 */
@CrossOrigin(origins = "*")
@RequestMapping("/medico")
public interface MedicoApi {

    @RequestMapping(
        value = "/listar",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<List<Medico>> listarMedicos();

    /**
     * Endpoint de prueba simple.
     */
    @RequestMapping(
        value = "/test",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<String> testMedico();

    /**
     * Lista los médicos por especialización.
     *
     * @param codigo Código de la especialización
     * @return Lista de médicos asociados a esa especialización
     * @throws BadRequestException si el código no es válido o no hay resultados
     */
    @RequestMapping(
        value = "/listar-por-especializacion",
        produces = {"application/json"},
        method = RequestMethod.GET
    )
    ResponseEntity<List<Medico>> listarMedicosPorEspecializacion(
        @RequestParam String codigo
    ) throws BadRequestException;

    /**
     * Guarda un nuevo médico en la base de datos.
     *
     * @param medicoRq Datos del médico a registrar
     * @return Respuesta con mensaje y estado del registro
     * @throws BadRequestException si los datos son inválidos
     */
    @RequestMapping(
        value = "/guardar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> guardarMedico(
        @RequestBody @Valid MedicoRq medicoRq
    ) throws BadRequestException;

    /**
     * Actualiza un médico existente.
     *
     * @param id Identificador del médico
     * @param medicoRq Datos actualizados del médico
     * @return Respuesta con mensaje y estado de la actualización
     * @throws BadRequestException si el médico no existe o los datos son inválidos
     */
    @RequestMapping(
        value = "/actualizar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> actualizarMedico(
        @RequestParam Integer id,
        @RequestBody @Valid MedicoRq medicoRq
    ) throws BadRequestException;

    /**
     * Elimina un médico por su ID.
     *
     * @param id Identificador del médico a eliminar
     * @return Respuesta con mensaje y estado de la eliminación
     * @throws BadRequestException si el médico no existe
     */
    @RequestMapping(
        value = "/eliminar",
        produces = {"application/json"},
        method = RequestMethod.POST
    )
    ResponseEntity<RespuestaRs> eliminarMedico(
        @RequestParam Integer id
    ) throws BadRequestException;
}
