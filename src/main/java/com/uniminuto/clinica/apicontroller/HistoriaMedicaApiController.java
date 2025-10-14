package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.entity.HistoriaMedica;
import com.uniminuto.clinica.service.HistoriaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historias")
@CrossOrigin(origins = "http://localhost:4200")
public class HistoriaMedicaApiController {

    @Autowired
    private HistoriaMedicaService service;

    @GetMapping("/listar")
    public ResponseEntity<List<HistoriaMedica>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping("/crear")
    public ResponseEntity<HistoriaMedica> crear(@RequestBody HistoriaMedica historia) {
        HistoriaMedica nueva = service.crear(historia);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody HistoriaMedica historia) {
        return service.actualizar(id, historia)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}



