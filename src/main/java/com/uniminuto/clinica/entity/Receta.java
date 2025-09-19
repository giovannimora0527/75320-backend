package com.uniminuto.clinica.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "receta")
public class Receta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_creacion_registro", nullable = false)
    private LocalDateTime fechaCreacionRegistro;

    @PrePersist
    public void prePersist() {
        if (this.fechaCreacionRegistro == null) {
            this.fechaCreacionRegistro = LocalDateTime.now();
        }
    }

    // Getters y Setters manuales por compatibilidad
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Cita getCita() { return cita; }
    public void setCita(Cita cita) { this.cita = cita; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDateTime getFechaCreacionRegistro() { return fechaCreacionRegistro; }
    public void setFechaCreacionRegistro(LocalDateTime fechaCreacionRegistro) { this.fechaCreacionRegistro = fechaCreacionRegistro; }
}










