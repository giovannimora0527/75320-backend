package com.uniminuto.clinica.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Clase que representa la solicitud para crear o actualizar un usuario.
 * @author lmora
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRq {
    private Long id;
    private String username;
    private String password;
    private String rol;
    private boolean activo;
    private String email;
}
