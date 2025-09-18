/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uniminuto.clinica.model;

import lombok.Data;

/**
 *
 * @author lmora
 */
@Data
public class UsuarioRq {
    private String username;
    private String password;
    private String rol;
    
}