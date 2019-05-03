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
-- Table structure for table `AccessMatrixEmployee`
--

DROP TABLE IF EXISTS `AccessMatrixEmployee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccessMatrixEmployee` (
  `idEmployee` int(11) NOT NULL COMMENT 'id сотрудника',
  `BlockEmploye` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Блокировка сотрудника',
  `ButtonAdministration` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Кнопка администратор',
  `ButtonSpecialOperation` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Кнопка специальные операции',
  `ButtonSettings` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Кнопка настройки',
  `ButtonEmployees` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Кнопка сотрудники',
  `ButtonJob` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Кнопка Работа',
  `ButtonControlSPC` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Кнопка Управление ЦСН',
  `ButtonPrivateOffice` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Кнопка личный кабинет',
  `PrivateOffice_Messages` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Личный кабинет - сообщения',
  `PrivateOffice_Keys` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Личный кабинет - ключи',
  `ControlSPC_Object` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Управление ЦСН - Объект',
  `ControlSPC_Equipment` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Управление ЦСН - Оборудование',
  `Job_Operations` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Работа - Операции',
  `Job_Documents` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Работа-Документы',
  `Job_Equipment` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Работа - оборудование',
  `CheckSum` varchar(40) NOT NULL COMMENT 'Контрольная сумма',
  PRIMARY KEY (`idEmployee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AccessMatrixEmployee`
--

LOCK TABLES `AccessMatrixEmployee` WRITE;
/*!40000 ALTER TABLE `AccessMatrixEmployee` DISABLE KEYS */;
INSERT INTO `AccessMatrixEmployee` VALUES (15,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,'Заглушка'),(101,0,0,1,0,0,1,0,1,1,1,1,1,1,1,1,'Заглушка'),(102,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,'Заглушка'),(103,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,'Заглушка'),(104,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,'Заглушка');
/*!40000 ALTER TABLE `AccessMatrixEmployee` ENABLE KEYS */;
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
