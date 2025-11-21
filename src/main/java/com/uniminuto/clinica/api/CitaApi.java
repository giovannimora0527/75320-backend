package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Paciente;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping("/cita")
public interface CitaApi {

    @PostMapping(value = "/guardar",
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<RespuestaRs> guardarCita(@RequestBody CitaRq citaRq);
    
    @RequestMapping(value = "/por-fechahora",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Cita>> listarCitaPorFechaHora();
    
    @RequestMapping(value = "/actualizar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> actualizarCita(@RequestParam Integer id, @RequestBody CitaRq citaRq) throws BadRequestException;
    
    @RequestMapping(value = "/eliminar",
        produces = {"application/json"},
        consumes = {"application/json"},
        method = RequestMethod.POST)
    ResponseEntity<RespuestaRs> eliminarCita(@RequestParam Integer id) throws BadRequestException;
}
