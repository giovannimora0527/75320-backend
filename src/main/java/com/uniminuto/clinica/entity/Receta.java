package com.uniminuto.clinica.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "receta")
public class Receta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Cita
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    // Relación con Medicamento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;

    @Column(nullable = false, length = 100)
    private String dosis;

    @Column(columnDefinition = "TEXT")
    private String indicaciones;

    // Getters y Setters
    public Long getId() 
        { return id; }
    public void setId(Long id) 
        { this.id = id; }

    public Cita getCita() 
        { return cita; }
    public void setCita(Cita cita) 
        { this.cita = cita; }

    public Medicamento getMedicamento() 
        { return medicamento; }
    public void setMedicamento(Medicamento medicamento) 
        { this.medicamento = medicamento; }

    public String getDosis() 
        { return dosis; }
    public void setDosis(String dosis) 
        { this.dosis = dosis; }

    public String getIndicaciones() 
        { return indicaciones; }
    public void setIndicaciones(String indicaciones) 
        { this.indicaciones = indicaciones; }
}
    
