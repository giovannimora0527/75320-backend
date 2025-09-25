package com.uniminuto.clinica.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cita")
public class Cita {

    /**
     * Id Cita.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Relacion con Entidad Paciente.
     */
    @ManyToOne //Relacion Muchos a Uno
    @JoinColumn(name = "paciente_id", nullable = false) //Mapear la Relacion a nivel de la DB
    private Paciente paciente; //Entidad

    /**
     * Relacion con Entidad Medico.
     */    
    @ManyToOne //Relacion Muchos a Uno
    @JoinColumn(name = "medico_id", nullable = false) //Mapear la Relacion a nivel de la DB
    private Medico medico; //Entidad

    /**
     * Fecha y Hora de la Cita.
     */  
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    /**
     * Estado de la Cita.
     */     
    @Column(nullable = false)
    private String estado;

    /**
     * Motivo de la Cita.
     */     
    private String motivo;

    // Metodos getters y setters públicos:

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}
