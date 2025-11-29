package com.instituto.compendium.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Guia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "juego_id", nullable = false)
    private Juego juego;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @ElementCollection
    private Set<String> tags = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private String imagen;

    @OneToMany(mappedBy = "guia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Archivo> archivos = new ArrayList<>();

    @OneToMany(mappedBy = "guia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    private Integer vistas = 0;
    private Double rating = 0.0;
    private Integer totalValoraciones = 0;

    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;

    @Enumerated(EnumType.STRING)
    private EstadoPublicacion estado = EstadoPublicacion.BORRADOR;

    public enum Dificultad {
        PRINCIPIANTE, INTERMEDIO, AVANZADO, EXPERTO
    }

    public enum Categoria {
        TUTORIAL, ESTRATEGIA, BUILD, SECRETOS, LOGROS, SPEEDRUN, GENERAL
    }

    public enum EstadoPublicacion {
        BORRADOR, PUBLICADO, ARCHIVADO
    }
}