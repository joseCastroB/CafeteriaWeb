package com.cafeteria.cafeteria_web.controller;

import com.cafeteria.cafeteria_web.entity.Resena;
import com.cafeteria.cafeteria_web.servicio.ResenaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/resenas")
public class ResenaController {

    @Autowired
    private ResenaServicio resenaServicio;

    @GetMapping
    public String listarResenas(Model modelo) {
        modelo.addAttribute("resenas", resenaServicio.listarResenas());
        return "resenas_lista";
    }

    @GetMapping("/nueva")
    public String formularioNuevaResena(Model modelo) {
        modelo.addAttribute("resena", new Resena());
        return "resenas_formulario";
    }

    @PostMapping("/guardar")
    public String guardarResena(@ModelAttribute("resena") Resena resena) {
        resenaServicio.guardarResena(resena);
        return "redirect:/resenas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarResena(@PathVariable Integer id) {
        resenaServicio.eliminarResena(id);
        return "redirect:/resenas";
    }
}
