package com.uniminuto.clinica.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;  
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import javax.persistence.JoinColumn;

/**
 * Entidad de cita de la base de datos
 */
/**
 * @author Anderson
 */

@Data
@Entity
@Table(name="cita")
public class Cita implements Serializable{
    /**
     * serializable
     */
    public static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;
    /**
     * paciente_id
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private  Paciente paciente;
    /**
    * medico_id
    */
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private  Medico medico;
    /**
    * fecha_hora
    */
    @Column(name = "fecha_hora")
    private  LocalDateTime fechaHora;
    /**
    * estado
    */
    @Column(name = "estado")
    private  String estado ; 
    /**
    * motivo
    */
    @Column(name = "motivo")
    private  String motivo ;
}

