package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.repository.CitaRepository;
import com.uniminuto.clinica.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recetas") // ✅ URL base coherente con tus pruebas
@CrossOrigin(origins = "*")
public class RecetaApiController {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private CitaRepository citaRepository;

    // ✅ Crear receta
    @PostMapping("/crear")
    public ResponseEntity<?> crearReceta(@RequestBody Receta recetaInput) {
        try {
            Receta recetaNueva = new Receta();

            // ✅ Se agrega descripción
            recetaNueva.setDescripcion(recetaInput.getDescripcion());
            recetaNueva.setMedicamento(recetaInput.getMedicamento());
            recetaNueva.setIndicaciones(recetaInput.getIndicaciones());
            recetaNueva.setFechaCreacionRegistro(LocalDateTime.now());

            // 🔹 Asociar cita si viene en el request
            if (recetaInput.getCita() != null && recetaInput.getCita().getId() != null) {
                Optional<Cita> citaOpt = citaRepository.findById(recetaInput.getCita().getId());
                citaOpt.ifPresent(recetaNueva::setCita);
            }

            recetaRepository.save(recetaNueva);
            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Receta registrada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Error al crear la receta: " + e.getMessage());
        }
    }

    // ✅ Listar todas las recetas
    @GetMapping("/listar")
    public ResponseEntity<List<Receta>> listarRecetas() {
        List<Receta> recetas = recetaRepository.findAll();
        return ResponseEntity.ok(recetas);
    }
}

