package com.uniminuto.clinica.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "recuperacion_password_auditoria")
public class RecuperarPasswordAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Long id;

    @Column(name = "usuario_ingresado")
    private String username;

    @Column(name = "error_descripcion")
    private String description;

    @CreationTimestamp
    @Column(name = "transaccion_fecha", updatable = false)
    private LocalDateTime transaccionFecha;

    @Column(name = "tipo_auditoria")
    private String tipoAuditoria;

    @Column(name = "ip_address")
    private String ipAddress;

    // CONSTRUCTOR VACÍO OBLIGATORIO PARA JPA
    public RecuperarPasswordAuditoria() {
    }

    public RecuperarPasswordAuditoria(String username, String description, String tipoAuditoria, String ipAddress) {
        this.username = username;
        this.description = description;
        this.tipoAuditoria = tipoAuditoria;
        this.ipAddress = ipAddress;
    }

    public RecuperarPasswordAuditoria(String username, String description) {
        this(username, description, "RECUPERACION", null);
    }

    public RecuperarPasswordAuditoria(String username, String description, String ipAddress) {
        this(username, description, "LOGIN", ipAddress);
    }
}