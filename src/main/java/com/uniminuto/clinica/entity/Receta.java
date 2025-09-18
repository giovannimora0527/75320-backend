package com.uniminuto.clinica.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "receta")
public class Receta implements Serializable {

    /**
     * Id serializable.
     */    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Relación con Entidadd Cita.
     */ 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cita_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cita cita;

    /**
     * Relación con Entidadd Medicamento.
     */    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicamento_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Medicamento medicamento;

    /**
     * Dosis de la Receta.
     */
    @Column(name = "dosis")
    private String dosis;

    /**
     * Indicaciones de la Receta.
     */    
    @Column(name = "indicaciones")
    private String indicaciones;
   
    /**
     * Creacion Registro de Receta.
     */    
    @Column(name = "fecha_creacion_registro", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCreacionRegistro;

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
    
    public LocalDateTime getFechaCreacionRegistro() {
        return fechaCreacionRegistro;
    }
    public void setFechaCreacionRegistro(LocalDateTime fechaCreacionRegistro) {
        this.fechaCreacionRegistro = fechaCreacionRegistro;
    }
}
    
