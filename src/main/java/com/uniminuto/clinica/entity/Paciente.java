package com.uniminuto.clinica.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import lombok.Data;
/**
 *
 * @author Andre
 */

@Data
@Entity
@Table(name="paciente")
public class Paciente implements Serializable{
       /**
     * Id serializable.
     */    
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del paciente.
=======
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
>>>>>>> 01711ebd80426cd1dd6e8ad30450e4c04c85b71c
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
<<<<<<< HEAD
    private Long id;    

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "numero_documento", unique = true)
    private String numeroDocumento;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    @Column(name = "genero")
    private String genero;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;
}
=======
    
    private Integer id;
    @Column(name = "usuario_id")
    
    private  Integer usuarioId;
    
     @Column(name = "tipo_documento")
    
    private  String tipoDocumento;
    
      @Column(name = "numero_documento")
    
    private  String numeroDocumento;
           @Column(name = "nombres")
    
    private  String nombres ; 
                 @Column(name = "apellidos")
    
    private  String apellidos ; 
                       @Column(name = "fecha_nacimiento")
    
    private  String fechaNacimiento ;            
                        @Column(name = "genero")
    
    private  String genero ;            
                                @Column(name = "telefono")
    
    private  String telefono ;    
                                @Column(name = "direccion")
    
    private  String direccion ;                                
                       
}
>>>>>>> 01711ebd80426cd1dd6e8ad30450e4c04c85b71c
