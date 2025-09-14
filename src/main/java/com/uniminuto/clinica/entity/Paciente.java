package com.uniminuto.clinica.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author nicolas
 */
@Data
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id del paciente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  // BIGINT -> Long

    /**
     * Id del usuario asociado.
     */
    @Column(name = "usuario_id")
    private Long usuarioId; // Cambiado de Integer a Long

    /**
     * Tipo de documento.
     */
    @Column(name = "tipo_documento")
    private String tipoDocumento;

    /**
     * Número de documento.
     */
    @Column(name = "numero_documento")
    private String numeroDocumento; 

    /**
     * Nombres del paciente.
     */
    @Column(name = "nombres")
    private String nombres;

    /**
     * Apellidos del paciente.
     */
    @Column(name = "apellidos")
    private String apellidos;

    /**
     * Fecha de nacimiento.
     */
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    /**
     * Género del paciente.
     */
    @Column(name = "genero")
    private String genero;

    /**
     * Teléfono.
     */
    @Column(name = "telefono")
    private String telefono; 
    
    /**
     * Dirección.
     */
    @Column(name = "direccion")
    private String direccion;
}
