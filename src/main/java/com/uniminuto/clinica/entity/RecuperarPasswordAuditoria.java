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
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name="recuperacion_password_auditoria")
public class RecuperarPasswordAuditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Long id;

    @CreationTimestamp
    @Column(name = "transaccion_fecha", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_ingresado")
    private String username;

    @Column(name = "error_descripcion")
    private String description;

    public RecuperarPasswordAuditoria(String username, String description) {
        this.username = username;
        this.description = description;
    }

    public RecuperarPasswordAuditoria() {}
}