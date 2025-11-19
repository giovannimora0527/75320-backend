package com.uniminuto.clinica.service;

import com.uniminuto.clinica.entity.RecuperarPasswordAuditoria;
import com.uniminuto.clinica.model.RecuperarPasswordRq;
import com.uniminuto.clinica.model.RespuestaRs;

import java.util.List;


public interface RecuperarService {
    RespuestaRs recuperarPassword(RecuperarPasswordRq request);

    List<RecuperarPasswordAuditoria> listarTodosLosRegistros();
}