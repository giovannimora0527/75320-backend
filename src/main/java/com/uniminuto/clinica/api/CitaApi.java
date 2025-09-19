/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Cita;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * API REST para la gestiÃ³n de citas mÃ©dicas.
 * Proporciona servicios para crear, listar y gestionar citas mÃ©dicas.
 * Establece la relaciÃ³n entre cita-mÃ©dico y cita-paciente.
 *
 * @author anago
 */
@CrossOrigin(origins = "*")
@RequestMapping("/api/citas")
public interface CitaApi {

    /**
     * Crea una nueva cita mÃ©dica.
     * Establece la relaciÃ³n entre cita-mÃ©dico y cita-paciente.
     *
     * @param cita Objeto Cita a crear
     * @return Cita creada con estado HTTP 200
     */
    @RequestMapping(value = "/crear",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Cita> crearCita(@RequestBody Cita cita);

    /**
     * Lista todas las citas ordenadas por fecha y hora descendente.
     * Las citas mÃ¡s recientes aparecen primero.
     *
     * @return Lista de citas ordenadas por fecha y hora
     */
    @RequestMapping(value = "/listar-ordenadas",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Cita>> listarCitasOrdenadas();

    /**
     * Busca citas por ID de paciente.
     *
     * @param pacienteId ID del paciente
     * @return Lista de citas del paciente
     */
    @RequestMapping(value = "/buscar-por-paciente/{pacienteId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Cita>> buscarCitasPorPaciente(@PathVariable("pacienteId") Long pacienteId);

    /**
     * Busca citas por ID de mÃ©dico.
     *
     * @param medicoId ID del mÃ©dico
     * @return Lista de citas del mÃ©dico
     */
    @RequestMapping(value = "/buscar-por-medico/{medicoId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Cita>> buscarCitasPorMedico(@PathVariable("medicoId") Long medicoId);

    /**
     * Busca citas por estado.
     *
     * @param estado Estado de la cita (programada, confirmada, completada, cancelada)
     * @return Lista de citas con el estado especificado
     */
    @RequestMapping(value = "/buscar-por-estado",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Cita>> buscarCitasPorEstado(@RequestParam("estado") String estado);

    /**
     * Actualiza una cita existente.
     *
     * @param id ID de la cita a actualizar
     * @param cita Cita con los datos actualizados
     * @return Cita actualizada
     */
    @RequestMapping(value = "/actualizar/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Cita> actualizarCita(@PathVariable("id") Long id, @RequestBody Cita cita);

    /**
     * Cancela una cita por su ID.
     *
     * @param id ID de la cita a cancelar
     * @return Cita cancelada
     */
    @RequestMapping(value = "/cancelar/{id}",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Cita> cancelarCita(@PathVariable("id") Long id);

    /**
     * Confirma una cita por su ID.
     *
     * @param id ID de la cita a confirmar
     * @return Cita confirmada
     */
    @RequestMapping(value = "/confirmar/{id}",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Cita> confirmarCita(@PathVariable("id") Long id);

    /**
     * Elimina una cita por su ID.
     *
     * @param id ID de la cita a eliminar
     * @return Respuesta de confirmaciÃ³n
     */
    @RequestMapping(value = "/eliminar/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> eliminarCita(@PathVariable("id") Long id);
}