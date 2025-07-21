package com.cafeteria.cafeteria_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para manejar las páginas principales y estáticas de la aplicación.
 */
@Controller
public class HomeController {

    /**
     * Maneja las solicitudes a la URL raíz ("/") y a "/index".
     * Devuelve el nombre de la vista de la página de inicio, que en este caso
     * corresponde a 'index.html' en la carpeta de plantillas (templates).
     *
     * @return El nombre de la plantilla de la página de inicio.
     */
    @GetMapping({"/", "/index"})
    public String home() {
        // Spring Boot, con Thymeleaf, buscará automáticamente un archivo llamado "index.html"
        // en la carpeta src/main/resources/templates/
        return "index";
    }

     /**
     * Muestra la página de contacto.
     * @return El nombre de la plantilla de la página de contacto.
     */
    @GetMapping("/contacto")
    public String showContactoPage() {
        return "contacto";
    }

}

