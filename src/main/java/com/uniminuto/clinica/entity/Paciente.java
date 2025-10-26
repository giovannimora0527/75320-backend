package com.uniminuto.clinica.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {
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
    private Integer id;
    /**
     * usuario_id
     */
    @Column(name = "usuario_id")
    private  Integer usuarioId;
    /**
     * tipo_documento
     */
    @Column(name = "tipo_documento")
    private  String tipoDocumento;
    /**
     * numero_documento
     */
    @Column(name = "numero_documento")
    private  String numeroDocumento;
    /**
     * nombres
     */
    @Column(name = "nombres")
    private  String nombres ;
    /**
     * apellidos
     */
    @Column(name = "apellidos")
    private  String apellidos ;
    /**
     * fecha_nacimiento
     */
    @Column(name = "fecha_nacimiento")
    private  String fechaNacimiento ;
    /**
     * genero
     */
    @Column(name = "genero")
    private  String genero ;
    /**
     * telefono
     */
    @Column(name = "telefono")
    private  String telefono ;
    /**
     * direccion
     */
    @Column(name = "direccion")
    private  String direccion ;
}
