package com.uniminuto.clinica.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lmora
 */
@Data
@Getter
@Setter
public class RespuestaRs {
    private String message;
    private boolean estaFuncionando;

    // Métodos getter y setter generados manualmente debido a problemas con Lombok
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEstaFuncionando() {
        return estaFuncionando;
    }

    public void setEstaFuncionando(boolean estaFuncionando) {
        this.estaFuncionando = estaFuncionando;
    }
}
