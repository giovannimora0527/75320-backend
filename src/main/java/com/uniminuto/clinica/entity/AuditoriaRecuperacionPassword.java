package com.uniminuto.clinica.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
/**
 *
 * @author Andre
 */

@Data
@Entity
@Table(name = "auditoria_recuperacion_password")

public class AuditoriaRecuperacionPassword implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "username_ingresado", nullable = false, length = 50)
    private String usernameIngresado;
    
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;
    
    @Column(name = "ip_origen", length = 50)
    private String ipOrigen;
    
    @Column(name = "exitoso", nullable = false)
    private boolean exitoso;
    
    @Column(name = "descripcion_error", columnDefinition = "TEXT")
    private String descripcionError;
    
    @Column(name = "email_destino")
    private String emailDestino;
    
}
