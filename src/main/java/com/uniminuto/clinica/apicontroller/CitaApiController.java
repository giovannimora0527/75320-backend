package com.uniminuto.clinica.apicontroller;

<<<<<<< HEAD
import com.uniminuto.clinica.api.CitaApi;
import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.CitaService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CitaApiController implements CitaApi {
=======
import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clinica/v1/api/citas")
public class CitaApiController {
>>>>>>> origin/Mariana_976621Castillo

    @Autowired
    private CitaService citaService;

<<<<<<< HEAD

    @Override
    public ResponseEntity<List<Cita>> listarCitas() {
        return ResponseEntity.ok(this.citaService.listarCitasOrdenadas());
    }

    @Override
    public ResponseEntity<RespuestaRs> guardarCitas(CitaRq citaRq) throws BadRequestException {
        return ResponseEntity.ok(this.citaService.guardarCita(citaRq));
    }
}
=======
    @PostMapping("/crear")
    public Cita crearCita(@RequestBody Cita cita) {
        return citaService.crearCita(cita);
    }

    @GetMapping("/listar")
    public List<Cita> listarCitas() {
        return citaService.listarCitasRecientes();
    }
}




>>>>>>> origin/Mariana_976621Castillo
