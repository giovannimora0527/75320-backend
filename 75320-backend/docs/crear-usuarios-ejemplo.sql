-- Script SQL para crear usuarios de ejemplo para diferentes roles
-- Ejecutar este script en la base de datos MySQL

-- ============================================
-- USUARIO ADMINISTRADOR
-- ============================================
INSERT INTO `usuario` (
    `username`, 
    `password_hash`, 
    `rol`, 
    `email`, 
    `fecha_creacion`, 
    `activo`,
    `intentos_fallidos`,
    `bloqueado_hasta`
) VALUES (
    'admin',
    '0192023a7bbd73250516f069df18b500',  -- MD5 de "admin123"
    'ADMIN',
    'admin@clinica.com',
    NOW(),
    1,
    0,
    NULL
);

-- ============================================
-- USUARIO MÉDICO
-- ============================================
INSERT INTO `usuario` (
    `username`, 
    `password_hash`, 
    `rol`, 
    `email`, 
    `fecha_creacion`, 
    `activo`,
    `intentos_fallidos`,
    `bloqueado_hasta`
) VALUES (
    'medico1',
    '0192023a7bbd73250516f069df18b500',  -- MD5 de "admin123"
    'MEDICO',
    'medico1@clinica.com',
    NOW(),
    1,
    0,
    NULL
);

-- ============================================
-- USUARIO PACIENTE
-- ============================================
INSERT INTO `usuario` (
    `username`, 
    `password_hash`, 
    `rol`, 
    `email`, 
    `fecha_creacion`, 
    `activo`,
    `intentos_fallidos`,
    `bloqueado_hasta`
) VALUES (
    'paciente1',
    '0192023a7bbd73250516f069df18b500',  -- MD5 de "admin123"
    'PACIENTE',
    'paciente1@clinica.com',
    NOW(),
    1,
    0,
    NULL
);

-- ============================================
-- VERIFICAR USUARIOS CREADOS
-- ============================================
SELECT 
    id,
    username,
    rol,
    email,
    activo,
    fecha_creacion
FROM `usuario`
ORDER BY fecha_creacion DESC;

-- ============================================
-- CREDENCIALES DE ACCESO
-- ============================================
-- Administrador:
--   Usuario: admin
--   Contraseña: admin123
--   Rol: ADMIN
--
-- Médico:
--   Usuario: medico1
--   Contraseña: admin123
--   Rol: MEDICO
--
-- Paciente:
--   Usuario: paciente1
--   Contraseña: admin123
--   Rol: PACIENTE

