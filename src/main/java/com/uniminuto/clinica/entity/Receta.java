package com.uniminuto.clinica.entity;

<<<<<<< HEAD
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entidad que representa una receta médica en el sistema.
 * Corresponde a la tabla 'receta' en la base de datos.
 */
@Entity
@Table(name = "receta")
@Data
public class Receta implements Serializable {
    /** Identificador único de la receta */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Identificador de la cita asociada */
    @ManyToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    /** Identificador del medicamento recetado */
    @ManyToOne
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;

    /** Dosis del medicamento */
    @Column(name = "dosis", nullable = false)
    private String dosis;

    /** Indicaciones adicionales para el medicamento */
    @Column(name = "indicaciones")
    private String indicaciones;

    /** Fecha de creación del registro de la receta */
    @Column(name = "fecha_creacion_registro")
    private LocalDateTime fechaCreacionRegistro;
}
=======
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "receta")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cita_id")
    private Cita cita;

    private LocalDateTime fechaCreacionRegistro;

    // Constructor vacío
    public Receta() {
        this.fechaCreacionRegistro = LocalDateTime.now();
    }

    // Constructor completo
    public Receta(String descripcion, Cita cita) {
        this.descripcion = descripcion;
        this.cita = cita;
        this.fechaCreacionRegistro = LocalDateTime.now();
    }

    // Getters y Setters
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


>>>>>>> origin/Mariana_976621Castillo
