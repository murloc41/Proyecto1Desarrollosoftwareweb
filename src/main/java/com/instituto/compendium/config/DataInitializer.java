package com.instituto.compendium.config;

import com.instituto.compendium.model.Role;
import com.instituto.compendium.model.Usuario;
import com.instituto.compendium.repository.RoleRepository;
import com.instituto.compendium.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Seed roles
        Role admin = roleRepository.findByName("ADMIN").orElseGet(() -> {
            Role r = new Role(); r.setName("ADMIN"); return roleRepository.save(r);
        });

        roleRepository.findByName("USER").orElseGet(() -> {
            Role r = new Role(); r.setName("USER"); return roleRepository.save(r);
        });

        // Create admin user if not exists
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario u = new Usuario();
            u.setUsername("admin");
            u.setEmail("admin@example.com");
            u.setPassword(passwordEncoder.encode("admin123"));
            u.setActivo(true);
            u.getRoles().add(admin);
            usuarioRepository.save(u);
        }
    }
}
