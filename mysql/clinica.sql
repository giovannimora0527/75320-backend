CREATE DATABASE  IF NOT EXISTS `clinica` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `clinica`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: clinica
-- ------------------------------------------------------
-- Server version	9.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `anotacion_historia`
--

DROP TABLE IF EXISTS `anotacion_historia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anotacion_historia` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `historia_id` int NOT NULL,
  `medico_id` int NOT NULL,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  `descripcion` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anotacion_historia`
--

LOCK TABLES `anotacion_historia` WRITE;
/*!40000 ALTER TABLE `anotacion_historia` DISABLE KEYS */;
/*!40000 ALTER TABLE `anotacion_historia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auditoria_recuperacion_password`
--

DROP TABLE IF EXISTS `auditoria_recuperacion_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auditoria_recuperacion_password` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `username_ingresado` varchar(50) NOT NULL,
  `fecha_hora` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ip_origen` varchar(50) DEFAULT NULL,
  `exitoso` tinyint(1) NOT NULL DEFAULT '0',
  `descripcion_error` text,
  `email_destino` varchar(255) DEFAULT NULL COMMENT 'Email al que se envió contraseña temporal (solo si exitoso=1)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `idx_auditoria_fecha` (`fecha_hora`),
  KEY `idx_auditoria_username` (`username_ingresado`),
  KEY `idx_auditoria_exitoso` (`exitoso`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Auditoría de intentos de recuperación de contraseña';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditoria_recuperacion_password`
--

LOCK TABLES `auditoria_recuperacion_password` WRITE;
/*!40000 ALTER TABLE `auditoria_recuperacion_password` DISABLE KEYS */;
INSERT INTO `auditoria_recuperacion_password` VALUES (1,'carlos','2025-11-17 16:26:11','0:0:0:0:0:0:0:1',1,NULL,'andrestco.16@gmail.com'),(2,'carlos','2025-11-17 18:02:31','0:0:0:0:0:0:0:1',1,NULL,'andrestco.16@gmail.com'),(3,'carlos','2025-11-18 22:34:30','0:0:0:0:0:0:0:1',1,NULL,'andrestco.16@gmail.com');
/*!40000 ALTER TABLE `auditoria_recuperacion_password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cita`
--

DROP TABLE IF EXISTS `cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cita` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `paciente_id` int NOT NULL,
  `medico_id` int NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `estado` varchar(20) NOT NULL,
  `motivo` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cita`
--

LOCK TABLES `cita` WRITE;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
INSERT INTO `cita` VALUES (1,1,3,'2025-10-16 21:30:00','programada','Consulta general'),(2,2,3,'2024-06-21 14:30:00','programada','Consulta odontologica'),(3,3,2,'2024-06-20 18:30:00','programada','Consulta general');
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especializacion`
--

DROP TABLE IF EXISTS `especializacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especializacion` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  `codigo_especializacion` varchar(10) NOT NULL DEFAULT '""',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `nombre` (`nombre`),
  UNIQUE KEY `codigo_especializacion_UNIQUE` (`codigo_especializacion`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especializacion`
--

LOCK TABLES `especializacion` WRITE;
/*!40000 ALTER TABLE `especializacion` DISABLE KEYS */;
INSERT INTO `especializacion` VALUES (1,'Cardiología 2','Especialización médica centrada en el diagnóstico y tratamiento de enfermedades del corazón y otras enfermedades asociadas.','COD_100'),(2,'Neurología','Se ocupa de los trastornos del sistema nervioso central y periférico.','COD_02'),(3,'Pediatría','Atención médica especializada en niños y adolescentes.','COD_03'),(4,'Oncología','Estudio y tratamiento de diferentes tipos de cáncer.','COD_04'),(5,'Dermatología','Diagnóstico y tratamiento de enfermedades de la piel.','COD_05'),(6,'Ginecología','Enfocada en el sistema reproductor femenino.','COD_06'),(7,'Urología','Tratamiento del sistema urinario y el sistema reproductor masculino.','COD_07'),(8,'Psiquiatría','Estudio y tratamiento de enfermedades mentales y emocionales.','COD_08'),(9,'Oftalmología','Especialización en la salud ocular y la visión.','COD_09'),(10,'Otorrinolaringología','Se encarga de enfermedades de oído, nariz y garganta.','COD_10'),(11,'Traumatología','Tratamiento de lesiones y enfermedades del sistema musculoesquelético.','COD_11'),(12,'Anestesiología','Responsable de la anestesia y control del dolor durante procedimientos quirúrgicos.','COD_12'),(13,'Reumatología','Estudia enfermedades autoinmunes y musculoesqueléticas.','COD_13'),(14,'Endocrinología','Especialización en glándulas y trastornos hormonales.','COD_14'),(15,'Gastroenterología','Diagnóstico y tratamiento de enfermedades del aparato digestivo.','COD_15');
/*!40000 ALTER TABLE `especializacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historia_medica`
--

DROP TABLE IF EXISTS `historia_medica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historia_medica` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `paciente_id` int NOT NULL,
  `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historia_medica`
--

LOCK TABLES `historia_medica` WRITE;
/*!40000 ALTER TABLE `historia_medica` DISABLE KEYS */;
/*!40000 ALTER TABLE `historia_medica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_intento`
--

DROP TABLE IF EXISTS `login_intento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_intento` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `usuario_id` bigint unsigned NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `exito` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_login_usuario` (`usuario_id`),
  CONSTRAINT `fk_login_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_intento`
--

LOCK TABLES `login_intento` WRITE;
/*!40000 ALTER TABLE `login_intento` DISABLE KEYS */;
INSERT INTO `login_intento` VALUES (1,27,'2025-11-16 20:38:31','0:0:0:0:0:0:0:1',1),(2,27,'2025-11-16 20:40:00','0:0:0:0:0:0:0:1',0),(3,27,'2025-11-16 20:40:22','0:0:0:0:0:0:0:1',0),(4,27,'2025-11-16 20:40:26','0:0:0:0:0:0:0:1',0),(5,27,'2025-11-16 20:43:37','0:0:0:0:0:0:0:1',0),(6,28,'2025-11-16 20:43:59','0:0:0:0:0:0:0:1',1),(7,27,'2025-11-16 20:45:14','0:0:0:0:0:0:0:1',0),(8,27,'2025-11-17 07:47:43','0:0:0:0:0:0:0:1',0),(9,27,'2025-11-17 07:47:47','0:0:0:0:0:0:0:1',0),(10,27,'2025-11-17 07:47:48','0:0:0:0:0:0:0:1',0),(11,27,'2025-11-17 07:48:10','0:0:0:0:0:0:0:1',0),(12,28,'2025-11-17 07:48:31','0:0:0:0:0:0:0:1',1),(13,27,'2025-11-17 07:48:47','0:0:0:0:0:0:0:1',0),(14,27,'2025-11-17 16:13:32','0:0:0:0:0:0:0:1',1),(15,27,'2025-11-17 16:14:04','0:0:0:0:0:0:0:1',0),(16,27,'2025-11-17 16:14:07','0:0:0:0:0:0:0:1',0),(17,27,'2025-11-17 16:14:09','0:0:0:0:0:0:0:1',0),(18,27,'2025-11-17 16:14:32','0:0:0:0:0:0:0:1',0),(19,28,'2025-11-17 16:18:45','0:0:0:0:0:0:0:1',1),(20,27,'2025-11-17 16:29:35','0:0:0:0:0:0:0:1',0),(21,27,'2025-11-17 16:30:08','0:0:0:0:0:0:0:1',0),(22,27,'2025-11-17 16:31:09','0:0:0:0:0:0:0:1',0),(23,28,'2025-11-17 16:31:23','0:0:0:0:0:0:0:1',1),(24,29,'2025-11-17 16:34:38','0:0:0:0:0:0:0:1',1),(25,27,'2025-11-17 18:01:35','0:0:0:0:0:0:0:1',1),(26,27,'2025-11-17 18:01:57','0:0:0:0:0:0:0:1',0),(27,27,'2025-11-17 18:02:02','0:0:0:0:0:0:0:1',0),(28,27,'2025-11-17 18:02:07','0:0:0:0:0:0:0:1',0),(29,27,'2025-11-18 22:33:12','0:0:0:0:0:0:0:1',0),(30,27,'2025-11-18 22:34:02','0:0:0:0:0:0:0:1',1),(31,27,'2025-11-18 22:34:49','0:0:0:0:0:0:0:1',1),(32,27,'2025-11-18 22:35:03','0:0:0:0:0:0:0:1',1),(33,30,'2025-11-19 09:09:58','0:0:0:0:0:0:0:1',1);
/*!40000 ALTER TABLE `login_intento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicamento`
--

DROP TABLE IF EXISTS `medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicamento` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  `presentacion` varchar(100) DEFAULT NULL,
  `fecha_compra` date NOT NULL,
  `fecha_vence` date NOT NULL,
  `fecha_creacion_registro` datetime NOT NULL,
  `fecha_modificacion_registro` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicamento`
--

LOCK TABLES `medicamento` WRITE;
/*!40000 ALTER TABLE `medicamento` DISABLE KEYS */;
INSERT INTO `medicamento` VALUES (2,'Paracetamol','Analgésico y antipirético','Tabletas 500mg','2024-06-01','2026-06-01','2025-09-11 20:56:13',NULL),(5,'Paracetamol Inyectable','Analgésico y antipirético','Tabletas 1200mg','2024-06-01','2026-06-01','2025-09-13 10:58:40','2025-09-13 12:49:23'),(6,'Paracetamol Solución','Analgésico y antipirético','Tabletas 800mg','2024-06-01','2026-06-01','2025-09-13 12:27:12',NULL);
/*!40000 ALTER TABLE `medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medico`
--

DROP TABLE IF EXISTS `medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medico` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `tipo_documento` varchar(10) NOT NULL,
  `numero_documento` varchar(20) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `registro_profesional` varchar(50) NOT NULL,
  `especializacion_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `numero_documento` (`numero_documento`),
  UNIQUE KEY `registro_profesional` (`registro_profesional`),
  KEY `idx_medico_documento` (`numero_documento`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medico`
--

LOCK TABLES `medico` WRITE;
/*!40000 ALTER TABLE `medico` DISABLE KEYS */;
INSERT INTO `medico` VALUES (1,'CC','1001001001','Juan','Pérez Gómez','3001112233','RP-1001',13),(2,'CC','1001001002','María','Rodríguez López','3001112234','RP-1002',2),(3,'CC','1001001003','Carlos','Martínez Ruiz','3001112235','RP-1003',3),(4,'CC','1001001004','Laura','Fernández Díaz','3001112236','RP-1004',4),(5,'CC','1001001005','Andrés','Ramírez Soto','3001112237','RP-1005',5),(6,'CC','1001001006','Diana','Gómez Torres','3001112238','RP-1006',6),(7,'CC','1001001007','Ricardo','Jiménez Vargas','3001112239','RP-1007',7),(8,'CC','1001001008','Patricia','Hernández Ríos','3001112240','RP-1008',8),(9,'CC','1001001009','Santiago','Morales Castro','3001112241','RP-1009',9),(10,'CC','1001001010','Natalia','García Mendoza','3001112242','RP-1010',9),(11,'CC','88274388','Giovanni','Mora','5711322','1234656789100',8),(12,'CC','12345678','Edson Arantes','Do nascimento','3207729661','RP-10011',12),(13,'CC','12345123','Giovanni','Mora Jaimes','3207729661','001RP1234',12);
/*!40000 ALTER TABLE `medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medico_especializacion`
--

DROP TABLE IF EXISTS `medico_especializacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medico_especializacion` (
  `medico_id` int NOT NULL,
  `especializacion_id` int NOT NULL,
  PRIMARY KEY (`medico_id`,`especializacion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medico_especializacion`
--

LOCK TABLES `medico_especializacion` WRITE;
/*!40000 ALTER TABLE `medico_especializacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `medico_especializacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paciente` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `usuario_id` int DEFAULT NULL,
  `tipo_documento` varchar(10) NOT NULL,
  `numero_documento` varchar(20) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `genero` char(1) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `direccion` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `numero_documento` (`numero_documento`),
  UNIQUE KEY `usuario_id` (`usuario_id`),
  KEY `idx_paciente_documento` (`numero_documento`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (1,1,'CC','100000001','Juan','Pérez López','1990-05-12','M','3001112233','Calle 10 #12-34'),(2,2,'CC','100000002','María','Gómez Rodríguez','1985-08-22','F','3012223344','Carrera 15 #20-10'),(3,3,'TI','900000003','Andrés','Martínez Díaz','2005-03-18','M','3023334455','Av. Boyacá #45-67'),(4,4,'CC','100000004','Laura','Hernández Ruiz','1992-12-01','F','3034445566','Calle 5 #7-89'),(5,5,'CC','100000005','Carlos','Ramírez Torres','1980-07-30','M','3045556677','Carrera 8 #15-20'),(6,6,'CE','200000006','Ana','Castro Moreno','1998-01-25','F','3056667788','Diagonal 12 #25-50'),(7,7,'CC','100000007','Santiago','Moreno Vargas','1995-09-14','M','3067778899','Transversal 20 #40-22'),(8,8,'CC','100000008','Paula','Ríos Fernández','1987-11-10','F','3078889900','Calle 22 #18-45'),(9,9,'TI','900000009','Felipe','Gutiérrez Méndez','2006-06-06','M','3089990011','Carrera 50 #60-12'),(10,10,'CC','100000010','Diana','Salazar Peña','1993-04-21','F','3090001122','Av. Caracas #100-50');
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receta`
--

DROP TABLE IF EXISTS `receta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receta` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `cita_id` int NOT NULL,
  `medicamento_id` int NOT NULL,
  `dosis` text NOT NULL,
  `indicaciones` text,
  `fecha_creacion_registro` datetime NOT NULL,
  `fecha_actualizacion_registro` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receta`
--

LOCK TABLES `receta` WRITE;
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
INSERT INTO `receta` VALUES (1,1,5,'800 mg cada 12 horas','Tomar después de las comidas durante 7 días','2025-09-17 15:17:32',NULL),(2,2,6,'500 mg cada 8 horas','Tomar después de las comidas durante 7 días','2025-09-20 11:52:42',NULL),(4,1,5,'Una pastilla cada 12 horas.','Cambiando Cualquier cosa','2025-10-30 21:09:02','2025-10-30 21:21:51'),(5,3,2,'Una pastilla cada 12 horas.','Tomar despues del almuerzo y con abundante liquido','2025-11-01 10:21:04',NULL);
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `session` (
  `session_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `token` varchar(500) NOT NULL,
  `fecha_ini_sesion` datetime DEFAULT NULL,
  `fecha_expiracion` datetime DEFAULT NULL,
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` VALUES (3,26,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnbW9yYSIsImNvcnJlbyI6Imdpb3Zhbm5pbW9yYTA1MjdAZ21haWwuY29tIiwiZmVjaGFfZmluX3Nlc2lvbiI6MTc2MjM2MDY2MCwiZmVjaGFfaW5pY2lvX3Nlc2lvbiI6MTc2MjI3NDI2MH0.lJTUCXWD26sxNSS2TJv5TzH0RNOtY-a7HiSfJbFnSNM','2025-11-04 11:37:40','2025-11-05 11:37:40'),(16,28,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyZXMiLCJjb3JyZW8iOiJhbmRyZXN0Y28uMTZAZ21haWwuY29tIiwiZmVjaGFfZmluX3Nlc2lvbiI6MTc2MzUwMTQ4MiwiZmVjaGFfaW5pY2lvX3Nlc2lvbiI6MTc2MzQxNTA4Mn0.jAIilgrtYVvnCCRmFyQDeYvBNmYokp9BWUOoXaqr4jk','2025-11-17 16:31:23','2025-11-18 16:31:22'),(17,29,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkaWFuYSIsImNvcnJlbyI6ImNhdGVyaW5lcm9tZXJvMjAwNEBnbWFpbC5jb20iLCJmZWNoYV9maW5fc2VzaW9uIjoxNzYzNTAxNjc3LCJmZWNoYV9pbmljaW9fc2VzaW9uIjoxNzYzNDE1Mjc3fQ.wH21piIPxBjqOLvpIqSpvNRdmth2tPx6HPi65XrGOnc','2025-11-17 16:34:38','2025-11-18 16:34:37'),(21,27,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXJsb3MiLCJjb3JyZW8iOiJhbmRyZXN0Y28uMTZAZ21haWwuY29tIiwiZmVjaGFfZmluX3Nlc2lvbiI6MTc2MzYwOTcwMiwiZmVjaGFfaW5pY2lvX3Nlc2lvbiI6MTc2MzUyMzMwMn0.isWyFZhKjlylYAMSRtZd6ndzwNunNtivVFB-snQXow8','2025-11-18 22:35:03','2025-11-19 22:35:02'),(24,30,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRlcnNvbiIsImNvcnJlbyI6ImFuZGVyc29uLmRhemFAdW5pbWludXRvLmVkdS5jbyIsImZlY2hhX2Zpbl9zZXNpb24iOjE3NjM2NDc3OTcsImZlY2hhX2luaWNpb19zZXNpb24iOjE3NjM1NjEzOTd9.q9-PoDss6q3A7nPUVfxoSI3xuENEuPwhCZtTppCnrQk','2025-11-19 09:09:58','2025-11-20 09:09:57');
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password_hash` text,
  `rol` varchar(30) NOT NULL,
  `fecha_creacion` datetime DEFAULT CURRENT_TIMESTAMP,
  `activo` tinyint(1) DEFAULT '1',
  `email` varchar(255) NOT NULL,
  `intentos_fallidos` int DEFAULT '0',
  `cuenta_bloqueada_hasta` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (26,'gmora','8f6d1367a01184ac584d9a77cb637151','ADMIN','2025-11-04 10:02:24',1,'giovannimora0527@gmail.com',0,NULL),(27,'carlos','29849a76df75a984b2940624c769329b','ADMIN','2025-11-04 10:02:24',1,'andrestco.16@gmail.com',0,NULL),(28,'andres','eee4c722592e51b8effb7a9d5dbe14c2','ADMIN','2025-11-04 10:02:24',1,'andrestco.16@gmail.com',0,NULL),(29,'diana','373bb2c80ad6717946911c366dc47271','ADMIN','2025-11-17 16:31:56',1,'caterineromero2004@gmail.com',0,NULL),(30,'anderson','a3590c58fe7014f43c09ba9641728725','ADMIN','2025-11-19 02:18:55',1,'anderson.daza@uniminuto.edu.co',0,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-19 20:47:14
