package com.cafeteria.cafeteria_web.services;

import com.cafeteria.cafeteria_web.entity.Usuario;
import com.cafeteria.cafeteria_web.model.RegistroDTO;
import com.cafeteria.cafeteria_web.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(RegistroDTO registroDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setCorreo(registroDTO.getCorreo());
        usuario.setContrasena(passwordEncoder.encode(registroDTO.getContrasena()));
        usuario.setTelefono(registroDTO.getTelefono());
        usuario.setRol("USUARIO"); // Asigna el rol por defecto
        usuario.setActivo(true);
        
        return usuarioRepository.save(usuario);
    }
}
