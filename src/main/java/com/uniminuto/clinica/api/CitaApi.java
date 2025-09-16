package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.entity.Medico;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping("/cita")
public interface CitaApi {

    @RequestMapping(
            value = "/agendarCita",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Cita> crearCita(@RequestBody Cita cita);
    
    @RequestMapping(
           value = "/listar-por-fechayhora",
           produces = {"application/json"},
           method = RequestMethod.GET
       )
        ResponseEntity<List<Cita>> getCitasOrdenadas();
    


}
