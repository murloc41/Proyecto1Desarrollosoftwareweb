package com.instituto.compendium.repository;

import com.instituto.compendium.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Long> {
    List<Juego> findByGenero(String genero);
    List<Juego> findByPlataforma(String plataforma);
    List<Juego> findByNombreContainingIgnoreCase(String nombre);
}