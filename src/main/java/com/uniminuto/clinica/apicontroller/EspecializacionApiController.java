package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.entity.Especializacion;
import com.uniminuto.clinica.service.EspecializacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/especializacion") // 👈 SOLO esto
@CrossOrigin(origins = "*")
public class EspecializacionApiController {

    @Autowired
    private EspecializacionService especializacionService;

    // ✅ Crear nueva especialización
    @PostMapping("/crear")
    public ResponseEntity<Especializacion> crear(@RequestBody Especializacion especializacion) {
        Especializacion nueva = especializacionService.crearEspecializacion(especializacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // ✅ Listar especializaciones
    @GetMapping("/listar")
    public ResponseEntity<List<Especializacion>> listar() {
        List<Especializacion> lista = especializacionService.listarEspecializaciones();
        return ResponseEntity.ok(lista);
    }
}

