package com.cafeteria.cafeteria_web.controller;


import com.cafeteria.cafeteria_web.entity.LibroReclamaciones;
import com.cafeteria.cafeteria_web.entity.Sugerencia;
import com.cafeteria.cafeteria_web.entity.Usuario;
import com.cafeteria.cafeteria_web.model.UsuarioEditDTO;
import com.cafeteria.cafeteria_web.services.FormularioService;
import com.cafeteria.cafeteria_web.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FormularioService formularioService;

    @GetMapping("/dashboard")
    public String showAdminDashboard() {
        return "admin/dashboard";
    }

    // --- Gestión de Usuarios ---
    @GetMapping("/usuarios")
    public String showUserList(Model model) {
        List<Usuario> usuarios = usuarioService.findUsersByRole("USUARIO");
        model.addAttribute("usuarios", usuarios);
        return "admin/lista_usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Usuario inválido:" + id));
        
        UsuarioEditDTO usuarioDTO = new UsuarioEditDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setCorreo(usuario.getCorreo());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setRol(usuario.getRol());
        
        model.addAttribute("usuarioDTO", usuarioDTO);
        return "admin/editar_usuario";
    }

    @PostMapping("/usuarios/editar")
    public String updateUser(@ModelAttribute("usuarioDTO") UsuarioEditDTO usuarioDTO, RedirectAttributes redirectAttributes) {
        usuarioService.updateUsuario(usuarioDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Usuario actualizado con éxito.");
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/usuarios/toggle/{id}")
    public String toggleUserStatus(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        usuarioService.toggleUsuarioStatus(id);
        redirectAttributes.addFlashAttribute("successMessage", "Estado del usuario cambiado con éxito.");
        return "redirect:/admin/usuarios";
    }

    // --- Gestión de Sugerencias ---
    @GetMapping("/sugerencias")
    public String showSugerenciasList(Model model) {
        List<Sugerencia> sugerencias = formularioService.findAllSugerencias();
        model.addAttribute("sugerencias", sugerencias);
        return "admin/lista_sugerencias";
    }

    @PostMapping("/sugerencias/eliminar/{id}")
    public String deleteSugerencia(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        formularioService.deleteSugerencia(id);
        redirectAttributes.addFlashAttribute("successMessage", "Sugerencia eliminada con éxito.");
        return "redirect:/admin/sugerencias";
    }

    // --- Gestión de Reclamaciones ---
    @GetMapping("/reclamaciones")
    public String showReclamacionesList(Model model) {
        List<LibroReclamaciones> reclamaciones = formularioService.findAllReclamaciones();
        model.addAttribute("reclamaciones", reclamaciones);
        return "admin/lista_reclamaciones";
    }

    @PostMapping("/reclamaciones/eliminar/{id}")
    public String deleteReclamacion(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        formularioService.deleteReclamacion(id);
        redirectAttributes.addFlashAttribute("successMessage", "Reclamación eliminada con éxito.");
        return "redirect:/admin/reclamaciones";
    }

    @PostMapping("/reclamaciones/estado/{id}")
    public String updateReclamacionStatus(@PathVariable("id") Long id, @RequestParam("estado") String estado, RedirectAttributes redirectAttributes) {
        formularioService.updateReclamacionStatus(id, estado);
        redirectAttributes.addFlashAttribute("successMessage", "Estado de la reclamación actualizado con éxito.");
        return "redirect:/admin/reclamaciones";
    }
}
