package com.uniminuto.clinica.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 *
 * @author lmora
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRq {
    private String username;
    private String password;
    private String rol;
    private Boolean activo;
}