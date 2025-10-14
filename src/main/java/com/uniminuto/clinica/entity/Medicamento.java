package com.uniminuto.clinica.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "medicamentos")
@Data
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
}



