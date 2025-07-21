package com.cafeteria.cafeteria_web.services;

import com.cafeteria.cafeteria_web.entity.LibroReclamaciones;
import com.cafeteria.cafeteria_web.entity.Sugerencia;
import com.cafeteria.cafeteria_web.repository.LibroReclamacionesRepository;
import com.cafeteria.cafeteria_web.repository.SugerenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Sugerencia> findAllSugerencias() {
        return sugerenciaRepository.findAll();
    }

    public void deleteSugerencia(Long id) {
        sugerenciaRepository.deleteById(id);
    }

    public List<LibroReclamaciones> findAllReclamaciones() {
        return libroReclamacionesRepository.findAll();
    }

    public void deleteReclamacion(Long id) {
        libroReclamacionesRepository.deleteById(id);
    }

    public void updateReclamacionStatus(Long id, String nuevoEstado) {
        LibroReclamaciones reclamacion = libroReclamacionesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Reclamación inválido: " + id));
        reclamacion.setEstado(nuevoEstado);
        libroReclamacionesRepository.save(reclamacion);
    }
}