/**
 * Entidad JPA que representa la tabla de auditoría para recuperación de contraseñas.
 * Almacena el historial de intentos de recuperación de contraseñas y accesos al sistema
 * con información detallada de cada transacción.
 *
 * @author Edwin Morales
 * @author Nahum Dominguez
 * @author Emily Aldana
 * @author Julian Amaya
 * @author Sebastian Paez
 */
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

    /**
     * Identificador único para la serialización de la clase.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único de cada registro de auditoría.
     * Generado automáticamente mediante una estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Long id;

    /**
     * Nombre de usuario ingresado durante el intento de recuperación o login.
     */
    @Column(name = "usuario_ingresado")
    private String username;

    /**
     * Descripción del error ocurrido durante el proceso.
     * Contiene detalles sobre la causa del fallo en la operación.
     */
    @Column(name = "error_descripcion")
    private String description;

    /**
     * Fecha y hora en que se realizó la transacción.
     * Se genera automáticamente en el momento de la creación del registro.
     */
    @CreationTimestamp
    @Column(name = "transaccion_fecha", updatable = false)
    private LocalDateTime transaccionFecha;

    /**
     * Tipo de auditoría que se está registrando.
     * Puede ser "RECUPERACION" para recuperación de contraseñas o "LOGIN" para accesos al sistema.
     */
    @Column(name = "tipo_auditoria")
    private String tipoAuditoria;

    /**
     * Dirección IP desde donde se realizó la solicitud.
     * Permite rastrear el origen geográfico de la transacción.
     */
    @Column(name = "ip_address")
    private String ipAddress;

    /**
     * Constructor vacío obligatorio para JPA.
     * Utilizado por el framework para instanciar la entidad.
     */
    public RecuperarPasswordAuditoria() {
    }

    /**
     * Constructor completo para registros de auditoría de recuperación de contraseñas.
     *
     * @param username Nombre de usuario que intentó la recuperación
     * @param description Descripción del error ocurrido
     * @param tipoAuditoria Tipo de auditoría ("RECUPERACION" o "LOGIN")
     * @param ipAddress Dirección IP del solicitante
     */
    public RecuperarPasswordAuditoria(String username, String description, String tipoAuditoria, String ipAddress) {
        this.username = username;
        this.description = description;
        this.tipoAuditoria = tipoAuditoria;
        this.ipAddress = ipAddress;
    }

    /**
     * Constructor simplificado para registros de recuperación de contraseñas sin IP.
     * Establece automáticamente el tipo de auditoría como "RECUPERACION".
     *
     * @param username Nombre de usuario que intentó la recuperación
     * @param description Descripción del error ocurrido
     */
    public RecuperarPasswordAuditoria(String username, String description) {
        this(username, description, "RECUPERACION", null);
    }

    /**
     * Constructor para registros de auditoría de login con dirección IP.
     * Establece automáticamente el tipo de auditoría como "LOGIN".
     *
     * @param username Nombre de usuario que intentó el login
     * @param description Descripción del error ocurrido
     * @param ipAddress Dirección IP del solicitante
     */
    public RecuperarPasswordAuditoria(String username, String description, String ipAddress) {
        this(username, description, "LOGIN", ipAddress);
    }
}