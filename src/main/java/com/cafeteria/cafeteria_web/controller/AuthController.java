package com.cafeteria.cafeteria_web.controller;

import com.cafeteria.cafeteria_web.model.RegistroDTO;
import com.cafeteria.cafeteria_web.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Devuelve la vista login.html
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new RegistroDTO());
        return "register"; // Devuelve la vista register.html
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("usuario") RegistroDTO registroDTO) {
        usuarioService.registrarUsuario(registroDTO);
        return "redirect:/login?success"; // Redirige al login con un mensaje de éxito
    }
}
