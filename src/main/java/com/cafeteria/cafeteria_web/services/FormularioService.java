package com.cafeteria.cafeteria_web.services;

import com.cafeteria.cafeteria_web.entity.LibroReclamaciones;
import com.cafeteria.cafeteria_web.entity.Sugerencia;
import com.cafeteria.cafeteria_web.repository.LibroReclamacionesRepository;
import com.cafeteria.cafeteria_web.repository.SugerenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormularioService {

    @Autowired
    private SugerenciaRepository sugerenciaRepository;

    @Autowired
    private LibroReclamacionesRepository libroReclamacionesRepository;

    public void guardarSugerencia(Sugerencia sugerencia) {
        sugerenciaRepository.save(sugerencia);
    }

    public void guardarReclamacion(LibroReclamaciones reclamacion) {
        libroReclamacionesRepository.save(reclamacion);
    }
}
