package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.CitaApi;
import com.uniminuto.clinica.entity.Cita;
import com.uniminuto.clinica.service.CitaService;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CitaApiController implements CitaApi {

    // Se declara una dependencia a la interfaz CitaService.
    // La palabra clave 'final' asegura que esta dependencia se inicialice en el constructor.
    private final CitaService citaService;

    // Constructor que usa la inyección de dependencias. Spring le proporcionará una instancia
    // de CitaService cuando cree una instancia de este controlador.
    public CitaApiController(CitaService citaService) {
        this.citaService = citaService;
    }

    @Override
    public ResponseEntity<Cita> crearCita(Cita cita) {
        
        // Llama al método 'guardarCita' del servicio. Aquí es donde se ejecuta la lógica de negocio.
        Cita guardar = citaService.guardarCita(cita);
        
        // Devuelve una respuesta HTTP con un estado 'OK' (200) y el objeto Cita guardado en el cuerpo.
        return ResponseEntity.ok(guardar);
    }
    
    @Override
    public ResponseEntity<List<Cita>> getCitasOrdenadas() {
        
        // Llama al servicio para obtener la lista de citas ordenadas.
        List<Cita> citas = citaService.obtenerCitasOrdenadasPorFechaDesc();
        
        // Devuelve una respuesta HTTP con un estado 'OK' y la lista de citas en el cuerpo.
        return ResponseEntity.ok(citas);
    }
}