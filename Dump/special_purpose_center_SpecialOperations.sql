-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: special_purpose_center
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.17.10.1

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
-- Table structure for table `SpecialOperations`
--

DROP TABLE IF EXISTS `SpecialOperations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SpecialOperations` (
  `idSpecialOperations` int(11) NOT NULL COMMENT 'id Специальной операции',
  `Description` longtext COMMENT 'Описание',
  `Allowance` int(11) DEFAULT NULL COMMENT 'Степень конфиденциальности/секретности\n\nФормы допуска:\n\n    1-я (самая высокая) форма – для лиц, которые работают со сведениями особой важности\n    2-я форма - для работников, соприкасающихся с совершенно секретными сведениями\n    3-я (низшая) форма - для лиц, работающих с (просто) секретными сведениями\n\n\n4 - конфиденциально \n5 - открыто\n0- закрыто ',
  `NameSpecialOperation` varchar(100) DEFAULT NULL COMMENT 'Название специальной операции',
  `JSON` json DEFAULT NULL,
  `CheckSum` varchar(40) NOT NULL,
  PRIMARY KEY (`idSpecialOperations`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SpecialOperations`
--

LOCK TABLES `SpecialOperations` WRITE;
/*!40000 ALTER TABLE `SpecialOperations` DISABLE KEYS */;
INSERT INTO `SpecialOperations` VALUES (1,'Описание специальной операции 1',4,'Град-12',NULL,''),(2,'Описание специальной операции 2',3,'Тайфун',NULL,'');
/*!40000 ALTER TABLE `SpecialOperations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-23 23:50:55
