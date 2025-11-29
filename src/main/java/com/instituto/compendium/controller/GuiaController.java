package com.instituto.compendium.controller;

import com.instituto.compendium.model.Guia;
import com.instituto.compendium.model.Usuario;
import com.instituto.compendium.repository.GuiaRepository;
import com.instituto.compendium.service.GuiaService;
import com.instituto.compendium.service.JuegoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guias")
public class GuiaController {

    // Verificar permisos de autor o admin para acciones de creación/edición
    private boolean tienePermisosEdicion(Usuario usuario) {
        return usuario != null && 
               (usuario.hasRole("ROLE_ADMIN") || usuario.hasRole("ROLE_AUTOR"));
    }

    @Autowired
    private GuiaService guiaService;

    @Autowired
    private GuiaRepository guiaRepository;

    @Autowired
    private JuegoService juegoService;

    @GetMapping
    public String listarGuias(@PageableDefault(size = 12) Pageable pageable,
                             @RequestParam(required = false) String buscar,
                             Model model) {
        Page<Guia> guias = buscar != null ? 
            guiaRepository.buscar(buscar, pageable) :
            guiaService.obtenerGuiasPublicadas(pageable);
            
        model.addAttribute("guias", guias);
        model.addAttribute("buscar", buscar);
        return "guias/lista";
    }

    @GetMapping("/nueva")
    public String nuevaGuiaForm(Model model, @AuthenticationPrincipal Usuario usuario) {
        if (!tienePermisosEdicion(usuario)) {
            return "redirect:/guias";
        }
        model.addAttribute("guia", new Guia());
        model.addAttribute("juegos", juegoService.listarJuegos());
        return "guias/form";
    }

    @PostMapping("/guardar")
    public String guardarGuia(@Valid @ModelAttribute Guia guia,
                             BindingResult result,
                             @RequestParam(required = false) MultipartFile imagen,
                             @RequestParam(required = false) MultipartFile[] archivos,
                             @AuthenticationPrincipal Usuario usuario,
                             RedirectAttributes redirectAttributes) {
        if (!tienePermisosEdicion(usuario)) {
            redirectAttributes.addFlashAttribute("mensaje", "No tienes permisos para crear guías");
            redirectAttributes.addFlashAttribute("tipo", "danger");
            return "redirect:/guias";
        }
        if (result.hasErrors()) {
            return "guias/form";
        }

        try {
            guia.setAutor(usuario);
            guiaService.crearGuia(guia, imagen, archivos);
            redirectAttributes.addFlashAttribute("mensaje", "Guía guardada exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/guias";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al guardar la guía");
            redirectAttributes.addFlashAttribute("tipo", "danger");
            return "redirect:/guias/nueva";
        }
    }

    @GetMapping("/{id}")
    public String verGuia(@PathVariable Long id, Model model) {
        try {
            Guia guia = guiaService.obtenerGuia(id);
            model.addAttribute("guia", guia);
            return "guias/ver";
        } catch (RuntimeException e) {
            return "redirect:/guias";
        }
    }

    @GetMapping("/{id}/editar")
    public String editarGuiaForm(@PathVariable Long id, Model model, @AuthenticationPrincipal Usuario usuario) {
        try {
            Guia guia = guiaService.obtenerGuia(id);
            
            if (!tienePermisosEdicion(usuario) || (!usuario.hasRole("ROLE_ADMIN") && !guia.getAutor().equals(usuario))) {
                return "redirect:/guias";
            }
            
            model.addAttribute("guia", guia);
            model.addAttribute("juegos", juegoService.listarJuegos());
            return "guias/form";
        } catch (RuntimeException e) {
            return "redirect:/guias";
        }
    }

    @PostMapping("/{id}/editar")
    public String editarGuia(@PathVariable Long id,
                            @Valid @ModelAttribute Guia guia,
                            BindingResult result,
                            @RequestParam(required = false) MultipartFile imagen,
                            @AuthenticationPrincipal Usuario usuario,
                            RedirectAttributes redirectAttributes) {
        if (!tienePermisosEdicion(usuario)) {
            redirectAttributes.addFlashAttribute("mensaje", "No tienes permisos para editar guías");
            redirectAttributes.addFlashAttribute("tipo", "danger");
            return "redirect:/guias";
        }

        Guia guiaExistente = guiaService.obtenerGuia(id);
        if (!usuario.hasRole("ROLE_ADMIN") && !guiaExistente.getAutor().equals(usuario)) {
            redirectAttributes.addFlashAttribute("mensaje", "Solo puedes editar tus propias guías");
            redirectAttributes.addFlashAttribute("tipo", "danger");
            return "redirect:/guias";
        }
        if (result.hasErrors()) {
            return "guias/form";
        }

        try {
            guiaService.actualizarGuia(id, guia, imagen);
            redirectAttributes.addFlashAttribute("mensaje", "Guía actualizada exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/guias/" + id;
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al actualizar la guía");
            redirectAttributes.addFlashAttribute("tipo", "danger");
            return "redirect:/guias/" + id + "/editar";
        }
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarGuia(@PathVariable Long id,
                              @AuthenticationPrincipal Usuario usuario,
                              RedirectAttributes redirectAttributes) {
        try {
            guiaService.eliminarGuia(id, usuario);
            redirectAttributes.addFlashAttribute("mensaje", "Guía eliminada exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensaje", e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "danger");
        }
        return "redirect:/guias";
    }
}