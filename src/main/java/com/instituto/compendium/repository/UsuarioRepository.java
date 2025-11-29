package com.instituto.compendium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.instituto.compendium.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}