/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cafeteria.cafeteria_web.servicio;

import com.cafeteria.cafeteria_web.entity.Resena;
import java.util.List;

public interface ResenaServicio {
    List<Resena> listarResenas();
    void guardarResena(Resena resena);
    Resena obtenerResenaPorId(Integer id);
    void eliminarResena(Integer id);
}
