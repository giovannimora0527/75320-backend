package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Cita;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@RequestMapping("/api/citas")
public interface CitaApi {

    @RequestMapping(value = "/crear",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Cita> crear(@RequestBody Cita cita);

    @RequestMapping(value = "/listar-recientes",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Cita>> listarRecientes();

    @RequestMapping(value = "/listar-por-rango",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Cita>> listarPorRango(
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta);
}










