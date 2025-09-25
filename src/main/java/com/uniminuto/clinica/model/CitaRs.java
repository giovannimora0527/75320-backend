package com.uniminuto.clinica.model;

import com.uniminuto.clinica.entity.Receta;


//DTO que encapsula datos que agrupa un ID de cita con un objeto de receta para su transferencia.
public class CitaRs {
    private Long citaId;
    private Receta receta;

    public Long getCitaId() {
        return citaId;
    }
    public void setCitaId(Long citaId) {
        this.citaId = citaId;
    }

    public Receta getReceta() {
        return receta;
    }
    public void setReceta(Receta receta) {
        this.receta = receta;
    }
}
