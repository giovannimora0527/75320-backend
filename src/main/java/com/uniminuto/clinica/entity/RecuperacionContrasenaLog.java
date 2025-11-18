package com.uniminuto.clinica.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recuperacion_contrasena_log")

public class RecuperacionContrasenaLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;

    private String username;

    private String descripcion;

    private String ipOrigen;

    // Getters & setters

    public Long getId() { return id; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getIpOrigen() { return ipOrigen; }
    public void setIpOrigen(String ipOrigen) { this.ipOrigen = ipOrigen; }

}
