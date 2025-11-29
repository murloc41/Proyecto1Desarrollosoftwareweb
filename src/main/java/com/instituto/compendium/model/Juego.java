package com.instituto.compendium.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String imagen;
    private String genero;
    private String plataforma;
    private Double rating = 0.0;

    @Column(name = "total_valoraciones")
    private Integer totalValoraciones = 0;
}