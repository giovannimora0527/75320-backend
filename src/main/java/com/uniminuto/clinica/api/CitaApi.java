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
            
            // El 'value' define la ruta del endpoint, en este caso "/agendarCita".
            value = "/agendarCita",
            
            // 'produces' especifica el tipo de contenido que el método puede generar.
            // Aquí, indica que el método devolverá una respuesta en formato JSON.
            produces = {"application/json"},
            
            // 'consumes' especifica el tipo de contenido que el método acepta en el cuerpo de la solicitud.
            // Aquí, indica que el método espera un cuerpo de solicitud en formato JSON.
            consumes = {"application/json"},
            
            // 'method' define el verbo HTTP que este endpoint maneja. POST se usa para crear recursos.
            method = RequestMethod.POST)
            
    // El método recibe el cuerpo de la solicitud como un objeto Cita y devuelve una respuesta HTTP completa.        
    ResponseEntity<Cita> crearCita(@RequestBody Cita cita);
    
    // Anotación para una solicitud GET.
    @RequestMapping(
            
           // La ruta del endpoint es "/listar-por-fechayhora".
           value = "/listar-por-fechayhora",
           
           // 'produces' especifica que la respuesta será en formato JSON. 
           produces = {"application/json"},
           
           // 'method' indica que este endpoint responde a peticiones GET, usadas para obtener recursos.
           method = RequestMethod.GET
       )
        // El método devuelve una lista de objetos Cita dentro de una respuesta HTTP.
        ResponseEntity<List<Cita>> getCitasOrdenadas();
    


}
