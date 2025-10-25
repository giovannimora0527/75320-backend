package com.uniminuto.clinica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entidad de receta de la base de datos
 */
/**
 * @author Anderson
 */

@Data
@Entity
@Table(name="Receta")
public class Receta implements Serializable{
    /**
     * serializable
     */
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * cita_id
     */
    @ManyToOne
    @JoinColumn(name = "cita_id")
    private Cita cita; 
    /**
     * medicamento_id
     */
    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento; 
    /**
     * dosis
     */
    @Column(name = "dosis")
    private String dosis; 
    /**
     * indicaciones
     */
    @Column(name = "indicaciones")
    private String indicaciones; 
    /**
     * fecha_creacion_registros
     */
    @Column(name = "fecha_creacion_registro")
    private LocalDateTime fechaCreacionRegistro;
}
