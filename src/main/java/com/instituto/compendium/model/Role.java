package com.instituto.compendium.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del rol, por ejemplo: ADMIN, USER
    @Column(unique = true, nullable = false)
    private String name;

}
