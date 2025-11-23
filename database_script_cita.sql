-- ============================================================
-- Tabla: cita (FALTA EN EL SCRIPT ORIGINAL)
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

