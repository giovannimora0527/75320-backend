package com.uniminuto.clinica.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login_intento_log")
public class LoginIntentoLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    private String username;

    private String descripcion;

    @Column(name = "ip_origen")
    private String ipOrigen;

    private boolean exito; // true = login ok, false = fallo

    // Getters y setters

    public Long getId() { return id; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getIpOrigen() { return ipOrigen; }
    public void setIpOrigen(String ipOrigen) { this.ipOrigen = ipOrigen; }

    public boolean isExito() { return exito; }
    public void setExito(boolean exito) { this.exito = exito; }
}
