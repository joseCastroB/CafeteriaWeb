package com.cafeteria.cafeteria_web.controller;

import com.cafeteria.cafeteria_web.entity.Usuario;
import com.cafeteria.cafeteria_web.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioServicio.listarUsuarios());
        return "usuarios_listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios_formulario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        if (usuario.getId() != null) {
            // EDITAR: buscar el existente
            Usuario existente = usuarioServicio.obtenerUsuarioPorId(usuario.getId());
            if (existente != null) {
                existente.setNombre(usuario.getNombre());
                existente.setCorreo(usuario.getCorreo());
                existente.setRol(usuario.getRol());
                usuarioServicio.guardarUsuario(existente); 
            }
        } else {
            // NUEVO: registrar
            usuarioServicio.guardarUsuario(usuario);
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(id);
        model.addAttribute("usuario", usuario);
        return "usuarios_formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioServicio.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
}
