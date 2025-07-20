/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cafeteria.cafeteria_web.servicio.impl;

import com.cafeteria.cafeteria_web.entity.Resena;
import com.cafeteria.cafeteria_web.repository.ResenaRepositorio;
import com.cafeteria.cafeteria_web.servicio.ResenaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaServicioImpl implements ResenaServicio {

    @Autowired
    private ResenaRepositorio resenaRepositorio;

    @Override
    public List<Resena> listarResenas() {
        return resenaRepositorio.findAll();
    }

    @Override
    public void guardarResena(Resena resena) {
        resenaRepositorio.save(resena);
    }

    @Override
    public Resena obtenerResenaPorId(Integer id) {
        return resenaRepositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminarResena(Integer id) {
        resenaRepositorio.deleteById(id);
    }
}