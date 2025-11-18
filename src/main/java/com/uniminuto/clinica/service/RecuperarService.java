package com.uniminuto.clinica.service;

import com.uniminuto.clinica.model.RecuperarPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;

public interface RecuperarService {
    RespuestaRs recuperarPassword(RecuperarPasswordRq request);
}