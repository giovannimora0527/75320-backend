package com.uniminuto.clinica.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author lmora
 */
@Entity
@Table(name = "medico")
public class Medico implements Serializable {
    /**
     * Id serializable.
     */    
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del médico.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;    

    /**
     * Tipo de documento del médico (por ejemplo, CC, TI, CE).
     */
    @Column(name = "tipo_documento")
    private String tipoDocumento;

    /**
     * Número de documento del médico.
     */
    @Column(name = "numero_documento")
    private String numeroDocumento;

    /**
     * Nombres del médico.
     */
    @Column(name = "nombres")
    private String nombres;

    /**
     * Apellidos del médico.
     */
    @Column(name = "apellidos")
    private String apellidos;

    /**
     * Número de teléfono de contacto del médico.
     */
    @Column(name = "telefono")
    private String telefono;

    /**
     * Número de registro profesional del médico.
     */
    @Column(name = "registro_profesional")
    private String registroProfesional;

    // Constructor por defecto
    public Medico() {
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRegistroProfesional() {
        return registroProfesional;
    }

    public void setRegistroProfesional(String registroProfesional) {
        this.registroProfesional = registroProfesional;
    }
}