-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: proyectos
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cargos`
--

DROP TABLE IF EXISTS `cargos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cargo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargos`
--

LOCK TABLES `cargos` WRITE;
/*!40000 ALTER TABLE `cargos` DISABLE KEYS */;
INSERT INTO `cargos` VALUES (1,'Supervisor'),(2,'Auxiliar'),(3,'Tecnico');
/*!40000 ALTER TABLE `cargos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docente_certificado`
--

DROP TABLE IF EXISTS `docente_certificado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docente_certificado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `idproyecto` int(11) NOT NULL,
  `iddocente` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_docente_certificado_proyectos1_idx` (`idproyecto`),
  KEY `fk_docente_certificado_docentes1_idx` (`iddocente`),
  CONSTRAINT `fk_docente_certificado_docentes1` FOREIGN KEY (`iddocente`) REFERENCES `docentes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_docente_certificado_proyectos1` FOREIGN KEY (`idproyecto`) REFERENCES `proyectos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docente_certificado`
--

LOCK TABLES `docente_certificado` WRITE;
/*!40000 ALTER TABLE `docente_certificado` DISABLE KEYS */;
INSERT INTO `docente_certificado` VALUES (1,'2020-04-17','15:27:51',1,2),(2,'2020-04-17','15:29:00',1,3),(3,'2020-04-17','15:30:13',2,5),(4,'2020-04-17','15:34:09',2,4),(7,'2020-04-25','20:10:09',2,3);
/*!40000 ALTER TABLE `docente_certificado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docente_proyecto`
--

DROP TABLE IF EXISTS `docente_proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docente_proyecto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idproyecto` int(11) NOT NULL,
  `iddocente` int(11) NOT NULL,
  `idcargo` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_docente_proyecto_proyectos1_idx` (`idproyecto`),
  KEY `fk_docente_proyecto_docentes1_idx` (`iddocente`),
  KEY `fk_docente_proyecto_cargos1_idx` (`idcargo`),
  CONSTRAINT `fk_docente_proyecto_cargos1` FOREIGN KEY (`idcargo`) REFERENCES `cargos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_docente_proyecto_docentes1` FOREIGN KEY (`iddocente`) REFERENCES `docentes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_docente_proyecto_proyectos1` FOREIGN KEY (`idproyecto`) REFERENCES `proyectos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docente_proyecto`
--

LOCK TABLES `docente_proyecto` WRITE;
/*!40000 ALTER TABLE `docente_proyecto` DISABLE KEYS */;
INSERT INTO `docente_proyecto` VALUES (10,2,5,1),(11,2,2,2),(12,2,3,2),(13,2,4,2),(14,1,2,1),(15,1,3,2),(16,1,6,3),(17,3,4,2),(18,3,7,2),(19,3,3,2),(20,3,6,2),(21,3,2,2),(22,3,5,2),(23,5,2,2),(26,5,5,3),(29,5,6,1),(31,5,4,1),(32,4,4,2),(33,4,7,3),(34,4,5,3),(35,6,2,2),(36,6,5,1),(37,6,3,1);
/*!40000 ALTER TABLE `docente_proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docentes`
--

DROP TABLE IF EXISTS `docentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docentes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `telefono` varchar(9) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `idsituacion` int(11) NOT NULL,
  `idpersona` int(11) NOT NULL,
  `idescuela` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_docentes_situacion_laboral_idx` (`idsituacion`),
  KEY `fk_docentes_personas1_idx` (`idpersona`),
  KEY `fk_docentes_escuelas1_idx` (`idescuela`),
  CONSTRAINT `fk_docentes_escuelas1` FOREIGN KEY (`idescuela`) REFERENCES `escuelas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_docentes_personas1` FOREIGN KEY (`idpersona`) REFERENCES `personas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_docentes_situacion_laboral` FOREIGN KEY (`idsituacion`) REFERENCES `situacion_laboral` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docentes`
--

LOCK TABLES `docentes` WRITE;
/*!40000 ALTER TABLE `docentes` DISABLE KEYS */;
INSERT INTO `docentes` VALUES (2,'987451236','juangarciatameki@mail.com',1,1,1),(3,'985740151','raquel@mail.com',2,2,2),(4,'965654511','antoniobanderas@mail.com',2,3,3),(5,'946545457','dora@mail.com',1,4,2),(6,'978945454','farncisco@mail.com',2,5,1),(7,'984132121','castro@mail.com',2,6,4);
/*!40000 ALTER TABLE `docentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `escuelas`
--

DROP TABLE IF EXISTS `escuelas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `escuelas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `escuela` varchar(45) NOT NULL,
  `idfacultad` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_escuelas_facultades1_idx` (`idfacultad`),
  CONSTRAINT `fk_escuelas_facultades1` FOREIGN KEY (`idfacultad`) REFERENCES `facultades` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `escuelas`
--

LOCK TABLES `escuelas` WRITE;
/*!40000 ALTER TABLE `escuelas` DISABLE KEYS */;
INSERT INTO `escuelas` VALUES (1,'Ingenieria  de Sistemas',1),(2,'Estadistica',1),(3,'Arquitectura Y Urbanismo',2),(4,'Ingenieria de Minas',3),(5,'Matematica',1),(6,'Derecho Y Ciencias Politicas',6);
/*!40000 ALTER TABLE `escuelas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facultades`
--

DROP TABLE IF EXISTS `facultades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facultades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `facultad` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facultades`
--

LOCK TABLES `facultades` WRITE;
/*!40000 ALTER TABLE `facultades` DISABLE KEYS */;
INSERT INTO `facultades` VALUES (1,'Ciencias'),(2,'Civil'),(3,'Minas'),(4,'Agrarias'),(5,'Educacion'),(6,'Derecho');
/*!40000 ALTER TABLE `facultades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informes`
--

DROP TABLE IF EXISTS `informes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `informes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(50) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `iddocente` int(11) NOT NULL,
  `idproyecto` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_informes_docentes1_idx` (`iddocente`),
  KEY `fk_informes_proyectos1_idx` (`idproyecto`),
  CONSTRAINT `fk_informes_docentes1` FOREIGN KEY (`iddocente`) REFERENCES `docentes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_informes_proyectos1` FOREIGN KEY (`idproyecto`) REFERENCES `proyectos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informes`
--

LOCK TABLES `informes` WRITE;
/*!40000 ALTER TABLE `informes` DISABLE KEYS */;
INSERT INTO `informes` VALUES (1,'I-N01-0001','2019-12-01','05:36:58',2,2),(2,'I-N01-0002','2019-12-13','12:08:03',4,2),(3,'I-N01-0003','2019-12-30','10:19:36',2,2),(4,'I-N01-0004','2019-12-30','11:23:41',2,2),(5,'informe_58','2020-04-27','10:59:45',2,3),(6,'ying_yang','2020-04-27','11:01:00',5,1),(7,'infrome_87','2020-04-27','11:02:32',6,1);
/*!40000 ALTER TABLE `informes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instituciones`
--

DROP TABLE IF EXISTS `instituciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instituciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `institucion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instituciones`
--

LOCK TABLES `instituciones` WRITE;
/*!40000 ALTER TABLE `instituciones` DISABLE KEYS */;
INSERT INTO `instituciones` VALUES (1,'Colegio Simon Bolivar'),(2,'Banco de la Nacion'),(3,'Sunat');
/*!40000 ALTER TABLE `instituciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dni` varchar(8) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `genero` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (1,'71458239','Garcia Soriano','Juan','Masculino'),(2,'41257845','Castillo Ramos','Raquel','Femenino'),(3,'41257485','Puntillo Ramirez','Antonio','Masculino'),(4,'32517455','Quispe Mamani','Maria','Femenino'),(5,'75445133','Valencia Norabuena','Francisco','Masculino'),(6,'87945631','Castro Castro','Julian','Masculino');
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyectos`
--

DROP TABLE IF EXISTS `proyectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proyectos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proyecto` varchar(150) NOT NULL,
  `condicion` varchar(45) NOT NULL,
  `presentacion` datetime NOT NULL,
  `inicio` date NOT NULL,
  `fin` date NOT NULL,
  `idinstitucion` int(11) NOT NULL,
  `idsemestre` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_proyectos_instituciones1_idx` (`idinstitucion`),
  KEY `fk_proyectos_semestres1_idx` (`idsemestre`),
  CONSTRAINT `fk_proyectos_instituciones1` FOREIGN KEY (`idinstitucion`) REFERENCES `instituciones` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyectos_semestres1` FOREIGN KEY (`idsemestre`) REFERENCES `semestres` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyectos`
--

LOCK TABLES `proyectos` WRITE;
/*!40000 ALTER TABLE `proyectos` DISABLE KEYS */;
INSERT INTO `proyectos` VALUES (1,'Proyecto de Saneamiento Industrial','Aprobado','2020-04-13 09:30:00','2020-04-16','2020-06-23',1,3),(2,'Proyecto de Seguridad Informatica','Aprobado','2019-10-20 10:00:00','2019-10-26','2019-12-30',2,2),(3,'Proyecto de instalaciones Electrico-Gravitacional','Aprobado','2020-04-15 13:45:00','2020-04-18','2020-07-22',3,3),(4,'Proyecto de Telecomunicaciones Nucleares','Aprobado','2020-04-17 16:15:12','2020-05-04','2020-08-01',2,5),(5,'Proyecto de Compra de focos agroindustriales patentadas','Aprobado','2020-04-23 22:58:49','2020-05-24','2020-08-18',3,5),(6,'Proyecto de implementacion de IA','Aprobado','2020-04-25 13:12:59','2020-05-19','2020-08-15',2,5);
/*!40000 ALTER TABLE `proyectos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semestres`
--

DROP TABLE IF EXISTS `semestres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semestres` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `semestre` varchar(45) NOT NULL,
  `inicio` date NOT NULL,
  `fin` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semestres`
--

LOCK TABLES `semestres` WRITE;
/*!40000 ALTER TABLE `semestres` DISABLE KEYS */;
INSERT INTO `semestres` VALUES (1,'Semestre 2018-I','2018-04-10','2018-07-27'),(2,'Semestre 2018-II','2018-09-03','2018-12-17'),(3,'Semestre 2019-I','2019-04-08','2019-07-08'),(4,'Semestre 2019-II','2019-09-16','2020-01-13'),(5,'Semestre 2020-I','2020-05-18','2020-08-24');
/*!40000 ALTER TABLE `semestres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `situacion_laboral`
--

DROP TABLE IF EXISTS `situacion_laboral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `situacion_laboral` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `situacion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `situacion_laboral`
--

LOCK TABLES `situacion_laboral` WRITE;
/*!40000 ALTER TABLE `situacion_laboral` DISABLE KEYS */;
INSERT INTO `situacion_laboral` VALUES (1,'Nombrado'),(2,'Contratado');
/*!40000 ALTER TABLE `situacion_laboral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v_docente_certificado`
--

DROP TABLE IF EXISTS `v_docente_certificado`;
/*!50001 DROP VIEW IF EXISTS `v_docente_certificado`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_docente_certificado` AS SELECT 
 1 AS `Id`,
 1 AS `Dni`,
 1 AS `Docente`,
 1 AS `Genero`,
 1 AS `Situacion`,
 1 AS `Escuela`,
 1 AS `facultad`,
 1 AS `IdProyecto`,
 1 AS `Proyecto`,
 1 AS `Fecha`,
 1 AS `Hora`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_docente_proyecto`
--

DROP TABLE IF EXISTS `v_docente_proyecto`;
/*!50001 DROP VIEW IF EXISTS `v_docente_proyecto`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_docente_proyecto` AS SELECT 
 1 AS `Id`,
 1 AS `IdProyecto`,
 1 AS `Dni`,
 1 AS `Docente`,
 1 AS `Escuela`,
 1 AS `Cargo`,
 1 AS `Situacion`,
 1 AS `Proyecto`,
 1 AS `Condicion`,
 1 AS `Inicio`,
 1 AS `Fin`,
 1 AS `Semestre`,
 1 AS `Institucion`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_docentes`
--

DROP TABLE IF EXISTS `v_docentes`;
/*!50001 DROP VIEW IF EXISTS `v_docentes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_docentes` AS SELECT 
 1 AS `Id`,
 1 AS `Dni`,
 1 AS `Docente`,
 1 AS `Genero`,
 1 AS `Telefono`,
 1 AS `Email`,
 1 AS `Situacion`,
 1 AS `Escuela`,
 1 AS `Facultad`,
 1 AS `iddocente`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_escuelas`
--

DROP TABLE IF EXISTS `v_escuelas`;
/*!50001 DROP VIEW IF EXISTS `v_escuelas`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_escuelas` AS SELECT 
 1 AS `Id`,
 1 AS `Escuela`,
 1 AS `Facultad`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_informes`
--

DROP TABLE IF EXISTS `v_informes`;
/*!50001 DROP VIEW IF EXISTS `v_informes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_informes` AS SELECT 
 1 AS `Id`,
 1 AS `Informe`,
 1 AS `Fecha`,
 1 AS `Hora`,
 1 AS `Dni`,
 1 AS `Docente`,
 1 AS `Escuela`,
 1 AS `Proyecto`,
 1 AS `IdProyecto`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_proyectos`
--

DROP TABLE IF EXISTS `v_proyectos`;
/*!50001 DROP VIEW IF EXISTS `v_proyectos`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_proyectos` AS SELECT 
 1 AS `Id`,
 1 AS `Proyecto`,
 1 AS `Condicion`,
 1 AS `Presentacion`,
 1 AS `Inicio`,
 1 AS `Fin`,
 1 AS `Institucion`,
 1 AS `Semestre`,
 1 AS `Fase`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'proyectos'
--
/*!50003 DROP FUNCTION IF EXISTS `f_faseProyecto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `f_faseProyecto`(idproy int) RETURNS varchar(45) CHARSET utf8
BEGIN
    set @condicion = (select condicion from proyectos where id = idproy);
    set @inicio = (select inicio from proyectos where id = idproy);
    set @fin = (select fin from proyectos where id = idproy);

	if @condicion <> 'Aprobado' then
		return 'En Evaluacion';
	else
		if ( CURDATE() BETWEEN @inicio AND @fin ) then
			return 'En proceso';
		end if;
        if (CURDATE() < @inicio) then
			return 'Sin Iniciar';
        end if;
        if (CURDATE() >= @fin) then
			if (SELECT COUNT(*) FROM informes WHERE (informes.idproyecto = idproy)) >= 2 then
				return 'Finalizado';
			else
				return 'Anulado';
            end if;
			
        end if;
    end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_datosDocenteEnProyecto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_datosDocenteEnProyecto`(in iddoc int, idproy int)
BEGIN

	select
    
    p.dni dni,
    concat_ws(' ', upper(p.apellidos), p.nombres) docente,
    p.genero genero,
    d.telefono telefono,
    d.email email,
    sl.situacion situacion,
    e.escuela escuela,
    f.facultad facultad,
    (select cargo from cargos where id = (select idcargo from docente_proyecto where iddocente = iddoc and idproyecto=idproy)) cargo,
    (select count(*) from informes where iddocente = iddoc and idproyecto=idproy) informes
    
    from 
    docentes d
    join situacion_laboral sl on sl.id = d.idsituacion
	join personas p on p.id = d.idpersona
	join escuelas e on e.id = d.idescuela
    join facultades f on f.id = e.idfacultad
    
    where d.id = iddoc;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_datosProyecto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_datosProyecto`(in _idproyecto varchar(4))
BEGIN
	
    select 
    
    pr.id id,
    pr.proyecto proyecto,
    pr.condicion condicion, 
	pr.presentacion presentacion,
	pr.inicio inicio, 
	pr.fin fin,
    f_faseProyecto(pr.id) fase,
    i.institucion institucion,
    sm.semestre semestre,
    sm.inicio inicioSemestre,
    sm.fin finSemestre,
    dp.iddocente docente
    
    from 
    docente_proyecto dp
    join proyectos pr on pr.id = dp.idproyecto
    join instituciones i on i.id = pr.idinstitucion
    join semestres sm on sm.id = pr.idsemestre    
    
    where pr.id = _idproyecto;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_docente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_docente`(in _id int,
	_dni varchar(8), _ape varchar(45), _nom varchar(45), _gen varchar(45),
    _tel varchar(9), _mail varchar(45), _sit varchar(45), _esc varchar(45))
BEGIN
	
    set @idS = (select id from situacion_laboral where situacion = _sit);
    set @idE = (select id from escuelas where escuela = _esc);
    
	if ( _id = 0) then -- guardar
		if (select count(*) from personas where dni = _dni) = 0 then
			insert into personas values (null, _dni, _ape, _nom, _gen);
			set @idP = (select id from personas order by id desc limit 1);
			insert into docentes values (null, _tel, _mail, @idS, @idP, @idE);    
			select concat_ws(' ', 'Se registro con exito a',_ape, _nom) msg;
        else
			select concat_ws(' ', 'Ya existe un docente registrado con el DNI N°',_dni) msg;
        end if;
    else -- editar
		update personas set dni = _dni, apellidos = _ape, nombres = _nom, genero = _gen where id = _id;
        update docentes set telefono = _tel, email = _mail, idsituacion = @idS, idescuela = @idE where idpersona = _id;
        select concat_ws(' ', 'Se actualizo con exito los datos de',_ape, _nom) msg;
    end if;           
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_docente_proyecto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_docente_proyecto`(in evt int, _idProy int, _idDoc int, _cargo varchar(45))
BEGIN
	case evt
		when 1 then -- crear
			if (select count(*) from docente_proyecto where idproyecto = _idProy) < 6 then
				if (select count(*) from docente_proyecto where idproyecto = _idProy and iddocente = _idDoc) = 0 then
					insert into docente_proyecto values (null, _idProy, _idDoc, (select id from cargos where cargo = _cargo));
					select 'Ok' msg;
				else
					select 'El docente ya esta registrado en el proyecto' msg;
				end if;
			else
				select 'El proyecto ya tiene 6 docentes registrados' msg;
            end if;
		when 2 then -- editar
			update docente_proyecto set idcargo = (select id from cargos where cargo = _cargo) 
				where id = _idProy and iddocente = _idDoc;
			select 'Ok' msg;
		when 3 then -- eliminar
			if (select count(*) from informes where iddocente = _idDoc and idproyecto = _idProy) = 0 then
				delete from docente_proyecto where idproyecto = _idProy and iddocente = _idDoc;
                select 'Ok' msg;
			else
				select concat_ws(' ',(select docente from v_docentes where iddocente = _idDoc),'no puede eliminarse.') msg;
            end if;
    end case;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_escuela` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_escuela`(in _id int, _esc varchar(45), _fac varchar(45))
BEGIN
	set @idF = (select id from facultades where facultad = _fac);
    if _id = 0 then
		if (select count(*) from escuelas where escuela = _esc) = 0 then
			insert into escuelas values (null, _esc, @idF);
            select concat_ws(' ','Se inserto con exito la escuela', _esc), 'success', 'Exito';
        else
			select concat_ws(' ','Ya existe una escuela llamado', _esc), 'danger', 'Error';
        end if;
    else
		update escuelas set escuela = _esc, idfacultad = @idF where id = _id;
        select concat_ws(' ','Se actualizo con exito la escuela', _esc), 'success', 'Exito';
    
    end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_facultad` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_facultad`(in _id int, _fac varchar(45))
BEGIN
	if (_id = 0) then
		if (select count(*) from facultades where facultad = _fac) = 0 then
			insert into facultades values (null, _fac);
            select concat_ws(' ','Se inserto con exito la facultad', _fac), 'success', 'Exito';
		else
			select concat_ws(' ','Ya existe una facultad llamado', _fac), 'danger', 'Error';
		end if;
    else
		update facultades set facultad = _fac where id = _id;
		select concat_ws(' ','Se actualizo los datos de', _fac), 'success', 'Exito';
    end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_proyecto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_proyecto`(in _id int, _proyecto varchar(150), _inicio date, _fin date, _institucion varchar(45))
BEGIN
	
    set @idInst = (select id from instituciones where institucion = _institucion);
    set @idSem = (select id from semestres where '2020-05-25' >= inicio and '2020-08-18' <= adddate(fin, interval 7 day));
    set @diff = (select datediff(_fin,fin) from semestres where id = @idSem);
    set @diffMes = (select datediff(_fin, _inicio));
    
	if (_id = 0) then -- guardar
		
        if @diffMes >= 60 and @diffMes <= 90 then
        
			if @diff <= 7 then
				insert into proyectos values (null, _proyecto, 'En Evaluacion', now(), _inicio, _fin, @idInst, @idSem);
                select 'Ok' msg;
			else
				select concat_ws(' ', 'El final del proyecto no debe ser mayor en una semana al final del semestre',
				(select fin from semestres where id = @idSem)) msg;
			end if;
        
        else
			select concat_ws(' ', 'El proyecto debe durar entre 60 y 90 dias, actualmente dura',
				@diffMes,'dias') msg;
        end if;        
        
    else -- editar
		if @diffMes >= 60 and @diffMes <= 90 then
        
			if @diff <= 7 then
				update proyectos set proyecto = _proyecto, inicio = _inicio, fin = _fin, 
					idinstitucion = @idInst, idsemestre = @idSem where id = _id;
                select 'Ok' msg;
			else
				select concat_ws(' ', 'El final del proyecto no debe ser mayor en una semana al final del semestre',
				(select fin from semestres where id = @idSem)) msg;
			end if;
        
        else
			select concat_ws(' ', 'El proyecto debe durar entre 60 y 90 dias, actualmente dura',
				@diffMes,'dias') msg;
        end if;
    end if;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_semestre` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_semestre`(in _id int, _sem varchar(45), _ini date, _fin date)
BEGIN

	if _id = 0 then
		
        if (select count(*) from semestres where semestre = _sem) = 0 then
        
			insert into semestres values (null, _sem, _ini, _fin);
            select 'Se registro con exito el semestre', 'success', 'Exito';
		else 
			select 'Ya existe un semestre con este nombre', 'danger', 'Error';        
        end if;
        
    else
		update semestres set semestre = _sem, inicio = _ini, fin = _fin where id = _id;
        select 'Se actualizo con exito el semestre', 'success', 'Exito';
    end if;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `v_docente_certificado`
--

/*!50001 DROP VIEW IF EXISTS `v_docente_certificado`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_docente_certificado` AS select `d`.`id` AS `Id`,`p`.`dni` AS `Dni`,concat_ws(' ',`p`.`apellidos`,`p`.`nombres`) AS `Docente`,`p`.`genero` AS `Genero`,`sl`.`situacion` AS `Situacion`,`e`.`escuela` AS `Escuela`,`f`.`facultad` AS `facultad`,`pr`.`id` AS `IdProyecto`,`pr`.`proyecto` AS `Proyecto`,`dc`.`fecha` AS `Fecha`,`dc`.`hora` AS `Hora` from ((((((`docente_certificado` `dc` join `proyectos` `pr` on((`pr`.`id` = `dc`.`idproyecto`))) join `docentes` `d` on((`d`.`id` = `dc`.`iddocente`))) join `personas` `p` on((`p`.`id` = `d`.`idpersona`))) join `situacion_laboral` `sl` on((`sl`.`id` = `d`.`idsituacion`))) join `escuelas` `e` on((`e`.`id` = `d`.`idescuela`))) join `facultades` `f` on((`f`.`id` = `e`.`idfacultad`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_docente_proyecto`
--

/*!50001 DROP VIEW IF EXISTS `v_docente_proyecto`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_docente_proyecto` AS select `d`.`id` AS `Id`,`pr`.`id` AS `IdProyecto`,`p`.`dni` AS `Dni`,concat_ws(' ',`p`.`apellidos`,`p`.`nombres`) AS `Docente`,`es`.`escuela` AS `Escuela`,`cg`.`cargo` AS `Cargo`,`sl`.`situacion` AS `Situacion`,`pr`.`proyecto` AS `Proyecto`,`pr`.`condicion` AS `Condicion`,`pr`.`inicio` AS `Inicio`,`pr`.`fin` AS `Fin`,`s`.`id` AS `Semestre`,`i`.`institucion` AS `Institucion` from ((((((((`docente_proyecto` `dp` join `proyectos` `pr` on((`pr`.`id` = `dp`.`idproyecto`))) join `instituciones` `i` on((`i`.`id` = `pr`.`idinstitucion`))) join `docentes` `d` on((`d`.`id` = `dp`.`iddocente`))) join `cargos` `cg` on((`cg`.`id` = `dp`.`idcargo`))) join `escuelas` `es` on((`es`.`id` = `d`.`idescuela`))) join `personas` `p` on((`p`.`id` = `d`.`idpersona`))) join `situacion_laboral` `sl` on((`sl`.`id` = `d`.`idsituacion`))) join `semestres` `s` on((`s`.`id` = `pr`.`idsemestre`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_docentes`
--

/*!50001 DROP VIEW IF EXISTS `v_docentes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_docentes` AS select `p`.`id` AS `Id`,`p`.`dni` AS `Dni`,concat_ws(' ',`p`.`apellidos`,`p`.`nombres`) AS `Docente`,`p`.`genero` AS `Genero`,`d`.`telefono` AS `Telefono`,`d`.`email` AS `Email`,`sl`.`situacion` AS `Situacion`,`e`.`escuela` AS `Escuela`,`f`.`facultad` AS `Facultad`,`d`.`id` AS `iddocente` from ((((`docentes` `d` join `personas` `p` on((`p`.`id` = `d`.`idpersona`))) join `situacion_laboral` `sl` on((`sl`.`id` = `d`.`idsituacion`))) join `escuelas` `e` on((`e`.`id` = `d`.`idescuela`))) join `facultades` `f` on((`f`.`id` = `e`.`idfacultad`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_escuelas`
--

/*!50001 DROP VIEW IF EXISTS `v_escuelas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_escuelas` AS select `e`.`id` AS `Id`,`e`.`escuela` AS `Escuela`,`f`.`facultad` AS `Facultad` from (`escuelas` `e` join `facultades` `f` on((`f`.`id` = `e`.`idfacultad`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_informes`
--

/*!50001 DROP VIEW IF EXISTS `v_informes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_informes` AS select `i`.`id` AS `Id`,`i`.`numero` AS `Informe`,`i`.`fecha` AS `Fecha`,`i`.`hora` AS `Hora`,`p`.`dni` AS `Dni`,concat_ws(' ',`p`.`apellidos`,`p`.`nombres`) AS `Docente`,`e`.`escuela` AS `Escuela`,`pr`.`proyecto` AS `Proyecto`,`pr`.`id` AS `IdProyecto` from ((((`informes` `i` join `proyectos` `pr` on((`pr`.`id` = `i`.`idproyecto`))) join `docentes` `d` on((`d`.`id` = `i`.`iddocente`))) join `personas` `p` on((`p`.`id` = `d`.`idpersona`))) join `escuelas` `e` on((`e`.`id` = `d`.`idescuela`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_proyectos`
--

/*!50001 DROP VIEW IF EXISTS `v_proyectos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_proyectos` AS select `p`.`id` AS `Id`,`p`.`proyecto` AS `Proyecto`,`p`.`condicion` AS `Condicion`,`p`.`presentacion` AS `Presentacion`,`p`.`inicio` AS `Inicio`,`p`.`fin` AS `Fin`,`i`.`institucion` AS `Institucion`,`s`.`semestre` AS `Semestre`,'En Evaluacion' AS `Fase` from ((`proyectos` `p` join `instituciones` `i` on((`i`.`id` = `p`.`idinstitucion`))) join `semestres` `s` on((`s`.`id` = `p`.`idsemestre`))) where (`p`.`condicion` <> 'Aprobado') union select `p`.`id` AS `Id`,`p`.`proyecto` AS `Proyecto`,`p`.`condicion` AS `Condicion`,`p`.`presentacion` AS `Presentacion`,`p`.`inicio` AS `Inicio`,`p`.`fin` AS `Fin`,`i`.`institucion` AS `Institucion`,`s`.`semestre` AS `Semestre`,'En Proceso' AS `Fase` from ((`proyectos` `p` join `instituciones` `i` on((`i`.`id` = `p`.`idinstitucion`))) join `semestres` `s` on((`s`.`id` = `p`.`idsemestre`))) where ((`p`.`condicion` = 'Aprobado') and (curdate() between `p`.`inicio` and `p`.`fin`)) union select `p`.`id` AS `Id`,`p`.`proyecto` AS `Proyecto`,`p`.`condicion` AS `Condicion`,`p`.`presentacion` AS `Presentacion`,`p`.`inicio` AS `Inicio`,`p`.`fin` AS `Fin`,`i`.`institucion` AS `Institucion`,`s`.`semestre` AS `Semestre`,'Sin Iniciar' AS `Fase` from ((`proyectos` `p` join `instituciones` `i` on((`i`.`id` = `p`.`idinstitucion`))) join `semestres` `s` on((`s`.`id` = `p`.`idsemestre`))) where ((`p`.`condicion` = 'Aprobado') and (curdate() < `p`.`inicio`)) union select `p`.`id` AS `Id`,`p`.`proyecto` AS `Proyecto`,`p`.`condicion` AS `Condicion`,`p`.`presentacion` AS `Presentacion`,`p`.`inicio` AS `Inicio`,`p`.`fin` AS `Fin`,`i`.`institucion` AS `Institucion`,`s`.`semestre` AS `Semestre`,'Finalizado' AS `Fase` from ((`proyectos` `p` join `instituciones` `i` on((`i`.`id` = `p`.`idinstitucion`))) join `semestres` `s` on((`s`.`id` = `p`.`idsemestre`))) where ((`p`.`condicion` = 'Aprobado') and (curdate() >= `p`.`fin`) and ((select count(0) from `informes` where (`informes`.`idproyecto` = `p`.`id`)) >= 2)) union select `p`.`id` AS `Id`,`p`.`proyecto` AS `Proyecto`,`p`.`condicion` AS `Condicion`,`p`.`presentacion` AS `Presentacion`,`p`.`inicio` AS `Inicio`,`p`.`fin` AS `Fin`,`i`.`institucion` AS `Institucion`,`s`.`semestre` AS `Semestre`,'Anulado' AS `Fase` from ((`proyectos` `p` join `instituciones` `i` on((`i`.`id` = `p`.`idinstitucion`))) join `semestres` `s` on((`s`.`id` = `p`.`idsemestre`))) where ((`p`.`condicion` = 'Aprobado') and (curdate() >= `p`.`fin`) and ((select count(0) from `informes` where (`informes`.`idproyecto` = `p`.`id`)) < 2)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-29 13:02:25
