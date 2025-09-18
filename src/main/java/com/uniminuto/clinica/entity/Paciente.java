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
     * Id serializable.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    /**
     * UsuarioId Paciente.
     */    
    @Column(name = "usuario_id")
    private  Integer usuarioId;
    
    /**
     * TipoDocumentoPaciente.
     */
    @Column(name = "tipo_documento")
    private  String tipoDocumento;
    
    /**
     * Documento Paciente.
     */
    @Column(name = "numero_documento")
    private  String numeroDocumento;
    
    /**
     * Nombres Paciente.
     */
    @Column(name = "nombres")
    private  String nombres ; 
    
    /**
     * Apellidos Paciente.
     */
    @Column(name = "apellidos")
    private  String apellidos ; 
    
    /**
     * FechaNacimiento Paciente.
     */
    @Column(name = "fecha_nacimiento")
    private  String fechaNacimiento ;            
    
    /**
     * Genero Paciente.
     */
    @Column(name = "genero")
    private  String genero ;            
    
    /**
     * Telefono Paciente.
     */
    @Column(name = "telefono")
    private  String telefono ; 
    
    /**
     * Direccion Paciente.
     */
    @Column(name = "direccion")
    private  String direccion ;                                
                       
}
