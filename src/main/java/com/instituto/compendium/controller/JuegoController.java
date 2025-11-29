package com.instituto.compendium.controller;

import com.instituto.compendium.model.Juego;
import com.instituto.compendium.service.JuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/juegos")
@Secured("ROLE_ADMIN")  // Solo los administradores pueden gestionar juegos
public class JuegoController {

    @Autowired
    private JuegoService juegoService;

    @GetMapping
    public String listarJuegos(Model model) {
        model.addAttribute("juegos", juegoService.listarJuegos());
        return "juegos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("juego", new Juego());
        return "juegos/form";
    }

    @PostMapping("/guardar")
    public String guardarJuego(@Valid @ModelAttribute Juego juego,
                              BindingResult result,
                              @RequestParam(required = false) MultipartFile imagen,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "juegos/form";
        }

        try {
            juegoService.crearJuego(juego, imagen);
            redirectAttributes.addFlashAttribute("mensaje", "Juego guardado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al guardar el juego");
            redirectAttributes.addFlashAttribute("tipo", "danger");
        }

        return "redirect:/juegos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("juego", juegoService.obtenerJuego(id));
        return "juegos/form";
    }

    @PostMapping("/editar/{id}")
    public String actualizarJuego(@PathVariable Long id,
                                 @Valid @ModelAttribute Juego juego,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "juegos/form";
        }

        try {
            juegoService.actualizarJuego(id, juego);
            redirectAttributes.addFlashAttribute("mensaje", "Juego actualizado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al actualizar el juego");
            redirectAttributes.addFlashAttribute("tipo", "danger");
        }

        return "redirect:/juegos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarJuego(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            juegoService.eliminarJuego(id);
            redirectAttributes.addFlashAttribute("mensaje", "Juego eliminado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al eliminar el juego");
            redirectAttributes.addFlashAttribute("tipo", "danger");
        }
        return "redirect:/juegos";
    }
}