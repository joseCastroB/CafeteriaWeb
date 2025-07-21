-- =================================================================
-- SCRIPT DE CREACIÓN DE BASE DE DATOS PARA CAFETERIA MILENARIA
-- =================================================================
-- Este script crea la base de datos, las tablas necesarias y
-- algunos datos de ejemplo para el proyecto.

-- Borra la base de datos si ya existe para empezar desde cero (opcional, usar con cuidado)
DROP DATABASE IF EXISTS cafeteria_db;

-- Crea la base de datos si no existe
CREATE DATABASE IF NOT EXISTS cafeteria_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- Selecciona la base de datos para trabajar sobre ella
USE cafeteria_db;

-- =================================================================
-- TABLA: usuario
-- Almacena los datos de los usuarios del sistema (clientes y administradores).
-- =================================================================
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL COMMENT 'Contraseña encriptada con BCrypt',
    rol VARCHAR(20) NOT NULL COMMENT 'Ej: ADMIN, USUARIO',
    telefono VARCHAR(20),
    activo BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Para desactivar usuarios sin borrarlos'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =================================================================
-- TABLA: sugerencia
-- Almacena las sugerencias enviadas por los clientes.
-- =================================================================
CREATE TABLE sugerencia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    correo VARCHAR(100),
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =================================================================
-- TABLA: libro_reclamaciones
-- Almacena los registros del libro de reclamaciones según la normativa.
-- =================================================================
CREATE TABLE libro_reclamaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    
    -- 1. Identificación del consumidor
    consumidor_nombre VARCHAR(255) NOT NULL,
    consumidor_documento VARCHAR(20) NOT NULL,
    consumidor_telefono VARCHAR(20) NOT NULL,
    consumidor_correo VARCHAR(100) NOT NULL,
    consumidor_direccion VARCHAR(255) NOT NULL,
    
    -- 2. Identificación del bien o servicio
    tipo_bien VARCHAR(20) NOT NULL COMMENT 'PRODUCTO o SERVICIO',
    descripcion_bien VARCHAR(255) NOT NULL,
    
    -- 3. Detalle de la reclamación
    tipo_solicitud VARCHAR(20) NOT NULL COMMENT 'RECLAMO o QUEJA',
    detalle_solicitud TEXT NOT NULL,
    pedido_consumidor TEXT NOT NULL,
    
    -- Datos de seguimiento
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(50) NOT NULL DEFAULT 'PENDIENTE' COMMENT 'Ej: PENDIENTE, EN PROCESO, ATENDIDO'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- =================================================================
-- INSERCIÓN DE DATOS DE EJEMPLO
-- =================================================================

-- Usuarios de ejemplo con contraseñas encriptadas.
-- La contraseña para ambos es: '12345'
-- Puedes generar tus propias contraseñas encriptadas en: https://www.bcryptcalculator.com/
INSERT INTO usuario (nombre, correo, contrasena, rol, telefono, activo) VALUES
('Administrador Milenaria', 'admin@milenaria.com', '$2a$10$32YI.e5a/mK.1l6E.pMvO.l6CqjZsiw4g3GvYpY.oR8GkKz2mKj8m', 'ADMIN', '987654321', TRUE),
('Cliente Fiel', 'cliente@example.com', '$2a$10$32YI.e5a/mK.1l6E.pMvO.l6CqjZsiw4g3GvYpY.oR8GkKz2mKj8m', 'USUARIO', '912345678', TRUE),
('Usuario Inactivo', 'inactivo@example.com', '$2a$10$32YI.e5a/mK.1l6E.pMvO.l6CqjZsiw4g3GvYpY.oR8GkKz2mKj8m', 'USUARIO', '998877665', FALSE);

-- Sugerencia de ejemplo
INSERT INTO sugerencia (nombre, correo, mensaje, fecha_creacion) VALUES
('Ana Torres', 'ana.t@correo.com', '¡Me encantaría que tuvieran más opciones de postres veganos! El café es excelente.', '2024-05-20 10:30:00');

-- Reclamo de ejemplo
INSERT INTO libro_reclamaciones (consumidor_nombre, consumidor_documento, consumidor_telefono, consumidor_correo, consumidor_direccion, tipo_bien, descripcion_bien, tipo_solicitud, detalle_solicitud, pedido_consumidor, estado) VALUES
('Carlos Gómez', '45678912', '998877665', 'carlos.gomez@example.com', 'Av. Siempre Viva 123, Miraflores', 'PRODUCTO', 'Capuccino y Croissant', 'RECLAMO', 'El capuccino que pedí estaba frío y el croissant no parecía fresco del día.', 'Solicito la devolución de mi dinero por los productos mencionados o un vale de consumo por el mismo valor.', 'PENDIENTE');

-- =================================================================
-- FIN DEL SCRIPT
-- =================================================================

select * from usuario
select * from libro_reclamaciones
select * from sugerencia

