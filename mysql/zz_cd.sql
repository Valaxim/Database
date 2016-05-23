-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: zz
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `cd`
--

DROP TABLE IF EXISTS `cd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cd`
--

LOCK TABLES `cd` WRITE;
/*!40000 ALTER TABLE `cd` DISABLE KEYS */;
INSERT INTO `cd` VALUES (1,'Taylor Swift','1989','AVALIBLE',13),(2,'Taylor Swift','RED','UNAVALIBLE',13),(3,'Taylor Swift','Speak Now','AVALIBLE',13),(4,'Taylor Swift','Fearless','UNAVALIBLE',13),(5,'Taylor Swift','Taylor Swift','AVALIBLE',13),(6,'O.S.T.R','Zycie po smierci','AVALIBLE',13),(7,'Ania Dabrowska','Dla naiwnych marzycieli','AVALIBLE',13),(8,'Maria Peszek','Karabin','UNAVALIBLE',13),(12,'Coldplay','A head full of dream','UNAVALIBLE',13),(14,'Lana Del Rey','Honeymoon','UNAVALIBLE',13),(15,'Various Artist','50 Shades of Grey','UNAVALIBLE',13),(16,'Various Artist','Frozen','AVALIBLE',13),(17,'LemON','Scarlett','UNAVALIBLE',13),(18,'Sam Smith','In the lonely hour','UNAVALIBLE',13),(19,'Andrzej Piaseczny','Kalejdoskop','UNAVALIBLE',13),(22,'Margaret','Just the two of us','AVALIBLE',13),(23,'Selena Gomez','Stars Dance','UNAVALIBLE',13),(24,'Selena Gomez','For You','UNAVALIBLE',13),(25,'Selena Gomez','Revival','UNAVALIBLE',13),(26,'Ariana Grande','Yours Truly','UNAVALIBLE',13),(27,'Ariana Grande','My Everything','UNAVALIBLE',13),(28,'Ariana Grande','Dangerous Women','UNAVALIBLE',13),(29,'Justin Bieber','My World 2.0','UNAVALIBLE',13),(30,'Justin Bieber','Under the Mistletoe','UNAVALIBLE',13),(31,'Justin Bieber','Believe','UNAVALIBLE',13),(32,'Justin Bieber','Purpose','UNAVALIBLE',13),(33,'Demi Lovato','Dont forger','UNAVALIBLE',13),(34,'Demi Lovato','Here we go again','UNAVALIBLE',13),(35,'Demi Lovato','Unbroken','UNAVALIBLE',13),(36,'Demi Lovato','Demi','AVALIBLE',13),(37,'Demi Lovato','Confident','UNAVALIBLE',13),(61,'Cyber Marian','Was was was','AVALIBLE',13),(68,'Edyta Gorniak','Zakochaj się na święta','AVALIBLE',13),(70,'Justin Bieber','Mistletoe','AVALIBLE',13);
/*!40000 ALTER TABLE `cd` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-31 10:25:59
