
package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.CitaApi;
import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.model.CitaRq;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.CitaService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andre
 */
@RestController
public class CitaApiController implements CitaApi{
    
    @Autowired
    private CitaService citaService;

    @Override
    public ResponseEntity<List<Cita>> listarCita() {
        return ResponseEntity.ok(this.citaService.listarCita());
        
    }

    @Override
    public ResponseEntity<RespuestaRs> guardarCita(CitaRq citaRq) throws BadRequestException {
       return ResponseEntity.ok(this.citaService.guardarCita(citaRq));
    }
    
    
     @Override
    public ResponseEntity<List<Cita>> ListarCitasOrdenadas() {
        return ResponseEntity.ok(this.citaService.ListarCitasOrdenadas());
        
    }   
    
}
