-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ghostnet
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `ghost_net`
--

DROP TABLE IF EXISTS `ghost_net`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ghost_net` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `location` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `status` enum('BERGUNG_BEVORSTEHEND','GEBORGEN','GEMELDET','VERSCHOLLEN') DEFAULT NULL,
  `person_id` bigint DEFAULT NULL,
  `reported_anonymously` bit(1) NOT NULL,
  `reporter_name` varchar(255) DEFAULT NULL,
  `reporter_phone` varchar(255) DEFAULT NULL,
  `missing_reporter_name` varchar(255) DEFAULT NULL,
  `missing_reporter_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgnsx2c6ne42hjylr0q9nu37pe` (`person_id`),
  CONSTRAINT `FKgnsx2c6ne42hjylr0q9nu37pe` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ghost_net`
--

LOCK TABLES `ghost_net` WRITE;
/*!40000 ALTER TABLE `ghost_net` DISABLE KEYS */;
INSERT INTO `ghost_net` VALUES (1,'10.100, 20,100','Klein','BERGUNG_BEVORSTEHEND',17,_binary '','Karin Kenner','Anonym',NULL,NULL),(2,'20.200, 30,300','Mittel','BERGUNG_BEVORSTEHEND',18,_binary '\0','Karin Kenner','0122127127',NULL,NULL),(3,'11.111, 22.222','Groß','GEBORGEN',NULL,_binary '','James Bond','Anonym',NULL,NULL),(4,'33.333, 44.444','Klein','VERSCHOLLEN',NULL,_binary '','James Bond','Anonym','Roger Moore','0122007007'),(5,'45.780, 80.000','Groß','GEMELDET',NULL,_binary '\0','Mister B','01744238987',NULL,NULL);
/*!40000 ALTER TABLE `ghost_net` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `person_type` enum('BERGEND','MELDEND') DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (16,'Karin Kenner','MELDEND','0122127127'),(17,'Fred Feuerstein','BERGEND','0174456789'),(18,'Max Mustermann','BERGEND','Anonym'),(19,'Mister B','MELDEND','01744238987'),(20,'Devin Maurer','BERGEND','Anonym');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-31 14:13:38
