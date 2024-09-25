CREATE DATABASE  IF NOT EXISTS `prueba_pentagonito` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `prueba_pentagonito`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: prueba_pentagonito
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.28-MariaDB

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
-- Table structure for table `animal_insumo`
--

DROP TABLE IF EXISTS `animal_insumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animal_insumo` (
  `id_animal` int(11) NOT NULL,
  `id_insumo` int(11) NOT NULL,
  `cantidad_usada` int(11) NOT NULL,
  `fecha_uso` date NOT NULL,
  PRIMARY KEY (`id_animal`,`id_insumo`),
  KEY `id_insumo` (`id_insumo`),
  CONSTRAINT `animal_insumo_ibfk_1` FOREIGN KEY (`id_animal`) REFERENCES `animales` (`id_animal`) ON DELETE CASCADE,
  CONSTRAINT `animal_insumo_ibfk_2` FOREIGN KEY (`id_insumo`) REFERENCES `insumos` (`id_insumo`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal_insumo`
--

LOCK TABLES `animal_insumo` WRITE;
/*!40000 ALTER TABLE `animal_insumo` DISABLE KEYS */;
INSERT INTO `animal_insumo` VALUES (85,39,10,'2024-09-25'),(86,40,10,'2024-09-25');
/*!40000 ALTER TABLE `animal_insumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animales`
--

DROP TABLE IF EXISTS `animales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animales` (
  `id_animal` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `especie` enum('CABALLO','PERRO','OTRO') DEFAULT NULL,
  `raza` varchar(255) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `estado` enum('ACTIVO','INACTIVO') DEFAULT NULL,
  `fecha_ingreso` timestamp NULL DEFAULT NULL,
  `id_responsable` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_animal`),
  KEY `id_responsable` (`id_responsable`),
  CONSTRAINT `animales_ibfk_1` FOREIGN KEY (`id_responsable`) REFERENCES `usuarios` (`id_usuario`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animales`
--

LOCK TABLES `animales` WRITE;
/*!40000 ALTER TABLE `animales` DISABLE KEYS */;
INSERT INTO `animales` VALUES (73,'Rayo','CABALLO','Pura Sangre',5,'ACTIVO','2024-09-22 05:03:18',176),(74,'Rayo','CABALLO','Pura Sangre',5,'ACTIVO','2024-09-22 05:06:24',177),(75,'Bolt 1726981584441','PERRO','Doberman',2,'ACTIVO','2024-09-22 05:06:24',178),(85,'Spirit','CABALLO','Pura Sangre',1,'ACTIVO','2024-09-25 18:22:49',NULL),(86,'Spirit','CABALLO','Pura Sangre',1,'ACTIVO','2024-09-25 18:25:35',NULL);
/*!40000 ALTER TABLE `animales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial_medico`
--

DROP TABLE IF EXISTS `historial_medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historial_medico` (
  `id_historial` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_animal` int(11) DEFAULT NULL,
  `fecha_atencion` timestamp NULL DEFAULT NULL,
  `diagnostico` varchar(255) DEFAULT NULL,
  `tratamiento` varchar(255) DEFAULT NULL,
  `id_veterinario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_historial`),
  KEY `id_animal` (`id_animal`),
  KEY `id_veterinario` (`id_veterinario`),
  CONSTRAINT `historial_medico_ibfk_1` FOREIGN KEY (`id_animal`) REFERENCES `animales` (`id_animal`) ON DELETE CASCADE,
  CONSTRAINT `historial_medico_ibfk_2` FOREIGN KEY (`id_veterinario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial_medico`
--

LOCK TABLES `historial_medico` WRITE;
/*!40000 ALTER TABLE `historial_medico` DISABLE KEYS */;
INSERT INTO `historial_medico` VALUES (16,75,'2024-09-22 05:06:24','Infección leve','Antibióticos',178);
/*!40000 ALTER TABLE `historial_medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insumos`
--

DROP TABLE IF EXISTS `insumos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insumos` (
  `id_insumo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `tipo` enum('ALIMENTO','MEDICAMENTO','OTRO') DEFAULT NULL,
  `cantidad_disponible` int(11) NOT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `id_proveedor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_insumo`),
  KEY `id_proveedor` (`id_proveedor`),
  CONSTRAINT `insumos_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedores` (`id_proveedor`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insumos`
--

LOCK TABLES `insumos` WRITE;
/*!40000 ALTER TABLE `insumos` DISABLE KEYS */;
INSERT INTO `insumos` VALUES (1,'Ivermectina','MEDICAMENTO',100,'2025-09-20',NULL),(2,'Ivermectina','MEDICAMENTO',100,'2025-09-20',NULL),(3,'Ivermectina','MEDICAMENTO',100,'2025-09-20',NULL),(4,'Ivermectina','MEDICAMENTO',100,'2025-09-20',NULL),(5,'Ivermectina','MEDICAMENTO',100,'2025-09-20',NULL),(6,'Ivermectina','MEDICAMENTO',100,'2025-09-20',NULL),(7,'Ivermectina','MEDICAMENTO',100,'2025-09-20',NULL),(8,'Ivermectina','MEDICAMENTO',100,'2025-09-20',NULL),(9,'Ivermectina','MEDICAMENTO',100,'2025-09-20',NULL),(10,'Ivermectina','MEDICAMENTO',100,'2025-09-20',NULL),(11,'Ivermectina','MEDICAMENTO',100,'2025-09-20',NULL),(12,'Ivermectina','MEDICAMENTO',100,'2025-09-20',NULL),(13,'Ivermectina','MEDICAMENTO',100,'2025-09-20',NULL),(14,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(15,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(16,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(17,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(18,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(19,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(20,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(21,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(22,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(23,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(24,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(25,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(26,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(27,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(28,'Ivermectina','MEDICAMENTO',100,'2025-09-21',NULL),(29,'Ivermectina','MEDICAMENTO',100,'2025-09-22',NULL),(39,'Vacuna Antitetánica','MEDICAMENTO',50,'2025-09-25',NULL),(40,'Vacuna Antitetánica','MEDICAMENTO',50,'2025-09-25',NULL);
/*!40000 ALTER TABLE `insumos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedores` (
  `id_proveedor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES (12,'Proveedor XYZ 1726892043079','Av. Los Andes 123','987654321'),(13,'Proveedor XYZ 1726892538003','Av. Los Andes 123','987654321'),(14,'Proveedor XYZ 1726894995715','Av. Los Andes 123','987654321'),(15,'Proveedor XYZ 1726896128882','Av. Los Andes 123','987654321'),(16,'Proveedor XYZ 1726899279206','Av. Los Andes 123','987654321'),(17,'Proveedor XYZ 1726899667376','Av. Los Andes 123','987654321'),(18,'Proveedor XYZ 1726901294888','Av. Los Andes 123','987654321'),(19,'Proveedor XYZ 1726972810798','Av. Los Andes 123','987654321'),(20,'Proveedor XYZ 1726972856324','Av. Los Andes 123','987654321'),(21,'Proveedor XYZ 1726973543747','Av. Los Andes 123','987654321'),(22,'Proveedor XYZ 1726973721447','Av. Los Andes 123','987654321'),(23,'Proveedor XYZ 1726973848111','Av. Los Andes 123','987654321'),(24,'Proveedor XYZ 1726974028373','Av. Los Andes 123','987654321'),(25,'Proveedor XYZ 1726974306450','Av. Los Andes 123','987654321'),(26,'Proveedor XYZ 1726975005644','Av. Los Andes 123','987654321'),(27,'Proveedor XYZ 1726975284679','Av. Los Andes 123','987654321'),(28,'Proveedor XYZ 1726975549578','Av. Los Andes 123','987654321'),(29,'Proveedor XYZ 1726981584557','Av. Los Andes 123','987654321');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reportes`
--

DROP TABLE IF EXISTS `reportes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reportes` (
  `id_reporte` bigint(20) NOT NULL AUTO_INCREMENT,
  `tipo_reporte` enum('ANIMALES','HISTORIAL','INSUMOS') DEFAULT NULL,
  `contenido` tinytext DEFAULT NULL,
  `fecha_generacion` timestamp NULL DEFAULT NULL,
  `id_generador` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_reporte`),
  KEY `id_generador` (`id_generador`),
  CONSTRAINT `reportes_ibfk_1` FOREIGN KEY (`id_generador`) REFERENCES `usuarios` (`id_usuario`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reportes`
--

LOCK TABLES `reportes` WRITE;
/*!40000 ALTER TABLE `reportes` DISABLE KEYS */;
INSERT INTO `reportes` VALUES (21,'INSUMOS','Reporte detallado de insumos 1726888314244','2024-09-21 03:11:54',NULL),(22,'ANIMALES','Reporte de animales 1726888374049','2024-09-21 03:12:54',NULL),(23,'INSUMOS','Reporte de insumos 1726888374054','2024-09-21 03:12:54',NULL),(24,'INSUMOS','Reporte detallado de insumos 1726888374139','2024-09-21 03:12:54',NULL),(25,'ANIMALES','Reporte de animales 1726888575281','2024-09-21 03:16:15',NULL),(26,'INSUMOS','Reporte de insumos 1726888575285','2024-09-21 03:16:15',NULL),(27,'INSUMOS','Reporte detallado de insumos 1726888575425','2024-09-21 03:16:15',NULL),(28,'ANIMALES','Reporte de animales 1726888680737','2024-09-21 03:18:00',NULL),(29,'INSUMOS','Reporte de insumos 1726888680741','2024-09-21 03:18:00',NULL),(30,'INSUMOS','Reporte detallado de insumos 1726888680811','2024-09-21 03:18:00',NULL),(31,'ANIMALES','Reporte de animales 1726888939720','2024-09-21 03:22:19',NULL),(32,'INSUMOS','Reporte de insumos 1726888939726','2024-09-21 03:22:19',NULL),(33,'INSUMOS','Reporte detallado de insumos 1726888939799','2024-09-21 03:22:19',NULL),(34,'ANIMALES','Reporte de animales 1726892043130','2024-09-21 04:14:03',NULL),(35,'INSUMOS','Reporte de insumos 1726892043130','2024-09-21 04:14:03',NULL),(36,'INSUMOS','Reporte detallado de insumos 1726892043183','2024-09-21 04:14:03',NULL),(37,'ANIMALES','Reporte de animales 1726892538109','2024-09-21 04:22:18',NULL),(38,'INSUMOS','Reporte de insumos 1726892538109','2024-09-21 04:22:18',NULL),(39,'INSUMOS','Reporte detallado de insumos 1726892538185','2024-09-21 04:22:18',NULL),(40,'ANIMALES','Reporte de animales 1726894995765','2024-09-21 05:03:15',NULL),(41,'INSUMOS','Reporte de insumos 1726894995765','2024-09-21 05:03:15',NULL),(42,'INSUMOS','Reporte detallado de insumos 1726894995799','2024-09-21 05:03:15',NULL),(43,'ANIMALES','Reporte de animales 1726896128935','2024-09-21 05:22:08',NULL),(44,'INSUMOS','Reporte de insumos 1726896128935','2024-09-21 05:22:08',NULL),(45,'INSUMOS','Reporte detallado de insumos 1726896128979','2024-09-21 05:22:08',NULL),(46,'ANIMALES','Reporte de animales 1726899279261','2024-09-21 06:14:39',NULL),(47,'INSUMOS','Reporte de insumos 1726899279262','2024-09-21 06:14:39',NULL),(48,'INSUMOS','Reporte detallado de insumos 1726899279324','2024-09-21 06:14:39',NULL),(49,'ANIMALES','Reporte de animales 1726899667449','2024-09-21 06:21:07',NULL),(50,'INSUMOS','Reporte de insumos 1726899667450','2024-09-21 06:21:07',NULL),(51,'INSUMOS','Reporte detallado de insumos 1726899667542','2024-09-21 06:21:07',NULL),(52,'ANIMALES','Reporte de animales 1726901294953','2024-09-21 06:48:14',NULL),(53,'INSUMOS','Reporte de insumos 1726901294954','2024-09-21 06:48:14',NULL),(54,'INSUMOS','Reporte detallado de insumos 1726901295038','2024-09-21 06:48:15',NULL),(55,'ANIMALES','Reporte de animales 1726972810904','2024-09-22 02:40:10',NULL),(56,'INSUMOS','Reporte de insumos 1726972810906','2024-09-22 02:40:10',NULL),(57,'INSUMOS','Reporte detallado de insumos 1726972811052','2024-09-22 02:40:11',NULL),(58,'ANIMALES','Reporte de animales 1726972856402','2024-09-22 02:40:56',NULL),(59,'INSUMOS','Reporte de insumos 1726972856403','2024-09-22 02:40:56',NULL),(60,'INSUMOS','Reporte detallado de insumos 1726972856526','2024-09-22 02:40:56',NULL),(61,'ANIMALES','Reporte de animales 1726973543827','2024-09-22 02:52:23',NULL),(62,'INSUMOS','Reporte de insumos 1726973543829','2024-09-22 02:52:23',NULL),(63,'INSUMOS','Reporte detallado de insumos 1726973543953','2024-09-22 02:52:23',NULL),(64,'ANIMALES','Reporte de animales 1726973721588','2024-09-22 02:55:21',NULL),(65,'INSUMOS','Reporte de insumos 1726973721588','2024-09-22 02:55:21',NULL),(66,'INSUMOS','Reporte detallado de insumos 1726973721685','2024-09-22 02:55:21',NULL),(67,'ANIMALES','Reporte de animales 1726973848205','2024-09-22 02:57:28',NULL),(68,'INSUMOS','Reporte de insumos 1726973848206','2024-09-22 02:57:28',NULL),(69,'INSUMOS','Reporte detallado de insumos 1726973848336','2024-09-22 02:57:28',NULL),(70,'ANIMALES','Reporte de animales 1726974028448','2024-09-22 03:00:28',NULL),(71,'INSUMOS','Reporte de insumos 1726974028448','2024-09-22 03:00:28',NULL),(72,'INSUMOS','Reporte detallado de insumos 1726974028549','2024-09-22 03:00:28',NULL),(73,'ANIMALES','Reporte de animales 1726974306515','2024-09-22 03:05:06',NULL),(74,'INSUMOS','Reporte de insumos 1726974306516','2024-09-22 03:05:06',NULL),(75,'INSUMOS','Reporte detallado de insumos 1726974306660','2024-09-22 03:05:06',NULL),(76,'ANIMALES','Reporte de animales 1726975005777','2024-09-22 03:16:45',NULL),(77,'INSUMOS','Reporte de insumos 1726975005777','2024-09-22 03:16:45',NULL),(78,'INSUMOS','Reporte detallado de insumos 1726975005946','2024-09-22 03:16:45',NULL),(79,'ANIMALES','Reporte de animales 1726975284735','2024-09-22 03:21:24',NULL),(80,'INSUMOS','Reporte de insumos 1726975284735','2024-09-22 03:21:24',NULL),(81,'INSUMOS','Reporte detallado de insumos 1726975284900','2024-09-22 03:21:24',NULL),(82,'ANIMALES','Reporte de animales 1726975549655','2024-09-22 03:25:49',NULL),(83,'INSUMOS','Reporte de insumos 1726975549655','2024-09-22 03:25:49',NULL),(84,'INSUMOS','Reporte detallado de insumos 1726975549773','2024-09-22 03:25:49',NULL),(85,'ANIMALES','Reporte de animales 1726981584595','2024-09-22 05:06:24',NULL),(86,'INSUMOS','Reporte de insumos 1726981584595','2024-09-22 05:06:24',NULL),(87,'INSUMOS','Reporte detallado de insumos 1726981584661','2024-09-22 05:06:24',NULL);
/*!40000 ALTER TABLE `reportes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `rol` enum('ADMIN','ENCARGADO','VETERINARIO') DEFAULT NULL,
  `contraseña` varchar(255) NOT NULL,
  `fecha_creacion` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (23,'Usuario 1','usuario593e4f5a-3ad8-433d-85bf-e232baeb0ee6@example.com','ADMIN','123','2024-09-20 17:38:34'),(24,'Usuario 2','usuario83fd59d5-9054-4b49-86b3-f7116c1f4fbd@example.com','ENCARGADO','456','2024-09-20 17:38:34'),(25,'John Doe','usuario53a86ba2-e2ea-48e1-ba60-27f1036b8f51@example.com','ADMIN','123456','2024-09-20 17:43:58'),(27,'Usuario 1','usuario31659b2a-053f-4b96-b0a2-21ee0dbe3515@example.com','ADMIN','123','2024-09-20 17:43:58'),(28,'Usuario 2','usuario796078ed-602f-4293-a1ed-4417db82d803@example.com','ENCARGADO','456','2024-09-20 17:43:58'),(29,'John Doe','usuario121d3f6b-a13a-409b-953b-9c569ee31794@example.com','ADMIN','123456','2024-09-20 18:09:15'),(31,'Usuario 1','usuario39d525a0-4b3a-44ef-823c-b4efe859bfac@example.com','ADMIN','123','2024-09-20 18:09:16'),(32,'Usuario 2','usuario1a9e09b6-b4b2-47c3-98f1-3c7c8c75691d@example.com','ENCARGADO','456','2024-09-20 18:09:16'),(33,'John Doe','usuarioedf34b27-ae67-415f-9611-e42860a14619@example.com','ADMIN','123456','2024-09-21 01:37:56'),(35,'Usuario 1','usuario80c399dd-d507-4cc5-92b0-204d9ff601ef@example.com','ADMIN','123','2024-09-21 01:37:56'),(36,'Usuario 2','usuario50cdd663-05ae-479c-929c-8f0dcefb8b63@example.com','ENCARGADO','456','2024-09-21 01:37:56'),(37,'John Doe','usuario27da95ad-c0c3-494b-91cf-254c61536be1@example.com','ADMIN','123456','2024-09-21 02:40:51'),(39,'Usuario 1','usuario5c78a7c0-4e14-40ac-9e34-8a9afeba68f6@example.com','ADMIN','123','2024-09-21 02:40:51'),(40,'Usuario 2','usuario8a0cebd8-d7f4-4de9-b6a2-542cd2af6206@example.com','ENCARGADO','456','2024-09-21 02:40:51'),(41,'John Doe','usuario1807d44b-735e-41f1-94d5-dffa9d995a5e@example.com','ADMIN','123456','2024-09-21 02:59:38'),(43,'Usuario 1','usuario758482c2-0c93-48c8-93a8-d1a525c8b979@example.com','ADMIN','123','2024-09-21 02:59:38'),(44,'Usuario 2','usuario1235a4a1-bf7b-4a0a-88ba-1a5b5327a03e@example.com','ENCARGADO','456','2024-09-21 02:59:38'),(45,'John Doe','usuario730e94f3-f364-4b15-875a-81db5e95cb7c@example.com','ADMIN','123456','2024-09-21 03:01:33'),(47,'Usuario 1','usuariofe77cb1f-e37c-4859-874b-4ee57f79519c@example.com','ADMIN','123','2024-09-21 03:01:33'),(48,'Usuario 2','usuarioda057594-85ff-4d0d-9285-d7dd6e4d0d4d@example.com','ENCARGADO','456','2024-09-21 03:01:33'),(49,'John Doe','usuario7655433d-950e-4b88-953c-e326adcbf148@example.com','ADMIN','123456','2024-09-21 03:08:24'),(51,'Usuario 1','usuario2cadb9f3-8af0-4d54-bbb5-fc2ac0c76f63@example.com','ADMIN','123','2024-09-21 03:08:24'),(52,'Usuario 2','usuario9d5b774d-06c1-4aff-93e3-ad5964fce840@example.com','ENCARGADO','456','2024-09-21 03:08:24'),(53,'John Doe','usuariodbb5ab7e-c6b5-4b31-9533-4f1ef14b1b9b@example.com','ADMIN','123456','2024-09-21 03:10:52'),(55,'Usuario 1','usuariod492a9b1-c3b8-4665-b422-a00a7644e3c0@example.com','ADMIN','123','2024-09-21 03:10:52'),(56,'Usuario 2','usuariof05607d5-99d3-42a8-a1ad-cfe57c2a8ad9@example.com','ENCARGADO','456','2024-09-21 03:10:52'),(57,'John Doe','usuariof71af100-4b1b-47d9-90e1-d5af185c4c14@example.com','ADMIN','123456','2024-09-21 03:11:54'),(59,'Usuario 1','usuario36d2b41b-a8fc-4713-900e-e014886e95f9@example.com','ADMIN','123','2024-09-21 03:11:54'),(60,'Usuario 2','usuario2aef6def-f420-49cc-8774-559350fc07f2@example.com','ENCARGADO','456','2024-09-21 03:11:54'),(61,'John Doe','usuario0b2f5d4e-8fd3-4d4d-9e08-b74267f4434c@example.com','ADMIN','123456','2024-09-21 03:12:54'),(63,'Usuario 1','usuario434cbe6c-5995-446a-bec3-a95ed19be1c4@example.com','ADMIN','123','2024-09-21 03:12:54'),(64,'Usuario 2','usuarioc8f17240-b20e-4ec0-ae7a-063a96c228db@example.com','ENCARGADO','456','2024-09-21 03:12:54'),(65,'John Doe','usuarioc6b573ae-ec6d-4aa6-ab80-ab09afa1152a@example.com','ADMIN','123456','2024-09-21 03:16:15'),(67,'Usuario 1','usuarioe996038d-8d81-42ed-9d7e-d188df5204e1@example.com','ADMIN','123','2024-09-21 03:16:15'),(68,'Usuario 2','usuarioe43b2db5-dd6c-42ac-9ff8-2857837ad470@example.com','ENCARGADO','456','2024-09-21 03:16:15'),(69,'John Doe','usuarioc6bd8779-d74e-449c-abc4-a19d72e2c42a@example.com','ADMIN','123456','2024-09-21 03:18:00'),(71,'Usuario 1','usuario3cdaa9a0-cbd2-4ce6-a725-c853b0492002@example.com','ADMIN','123','2024-09-21 03:18:01'),(72,'Usuario 2','usuariocd02b314-96ce-4edf-a5cc-a79fd3a0f76a@example.com','ENCARGADO','456','2024-09-21 03:18:01'),(73,'John Doe','usuario1ff4c497-184d-4abb-948f-75a15f82ea92@example.com','ADMIN','123456','2024-09-21 03:22:19'),(75,'Usuario 1','usuario1753f643-6783-4781-9610-feeacf59eea1@example.com','ADMIN','123','2024-09-21 03:22:20'),(76,'Usuario 2','usuario04e8515b-8802-4743-baf2-2b93e6005f12@example.com','ENCARGADO','456','2024-09-21 03:22:20'),(77,'John Doe','usuario25527295-96d9-4019-ad3e-484096353d45@example.com','ADMIN','123456','2024-09-21 04:14:03'),(79,'Usuario 1','usuarioe9d77dc7-e50f-4c24-8818-34469df6d427@example.com','ADMIN','123','2024-09-21 04:14:03'),(80,'Usuario 2','usuario990f617e-566b-43ae-868e-ed49615879f1@example.com','ENCARGADO','456','2024-09-21 04:14:03'),(81,'John Doe','usuario9fbc575e-040a-4905-8761-b2f21db4b4c0@example.com','ADMIN','123456','2024-09-21 04:22:18'),(83,'Usuario 1','usuario04a667d9-15f7-4b46-9259-d63fd26079ab@example.com','ADMIN','123','2024-09-21 04:22:18'),(84,'Usuario 2','usuarioe5b6464a-4850-46d8-851a-03107e911c9b@example.com','ENCARGADO','456','2024-09-21 04:22:18'),(85,'John Doe','usuarioecb6ea38-ba5d-412a-94a1-c1eb6a990885@example.com','ADMIN','123456','2024-09-21 05:03:15'),(87,'Usuario 1','usuario3f0e064b-65fd-423e-88b2-f0b7a1e87025@example.com','ADMIN','123','2024-09-21 05:03:15'),(88,'Usuario 2','usuario855be7e4-9964-428d-8e9a-54260aad3a70@example.com','ENCARGADO','456','2024-09-21 05:03:15'),(89,'John Doe','usuariof5cebdf5-9713-4809-8da5-99c452c7351d@example.com','ADMIN','123456','2024-09-21 05:22:09'),(91,'Usuario 1','usuario4a8cf79d-575a-4217-9d22-f7624f4ba8c2@example.com','ADMIN','123','2024-09-21 05:22:09'),(92,'Usuario 2','usuario6d331eda-d6ec-4a0c-b9db-98a58fdf1e5c@example.com','ENCARGADO','456','2024-09-21 05:22:09'),(93,'Juan Pérez','usuario1726899278514@example.com','ENCARGADO','password123','2024-09-21 06:14:38'),(94,'John Doe','usuariofeff2ddb-40d4-4281-8cde-689d76ed293b@example.com','ADMIN','123456','2024-09-21 06:14:39'),(96,'Usuario 1','usuarioc706e16d-139d-4121-81b9-261a9eba6395@example.com','ADMIN','123','2024-09-21 06:14:39'),(97,'Usuario 2','usuario46590bd0-9285-48c4-8da0-142cfa5c22f8@example.com','ENCARGADO','456','2024-09-21 06:14:39'),(98,'Juan Pérez','usuario1726899666576@example.com','ENCARGADO','password123','2024-09-21 06:21:06'),(99,'John Doe','usuariocb576566-de8a-4183-a032-ab408b25c7db@example.com','ADMIN','123456','2024-09-21 06:21:07'),(101,'Usuario 1','usuario672c7be6-7c7f-4171-a967-4843baa858e5@example.com','ADMIN','123','2024-09-21 06:21:07'),(102,'Usuario 2','usuario9c1a1093-224d-4167-a55e-436a52e70bf2@example.com','ENCARGADO','456','2024-09-21 06:21:07'),(103,'Juan Pérez','usuario1726901294209@example.com','ENCARGADO','password123','2024-09-21 06:48:14'),(104,'Juan Pérez','usuario1726901294704@example.com','ENCARGADO','password123','2024-09-21 06:48:14'),(105,'John Doe','usuario43a99a12-3019-4a6f-8c45-12a206fe1fda@example.com','ADMIN','123456','2024-09-21 06:48:15'),(107,'Usuario 1','usuario8c325d1a-28e5-43a3-b2c0-533d6af85c98@example.com','ADMIN','123','2024-09-21 06:48:15'),(108,'Usuario 2','usuarioff9d79e9-21c2-4842-a42c-1d60506d0b40@example.com','ENCARGADO','456','2024-09-21 06:48:15'),(109,'Juan Pérez','usuario1726972810048@example.com','ENCARGADO','password123','2024-09-22 02:40:10'),(110,'Juan Pérez','usuario1726972810659@example.com','ENCARGADO','password123','2024-09-22 02:40:10'),(111,'John Doe','usuariob02e472d-0adf-42c9-a2cf-885114468ba6@example.com','ADMIN','123456','2024-09-22 02:40:11'),(113,'Usuario 1','usuario61d7fe9f-2193-44d5-93d1-2c6a809b2c2d@example.com','ADMIN','123','2024-09-22 02:40:11'),(114,'Usuario 2','usuario8b054ec6-9837-4cc8-98d3-ccbc4b6d08b5@example.com','ENCARGADO','456','2024-09-22 02:40:11'),(115,'Juan Pérez','usuario1726972855564@example.com','ENCARGADO','password123','2024-09-22 02:40:55'),(116,'Juan Pérez','usuario1726972856149@example.com','ENCARGADO','password123','2024-09-22 02:40:56'),(117,'John Doe','usuario5e1067f7-5141-494b-bd16-5d7e944b3a6c@example.com','ADMIN','123456','2024-09-22 02:40:56'),(119,'Usuario 1','usuario4c827fee-51fd-4ff2-b3bd-a5527a1d28a4@example.com','ADMIN','123','2024-09-22 02:40:56'),(120,'Usuario 2','usuario4b30a503-9c81-490e-a736-517c1df91064@example.com','ENCARGADO','456','2024-09-22 02:40:56'),(121,'Juan Pérez','usuario1726973542640@example.com','ENCARGADO','password123','2024-09-22 02:52:22'),(122,'Juan Pérez','usuario1726973543526@example.com','ENCARGADO','password123','2024-09-22 02:52:23'),(123,'John Doe','usuario66ba6f19-9cba-48a6-86f2-80f624486a11@example.com','ADMIN','123456','2024-09-22 02:52:24'),(125,'Usuario 1','usuario05ca8ea9-e6f2-4c1a-9b2a-30543e615023@example.com','ADMIN','123','2024-09-22 02:52:24'),(126,'Usuario 2','usuariod929cd8f-d736-4785-a475-238f6d05ca95@example.com','ENCARGADO','456','2024-09-22 02:52:24'),(127,'Juan Pérez','usuario1726973720423@example.com','ENCARGADO','password123','2024-09-22 02:55:20'),(128,'Juan Pérez','usuario1726973721254@example.com','ENCARGADO','password123','2024-09-22 02:55:21'),(129,'John Doe','usuario0a79d927-d733-4263-81e6-6b8071fcaf9a@example.com','ADMIN','123456','2024-09-22 02:55:21'),(131,'Usuario 1','usuarioeee3f4a8-764a-4db4-82a2-feb7fe054768@example.com','ADMIN','123','2024-09-22 02:55:21'),(132,'Usuario 2','usuariodc983f67-43b6-4038-af33-728383ee8940@example.com','ENCARGADO','456','2024-09-22 02:55:21'),(133,'Juan Pérez','usuario1726973846468@example.com','ENCARGADO','password123','2024-09-22 02:57:26'),(134,'Juan Pérez','usuario1726973847805@example.com','ENCARGADO','password123','2024-09-22 02:57:27'),(135,'John Doe','usuario96ce3de1-7e09-4541-8c5b-3b2bd2681851@example.com','ADMIN','123456','2024-09-22 02:57:28'),(137,'Usuario 1','usuario8c48cd2d-529f-4236-be16-be621943b0b9@example.com','ADMIN','123','2024-09-22 02:57:28'),(138,'Usuario 2','usuariob8f8abd6-badf-4b4a-a956-f33aedb1a739@example.com','ENCARGADO','456','2024-09-22 02:57:28'),(139,'Juan Pérez','usuario1726974027515@example.com','ENCARGADO','password123','2024-09-22 03:00:27'),(140,'Juan Pérez','usuario1726974028182@example.com','ENCARGADO','password123','2024-09-22 03:00:28'),(141,'John Doe','usuario683319c6-2ec1-400f-a40a-29ca20275d01@example.com','ADMIN','123456','2024-09-22 03:00:28'),(143,'Usuario 1','usuario6f71f622-e9cc-4cd5-9380-1cd466c44fad@example.com','ADMIN','123','2024-09-22 03:00:28'),(144,'Usuario 2','usuariob1ae89b5-1721-4ae0-927a-b2e1a72a35a9@example.com','ENCARGADO','456','2024-09-22 03:00:28'),(145,'Juan Pérez','usuario1726974305426@example.com','ENCARGADO','password123','2024-09-22 03:05:05'),(146,'Juan Pérez','usuario1726974306215@example.com','ENCARGADO','password123','2024-09-22 03:05:06'),(147,'John Doe','usuario11956a72-14b4-48a9-95aa-0a1565a88b87@example.com','ADMIN','123456','2024-09-22 03:05:06'),(149,'Usuario 1','usuariof6b7cf91-f221-4d4d-b05d-aefd84903e86@example.com','ADMIN','123','2024-09-22 03:05:06'),(150,'Usuario 2','usuario1f4844fa-ef31-421d-aabc-c3de5038b0f2@example.com','ENCARGADO','456','2024-09-22 03:05:06'),(151,'Juan Pérez','usuario1726975003867@example.com','ENCARGADO','password123','2024-09-22 03:16:43'),(152,'Juan Pérez','usuario1726975005311@example.com','VETERINARIO','password123','2024-09-22 03:16:45'),(153,'John Doe','usuariob7a8d7a5-44ae-46ff-9835-9ae6eb53a9a5@example.com','ADMIN','123456','2024-09-22 03:16:46'),(155,'Usuario 1','usuariob75a1770-b2b6-46c1-9bf1-45a96703a43a@example.com','ADMIN','123','2024-09-22 03:16:46'),(156,'Usuario 2','usuario1e86f081-69bd-43fe-b9df-e90570041ec7@example.com','ENCARGADO','456','2024-09-22 03:16:46'),(157,'Juan Pérez','usuario@example.com','ENCARGADO','password123','2024-09-22 03:21:23'),(158,'Juan Pérez','usuario1726975284511@example.com','VETERINARIO','password123','2024-09-22 03:21:24'),(159,'John Doe','usuarioc34dfb69-8054-464e-a43e-0beef8ece791@example.com','ADMIN','123456','2024-09-22 03:21:24'),(161,'Usuario 1','usuario7820ef0d-e132-4f2f-a206-181f3fea33b8@example.com','ADMIN','123','2024-09-22 03:21:25'),(162,'Usuario 2','usuario42869d6e-e4fb-4235-a163-cb282c9b48ee@example.com','ENCARGADO','456','2024-09-22 03:21:25'),(163,'Juan Pérez','usuario1726975548035@example.com','ENCARGADO','password123','2024-09-22 03:25:48'),(164,'Juan Pérez','usuario1726975549313@example.com','VETERINARIO','password123','2024-09-22 03:25:49'),(165,'John Doe','usuariob1686e86-dd9a-4913-90b3-00be2542f04c@example.com','ADMIN','123456','2024-09-22 03:25:49'),(167,'Usuario 1','usuario92fffe9d-e48b-4012-92af-358ad7b0dede@example.com','ADMIN','123','2024-09-22 03:25:50'),(168,'Usuario 2','usuario37cfac5c-00e2-43c5-8b65-30a3392ec557@example.com','ENCARGADO','456','2024-09-22 03:25:50'),(169,'Juan Pérez','usuario1726976497250@example.com','ENCARGADO','password123','2024-09-22 03:41:37'),(170,'Juan Pérez','usuario1726976628924@example.com','ENCARGADO','password123','2024-09-22 03:43:48'),(171,'Juan Pérez','usuario1726977347602@example.com','ENCARGADO','password123','2024-09-22 03:55:47'),(172,'Juan Pérez','usuario1726977778397@example.com','ENCARGADO','password123','2024-09-22 04:02:58'),(173,'Juan Pérez','usuario1726978314179@example.com','ENCARGADO','password123','2024-09-22 04:11:54'),(174,'Juan Pérez','usuario1726978421628@example.com','ENCARGADO','password123','2024-09-22 04:13:41'),(175,'Juan Pérez','usuario1726979098113@example.com','ENCARGADO','password123','2024-09-22 04:24:58'),(176,'Juan Pérez','usuario1726981398663@example.com','ENCARGADO','password123','2024-09-22 05:03:18'),(177,'Juan Pérez','usuario1726981584102@example.com','ENCARGADO','password123','2024-09-22 05:06:24'),(178,'Juan Pérez','usuario1726981584438@example.com','VETERINARIO','password123','2024-09-22 05:06:24'),(179,'John Doe','usuariof5a7c70e-df5e-42e6-8042-89d5ab8b4bb5@example.com','ADMIN','123456','2024-09-22 05:06:24'),(181,'Usuario 1','usuario92a8b606-361a-4c2c-9984-90a5e49a25ee@example.com','ADMIN','123','2024-09-22 05:06:24'),(182,'Usuario 2','usuariofc90e506-a511-4102-9a4a-0e46c44836a2@example.com','ENCARGADO','456','2024-09-22 05:06:24');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-25 17:21:58
