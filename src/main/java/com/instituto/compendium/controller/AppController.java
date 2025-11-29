package com.instituto.compendium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    // Navegación CRUD simulada para la evaluación (sin lógica de negocio)

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        return "form-crear"; // templates/form-crear.html
    }

    @PostMapping("/guardar")
    public String guardarElemento() {
        return "redirect:/"; // Simula guardado y vuelve al índice
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "form-editar"; // templates/form-editar.html
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarElemento(@PathVariable("id") Long id) {
        return "redirect:/"; // Simula actualización
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarElemento(@PathVariable("id") Long id) {
        return "redirect:/"; // Simula eliminación
    }

    @GetMapping("/detalle")
    public String mostrarDetalle(Model model) {
        return "detalle"; // templates/detalle.html
    }

    @GetMapping("/detalle/{id}")
    public String mostrarDetalleConId(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "detalle"; // templates/detalle.html
    }
}
