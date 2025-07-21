package com.cafeteria.cafeteria_web.controller;

import com.cafeteria.cafeteria_web.entity.LibroReclamaciones;
import com.cafeteria.cafeteria_web.entity.Sugerencia;
import com.cafeteria.cafeteria_web.entity.Usuario;
import com.cafeteria.cafeteria_web.repository.UsuarioRepository;
import com.cafeteria.cafeteria_web.services.FormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    @Autowired
    private UsuarioRepository usuarioRepository; // Inyectar el repositorio de usuarios

    // --- Formulario de Sugerencias ---
    @GetMapping("/sugerencias")
    public String showSugerenciasForm(Model model) {
        Sugerencia sugerencia = new Sugerencia();
        
        // Obtener la información del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            String userEmail = authentication.getName();
            Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(userEmail);
            
            // Si el usuario existe, autocompletar los campos
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                sugerencia.setNombre(usuario.getNombre());
                sugerencia.setCorreo(usuario.getCorreo());
            }
        }
        
        model.addAttribute("sugerencia", sugerencia);
        return "sugerencias";
    }

    @PostMapping("/sugerencias")
    public String submitSugerencia(@ModelAttribute Sugerencia sugerencia, RedirectAttributes redirectAttributes) {
        formularioService.guardarSugerencia(sugerencia);
        redirectAttributes.addFlashAttribute("successMessage", "¡Gracias! Tu sugerencia ha sido enviada con éxito.");
        return "redirect:/sugerencias";
    }

    // --- Libro de Reclamaciones ---
    @GetMapping("/reclamaciones")
    public String showReclamacionesForm(Model model) {
        LibroReclamaciones reclamacion = new LibroReclamaciones();
        
        // Obtener la información del usuario autenticado para autocompletar
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            String userEmail = authentication.getName();
            Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(userEmail);

            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                reclamacion.setConsumidorNombre(usuario.getNombre());
                reclamacion.setConsumidorCorreo(usuario.getCorreo());
                reclamacion.setConsumidorTelefono(usuario.getTelefono());
            }
        }
        
        model.addAttribute("reclamacion", reclamacion);
        return "reclamaciones";
    }

    @PostMapping("/reclamaciones")
    public String submitReclamacion(@ModelAttribute LibroReclamaciones reclamacion, RedirectAttributes redirectAttributes) {
        formularioService.guardarReclamacion(reclamacion);
        redirectAttributes.addFlashAttribute("successMessage", "Tu reclamación ha sido registrada. Nos pondremos en contacto contigo pronto.");
        return "redirect:/reclamaciones";
    }
}
