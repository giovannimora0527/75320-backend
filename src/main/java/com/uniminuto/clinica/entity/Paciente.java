package com.uniminuto.clinica.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
<<<<<<< HEAD

import lombok.Data;

@Data
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

=======
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que representa un paciente en el sistema de clínica.
 *
 * @author lmora
 */
@Data
@Getter
@Setter
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {
>>>>>>> origin/Mariana_976621Castillo
    /**
     * Id serializable.
     */
    private static final long serialVersionUID = 1L;
<<<<<<< HEAD
    /**
     * Id.
=======

    /**
     * Identificador único del paciente.
>>>>>>> origin/Mariana_976621Castillo
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
<<<<<<< HEAD
    private Integer id;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "genero")
    private String genero;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

}
=======
    private Long id;

    /**
     * Tipo de documento del paciente (CC, TI, CE, RC).
     */
    @Column(name = "tipo_documento")
    private String tipoDocumento;

    /**
     * Número de documento del paciente.
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
     * Fecha de nacimiento del paciente.
     */
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    /**
     * Número de teléfono de contacto del paciente.
     */
    @Column(name = "telefono")
    private String telefono;

    /**
     * Dirección de residencia del paciente.
     */
    @Column(name = "direccion")
    private String direccion;

    /**
     * Correo electrónico del paciente.
     */
    @Column(name = "email")
    private String email;

    /**
     * Género del paciente (M, F, O).
     */
    @Column(name = "genero")
    private String genero;

    /**
     * Estado civil del paciente.
     */
    @Column(name = "estado_civil")
    private String estadoCivil;

    /**
     * Ocupación del paciente.
     */
    @Column(name = "ocupacion")
    private String ocupacion;

    /**
     * Nombre del contacto de emergencia.
     */
    @Column(name = "contacto_emergencia_nombre")
    private String contactoEmergenciaNombre;

    /**
     * Teléfono del contacto de emergencia.
     */
    @Column(name = "contacto_emergencia_telefono")
    private String contactoEmergenciaTelefono;

    /**
     * Relación con el contacto de emergencia.
     */
    @Column(name = "contacto_emergencia_relacion")
    private String contactoEmergenciaRelacion;

    /**
     * Indica si el paciente está activo en el sistema.
     */
    @Column(name = "activo")
    private Boolean activo = true;

    /**
     * Fecha de registro del paciente en el sistema.
     */
    @Column(name = "fecha_registro")
    private java.time.LocalDateTime fechaRegistro;

    /**
     * Observaciones médicas generales del paciente.
     */
    @Column(name = "observaciones", length = 1000)
    private String observaciones;

    // Métodos getter y setter generados manualmente debido a problemas con Lombok
    
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getContactoEmergenciaNombre() {
        return contactoEmergenciaNombre;
    }

    public void setContactoEmergenciaNombre(String contactoEmergenciaNombre) {
        this.contactoEmergenciaNombre = contactoEmergenciaNombre;
    }

    public String getContactoEmergenciaTelefono() {
        return contactoEmergenciaTelefono;
    }

    public void setContactoEmergenciaTelefono(String contactoEmergenciaTelefono) {
        this.contactoEmergenciaTelefono = contactoEmergenciaTelefono;
    }

    public String getContactoEmergenciaRelacion() {
        return contactoEmergenciaRelacion;
    }

    public void setContactoEmergenciaRelacion(String contactoEmergenciaRelacion) {
        this.contactoEmergenciaRelacion = contactoEmergenciaRelacion;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public java.time.LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(java.time.LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

>>>>>>> origin/Mariana_976621Castillo
