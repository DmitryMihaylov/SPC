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
-- Table structure for table `Message`
--

DROP TABLE IF EXISTS `Message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Message` (
  `idMessage` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id сообщения',
  `MessageSubject` varchar(100) NOT NULL COMMENT 'Тема сообщения',
  `Sender` int(11) NOT NULL COMMENT 'Отправитель',
  `Recipient` int(11) NOT NULL COMMENT 'Получатель',
  `Message` longtext NOT NULL COMMENT 'Сообщение',
  `CheckSum` varchar(64) NOT NULL COMMENT 'Контрольная сумма',
  PRIMARY KEY (`idMessage`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Message`
--

LOCK TABLES `Message` WRITE;
/*!40000 ALTER TABLE `Message` DISABLE KEYS */;
INSERT INTO `Message` VALUES (1,'Количественный рост',15,101,'Таким образом постоянный количественный рост и сфера нашей активности требуют определения и уточнения направлений прогрессивного развития. Идейные соображения высшего порядка, а также постоянный количественный рост и сфера нашей активности в значительной степени обуславливает создание соответствующий условий активизации. ','Заглушка (контрольная сумма)'),(3,'Структура организации',15,101,'е следует, однако забывать, что сложившаяся структура организации требуют определения и уточнения соответствующий условий активизации. Идейные соображения высшего порядка, а также консультация с широким активом в значительной степени обуславливает создание позиций, занимаемых участниками в отношении поставленных задач. Равным образом начало повседневной работы по формированию позиции позволяет оценить значение форм развития. Разнообразный и богатый опыт сложившаяся структура организации влечет за собой процесс внедрения и модернизации направлений прогрессивного развития. С другой стороны постоянное информационно-пропагандистское обеспечение нашей деятельности играет важную роль в формировании направлений прогрессивного развития. Идейные соображения высшего порядка, а также дальнейшее развитие различных форм деятельности требуют от нас анализа направлений прогрессивного развития. ','Заглушка (контрольная сумма)'),(4,'Формирование позиции',101,15,'Таким образом начало повседневной работы по формированию позиции требуют определения и уточнения позиций, занимаемых участниками в отношении поставленных задач. Товарищи! укрепление и развитие структуры играет важную роль в формировании дальнейших направлений развития. С другой стороны постоянное информационно-пропагандистское обеспечение нашей деятельности влечет за собой процесс внедрения и модернизации системы обучения кадров, соответствует насущным потребностям. ','Заглушка (контрольная сумма)'),(5,'Информационное обеспечение (ответ)',101,15,'Не следует, однако забывать, что постоянное информационно-пропагандистское обеспечение нашей деятельности обеспечивает широкому кругу (специалистов) участие в формировании позиций, занимаемых участниками в отношении поставленных задач. Равным образом новая модель организационной деятельности позволяет оценить значение соответствующий условий активизации. \n\nТестирование всплывающего окна (1) 20.57','Заглушка (контрольная сумма)'),(6,'Информационное обеспечение (ответ)',101,15,'Не следует, однако забывать, что постоянное информационно-пропагандистское обеспечение нашей деятельности обеспечивает широкому кругу (специалистов) участие в формировании позиций, занимаемых участниками в отношении поставленных задач. Равным образом новая модель организационной деятельности позволяет оценить значение соответствующий условий активизации. \n\nТестирование всплывающего окна (2) 20.58','Заглушка (контрольная сумма)'),(8,'Формирование позиции',15,101,'Таким образом начало повседневной работы по формированию позиции требуют определения и уточнения позиций, занимаемых участниками в отношении поставленных задач. Товарищи! укрепление и развитие структуры играет важную роль в формировании дальнейших направлений развития. С другой стороны постоянное информационно-пропагандистское обеспечение нашей деятельности влечет за собой процесс внедрения и модернизации системы обучения кадров, соответствует насущным потребностям. ','Заглушка (контрольная сумма)'),(9,'Формирование позиции',15,101,'Таким образом начало повседневной работы по формированию позиции требуют определения и уточнения позиций, занимаемых участниками в отношении поставленных задач. Товарищи! укрепление и развитие структуры играет важную роль в формировании дальнейших направлений развития. С другой стороны постоянное информационно-пропагандистское обеспечение нашей деятельности влечет за собой процесс внедрения и модернизации системы обучения кадров, соответствует насущным потребностям. ','Заглушка (контрольная сумма)');
/*!40000 ALTER TABLE `Message` ENABLE KEYS */;
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