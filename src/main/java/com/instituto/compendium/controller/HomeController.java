package com.instituto.compendium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Compendium - Portal de Guías y Builds");
        return "index";
    }

    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("titulo", "Lista de juegos");
        return "lista";
    }

    @GetMapping("/formulario")
    public String formulario(Model model) {
        model.addAttribute("titulo", "Crear nueva guía");
        return "formulario";
    }
}