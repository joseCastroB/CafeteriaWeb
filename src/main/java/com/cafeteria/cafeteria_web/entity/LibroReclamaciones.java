package com.cafeteria.cafeteria_web.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "libro_reclamaciones")
public class LibroReclamaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consumidor_nombre", nullable = false)
    private String consumidorNombre;

    @Column(name = "consumidor_documento", nullable = false)
    private String consumidorDocumento;

    @Column(name = "consumidor_telefono", nullable = false)
    private String consumidorTelefono;
    
    @Column(name = "consumidor_correo", nullable = false)
    private String consumidorCorreo;

    @Column(name = "consumidor_direccion", nullable = false)
    private String consumidorDireccion;

    @Column(name = "tipo_bien", nullable = false)
    private String tipoBien;

    @Column(name = "descripcion_bien", nullable = false)
    private String descripcionBien;

    @Column(name = "tipo_solicitud", nullable = false)
    private String tipoSolicitud;

    @Column(name = "detalle_solicitud", nullable = false, columnDefinition = "TEXT")
    private String detalleSolicitud;

    @Column(name = "pedido_consumidor", nullable = false, columnDefinition = "TEXT")
    private String pedidoConsumidor;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    
    @Column(nullable = false)
    private String estado = "PENDIENTE";
}