package com.cafeteria.cafeteria_web.model;

import lombok.Data;

@Data
public class UsuarioEditDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    private String rol;
    // No incluimos contraseña ni estado activo para simplificar la edición
}