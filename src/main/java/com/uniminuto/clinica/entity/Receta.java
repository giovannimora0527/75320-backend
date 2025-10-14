package com.uniminuto.clinica.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "receta")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", length = 1000) // ✅ Coincide con la columna de tu tabla SQL
    private String descripcion;

    private String medicamento;
    private String indicaciones;

    // 🔹 Relación con la tabla citas
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cita_id")
    private Cita cita;

    @Column(name = "fecha_creacion_registro")
    private LocalDateTime fechaCreacionRegistro = LocalDateTime.now();

    // ✅ Constructor vacío
    public Receta() {}

    // ✅ Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public LocalDateTime getFechaCreacionRegistro() {
        return fechaCreacionRegistro;
    }

    public void setFechaCreacionRegistro(LocalDateTime fechaCreacionRegistro) {
        this.fechaCreacionRegistro = fechaCreacionRegistro;
    }
}







