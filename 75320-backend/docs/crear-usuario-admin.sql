-- Script SQL para crear un usuario administrador por defecto
-- Ejecutar este script en la base de datos MySQL antes de usar la aplicación

-- Nota: La contraseña se cifra usando MD5 en el backend
-- Contraseña en texto plano: "admin123"
-- Hash MD5: "0192023a7bbd73250516f069df18b500"

-- Insertar usuario administrador
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
    1,  -- activo = true
    0,  -- sin intentos fallidos
    NULL  -- no bloqueado
);

-- Verificar que el usuario se creó correctamente
SELECT * FROM `usuario` WHERE `username` = 'admin';

-- Credenciales de acceso:
-- Usuario: admin
-- Contraseña: admin123
-- Rol: ADMIN

