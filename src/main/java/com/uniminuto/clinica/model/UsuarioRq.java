package com.uniminuto.clinica.model;

import lombok.Data;

/**
 * Se utiliza como objeto de entrada en los controladores REST.
 */
/**
 * @author Anderson
 */

@Data
public class UsuarioRq {
    /**
     * Otros campos para el objeto de entrada
     */
    private String username;
    
    private String password;
    
    private String rol;
}