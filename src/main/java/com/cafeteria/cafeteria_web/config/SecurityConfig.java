package com.cafeteria.cafeteria_web.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // UserDetailsService bean no cambia
    @Bean
    public UserDetailsService userDetailsService(com.cafeteria.cafeteria_web.repository.UsuarioRepository usuarioRepository) {
        return username -> {
            var usuario = usuarioRepository.findByCorreo(username)
                .orElseThrow(() -> new org.springframework.security.core.userdetails.UsernameNotFoundException("Usuario no encontrado: " + username));
            
            return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getContrasena())
                .roles(usuario.getRol())
                .build();
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/css/**", "/js/**", "/images/**", "/videos/**").permitAll()
                .requestMatchers("/", "/index", "/register", "/login", "/contacto").permitAll()
                // Proteger rutas de admin
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                // Usar el handler personalizado
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}
