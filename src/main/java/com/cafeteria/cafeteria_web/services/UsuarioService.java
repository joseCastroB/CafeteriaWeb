package com.cafeteria.cafeteria_web.services;


import com.cafeteria.cafeteria_web.entity.Usuario;
import com.cafeteria.cafeteria_web.model.RegistroDTO;
import com.cafeteria.cafeteria_web.model.UsuarioEditDTO;
import com.cafeteria.cafeteria_web.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        usuario.setRol("USUARIO");
        usuario.setActivo(true);
        
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findUsersByRole(String role) {
        return usuarioRepository.findAllByRol(role);
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public void updateUsuario(UsuarioEditDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("ID de Usuario inválido:" + usuarioDTO.getId()));
        
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setRol(usuarioDTO.getRol());
        
        usuarioRepository.save(usuario);
    }

    public void toggleUsuarioStatus(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Usuario inválido:" + id));
        
        usuario.setActivo(!usuario.isActivo());
        usuarioRepository.save(usuario);
    }
}
