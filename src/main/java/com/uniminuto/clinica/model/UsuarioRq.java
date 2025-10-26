package com.uniminuto.clinica.model;

import lombok.Data;



@Data
public class UsuarioRq {
    /**
     * Otros campos para el objeto de entrada
     */
    private Integer id;

    private String username;

    private String password;

    private String rol;

    private boolean activo;
}
