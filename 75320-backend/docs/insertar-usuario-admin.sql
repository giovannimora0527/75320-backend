-- Script para crear usuario administrador
-- Ejecutar en MySQL: mysql -u root -p1234 clinica < insertar-usuario-admin.sql
-- O copiar y pegar en MySQL Workbench o cliente MySQL

USE clinica;

-- Eliminar usuario si existe (para evitar duplicados)
DELETE FROM usuario WHERE username = 'admin';

-- Insertar usuario administrador
INSERT INTO usuario (
    username, 
    password_hash, 
    rol, 
    email, 
    fecha_creacion, 
    activo,
    intentos_fallidos,
    bloqueado_hasta
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

-- Verificar que se creó correctamente
SELECT 
    id,
    username,
    rol,
    email,
    activo,
    fecha_creacion
FROM usuario 
WHERE username = 'admin';

