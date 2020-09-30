-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: kabeca
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `aaccesorio`
--

DROP TABLE IF EXISTS `aaccesorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aaccesorio` (
  `id_aaccesorio` int NOT NULL AUTO_INCREMENT,
  `id_accesorio` int NOT NULL,
  `id_asignacion` int NOT NULL,
  PRIMARY KEY (`id_aaccesorio`),
  UNIQUE KEY `id_aaccesorio_UNIQUE` (`id_aaccesorio`),
  KEY `fk_AAccesorio_Asignacion1_idx` (`id_asignacion`),
  KEY `id_accesorio_acc_idx` (`id_accesorio`),
  CONSTRAINT `id_accesorio_acc` FOREIGN KEY (`id_accesorio`) REFERENCES `accesorio` (`id_accesorio`),
  CONSTRAINT `id_asignacion_AA` FOREIGN KEY (`id_asignacion`) REFERENCES `asignacion` (`id_asignacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `accesorio`
--

DROP TABLE IF EXISTS `accesorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accesorio` (
  `id_accesorio` int NOT NULL AUTO_INCREMENT,
  `nombre_accesorio` varchar(45) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `modelo` varchar(45) NOT NULL,
  `producto` varchar(45) NOT NULL,
  `hecho_en` varchar(45) NOT NULL,
  `serie` varchar(23) NOT NULL,
  `id_estatus` int NOT NULL,
  `id_equipo` int DEFAULT NULL,
  `costo` float DEFAULT NULL,
  PRIMARY KEY (`id_accesorio`),
  UNIQUE KEY `id_accesorio_UNIQUE` (`id_accesorio`),
  KEY `fk_Accesorio_EstatusRecurso1_idx` (`id_estatus`),
  CONSTRAINT `id_estatus` FOREIGN KEY (`id_estatus`) REFERENCES `estatusrecurso` (`id_estatus`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `accesorion`
--

DROP TABLE IF EXISTS `accesorion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accesorion` (
  `asignacionAccesorios_id_asignacion` int NOT NULL,
  `accesorio_id_accesorio` int NOT NULL,
  PRIMARY KEY (`asignacionAccesorios_id_asignacion`,`accesorio_id_accesorio`),
  KEY `fk_accesorioN_asignacionAccesorios1_idx` (`asignacionAccesorios_id_asignacion`),
  KEY `fk_accesorioN_accesorio1_idx` (`accesorio_id_accesorio`),
  CONSTRAINT `fk_accesorioN_accesorio1` FOREIGN KEY (`accesorio_id_accesorio`) REFERENCES `accesorio` (`id_accesorio`),
  CONSTRAINT `fk_accesorioN_asignacionAccesorios1` FOREIGN KEY (`asignacionAccesorios_id_asignacion`) REFERENCES `asignacionaccesorios` (`id_asignacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `asignacion`
--

DROP TABLE IF EXISTS `asignacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asignacion` (
  `id_asignacion` int NOT NULL AUTO_INCREMENT,
  `nombre_consultor` varchar(50) NOT NULL,
  `fecha_asignacion` date NOT NULL,
  `costo` varchar(9) DEFAULT NULL,
  `letra` varchar(50) DEFAULT NULL,
  `id_dequipo` int NOT NULL,
  `id_estatus` int NOT NULL,
  `usuario` varchar(45) NOT NULL,
  PRIMARY KEY (`id_asignacion`),
  UNIQUE KEY `id_asignacion_UNIQUE` (`id_asignacion`),
  KEY `fk_Asignacion_DEquipo1_idx` (`id_dequipo`),
  KEY `fk_Asignacion_EstatusRecurso1_idx` (`id_estatus`),
  CONSTRAINT `id_dequipo` FOREIGN KEY (`id_dequipo`) REFERENCES `dequipo` (`id_dequipo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_estatusrecurso` FOREIGN KEY (`id_estatus`) REFERENCES `estatusrecurso` (`id_estatus`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `asignacionaccesorios`
--

DROP TABLE IF EXISTS `asignacionaccesorios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asignacionaccesorios` (
  `id_asignacion` int NOT NULL AUTO_INCREMENT,
  `nombre_consultor` varchar(50) NOT NULL,
  `fecha_asignacion` date NOT NULL,
  `costo` varchar(9) DEFAULT NULL,
  `letra` varchar(50) DEFAULT NULL,
  `id_estatus` int NOT NULL,
  `usuario` varchar(45) NOT NULL,
  PRIMARY KEY (`id_asignacion`),
  UNIQUE KEY `id_asignacion_UNIQUE` (`id_asignacion`),
  KEY `fk_Asignacion_EstatusRecurso1_idx` (`id_estatus`),
  CONSTRAINT `id_estatusrecurso0` FOREIGN KEY (`id_estatus`) REFERENCES `estatusrecurso` (`id_estatus`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dequipo`
--

DROP TABLE IF EXISTS `dequipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dequipo` (
  `id_dequipo` int NOT NULL AUTO_INCREMENT,
  `fecha_actualizacion_estatus` timestamp NOT NULL,
  `disco_duro_solido` varchar(45) DEFAULT NULL,
  `comentarios` varchar(255) DEFAULT NULL,
  `id_estatus` int NOT NULL,
  `id_equipo` int NOT NULL,
  PRIMARY KEY (`id_dequipo`),
  UNIQUE KEY `id_dequipo_UNIQUE` (`id_dequipo`),
  KEY `fk_DEquipo_EstatusRecurso1_idx` (`id_estatus`),
  KEY `fk_dequipo_mequipo1_idx` (`id_equipo`),
  CONSTRAINT `id_equipo` FOREIGN KEY (`id_equipo`) REFERENCES `mequipo` (`id_equipo`),
  CONSTRAINT `id_estatus_DE` FOREIGN KEY (`id_estatus`) REFERENCES `estatusrecurso` (`id_estatus`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equiposoftware`
--

DROP TABLE IF EXISTS `equiposoftware`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equiposoftware` (
  `mequipo_id_equipo` int NOT NULL,
  `software_id_software` int NOT NULL,
  PRIMARY KEY (`mequipo_id_equipo`,`software_id_software`),
  KEY `fk_EquipoSoftware_mequipo1_idx` (`mequipo_id_equipo`),
  KEY `fk_EquipoSoftware_software1_idx` (`software_id_software`),
  CONSTRAINT `fk_EquipoSoftware_mequipo1` FOREIGN KEY (`mequipo_id_equipo`) REFERENCES `mequipo` (`id_equipo`),
  CONSTRAINT `fk_EquipoSoftware_software1` FOREIGN KEY (`software_id_software`) REFERENCES `software` (`id_software`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estatusrecurso`
--

DROP TABLE IF EXISTS `estatusrecurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estatusrecurso` (
  `id_estatus` int NOT NULL AUTO_INCREMENT,
  `nombre_estatus` varchar(45) NOT NULL,
  PRIMARY KEY (`id_estatus`),
  UNIQUE KEY `id_estatus_UNIQUE` (`id_estatus`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `historico`
--

DROP TABLE IF EXISTS `historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historico` (
  `id_historico` int NOT NULL AUTO_INCREMENT,
  `nombre_equipo` varchar(45) DEFAULT NULL,
  `marca` varchar(45) DEFAULT NULL,
  `modelo` varchar(45) DEFAULT NULL,
  `modelo_equipo_cmd` varchar(45) DEFAULT NULL,
  `numero_serie` varchar(23) DEFAULT NULL,
  `numero_serie_cmd` varchar(23) DEFAULT NULL,
  `procesador` varchar(33) DEFAULT NULL,
  `ram` int DEFAULT NULL,
  `disco_duro` varchar(18) DEFAULT NULL,
  `tipo_computadora` varchar(45) DEFAULT NULL,
  `fecha_fabricacion` date DEFAULT NULL,
  `nombre_sistema_operativo` varchar(40) DEFAULT NULL,
  `tipo_sistema_operativo` varchar(7) DEFAULT NULL,
  `direccion_mac` varchar(17) DEFAULT NULL,
  `mequipo_id_equipo` int NOT NULL,
  PRIMARY KEY (`id_historico`),
  UNIQUE KEY `id_equipo_UNIQUE` (`id_historico`),
  KEY `fk_Historico_mequipo1_idx` (`mequipo_id_equipo`),
  CONSTRAINT `fk_Historico_mequipo1` FOREIGN KEY (`mequipo_id_equipo`) REFERENCES `mequipo` (`id_equipo`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mequipo`
--

DROP TABLE IF EXISTS `mequipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mequipo` (
  `id_equipo` int NOT NULL AUTO_INCREMENT,
  `nombre_equipo` varchar(45) DEFAULT NULL,
  `marca` varchar(45) DEFAULT NULL,
  `modelo` varchar(45) DEFAULT NULL,
  `modelo_equipo_cmd` varchar(45) DEFAULT NULL,
  `numero_serie` varchar(23) DEFAULT NULL,
  `numero_serie_cmd` varchar(23) DEFAULT NULL,
  `procesador` varchar(33) DEFAULT NULL,
  `ram` int DEFAULT NULL,
  `disco_duro` varchar(18) DEFAULT NULL,
  `cuenta_usuario` varchar(45) DEFAULT NULL,
  `cuenta_usuario_contraseña` varchar(45) DEFAULT NULL,
  `tipo_computadora` varchar(45) DEFAULT NULL,
  `fecha_fabricacion` date DEFAULT NULL,
  `nombre_sistema_operativo` varchar(40) DEFAULT NULL,
  `tipo_sistema_operativo` varchar(7) DEFAULT NULL,
  `direccion_mac` varchar(17) DEFAULT NULL,
  `email_gnp` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_equipo`),
  UNIQUE KEY `id_equipo_UNIQUE` (`id_equipo`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `musuario`
--

DROP TABLE IF EXISTS `musuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `musuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombres` varchar(45) NOT NULL,
  `apellido_p` varchar(45) NOT NULL,
  `apellido_m` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `contraseña` varchar(45) NOT NULL,
  `fecha_creacion` date NOT NULL,
  `id_rol` int NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `id_usuario_UNIQUE` (`id_usuario`),
  KEY `fk_MUsuario_Roles1_idx` (`id_rol`),
  CONSTRAINT `id_rol` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `responsiva`
--

DROP TABLE IF EXISTS `responsiva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `responsiva` (
  `id_responsiva` int NOT NULL AUTO_INCREMENT,
  `estatus_responsiva` tinyint NOT NULL,
  `fecha_responsiva` date NOT NULL,
  `id_asignacion` int NOT NULL,
  PRIMARY KEY (`id_responsiva`),
  UNIQUE KEY `id_responsiva_UNIQUE` (`id_responsiva`),
  KEY `fk_Responsiva_Asignacion1_idx` (`id_asignacion`),
  CONSTRAINT `id_asignacion` FOREIGN KEY (`id_asignacion`) REFERENCES `asignacion` (`id_asignacion`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `rol` varchar(45) NOT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `id_rol_UNIQUE` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `software`
--

DROP TABLE IF EXISTS `software`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `software` (
  `id_software` int NOT NULL AUTO_INCREMENT,
  `no_serie` varchar(29) DEFAULT NULL,
  `fecha_licencia` date DEFAULT NULL,
  `nombre_software` varchar(45) NOT NULL,
  PRIMARY KEY (`id_software`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-30 12:12:21
