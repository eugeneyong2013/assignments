-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: info
-- ------------------------------------------------------
-- Server version	5.5.56-log

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
-- Table structure for table `credentials`
--

DROP TABLE IF EXISTS `credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credentials` (
  `serialNo` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(300) DEFAULT NULL,
  `nric` varchar(9) DEFAULT NULL,
  `email` varchar(300) DEFAULT NULL,
  `dob` varchar(300) DEFAULT NULL,
  `mobile` int(11) DEFAULT NULL,
  `password` varchar(300) DEFAULT NULL,
  `role` varchar(45) DEFAULT 'user',
  `securityQn` varchar(300) DEFAULT NULL,
  `securityAns` varchar(300) DEFAULT NULL,
  `firstLogin` int(11) DEFAULT '1',
  `status` varchar(45) DEFAULT 'unlock',
  PRIMARY KEY (`serialNo`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credentials`
--

LOCK TABLES `credentials` WRITE;
/*!40000 ALTER TABLE `credentials` DISABLE KEYS */;
INSERT INTO `credentials` VALUES (1,'Admin','','admin@optimum.net','21/22/1880',96323291,'iamadmin','admin','','',2,'unlock'),(2,'eugene','S9234282D','eugene@optimum.net','22/02/1990',92293836,'test','user','Favorite color','blue',2,'unlock'),(3,'tom','S3234482E','eugene@gmail.com','24/09/1992',92293836,'eugene','user','favorite book','lordoftherings',2,'unlock'),(21,'lahvin','S9158173O','lahvin_619@live.com','23/07/1992',90258619,'91588619','user',NULL,NULL,1,'unlock');
/*!40000 ALTER TABLE `credentials` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-12 17:58:18
