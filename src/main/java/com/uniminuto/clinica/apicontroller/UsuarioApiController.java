package com.uniminuto.clinica.apicontroller;

import com.uniminuto.clinica.api.UsuarioApi;
import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import com.uniminuto.clinica.service.UsuarioService;
import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lmora
 */
@RestController
public class UsuarioApiController implements UsuarioApi {

    // Se declara una dependencia a la interfaz UsuarioService.
    // La anotación @Autowired le dice a Spring que inyecte una instancia del servicio aquí.
    @Autowired
    private UsuarioService usuarioService;

    // Este método implementa el método 'listarUsuarios' de la interfaz UsuarioApi
    @Override
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        
        // Llama al método del servicio para obtener todos los usuarios.
        // Se devuelve una respuesta HTTP con un estado 'OK' (200) y la lista de usuarios en el cuerpo.
        return ResponseEntity.ok(usuarioService.encontrarTodosLosUsuarios());
    }

    // Este método implementa el método 'buscarUsuarioXUsername' de la interfaz UsuarioApi.
    @Override
    public ResponseEntity<Usuario> buscarUsuarioXUsername(String username)
            throws BadRequestException {
        
        // Llama al servicio para encontrar un usuario por su nombre de usuario.
        // La excepción BadRequestException es manejada por el controlador.
        // Se devuelve una respuesta HTTP 'OK' con el usuario encontrado en el cuerpo.
        return ResponseEntity.ok(usuarioService
                .encontrarUsuarioPorNombre(username));
    }

    // Este método implementa el método 'guardarUsuario' de la interfaz UsuarioApi.
    @Override
    public ResponseEntity<RespuestaRs> guardarUsuario(UsuarioRq usuarioRq) throws BadRequestException {
        
        // Llama al servicio para guardar el nuevo usuario.
        // El servicio devolverá un objeto de respuesta (RespuestaRs).
        // Se devuelve una respuesta HTTP 'OK' con la respuesta del servicio en el cuerpo.
        return ResponseEntity.ok(usuarioService.guardarUsuario(usuarioRq));
    }

}
