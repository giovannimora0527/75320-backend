package com.uniminuto.clinica.model;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author lmora
 */
@Data
public class RespuestaRs implements Serializable {

    private Integer status;
    private String mensaje;

    // ❌ NO pongas setters manuales aquí
    // Lombok (@Data) ya genera:
    // - getStatus()
    // - setStatus(Integer status)
    // - getMensaje()
    // - setMensaje(String mensaje)
}
