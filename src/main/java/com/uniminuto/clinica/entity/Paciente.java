package com.uniminuto.clinica.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "id")
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
