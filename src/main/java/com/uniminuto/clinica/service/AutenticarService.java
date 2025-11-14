package com.uniminuto.clinica.service;

import com.uniminuto.clinica.model.AutenticatorRs;
import com.uniminuto.clinica.model.AuthenticatorRq;
import com.uniminuto.clinica.utils.BadRequestException;

public interface AutenticarService {

    AutenticatorRs autenticar(AuthenticatorRq request, String ipOrigen) throws BadRequestException;
}
