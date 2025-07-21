package com.cafeteria.cafeteria_web.repository;

import com.cafeteria.cafeteria_web.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su dirección de correo electrónico.
     * Es crucial para el proceso de login.
     * @param correo El correo del usuario a buscar.
     * @return Un Optional que contiene al usuario si se encuentra.
     */
    Optional<Usuario> findByCorreo(String correo);
}
