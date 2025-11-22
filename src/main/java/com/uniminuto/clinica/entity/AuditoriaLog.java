package com.uniminuto.clinica.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entidad para registrar logs de auditoría de seguridad.
 */
@Data
@Entity
@Table(name = "auditoria_log")
public class AuditoriaLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "tipo_evento", nullable = false, length = 100)
    private String tipoEvento; // LOGIN_FALLIDO, RECUPERACION_FALLIDA, CUENTA_BLOQUEADA, etc.

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "direccion_ip", length = 45)
    private String direccionIp;

    @Column(name = "fecha_evento", nullable = false)
    private LocalDateTime fechaEvento;

    @Column(name = "exitoso")
    private Boolean exitoso;
}