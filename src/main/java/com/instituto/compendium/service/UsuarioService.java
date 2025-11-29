package com.instituto.compendium.service;

import com.instituto.compendium.model.Usuario;
import com.instituto.compendium.repository.UsuarioRepository;
import com.instituto.compendium.repository.RoleRepository;
import com.instituto.compendium.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    public Usuario registrarUsuario(Usuario usuario, String rol) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRoles(new HashSet<>());
        
        Role userRole = roleRepository.findByName(rol)
            .orElseGet(() -> {
                Role newRole = new Role();
                newRole.setName(rol);
                return roleRepository.save(newRole);
            });
        usuario.getRoles().add(userRole);
        
        return usuarioRepository.save(usuario);
    }

    public Usuario registrarUsuario(Usuario usuario) {
        return registrarUsuario(usuario, "ROLE_USER");
    }

    public Iterable<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuario(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario, String[] roles) {
        Usuario usuarioExistente = obtenerUsuario(id);
        
        usuarioExistente.setUsername(usuario.getUsername());
        usuarioExistente.setEmail(usuario.getEmail());
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        
        usuarioExistente.getRoles().clear();
        for (String rolNombre : roles) {
            Role rol = roleRepository.findByName(rolNombre)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(rolNombre);
                    return roleRepository.save(newRole);
                });
            usuarioExistente.getRoles().add(rol);
        }
        
        return usuarioRepository.save(usuarioExistente);
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = obtenerUsuario(id);
        usuarioRepository.delete(usuario);
    }
}