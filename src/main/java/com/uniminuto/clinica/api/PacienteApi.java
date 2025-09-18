<<<<<<< HEAD
package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.service.PacienteService;
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.entity.Usuario;
>>>>>>> 62bbf5ad50e20053f4fca59ad1ef11555df8f6bf
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
=======
>>>>>>> 62bbf5ad50e20053f4fca59ad1ef11555df8f6bf
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
<<<<<<< HEAD
 * @author lmora
=======
 * @author Julian
>>>>>>> 62bbf5ad50e20053f4fca59ad1ef11555df8f6bf
 */
@CrossOrigin(origins = "*")
@RequestMapping("/paciente")
public interface PacienteApi {
<<<<<<< HEAD

    /**
     * Lista los usuarios de la bd.
     *
     * @return
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> listarPacientes();

    @RequestMapping(value = "/buscar-paciente-documento",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Paciente> buscarPacienteXIdentificacion(
            @RequestParam String numeroDocumento)
            throws BadRequestException;

    /**
     *
     * @author JulianLopez
     *
     * Nuevo método: se crea nuevo endpoint para el servicio listar de mayor a
     * menor
     */
    @GetMapping("/listar-por-edad-asc")
    ResponseEntity<List<Paciente>> listarPacientesPorEdadAsc();

=======
    
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Paciente>> listarPacientes();
    
    
    @RequestMapping(value = "/buscar-documento",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Paciente> buscarPacienteXdocumento(
            @RequestParam String documento) 
            throws BadRequestException;
>>>>>>> 62bbf5ad50e20053f4fca59ad1ef11555df8f6bf
}
