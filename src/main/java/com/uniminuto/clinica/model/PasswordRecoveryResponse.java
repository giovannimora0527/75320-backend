package com.uniminuto.clinica.model;

public class PasswordRecoveryResponse {

    private String mensaje;

    public PasswordRecoveryResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() { return mensaje; }
}
