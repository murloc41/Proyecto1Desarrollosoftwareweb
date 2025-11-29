package com.instituto.compendium.repository;

import com.instituto.compendium.model.Guia;
import com.instituto.compendium.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuiaRepository extends JpaRepository<Guia, Long> {
    Page<Guia> findByEstado(Guia.EstadoPublicacion estado, Pageable pageable);
    Page<Guia> findByAutor(Usuario autor, Pageable pageable);
    Page<Guia> findByJuegoId(Long juegoId, Pageable pageable);
    
    @Query("SELECT g FROM Guia g WHERE g.estado = 'PUBLICADO' AND " +
           "(LOWER(g.titulo) LIKE LOWER(CONCAT('%', ?1, '%')) OR " +
           "LOWER(g.descripcion) LIKE LOWER(CONCAT('%', ?1, '%')))")
    Page<Guia> buscar(String termino, Pageable pageable);
    
    @Query("SELECT g FROM Guia g WHERE g.estado = 'PUBLICADO' AND " +
           "?1 MEMBER OF g.tags")
    List<Guia> findByTag(String tag);
}