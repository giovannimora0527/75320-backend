/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Paciente;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * API REST para la lógica de pacientes.
 *
 * @author lmora
 */
@CrossOrigin(origins = "*")
@RequestMapping("/api/pacientes")
public interface PacienteApi {

    /**
     * Lista todos los pacientes.
     *
     * @return Lista de todos los pacientes
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> listarPacientes();

    /**
     * Lista todos los pacientes activos.
     *
     * @return Lista de pacientes activos
     */
    @RequestMapping(value = "/listar-activos",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> listarPacientesActivos();

    /**
     * Busca un paciente por su ID.
     *
     * @param id ID del paciente
     * @return Paciente encontrado
     */
    @RequestMapping(value = "/buscar/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Paciente> buscarPacientePorId(@PathVariable("id") Long id);

    /**
     * Busca un paciente por número de documento.
     *
     * @param numeroDocumento Número de documento del paciente
     * @return Paciente encontrado
     */
    @RequestMapping(value = "/buscar-por-documento",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Paciente> buscarPacientePorDocumento(@RequestParam("numeroDocumento") String numeroDocumento);

    /**
     * Busca pacientes por nombre.
     *
     * @param nombre Nombre a buscar
     * @return Lista de pacientes que coinciden con el nombre
     */
    @RequestMapping(value = "/buscar-por-nombre",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> buscarPacientesPorNombre(@RequestParam("nombre") String nombre);

    /**
     * Busca pacientes por tipo de documento.
     *
     * @param tipoDocumento Tipo de documento
     * @return Lista de pacientes con el tipo de documento especificado
     */
    @RequestMapping(value = "/buscar-por-tipo-documento",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> buscarPacientesPorTipoDocumento(@RequestParam("tipoDocumento") String tipoDocumento);

    /**
     * Busca pacientes por rango de edad.
     *
     * @param edadMinima Edad mínima
     * @param edadMaxima Edad máxima
     * @return Lista de pacientes en el rango de edad especificado
     */
    @RequestMapping(value = "/buscar-por-edad",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> buscarPacientesPorEdad(
            @RequestParam("edadMinima") int edadMinima,
            @RequestParam("edadMaxima") int edadMaxima);

    /**
     * Crea un nuevo paciente.
     *
     * @param paciente Paciente a crear
     * @return Paciente creado
     */
    @RequestMapping(value = "/crear",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Paciente> crearPaciente(@RequestBody Paciente paciente);

    /**
     * Actualiza un paciente existente.
     *
     * @param id ID del paciente a actualizar
     * @param paciente Paciente con los datos actualizados
     * @return Paciente actualizado
     */
    @RequestMapping(value = "/actualizar/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Paciente> actualizarPaciente(@PathVariable("id") Long id, @RequestBody Paciente paciente);

    /**
     * Elimina un paciente por su ID.
     *
     * @param id ID del paciente a eliminar
     * @return Respuesta de confirmación
     */
    @RequestMapping(value = "/eliminar/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> eliminarPaciente(@PathVariable("id") Long id);

    /**
     * Desactiva un paciente.
     *
     * @param id ID del paciente a desactivar
     * @return Paciente desactivado
     */
    @RequestMapping(value = "/desactivar/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Paciente> desactivarPaciente(@PathVariable("id") Long id);

    /**
     * Activa un paciente.
     *
     * @param id ID del paciente a activar
     * @return Paciente activado
     */
    @RequestMapping(value = "/activar/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Paciente> activarPaciente(@PathVariable("id") Long id);

    /**
     * Obtiene el conteo de pacientes activos.
     *
     * @return Número de pacientes activos
     */
    @RequestMapping(value = "/contar-activos",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Long> contarPacientesActivos();
}
