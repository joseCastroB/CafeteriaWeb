package com.cafeteria.cafeteria_web.repository;

import com.cafeteria.cafeteria_web.entity.LibroReclamaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroReclamacionesRepository extends JpaRepository<LibroReclamaciones, Long> {
}
