package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.service.CitaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaApiController {

    @Autowired
    private CitaService citaService;

    /**
     * Crear una nueva cita
     */
    @PostMapping("/crear")
    public ResponseEntity<Cita> crearCita(@RequestBody Cita cita) {
        try {
            Cita citaCreada = citaService.crearCita(cita);
            return ResponseEntity.status(HttpStatus.CREATED).body(citaCreada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Listar todas las citas
     */
    @GetMapping("/listar")
    public ResponseEntity<List<Cita>> listarCitas() {
        List<Cita> citas = citaService.listarCitasRecientes();
        return ResponseEntity.ok(citas);
    }
}





