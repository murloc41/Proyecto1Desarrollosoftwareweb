package com.instituto.compendium.controller;

import com.instituto.compendium.model.Usuario;
import com.instituto.compendium.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", new String[]{"ROLE_USER", "ROLE_AUTOR"});
        return "usuarios/registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@Valid @ModelAttribute Usuario usuario,
                                 BindingResult result,
                                 @RequestParam(required = false) String rol,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "usuarios/registro";
        }

        try {
            usuarioService.registrarUsuario(usuario, rol != null ? rol : "ROLE_USER");
            redirectAttributes.addFlashAttribute("mensaje", "Usuario registrado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al registrar usuario: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "danger");
            return "redirect:/usuarios/registro";
        }
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuarios/lista";
    }

    @GetMapping("/editar/{id}")
    @Secured("ROLE_ADMIN")
    public String editarUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.obtenerUsuario(id));
        model.addAttribute("roles", new String[]{"ROLE_USER", "ROLE_AUTOR", "ROLE_ADMIN"});
        return "usuarios/form";
    }

    @PostMapping("/editar/{id}")
    @Secured("ROLE_ADMIN")
    public String actualizarUsuario(@PathVariable Long id,
                                  @Valid @ModelAttribute Usuario usuario,
                                  BindingResult result,
                                  @RequestParam String[] roles,
                                  RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "usuarios/form";
        }

        try {
            usuarioService.actualizarUsuario(id, usuario, roles);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al actualizar usuario: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "danger");
        }

        return "redirect:/usuarios";
    }

    @PostMapping("/eliminar/{id}")
    @Secured("ROLE_ADMIN")
    public String eliminarUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.eliminarUsuario(id);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al eliminar usuario: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "danger");
        }
        return "redirect:/usuarios";
    }
}