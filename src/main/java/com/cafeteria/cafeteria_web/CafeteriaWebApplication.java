package com.cafeteria.cafeteria_web;

import com.cafeteria.cafeteria_web.entity.Usuario;
import com.cafeteria.cafeteria_web.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class CafeteriaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeteriaWebApplication.class, args);
    }

    /**
     * Este Bean se ejecuta una vez al iniciar la aplicación.
     * Su propósito es asegurar que la contraseña del administrador esté correctamente
     * encriptada en la base de datos, sincronizándola si es necesario.
     */
    @Bean
    public CommandLineRunner initAdmin(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String adminEmail = "admin@milenaria.com";
            Optional<Usuario> adminOpt = usuarioRepository.findByCorreo(adminEmail);

            if (adminOpt.isPresent()) {
                Usuario admin = adminOpt.get();
                String rawPassword = "12345"; // La contraseña que queremos asegurar

                // Verificamos si la contraseña actual en la BD NO coincide con la que debería ser.
                // Si no coincide, la actualizamos con la versión correctamente encriptada.
                if (!passwordEncoder.matches(rawPassword, admin.getContrasena())) {
                    admin.setContrasena(passwordEncoder.encode(rawPassword));
                    usuarioRepository.save(admin);
                    System.out.println("************************************************************");
                    System.out.println("Contraseña del administrador 'admin@milenaria.com' actualizada.");
                    System.out.println("La contraseña ahora es: " + rawPassword);
                    System.out.println("************************************************************");
                }
            }
        };
    }
}
