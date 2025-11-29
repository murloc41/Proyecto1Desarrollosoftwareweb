package com.instituto.compendium.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class Archivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ruta;
    private String tipo;
    private Long tamano;

    @ManyToOne
    @JoinColumn(name = "guia_id", nullable = false)
    private Guia guia;

    @CreationTimestamp
    private LocalDateTime fechaSubida;
}