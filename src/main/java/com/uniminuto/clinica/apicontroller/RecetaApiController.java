package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.repository.RecetaRepository;
import com.uniminuto.clinica.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/recetas")
public class RecetaApiController {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private CitaRepository citaRepository;

    @PostMapping("/crear")
    public Receta crearReceta(@RequestBody Receta recetaInput) {
        Cita cita = citaRepository.findById(recetaInput.getCita().getId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        recetaInput.setCita(cita);
        recetaInput.setFechaCreacionRegistro(LocalDateTime.now());

        return recetaRepository.save(recetaInput);
    }

    @GetMapping("/{id}")
    public Receta obtenerReceta(@PathVariable Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        // Forzar carga de paciente y médico completos
        receta.getCita().getPaciente().getNombres();
        receta.getCita().getMedico().getNombres();

        return receta;
    }

    @GetMapping("/listar")
    public List<Receta> listarRecetas() {
        List<Receta> recetas = recetaRepository.findAll();

        // Forzar carga de paciente y médico completos
        recetas.forEach(r -> {
            r.getCita().getPaciente().getNombres();
            r.getCita().getMedico().getNombres();
        });

        return recetas;
    }

}
