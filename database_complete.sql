-- ============================================================
-- MySQL Dump con auditoría de recuperación
-- Base de datos: clinica
-- Compatible con MySQL 8.0+
-- ============================================================

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS `clinica` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `clinica`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- ============================================================
-- Tabla: auditoria_recuperacion (NUEVA)
-- ============================================================

DROP TABLE IF EXISTS `auditoria_recuperacion`;
CREATE TABLE `auditoria_recuperacion` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username_ingresado` VARCHAR(50) NOT NULL,
  `fecha_hora` DATETIME NOT NULL,
  `descripcion` TEXT,
  `exitoso` TINYINT(1) NOT NULL DEFAULT 0,
  `email_usuario` VARCHAR(100) DEFAULT NULL,
  `ip_origen` VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_username` (`username_ingresado`),
  KEY `idx_fecha_hora` (`fecha_hora`),
  KEY `idx_exitoso` (`exitoso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
COMMENT='Auditoría de intentos de recuperación de contraseña.';

-- ============================================================
-- Tabla: auditoria_login
-- ============================================================

CREATE TABLE IF NOT EXISTS `auditoria_login` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `username_ingresado` VARCHAR(50) NOT NULL COMMENT 'Nombre de usuario ingresado en el intento de login',
  `fecha_hora` DATETIME NOT NULL COMMENT 'Fecha y hora del intento de login',
  `exitoso` BOOLEAN NOT NULL COMMENT 'Indica si el intento fue exitoso o fallido',
  `descripcion` TEXT COMMENT 'Descripción del resultado (éxito o motivo del fallo)',
  `ip_origen` VARCHAR(45) COMMENT 'IP desde donde se realizó el intento de login',
  `usuario_id` BIGINT COMMENT 'ID del usuario si el login fue exitoso (null si falló)',
  INDEX `idx_username_fecha` (`username_ingresado`, `fecha_hora`),
  INDEX `idx_fecha_hora` (`fecha_hora`),
  INDEX `idx_usuario_id` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT='Tabla de auditoría para registrar intentos de inicio de sesión.';

-- ============================================================
-- Tabla: usuario
-- ============================================================

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password_hash` TEXT,
  `rol` VARCHAR(30) NOT NULL,
  `fecha_creacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `activo` TINYINT(1) DEFAULT 1,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `usuario` (`id`, `username`, `password_hash`, `rol`, `fecha_creacion`, `activo`, `email`)
VALUES
(26,'gmora','8f6d1367a01184ac584d9a77cb637151','ADMIN','2025-11-04 10:02:24',1,'giovannimora0527@gmail.com'),
(27,'admin','0192023a7bbd73250516f069df18b500','ADMIN',NOW(),1,'admin@clinica.local');

-- ============================================================
-- Agregar columnas de seguridad si no existen
-- ============================================================

-- Columna: intentos_fallidos
SET @sql := (
  SELECT IF(
    COUNT(*) = 0,
    'ALTER TABLE usuario ADD COLUMN intentos_fallidos INT NOT NULL DEFAULT 0 COMMENT "Número de intentos fallidos consecutivos de inicio de sesión";',
    'SELECT "Columna intentos_fallidos ya existe";'
  )
  FROM information_schema.columns
  WHERE table_schema = DATABASE()
    AND table_name = 'usuario'
    AND column_name = 'intentos_fallidos'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- Columna: bloqueado_hasta
SET @sql := (
  SELECT IF(
    COUNT(*) = 0,
    'ALTER TABLE usuario ADD COLUMN bloqueado_hasta DATETIME NULL COMMENT "Fecha y hora hasta la cual el usuario está bloqueado (null si no está bloqueado)";',
    'SELECT "Columna bloqueado_hasta ya existe";'
  )
  FROM information_schema.columns
  WHERE table_schema = DATABASE()
    AND table_name = 'usuario'
    AND column_name = 'bloqueado_hasta'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- Inicializar valores de seguridad
UPDATE usuario 
SET intentos_fallidos = 0, bloqueado_hasta = NULL 
WHERE (intentos_fallidos IS NULL OR bloqueado_hasta IS NOT NULL)
  AND id > 0;

-- ============================================================
-- Tabla: cita (AGREGADA - FALTABA EN EL SCRIPT ORIGINAL)
-- ============================================================

DROP TABLE IF EXISTS `cita`;
CREATE TABLE `cita` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `paciente_id` int(11) NOT NULL,
  `medico_id` int(11) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `motivo` text,
  PRIMARY KEY (`id`),
  KEY `idx_paciente_id` (`paciente_id`),
  KEY `idx_medico_id` (`medico_id`),
  KEY `idx_fecha_hora` (`fecha_hora`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `cita` WRITE;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;
UNLOCK TABLES;

-- ============================================================
-- Resto de tablas (orden lógico)
-- ============================================================

DROP TABLE IF EXISTS `especializacion`;
CREATE TABLE `especializacion` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` TEXT,
  `codigo_especializacion` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`),
  UNIQUE KEY `codigo_especializacion_UNIQUE` (`codigo_especializacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `especializacion` WRITE;
/*!40000 ALTER TABLE `especializacion` DISABLE KEYS */;
INSERT INTO `especializacion` VALUES 
(1,'Cardiología 2','Especialización médica centrada en el diagnóstico y tratamiento de enfermedades del corazón y otras enfermedades asociadas.','COD_100'),
(2,'Neurología','Se ocupa de los trastornos del sistema nervioso central y periférico.','COD_02'),
(3,'Pediatría','Atención médica especializada en niños y adolescentes.','COD_03'),
(4,'Oncología','Estudio y tratamiento de diferentes tipos de cáncer.','COD_04'),
(5,'Dermatología','Diagnóstico y tratamiento de enfermedades de la piel.','COD_05'),
(6,'Ginecología','Enfocada en el sistema reproductor femenino.','COD_06'),
(7,'Urología','Tratamiento del sistema urinario y el sistema reproductor masculino.','COD_07'),
(8,'Psiquiatría','Estudio y tratamiento de enfermedades mentales y emocionales.','COD_08'),
(9,'Oftalmología','Especialización en la salud ocular y la visión.','COD_09'),
(10,'Otorrinolaringología','Se encarga de enfermedades de oído, nariz y garganta.','COD_10'),
(11,'Traumatología','Tratamiento de lesiones y enfermedades del sistema musculoesquelético.','COD_11'),
(12,'Anestesiología','Responsable de la anestesia y control del dolor durante procedimientos quirúrgicos.','COD_12'),
(13,'Reumatología','Estudia enfermedades autoinmunes y musculoesqueléticas.','COD_13'),
(14,'Endocrinología','Especialización en glándulas y trastornos hormonales.','COD_14'),
(15,'Gastroenterología','Diagnóstico y tratamiento de enfermedades del aparato digestivo.','COD_15');
/*!40000 ALTER TABLE `especializacion` ENABLE KEYS */;
UNLOCK TABLES;

-- ============================================================
-- Tabla: historia_medica
-- ============================================================

DROP TABLE IF EXISTS `historia_medica`;
CREATE TABLE `historia_medica` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `paciente_id` int(11) NOT NULL,
  `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `historia_medica` WRITE;
UNLOCK TABLES;

-- ============================================================
-- Tabla: medicamento
-- ============================================================

DROP TABLE IF EXISTS `medicamento`;
CREATE TABLE `medicamento` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  `presentacion` varchar(100) DEFAULT NULL,
  `fecha_compra` date NOT NULL,
  `fecha_vence` date NOT NULL,
  `fecha_creacion_registro` datetime NOT NULL,
  `fecha_modificacion_registro` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `medicamento` WRITE;
/*!40000 ALTER TABLE `medicamento` DISABLE KEYS */;
INSERT INTO `medicamento` VALUES 
(2,'Paracetamol','Analgésico y antipirético','Tabletas 500mg','2024-06-01','2026-06-01','2025-09-11 20:56:13',NULL),
(5,'Paracetamol Inyectable','Analgésico y antipirético','Tabletas 1200mg','2024-06-01','2026-06-01','2025-09-13 10:58:40','2025-09-13 12:49:23'),
(6,'Paracetamol Solución','Analgésico y antipirético','Tabletas 800mg','2024-06-01','2026-06-01','2025-09-13 12:27:12',NULL);
/*!40000 ALTER TABLE `medicamento` ENABLE KEYS */;
UNLOCK TABLES;

-- ============================================================
-- Tabla: medico
-- ============================================================

DROP TABLE IF EXISTS `medico`;
CREATE TABLE `medico` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_documento` varchar(10) NOT NULL,
  `numero_documento` varchar(20) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `registro_profesional` varchar(50) NOT NULL,
  `especializacion_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_documento` (`numero_documento`),
  UNIQUE KEY `registro_profesional` (`registro_profesional`),
  KEY `idx_medico_documento` (`numero_documento`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `medico` WRITE;
/*!40000 ALTER TABLE `medico` DISABLE KEYS */;
INSERT INTO `medico` VALUES 
(1,'CC','1001001001','Juan','Pérez Gómez','3001112233','RP-1001',13),
(2,'CC','1001001002','María','Rodríguez López','3001112234','RP-1002',2),
(3,'CC','1001001003','Carlos','Martínez Ruiz','3001112235','RP-1003',3),
(4,'CC','1001001004','Laura','Fernández Díaz','3001112236','RP-1004',4),
(5,'CC','1001001005','Andrés','Ramírez Soto','3001112237','RP-1005',5),
(6,'CC','1001001006','Diana','Gómez Torres','3001112238','RP-1006',6),
(7,'CC','1001001007','Ricardo','Jiménez Vargas','3001112239','RP-1007',7),
(8,'CC','1001001008','Patricia','Hernández Ríos','3001112240','RP-1008',8),
(9,'CC','1001001009','Santiago','Morales Castro','3001112241','RP-1009',9),
(10,'CC','1001001010','Natalia','García Mendoza','3001112242','RP-1010',9),
(11,'CC','88274388','Giovanni','Mora','5711322','1234656789100',8),
(12,'CC','12345678','Edson Arantes','Do nascimento','3207729661','RP-10011',12),
(13,'CC','12345123','Giovanni','Mora Jaimes','3207729661','001RP1234',12);
/*!40000 ALTER TABLE `medico` ENABLE KEYS */;
UNLOCK TABLES;

-- ============================================================
-- Tabla: paciente
-- ============================================================

DROP TABLE IF EXISTS `paciente`;
CREATE TABLE `paciente` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `tipo_documento` varchar(10) NOT NULL,
  `numero_documento` varchar(20) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `genero` char(1) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `direccion` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_documento` (`numero_documento`),
  UNIQUE KEY `usuario_id` (`usuario_id`),
  KEY `idx_paciente_documento` (`numero_documento`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES 
(1,1,'CC','100000001','Juan','Pérez López','1990-05-12','M','3001112233','Calle 10 #12-34'),
(2,2,'CC','100000002','María','Gómez Rodríguez','1985-08-22','F','3012223344','Carrera 15 #20-10'),
(3,3,'TI','900000003','Andrés','Martínez Díaz','2005-03-18','M','3023334455','Av. Boyacá #45-67'),
(4,4,'CC','100000004','Laura','Hernández Ruiz','1992-12-01','F','3034445566','Calle 5 #7-89'),
(5,5,'CC','100000005','Carlos','Ramírez Torres','1980-07-30','M','3045556677','Carrera 8 #15-20'),
(6,6,'CE','200000006','Ana','Castro Moreno','1998-01-25','F','3056667788','Diagonal 12 #25-50'),
(7,7,'CC','100000007','Santiago','Moreno Vargas','1995-09-14','M','3067778899','Transversal 20 #40-22'),
(8,8,'CC','100000008','Paula','Ríos Fernández','1987-11-10','F','3078889900','Calle 22 #18-45'),
(9,9,'TI','900000009','Felipe','Gutiérrez Méndez','2006-06-06','M','3089990011','Carrera 50 #60-12'),
(10,10,'CC','100000010','Diana','Salazar Peña','1993-04-21','F','3090001122','Av. Caracas #100-50');
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

-- ============================================================
-- Tabla: receta
-- ============================================================

DROP TABLE IF EXISTS `receta`;
CREATE TABLE `receta` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `cita_id` int(11) NOT NULL,
  `medicamento_id` int(11) NOT NULL,
  `dosis` text NOT NULL,
  `indicaciones` text,
  `fecha_creacion_registro` datetime NOT NULL,
  `fecha_actualizacion_registro` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `receta` WRITE;
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
INSERT INTO `receta` VALUES 
(1,1,5,'800 mg cada 12 horas','Tomar después de las comidas durante 7 días','2025-09-17 15:17:32',NULL),
(2,2,6,'500 mg cada 8 horas','Tomar después de las comidas durante 7 días','2025-09-20 11:52:42',NULL),
(4,1,5,'Una pastilla cada 12 horas.','Cambiando Cualquier cosa','2025-10-30 21:09:02','2025-10-30 21:21:51'),
(5,3,2,'Una pastilla cada 12 horas.','Tomar despues del almuerzo y con abundante liquido','2025-11-01 10:21:04',NULL);
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;
UNLOCK TABLES;

-- ============================================================
-- Tabla: session
-- ============================================================

DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
  `session_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `token` varchar(500) NOT NULL,
  `fecha_ini_sesion` datetime DEFAULT NULL,
  `fecha_expiracion` datetime DEFAULT NULL,
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` VALUES 
(3,26,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnbW9yYSIsImNvcnJlbyI6Imdpb3Zhbm5pbW9yYTA1MjdAZ21haWwuY29tIiwiZmVjaGFfZmluX3Nlc2lvbiI6MTc2MjM2MDY2MCwiZmVjaGFfaW5pY2lvX3Nlc2lvbiI6MTc2MjI3NDI2MH0.lJTUCXWD26sxNSS2TJv5TzH0RNOtY-a7HiSfJbFnSNM','2025-11-04 11:37:40','2025-11-05 11:37:40');
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

-- ============================================================
-- FIN SCRIPT
-- ============================================================

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

