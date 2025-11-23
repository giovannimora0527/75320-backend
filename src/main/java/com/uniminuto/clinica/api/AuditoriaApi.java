/**
 * Interfaz API REST para manejar operaciones de auditoría en el sistema clínico.
 * Proporciona endpoints para recuperar registros de auditoría relacionados con operaciones de recuperación de contraseñas.
 *
 * @author Edwin Morales
 * @author Nahum Dominguez
 * @author Emily Aldana
 * @author Julian Amaya
 * @author Sebastian Paez
 */
package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.RecuperarPasswordAuditoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/auditoria")
public interface AuditoriaApi {

    /**
     * Recupera todos los registros de auditoría de recuperación de contraseñas del sistema.
     * Este endpoint retorna una lista completa de todas las entradas de auditoría relacionadas con operaciones de recuperación de contraseñas,
     * incluyendo detalles como marcas de tiempo, información de usuario y estado de la recuperación.
     *
     * @return ResponseEntity que contiene una lista de objetos RecuperarPasswordAuditoria con todos los registros de auditoría
     *         y el estado HTTP apropiado. Retorna una lista vacía si no se encuentran registros.
     */
    @RequestMapping(value = "/listar",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<RecuperarPasswordAuditoria>> listarTodosLosRegistros();
}