package com.uniminuto.clinica.service.impl;

import com.uniminuto.clinica.entity.Usuario;
import com.uniminuto.clinica.model.RespuestaRs;
import com.uniminuto.clinica.model.UsuarioRq;
import com.uniminuto.clinica.repository.UsuarioRepository;
import com.uniminuto.clinica.service.UsuarioService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author lmora
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    /**
     * UsuarioRepository.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> encontrarTodosLosUsuarios() {

        // Llama al método findAll() del repositorio para obtener una lista de todas las entidades de Usuario.
        return this.usuarioRepository.findAll();
    }

    @Override
    public Usuario encontrarUsuarioPorNombre(String username) 
            throws BadRequestException {
        
        /** Utiliza el método del repositorio para buscar un usuario por su nombre.
         Optional<Usuario> es una forma de manejar la posibilidad de que no se encuentre el usuario.
        **/
        Optional<Usuario> optUser = this.usuarioRepository
                .findByUsername(username);
        
        // Verifica si el Optional está vacío (es decir, el usuario no existe).
        if (!optUser.isPresent()) {
            
            // Lanza una excepción personalizada si el usuario no se encuentra.
            throw new BadRequestException("No se encuentra el usuario.");
        }
        // Si el usuario está presente, devuelve la instancia de Usuario.
        return optUser.get();
    }

    @Override
    public RespuestaRs guardarUsuario(UsuarioRq usuarioNuevo)
            throws BadRequestException {
        
        // Llama a un método privado para validar los datos de entrada.
        this.validarCampos(usuarioNuevo);
        
        // Intenta encontrar un usuario existente con el mismo nombre de usuario.
        Optional<Usuario> optUser = this.usuarioRepository
                .findByUsername(usuarioNuevo.getUsername().toLowerCase());
        
        // Si el usuario ya existe, lanza una excepción.
        if (optUser.isPresent()) {
      
            // Paso 3. Si existe lanzo error que ya existe el usuario
            throw new BadRequestException("El usuario ya existe.");
        }
        
        // Crea una nueva instancia de la entidad Usuario.
        Usuario nuevo = new Usuario();
        
       // Asigna los valores del DTO a la entidad Usuario.
        nuevo.setActivo(true);
        nuevo.setFechaCreacion(LocalDateTime.now());
        
        // Encripta la contraseña antes de guardarla para mayor seguridad.
        nuevo.setPassword(this.encriptarPassword(usuarioNuevo.getPassword()));
        nuevo.setRol(usuarioNuevo.getRol().toUpperCase());
        nuevo.setUsername(usuarioNuevo.getUsername().toLowerCase());

        // Guarda la nueva entidad en la base de datos a través del repositorio.
        this.usuarioRepository.save(nuevo);

        // Crea y devuelve un objeto de respuesta para confirmar la operación exitosa.
        RespuestaRs rta = new RespuestaRs();
        rta.setMessage("El usuario se ha guardado correctamente.");
        rta.setStatus(200);
        return rta;
    }

    // Método privado para validar que los campos del DTO no estén nulos o vacíos.
    private void validarCampos(UsuarioRq usuarioNuevo)
            throws BadRequestException {
        if (usuarioNuevo.getUsername() == null
                || usuarioNuevo.getUsername().isBlank()
                || usuarioNuevo.getUsername().isEmpty()) {
            throw new BadRequestException("El campo username es obligatorio.");
        }
        if (usuarioNuevo.getPassword() == null
                || usuarioNuevo.getPassword().isBlank()
                || usuarioNuevo.getPassword().isEmpty()) {
            throw new BadRequestException("El campo pass es obligatorio.");
        }
        if (usuarioNuevo.getRol() == null
                || usuarioNuevo.getRol().isBlank()
                || usuarioNuevo.getRol().isEmpty()) {
            throw new BadRequestException("El campo rol es obligatorio.");
        }
    }

    /** 
     * Método privado para encriptar la contraseña usando el algoritmo MD5.
    * */
    private String encriptarPassword(String passAEncriptar) {
        String algoritmo = "MD5";
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo); // Ej: "SHA-256", "MD5"
            byte[] hashBytes = md.digest(passAEncriptar.getBytes());

            // Convertir a hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo no soportado: " + algoritmo, e);
        }
    }

}
