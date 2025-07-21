package com.cafeteria.cafeteria_web.repository;


import com.cafeteria.cafeteria_web.entity.Sugerencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SugerenciaRepository extends JpaRepository<Sugerencia, Long> {
}
