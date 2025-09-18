package com.uniminuto.clinica.api;

import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.CitaRs;
import com.uniminuto.clinica.model.RecetaRs;
import com.uniminuto.clinica.model.RespuestaRs;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/receta")
public interface RecetaApi {

    // Anotación que mapea una solicitud web a este método.
    @RequestMapping(
            
            // 'value' define la ruta del endpoint, en este caso "/agregarReceta".
            value = "/agregarReceta",
            
            // 'produces' especifica el tipo de contenido que el método puede generar.
            // Aquí, indica que el método devolverá una respuesta en formato JSON.
            produces = {"application/json"},
            
            // 'consumes' especifica el tipo de contenido que el método acepta en el cuerpo de la solicitud.
            // Espera un cuerpo de solicitud en formato JSON.
            consumes = {"application/json"},
            
            // 'method' define el verbo HTTP que este endpoint maneja. POST se usa para crear un nuevo recurso.
            method = RequestMethod.POST)
            
    // El método recibe el cuerpo de la solicitud (RequestBody) como un objeto DTO CitaRs.
    // Lanza una excepción personalizada si la solicitud es incorrecta.
    // Devuelve una respuesta HTTP completa con el estado y un cuerpo de tipo RespuestaRs.
    ResponseEntity<RespuestaRs> createReceta(
            @RequestBody CitaRs request)
            throws BadRequestException;

    // Anotación para una solicitud GET.
    @RequestMapping(
            
           // La ruta del endpoint es "/listarRecetas". 
           value = "/listarRecetas",
            
           // 'produces' especifica que la respuesta será en formato JSON. 
           produces = {"application/json"},
           
           // 'method' indica que este endpoint responde a peticiones GET, usadas para obtener recursos.
           method = RequestMethod.GET
       )
        // El método devuelve una lista de objetos DTO RecetaRs dentro de una respuesta HTTP.        
        ResponseEntity<List<RecetaRs>> getRecetasOrdenadas();
    
}
