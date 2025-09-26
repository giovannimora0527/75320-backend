package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clinica/v1/api/citas")
public class CitaApiController {

    @Autowired
    private CitaService citaService;

    @PostMapping("/crear")
    public Cita crearCita(@RequestBody Cita cita) {
        return citaService.crearCita(cita);
    }

    @GetMapping("/listar")
    public List<Cita> listarCitas() {
        return citaService.listarCitasRecientes();
    }
}




