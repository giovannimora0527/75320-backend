package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.RecetaApi;
import com.uniminuto.clinica.entity.Receta;
import com.uniminuto.clinica.model.CitaRs;

import com.uniminuto.clinica.model.RecetaRs;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.service.RecetaService;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RecetaApiController implements RecetaApi {

    // Se declara una dependencia a la interfaz RecetaService.
    // La palabra clave 'final' asegura que la dependencia se inicialice en el constructor.
    private final RecetaService recetaService;

    // Constructor que usa la inyección de dependencias. Spring le proporcionará una instancia
    // de RecetaService cuando cree una instancia de este controlador.
    public RecetaApiController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    // Este método implementa el método 'createReceta' de la interfaz RecetaApi.
    @Override
    public ResponseEntity<RespuestaRs> createReceta(CitaRs request) throws BadRequestException {
        
        // Un bloque try-catch para manejar las posibles excepciones que puedan ocurrir
        // durante la creación de la receta.
        try {
            
            // Se extraen los datos del objeto de solicitud (DTO CitaRs).
            Long citaId = request.getCitaId();
            Receta receta = request.getReceta();

            // Se llama al servicio para crear la receta, pasando los datos necesarios.
            Receta nueva = recetaService.createReceta(
                    receta.getMedicamento().getId(),
                    citaId,
                    receta.getDosis(),
                    receta.getIndicaciones()
            );

            // Si la creación es exitosa, se crea un objeto de respuesta con los detalles del éxito.
            RespuestaRs respuesta = new RespuestaRs();
            respuesta.setStatus(200);
            respuesta.setMessage("Receta creada correctamente con id " + nueva.getId());
            
            // Se devuelve una respuesta HTTP con un estado de 201 (CREATED) y el cuerpo de respuesta.
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

        } catch (Exception e) {
            
            // Si ocurre alguna excepción, se crea una respuesta de error.
            RespuestaRs respuesta = new RespuestaRs();
            respuesta.setStatus(400);
            respuesta.setMessage("No se pudo crear la receta: " + e.getMessage());
            
            // Se devuelve una respuesta HTTP con un estado de 400 (BAD_REQUEST) y el cuerpo de error.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<List<RecetaRs>> getRecetasOrdenadas() {
        
        // Se llama al servicio para obtener la lista de entidades Receta desde la base de datos.
        List<Receta> recetas = recetaService.obtenerRecetasOrdenadasPorFechaDesc();

        // Se utiliza un stream para mapear cada objeto entidad Receta a un objeto DTO RecetaRs.
        List<RecetaRs> respuesta = recetas.stream().map(r -> {
            RecetaRs rs = new RecetaRs();
            rs.setId(r.getId().intValue());
            rs.setCitaId(r.getCita().getId().intValue());
            rs.setMedicamentoId(r.getMedicamento().getId());
            rs.setDosis(r.getDosis());
            rs.setIndicaciones(r.getIndicaciones());
            rs.setFechaCreacionRegistro(r.getFechaCreacionRegistro());
            return rs;
        }).collect(Collectors.toList());// Se recolecta el resultado en una nueva lista.

        // Se devuelve una respuesta HTTP con un estado 'OK' (200) y la lista de DTOs en el cuerpo.
        return ResponseEntity.ok(respuesta);
    }
}
