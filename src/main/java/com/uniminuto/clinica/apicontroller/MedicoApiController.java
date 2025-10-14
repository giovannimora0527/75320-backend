package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.entity.Medico;
import com.uniminuto.clinica.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicoApiController {

    @Autowired
    private MedicoRepository medicoRepository;

    // ✅ Listar todos los médicos
    @GetMapping("/listar")
    public ResponseEntity<List<Medico>> listarMedicos() {
        return ResponseEntity.ok(medicoRepository.findAll());
    }

    // ✅ Crear un médico
    @PostMapping("/crear")
    public ResponseEntity<Medico> crearMedico(@RequestBody Medico medico) {
        return ResponseEntity.status(201).body(medicoRepository.save(medico));
    }
}







