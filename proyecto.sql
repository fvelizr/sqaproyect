CREATE DATABASE  IF NOT EXISTS `PROYECTO` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `PROYECTO`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 45.55.47.25    Database: PROYECTO
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `cl_id` int NOT NULL,
  `cl_Nombre` varchar(100) DEFAULT NULL,
  `cl_nit` varchar(20) DEFAULT NULL,
  `cl_direccion` varchar(40) DEFAULT NULL,
  `cl_telefono` int DEFAULT NULL,
  PRIMARY KEY (`cl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'JOSE SANTIZO','1234567','ESCUINTLA',12345678),(2,'TOMAS MORA','1122337','taxisco',12345678),(3,'OTTO BLOCK','5225566','ciudad',51680349),(4,'MARCOS ALVARADO','82934721','ciudad',51680349),(5,'MARIO ESCOBAR','5225566','ciudad',54256352),(6,'MIGUEL DONIS','5225566','ciudad',54256352),(7,'MANUEL MIGUEL','5225566','ciudad',54256352),(8,'miguel ponce','123','ciudad',12345678),(9,'oscar estal','123456','ciudad',12345678),(10,'oscar estal','123456','ciudad',12345678),(11,'oscar estal','123456','ciudad',12345678),(12,'mario lopez','11111','ciudad',12345678),(13,'Juan','2323121','Ciudad',32325667),(14,'Pedro','23810\'','Taxisco',155442312),(15,'josue garcia','434686878','ciudad',54669877),(16,'CARLOS SANTIZO','22222','CIUDAD',58565455),(17,'OSCAR PEREZ','147741','CIUDAD',54525622),(18,'CINDY AREVALO','78998','CIUDAD',56985632),(19,'SERGIO SOTO','321123','MAZATENANGO',54869856),(20,'Ordan Garcia','54888855','ciudad',58565455);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compras` (
  `com_id` int NOT NULL,
  `com_prov_id` int DEFAULT NULL,
  `IdEmpleado` int DEFAULT NULL,
  `com_estado` char(1) DEFAULT NULL,
  `com_total` int DEFAULT NULL,
  `com_fecha_alta` date DEFAULT NULL,
  `com_usuario_alta` varchar(20) DEFAULT NULL,
  `com_fecha_modifica` date DEFAULT NULL,
  `com_usuario_modifica` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`com_id`),
  KEY `com_prov_id` (`com_prov_id`),
  KEY `IdEmpleado` (`IdEmpleado`),
  CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`com_prov_id`) REFERENCES `proveedor` (`prov_id`),
  CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_compra`
--

DROP TABLE IF EXISTS `detalle_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_compra` (
  `detc_com_id` int NOT NULL,
  `detc_pro_id` int NOT NULL,
  `detc_precio_compra` int DEFAULT NULL,
  `detc_cantidad` int DEFAULT NULL,
  PRIMARY KEY (`detc_com_id`,`detc_pro_id`),
  KEY `detc_pro_id` (`detc_pro_id`),
  CONSTRAINT `detalle_compra_ibfk_1` FOREIGN KEY (`detc_com_id`) REFERENCES `compras` (`com_id`),
  CONSTRAINT `detalle_compra_ibfk_2` FOREIGN KEY (`detc_pro_id`) REFERENCES `producto` (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_compra`
--

LOCK TABLES `detalle_compra` WRITE;
/*!40000 ALTER TABLE `detalle_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_ventas`
--

DROP TABLE IF EXISTS `detalle_ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_ventas` (
  `IdVentas` int NOT NULL,
  `pro_id` int NOT NULL,
  `Cantidad` int DEFAULT NULL,
  `PrecioVenta` float DEFAULT NULL,
  PRIMARY KEY (`IdVentas`,`pro_id`),
  KEY `pro_id` (`pro_id`),
  CONSTRAINT `detalle_ventas_ibfk_1` FOREIGN KEY (`IdVentas`) REFERENCES `ventas` (`IdVentas`),
  CONSTRAINT `detalle_ventas_ibfk_2` FOREIGN KEY (`pro_id`) REFERENCES `producto` (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_ventas`
--

LOCK TABLES `detalle_ventas` WRITE;
/*!40000 ALTER TABLE `detalle_ventas` DISABLE KEYS */;
INSERT INTO `detalle_ventas` VALUES (1,1,10,1),(2,1,1,1),(3,1,1,1),(4,1,1,1),(5,1,1,1),(6,1,3,1),(6,3,2,75),(7,1,2,1),(7,3,2,75),(8,1,3,1),(8,2,7,1),(8,3,2,75),(8,4,5,11),(9,1,3,1),(9,2,4,1),(9,3,5,75),(9,4,2,11),(10,1,45,1),(10,2,45,1),(10,6,1,25),(11,1,3,1),(12,1,1,1),(13,1,1,1),(14,1,1,1),(15,1,1,1),(16,1,2,1),(17,1,1,1),(18,1,1,1),(19,1,1,1),(20,1,1,1),(21,1,1,1),(22,1,1,1),(23,1,1,1),(24,1,1,1),(25,1,1,1),(26,1,1,1),(27,1,3,1),(28,1,2,1),(28,2,3,1),(28,3,4,75),(28,4,3,11),(29,1,1,1),(31,1,3,1),(31,4,2,11),(32,4,1,11),(33,4,1,11),(34,4,1,11),(35,10,1,10),(36,5,1,10),(37,12,1,50),(38,10,1,10),(39,1,1,1),(39,2,3,1),(39,3,2,75),(39,4,1,10),(40,2,1,1),(40,3,3,75),(41,1,1,1),(41,4,1,10),(42,2,1,1),(42,3,2,75),(43,3,2,75),(43,5,3,10),(44,2,3,1),(45,2,1,1),(46,4,1,10),(47,4,1,10),(48,4,1,10),(49,4,1,10),(51,4,1,10),(52,3,2,75),(53,3,2,75),(54,2,4,1),(54,3,2,75),(54,5,1,10),(57,2,2,1),(57,4,1,10),(58,8,2,10),(59,1,12,2),(59,7,12,1);
/*!40000 ALTER TABLE `detalle_ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `IdEmpleado` int NOT NULL,
  `NombreEmpleado` varchar(100) DEFAULT NULL,
  `DpiEmpleado` decimal(13,0) DEFAULT NULL,
  `CorreoEmpleado` varchar(40) DEFAULT NULL,
  `DireccionEmpleado` varchar(40) DEFAULT NULL,
  `TelefonoEmpleado` decimal(10,0) DEFAULT NULL,
  `Fecha_nacimientoEmpleado` date DEFAULT NULL,
  `PuestoEmpleado` varchar(35) DEFAULT NULL,
  `Fecha_ingresoEmpleado` datetime DEFAULT NULL,
  `EstadoEmpleado` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`IdEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,'Juan Manuel Gonzales Cruz',4586896330505,'JuanG@correo.es','Siquinala, Escuintla',45896321,'1991-05-03','Vendedor','2021-02-02 00:00:00','A'),(2,'Maria Carmela Gomez Morales',5426589536256,'MariaC@correo.es','Palin,Escuintla',54526589,'1990-03-25','Vendedor','2021-03-01 00:00:00','A'),(3,'Jose Valladares',5425325658888,'jose@corre.com','Taxisco',52452125,'1990-04-22','Vendedor','2021-04-26 00:00:00','A'),(4,'miguel gonzales',5446464,'correo','ciudad',544464,'2002-01-01','ventas','2021-03-01 00:00:00','A'),(5,'Emely Espinoza',229388882,'eespinoza@correo.com','masagua',54289562,'2000-01-01','Vendedora','2019-05-01 00:00:00','A'),(6,'ADMINISTRADOR',1234567890123,'admin@correo.com','ciudad',52462563,'1993-02-18','Administrador','2021-01-01 00:00:00','A'),(7,'Jose Alvarez',5522145555555,'jalvarez@correo.com','taxisco',52655588,'1990-02-12','Vendedor','2021-05-20 00:00:00','A'),(8,'Erick Velasquez',4546566465465,'evelasquez@correo.com','Escuintla',54789635,'1990-02-12','Vendedor','2021-05-20 00:00:00','A');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `fac_id` int NOT NULL,
  `IdVentas` int DEFAULT NULL,
  `fac_estado` char(1) DEFAULT NULL,
  `fac_total` decimal(10,2) DEFAULT NULL,
  `fac_tipo_pago` char(1) DEFAULT NULL,
  `fac_fecha_alta` datetime DEFAULT NULL,
  `fac_usuario_alta` varchar(20) DEFAULT NULL,
  `fac_fecha_modifica` date DEFAULT NULL,
  `fac_usuario_modifica` varchar(20) DEFAULT NULL,
  `fac_recibido` decimal(10,2) DEFAULT NULL,
  `fac_cambio` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`fac_id`),
  KEY `IdVentas` (`IdVentas`),
  CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`IdVentas`) REFERENCES `ventas` (`IdVentas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` VALUES (5,5,'E',1.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,1.00,0.00),(6,6,'E',153.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,153.00,0.00),(7,7,'E',152.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,152.00,0.00),(8,8,'E',215.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,215.00,0.00),(9,9,'E',404.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,404.00,0.00),(10,10,'E',115.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,120.00,-5.00),(11,11,'E',3.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,3.00,0.00),(12,12,'E',1.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,1.00,0.00),(13,13,'E',1.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,1.00,0.00),(14,14,'E',1.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,1.00,0.00),(15,15,'E',1.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,1.00,0.00),(16,16,'E',2.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,2.00,0.00),(17,17,'E',1.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,1.00,0.00),(18,18,'E',1.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,1.00,0.00),(19,19,'E',2.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,2.00,0.00),(20,20,'E',1.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,1.00,0.00),(21,21,'E',1.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,1.00,0.00),(22,22,'E',1.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,1.00,0.00),(23,23,'E',1.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,1.00,0.00),(24,24,'E',1.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,1.00,0.00),(25,25,'E',1.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,1.00,0.00),(26,26,'E',1.00,'E','2021-05-24 00:00:00','jv21',NULL,NULL,1.00,0.00),(27,27,'E',3.00,'E','2021-05-25 05:03:05','jv21',NULL,NULL,3.00,0.00),(28,28,'E',338.00,'E','2021-05-25 05:03:05','jv21',NULL,NULL,338.00,0.00),(29,44,'E',3.00,'E','2021-05-29 04:46:38','jv21',NULL,NULL,3.00,0.00),(30,46,'E',10.00,'E','2021-05-29 05:21:32','jv21',NULL,NULL,10.00,0.00),(31,47,'E',10.00,'E','2021-05-29 05:22:50','jv21',NULL,NULL,10.00,0.00),(32,48,'E',10.00,'E','2021-05-29 05:28:13','jv21',NULL,NULL,10.00,0.00),(33,49,'E',10.00,'E','2021-05-29 05:30:37','jv21',NULL,NULL,10.00,0.00),(34,51,'E',10.00,'E','2021-05-29 05:45:00','jv21',NULL,NULL,10.00,0.00),(35,52,'E',150.00,'E','2021-05-29 05:45:31','jv21',NULL,NULL,150.00,0.00),(36,53,'E',150.00,'E','2021-05-29 05:45:59','jv21',NULL,NULL,150.00,0.00),(37,55,'E',14.00,'E','2021-05-29 06:31:54','eespinoza',NULL,NULL,50.00,-36.00),(38,56,'E',14.00,'E','2021-05-29 06:33:03','eespinoza',NULL,NULL,50.00,-36.00),(39,57,'E',12.00,'E','2021-05-29 06:34:37','eespinoza',NULL,NULL,20.00,-8.00),(40,59,'E',36.00,'E','2021-05-29 13:17:50','jalvarez',NULL,NULL,40.00,-4.00);
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `pro_id` int NOT NULL,
  `pro_Nombre` varchar(100) DEFAULT NULL,
  `pro_precio_compra` decimal(10,0) DEFAULT NULL,
  `pro_stock` int DEFAULT NULL,
  `pro_marca` varchar(30) DEFAULT NULL,
  `pro_fechaIngreso` datetime DEFAULT NULL,
  `pro_minimo` int DEFAULT NULL,
  PRIMARY KEY (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'ACETAMINOFEN',2,19,'MK','2021-04-14 00:00:00',60),(2,'IBUPROFENO',1,26,'MK','2021-04-14 00:00:00',30),(3,'CATAFLAN',75,72,'NOVARTIS','2021-04-15 00:00:00',30),(4,'RECARGA 10 CLARO',10,77,'CLARO','2021-04-28 01:58:56',5),(5,'RECARGA 10 CLARO',10,95,'CLARO','2021-04-28 02:05:12',5),(6,'RECARGA 25 CLARO',25,99,'CLARO','2021-04-28 02:22:34',5),(7,'Panadol',1,88,'MK','2021-05-04 00:00:00',5),(8,'RECARGA 10 TIGO',10,100,'TIGO','2021-05-04 00:00:00',5),(9,'RECARGA 25 TIGO',25,100,'TIGO','2021-05-04 00:00:00',25),(10,'PAQUETIGO TODO INCLUIDO Q.10.00',10,98,'TIGO','2021-05-04 00:00:00',30),(11,'PAQUETIGO TODO INCLUIDO Q.25.00',25,100,'TIGO','2021-05-04 00:00:00',30),(12,'PAQUETIGO TODO INCLUIDO Q.50.00',50,99,'TIGO','2021-05-04 00:00:00',30),(13,'TODO INCLUIDO CLARO Q.10.00',10,100,'CLARO','2021-05-04 00:00:00',30),(14,'TODO INCLUIDO CLARO Q.25.00',25,100,'CLARO','2021-05-04 00:00:00',30),(15,'TODO INCLUIDO CLARO Q.50.00',50,100,'CLARO','2021-05-04 00:00:00',30),(16,'FLAMIDOL',8,100,'UNIPHARM','2021-05-29 00:00:00',51);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `prov_id` int NOT NULL,
  `prov_Nombre` varchar(100) DEFAULT NULL,
  `prov_nit` varchar(20) DEFAULT NULL,
  `prov_direccion` varchar(40) DEFAULT NULL,
  `prov_telefono` int DEFAULT NULL,
  `prov_fechaIngreso` date DEFAULT NULL,
  PRIMARY KEY (`prov_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'LANCASCO S.A.','44568923145',' 2 Calle 14-90 Ciudad de Guatemala',24211000,'2021-04-15'),(2,'LANQUETIN','89633589696','10 Avenida 4-58 Zona 1',23897142,'2021-04-15'),(3,'CHEMILCO','455588896','GUATEMALA',12345678,'2021-04-28'),(4,'LAPRIN','54545411','CIUDAD',58852255,'2021-05-03'),(5,'mediproductos','55554448','ciudad',45444646,'2021-05-03'),(6,'PHARMALAT','58658888','CIUDAD',45698523,'2021-05-04'),(7,'LAPRIN','54545411','CIUDAD',58852255,'2021-05-04'),(8,'NOVARTIS','466656','CIUDAD',55666663,'2021-05-04'),(9,'ROEERMERS','666666','CIUDAD',52452555,'2021-05-21'),(10,'PIERSAN','777777','ciudad',58695555,'2021-05-21'),(11,'FARMANDINA','111222333','CIUDAD',52632588,NULL),(12,'NOVARTIS','331122','CIUDAD',45625566,NULL),(13,'PROVEEDOR','44444','CIUDAD',11111,NULL),(14,'CHEMILCO','448899','CIUDAD',45698555,NULL),(15,'MEDIPRODUCTOS','54546546','CIUDAD',56449888,NULL);
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL,
  `IdEmpleado` int DEFAULT NULL,
  `UserEmpleado` varchar(50) NOT NULL,
  `PassEmpleado` varchar(50) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `IdEmpleado` (`IdEmpleado`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,1,'JUANGC','123'),(2,2,'MARIAGM','123'),(3,3,'jv21','abc'),(4,4,'123c','secreto'),(5,5,'eespinoza','secreto'),(6,6,'admin','secreto'),(7,7,'jalvarez','jose'),(8,8,'evelasquez','secreto');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `IdVentas` int NOT NULL,
  `cl_id` int DEFAULT NULL,
  `IdEmpleado` int DEFAULT NULL,
  `FechaVentas` date DEFAULT NULL,
  PRIMARY KEY (`IdVentas`),
  KEY `cl_id` (`cl_id`),
  KEY `IdEmpleado` (`IdEmpleado`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`cl_id`) REFERENCES `cliente` (`cl_id`),
  CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (1,8,3,'2021-05-24'),(2,8,3,'2021-05-24'),(3,8,3,'2021-05-24'),(4,8,3,'2021-05-24'),(5,8,3,'2021-05-24'),(6,8,3,'2021-05-24'),(7,8,3,'2021-05-24'),(8,8,3,'2021-05-24'),(9,8,3,'2021-05-24'),(10,1,3,'2021-05-24'),(11,8,3,'2021-05-24'),(12,8,3,'2021-05-24'),(13,8,3,'2021-05-24'),(14,8,3,'2021-05-24'),(15,8,3,'2021-05-24'),(16,8,3,'2021-05-24'),(17,8,3,'2021-05-24'),(18,8,3,'2021-05-24'),(19,8,3,'2021-05-24'),(20,8,3,'2021-05-24'),(21,8,3,'2021-05-24'),(22,8,3,'2021-05-24'),(23,8,3,'2021-05-24'),(24,8,3,'2021-05-24'),(25,8,3,'2021-05-24'),(26,8,3,'2021-05-24'),(27,8,3,'2021-05-24'),(28,8,3,'2021-05-25'),(29,8,3,'2021-05-25'),(30,8,3,'2021-05-25'),(31,8,3,'2021-05-25'),(32,8,3,'2021-05-25'),(33,8,3,'2021-05-27'),(34,8,3,'2021-05-27'),(35,8,3,'2021-05-27'),(36,18,3,'2021-05-27'),(37,8,6,'2021-05-28'),(38,19,6,'2021-05-28'),(39,19,3,'2021-05-28'),(40,8,3,'2021-05-28'),(41,8,3,'2021-05-28'),(42,8,3,'2021-05-28'),(43,NULL,3,'2021-05-29'),(44,8,3,'2021-05-29'),(45,8,3,'2021-05-29'),(46,8,3,'2021-05-29'),(47,8,3,'2021-05-29'),(48,8,3,'2021-05-29'),(49,8,3,'2021-05-29'),(50,8,3,'2021-05-29'),(51,8,3,'2021-05-29'),(52,8,3,'2021-05-29'),(53,8,3,'2021-05-29'),(54,8,3,'2021-05-29'),(55,1,5,'2021-05-29'),(56,1,5,'2021-05-29'),(57,1,5,'2021-05-29'),(58,8,5,'2021-05-29'),(59,8,7,'2021-05-29');
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'PROYECTO'
--
/*!50003 DROP PROCEDURE IF EXISTS `ActualizaCliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `ActualizaCliente`(p_Nombre varchar(100), p_Nit varchar(40),p_Direccion varchar(40),p_Telefono decimal(8),p_id decimal(5))
BEGIN
	update  cliente set cl_Nombre=p_Nombre,cl_nit=p_Nit,cl_direccion=p_Direccion,cl_telefono=p_Telefono where cl_id=p_id;     
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ActualizaEmpleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `ActualizaEmpleado`(p_Nombre varchar(100), p_Dpi decimal(13), 
p_Correo varchar(40),p_Direccion varchar(40),p_Telefono int,p_FechaNacimiento date,
p_Puesto varchar(40),p_FechaIngreso date,p_estado varchar(1),p_id varchar(20))
BEGIN
	update empleado set  NombreEmpleado=p_Nombre,DpiEmpleado=p_Dpi,CorreoEmpleado=p_Correo,DireccionEmpleado=p_Direccion,
    TelefonoEmpleado=p_Telefono, Fecha_nacimientoEmpleado=p_FechaNacimiento,PuestoEmpleado=p_Puesto,Fecha_ingresoEmpleado=p_FechaIngreso,EstadoEmpleado=p_estado 
    where IdEmpleado=p_id;
     
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ActualizaProducto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `ActualizaProducto`(p_Nombre varchar(100), p_Precio DECIMAL(6,2),p_Stock DECIMAL(6),p_Marca varchar(50),p_fecha date,p_Minimo decimal(6),p_id decimal(6))
BEGIN
	update  producto set pro_Nombre=p_Nombre,pro_precio_compra=p_Precio,pro_stock=p_Stock,
			pro_marca=p_Marca,pro_fechaIngreso=p_fecha,pro_minimo=p_Minimo where pro_id=p_id;
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AutenticacionUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `AutenticacionUsuario`(IN p_usuario varchar(100),IN p_clave varchar(100))
BEGIN
	select e.NombreEmpleado,u.UserEmpleado,u.PassEmpleado,e.IdEmpleado
    from empleado e,usuario u 
    where e.IdEmpleado = u.IdEmpleado and u.UserEmpleado=p_usuario and u.PassEmpleado=p_clave;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `CambioClave` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `CambioClave`(usuario char(20),pass char(20))
BEGIN
	update PROYECTO.usuario set  PassEmpleado=pass where idEmpleado=usuario;
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ConsultaCliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `ConsultaCliente`(IN p_nit VARCHAR(15))
BEGIN
    IF p_nit <> 0 then  -- 0 = TODOS LOS CLIENTES, >0  = CLIENTES POR CODIGO
		SELECT CL_ID CODIGO, 
			   CL_NOMBRE NOMBRE,
			   CL_NIT NIT,
			   CL_DIRECCION DIRECCION,
			   CL_TELEFONO TELEFONO
		FROM cliente
		WHERE cl_nit = p_nit;
    else
		SELECT CL_ID CODIGO, 
			   CL_NOMBRE NOMBRE,
			   CL_NIT NIT,
			   CL_DIRECCION DIRECCION,
			   CL_TELEFONO TELEFONO
		FROM cliente;
    end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ConsultaCorteCaja` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `ConsultaCorteCaja`(p_fecha date,p_idemp decimal(6))
BEGIN
	SELECT F.fac_id,
                                          DATE_FORMAT(F.FAC_FECHA_ALTA,'%H:%i:%S') FECHA,
                                          F.fac_estado,
                                          (CASE F.FAC_TIPO_PAGO WHEN 'E' THEN 'EFECTIVO' WHEN 'T' THEN 'TARJETA' ELSE ' ' END) ESTADO,
                                          F.FAC_TOTAL,
                                          E.IdEmpleado,
                                          E.NombreEmpleado,
                                          F.fac_usuario_alta USUARIO,
                                          C.cl_id,
                                          C.cl_Nombre,
                                          C.cl_nit,
                                          C.cl_direccion
                                        FROM factura F, ventas V, empleado E, cliente C
                                        WHERE F.IdVentas = V.IdVentas
                                         AND V.IdEmpleado = E.IdEmpleado
                                         AND V.cl_id = C.cl_id
                          and DATE(F.FAC_FECHA_ALTA) = p_fecha
                          and V.IdEmpleado =  p_idemp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ConsultaDetalles` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `ConsultaDetalles`()
BEGIN
	SELECT D.pro_id,
                                       IFNULL(P.pro_Nombre,' '),
                                       IFNULL(P.pro_marca,' '),
                                       IFNULL(FORMAT(D.PrecioVenta,2),0),
                                       IFNULL(D.Cantidad,0)
                                FROM PROYECTO.detalle_ventas D,PROYECTO.producto P
                                WHERE D.pro_id = P.pro_id
                                AND D.IdVentas =(select IFNULL(MAX(IdVentas),0) FROM PROYECTO.ventas J);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ConsultaEmpleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `ConsultaEmpleado`(IN p_id int)
BEGIN
	IF p_id <> 0 THEN  -- 0 = TODOS LOS EMPLEADOS   >0 = EMPLEADOS POR CODIGO
		SELECT e.*,u.UserEmpleado from empleado e,usuario u where e.IdEmpleado = u.IdEmpleado
		AND e.IDEMPLEADO = p_id;
	else
		SELECT e.*,u.UserEmpleado from empleado e,usuario u where e.IdEmpleado = u.IdEmpleado;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ConsultaEmpleadoNit` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `ConsultaEmpleadoNit`(IN p_nit varchar(15))
BEGIN
	select * from cliente where cl_nit = p_nit;	 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ConsultaEncabezadoCotizacion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `ConsultaEncabezadoCotizacion`()
BEGIN
	SELECT V.IdVentas,
                      E.IdEmpleado,
                      E.NombreEmpleado,
                      C.cl_id,
                      C.cl_Nombre,
                      C.cl_nit,
                      C.cl_direccion
                    FROM ventas V, empleado E, cliente C
                    WHERE V.IdEmpleado = E.IdEmpleado
                     AND V.cl_id = C.cl_id
                     and V.IdVentas =(select IFNULL(MAX(IdVentas),0) FROM PROYECTO.ventas J);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ConsultaEncabezadoFactura` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `ConsultaEncabezadoFactura`()
BEGIN
	SELECT F.fac_id,
                      DATE_FORMAT(F.FAC_FECHA_ALTA,'%d/%m/%Y') FECHA,
                      F.fac_estado,
                      (CASE F.FAC_TIPO_PAGO WHEN 'E' THEN 'EFECTIVO' WHEN 'T' THEN 'TARJETA' ELSE ' ' END) ESTADO,
                      F.FAC_TOTAL,
                      E.IdEmpleado,
                      E.NombreEmpleado,
                      F.fac_usuario_alta USUARIO,
                      C.cl_id,
                      C.cl_Nombre,
                      C.cl_nit,
                      C.cl_direccion,
                      F.fac_recibido,
                      F.fac_cambio
                    FROM factura F, ventas V, empleado E, cliente C
                    WHERE F.IdVentas = V.IdVentas
                     AND V.IdEmpleado = E.IdEmpleado
                     AND V.cl_id = C.cl_id
                     and F.fac_id = (SELECT MAX(fac_id) FROM factura);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ConsultaProducto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `ConsultaProducto`(IN p_id int)
BEGIN
	IF p_id <> 0 then  --  0 = TODOS   >0  = BUSCAR POR CODIGO
		SELECT PRO_ID CODIGO,
			PRO_NOMBRE NOMBRE,
            PRO_PRECIO_COMPRA PRECIO,
            PRO_STOCK EXISTENCIAS,
            PRO_MARCA MARCA,
            DATE_FORMAT(PRO_FECHAINGRESO,'%Y/%m/%d') FECHAINGRESO,
            PRO_MINIMO MINIMO
		FROM PROYECTO.producto
        WHERE PRO_ID = p_id;
	ELSE
		SELECT PRO_ID CODIGO,
			PRO_NOMBRE NOMBRE,
            PRO_PRECIO_COMPRA PRECIO,
            PRO_STOCK EXISTENCIAS,
            PRO_MARCA MARCA,
            DATE_FORMAT(PRO_FECHAINGRESO,'%Y/%m/%d') FECHAINGRESO,
            PRO_MINIMO MINIMO
		FROM PROYECTO.producto;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ConsultaProductoMinimos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `ConsultaProductoMinimos`()
BEGIN
	SELECT *
    FROM PROYECTO.producto
    WHERE PRO_STOCK < PRO_MINIMO;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ConsultaProductoRecarga` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `ConsultaProductoRecarga`()
BEGIN
	select * from producto where pro_marca IN('TIGO','CLARO');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ConsultaProveedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `ConsultaProveedor`(IN p_id int)
BEGIN
	IF p_id <> 0 THEN    --   0 = TODOS LOS PROVEEDORES   >0  =  BUSCAR POR CODIGO
		SELECT PROV_ID CODIGO,
				PROV_NOMBRE NOMBRE,
				PROV_NIT NIT,
				PROV_DIRECCION DIRECCION,
				PROV_TELEFONO TELEFONO,
				PROV_FECHAINGRESO FECHAINGRESO
		FROM PROYECTO.proveedor
		WHERE PROV_ID = p_id;
	else
		SELECT PROV_ID CODIGO,
				PROV_NOMBRE NOMBRE,
				PROV_NIT NIT,
				PROV_DIRECCION DIRECCION,
				PROV_TELEFONO TELEFONO,
				PROV_FECHAINGRESO FECHAINGRESO
		FROM PROYECTO.proveedor;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `InsertaDetallesPrefactura` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `InsertaDetallesPrefactura`(idProducto int,idCantidad int,precioVenta decimal(10,2),p_activ varchar(1))
BEGIN
	INSERT INTO detalle_ventas (IdVentas, pro_id, Cantidad, PrecioVenta) 
				   VALUES ((select IFNULL(MAX(IdVentas),0) from ventas e),idProducto,idCantidad,precioVenta);
                   
    IF p_activ = 'F' then
		UPDATE producto set pro_stock = pro_stock - idCantidad         
		where pro_id = idProducto;
    END IF;
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `InsertaEncabezadoCotizacion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `InsertaEncabezadoCotizacion`(idCliente char(20),idEmpleado int)
BEGIN
	INSERT INTO ventas (IdVentas, cl_id, IdEmpleado, FechaVentas) 
				   VALUES ((select IFNULL(MAX(IdVentas),0)+1 from ventas e), 
                   (select c.cl_id from cliente c where cl_nit = idCliente), idEmpleado,now());
                   
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `InsertaEncabezadoFactura` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `InsertaEncabezadoFactura`(idCliente char(20),idEmpleado int,totalFactura decimal(10,2), tipoPago char(1), usuarioAlta varchar(20),totalRecibido decimal(10,2))
BEGIN
	INSERT INTO ventas (IdVentas, cl_id, IdEmpleado, FechaVentas) 
				   VALUES ((select IFNULL(MAX(IdVentas),0)+1 from ventas e), 
                   (select c.cl_id from cliente c where cl_nit = idCliente), idEmpleado,now());
                   
    INSERT INTO factura (fac_id, IdVentas, fac_estado, fac_total, fac_tipo_pago,fac_fecha_alta,fac_usuario_alta,fac_recibido,fac_cambio)
    VALUES ((select IFNULL(MAX(fac_id),0)+1 from factura e),
			(select IFNULL(MAX(IdVentas),0) from ventas e), 'E', totalFactura,tipoPago,now(),usuarioAlta,totalRecibido,(totalFactura-totalRecibido));
                   
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `InsertarCliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `InsertarCliente`(p_Nombre varchar(100), p_Nit varchar(40),p_Direccion varchar(40),p_Telefono int)
BEGIN
	INSERT INTO `cliente` (`cl_id`, `cl_Nombre`, `cl_nit`, `cl_direccion`, `cl_telefono`) 
				   VALUES ((select IFNULL(MAX(cl_id),0)+1 from cliente e), p_Nombre, p_Nit,p_Direccion,p_Telefono);
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `InsertarEmpleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `InsertarEmpleado`(p_Nombre varchar(100), p_Dpi decimal(13), p_Correo varchar(40),p_Direccion varchar(40),p_Telefono int,p_FechaNacimiento date,p_Puesto varchar(40),p_FechaIngreso date,p_Usuario varchar(20))
BEGIN
	INSERT INTO `empleado` (`IdEmpleado`, `NombreEmpleado`, `DpiEmpleado`, `CorreoEmpleado`, 
							`DireccionEmpleado`, `TelefonoEmpleado`, `Fecha_nacimientoEmpleado`, 
                            `PuestoEmpleado`, `Fecha_ingresoEmpleado`, `EstadoEmpleado`) 
					VALUES ((select IFNULL(MAX(IdEmpleado),0)+1 from empleado e), p_Nombre, p_Dpi, p_Correo, 
							p_Direccion,p_Telefono,p_FechaNacimiento, 
                            p_Puesto,p_FechaIngreso,'A');
                            
	INSERT INTO `usuario` (`idUsuario`, `IdEmpleado`, `UserEmpleado`, `PassEmpleado`) 
					VALUES ((select IFNULL(MAX(idUsuario),0)+1 from usuario e), (select IFNULL(MAX(IdEmpleado),0) from empleado e), p_Usuario,'secreto');
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `InsertarProducto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `InsertarProducto`(p_Nombre varchar(100), p_Precio DECIMAL(6,2),p_Stock DECIMAL(6),p_Marca varchar(50),p_Minimo decimal(6))
BEGIN
	INSERT INTO `producto` (`pro_id`, `pro_Nombre`, `pro_precio_compra`, `pro_stock`, 
                            `pro_marca`, `pro_fechaIngreso`, `pro_minimo`) 
					VALUES ((select IFNULL(MAX(pro_id),0)+1 from producto e), p_Nombre, p_Precio, p_Stock, 
							p_Marca, NOW(), p_Minimo);
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `InsertarProveedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`INGESOFT`@`%` PROCEDURE `InsertarProveedor`(p_Nombre varchar(100), p_Nit varchar(40),p_Direccion varchar(40),p_Telefono int)
BEGIN
	INSERT INTO `proveedor` (`prov_id`, `prov_Nombre`, `prov_nit`, `prov_direccion`, `prov_telefono`, `prov_fechaIngreso`)
    			     VALUES ((select IFNULL(MAX(prov_id),0)+1 from proveedor e), p_Nombre, p_Nit,p_Direccion,p_Telefono,now());
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-29  7:19:50
