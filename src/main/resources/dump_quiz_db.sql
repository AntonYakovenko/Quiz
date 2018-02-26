-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quiz_db
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` varchar(256) NOT NULL,
  `correct` tinyint(1) DEFAULT NULL,
  `explanation` varchar(256) DEFAULT NULL,
  `questionId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `questionId` (`questionId`),
  CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`questionId`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` (`id`, `answer`, `correct`, `explanation`, `questionId`) VALUES (1,'Yes',1,NULL,1),(2,'No',0,NULL,1),(3,'Yes',0,NULL,2),(4,'No',1,NULL,2),(5,'Yes',1,NULL,3),(6,'No',0,NULL,3),(7,'Yes',0,NULL,4),(8,'No',1,NULL,4),(9,'Runtime error',0,NULL,4),(10,'Yes, always',0,NULL,5),(11,'Yes, if we have \'finally\'',0,NULL,5),(12,'Yes, in \'try-with-resources\'',1,NULL,5),(13,'No',0,'Are you stupid?',5),(14,'Yes, always',1,NULL,6),(15,'Yes, only if we have \'catch\'',0,'<null>',6),(16,'No',0,NULL,6),(17,'Yes',0,NULL,7),(18,'No',1,NULL,7),(19,'Yes',1,NULL,8),(20,'No',0,NULL,8),(21,'!',0,NULL,9),(22,'!, ++, --, +, -',1,NULL,9),(23,'!, ==, !=',0,NULL,9),(24,'!, ++, --, -',0,NULL,9);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` int(11) NOT NULL,
  `description` varchar(256) NOT NULL,
  `explanation` varchar(256) NOT NULL,
  `quizId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `quizId` (`quizId`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`quizId`) REFERENCES `quizzes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` (`id`, `name`, `description`, `explanation`, `quizId`) VALUES (1,1,'\'for\' - is it a loop?','\'for\' is a loop',1),(2,2,'\'switch\' - is it a loop?','\'switch\' isn\'t a loop',1),(3,1,'Can calculate factorial recursively?','Yes',2),(4,2,'int x = 1; // Is it recursion?','No',2),(5,1,'Can exists \'try\' without \'catch\'','Only in \'try-with-resources\'',3),(6,2,'Can exists \'try\' without \'finally\'?','Yes',3),(7,1,'ClassCastException - checked?','No',4),(8,2,'ClassNotFoundException - checked?','Yes',4),(9,3,'Select unary operators in Java','there is 5: +(implicit), -, ++, --, !',1);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quizzes`
--

DROP TABLE IF EXISTS `quizzes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quizzes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `description` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quizzes`
--

LOCK TABLES `quizzes` WRITE;
/*!40000 ALTER TABLE `quizzes` DISABLE KEYS */;
INSERT INTO `quizzes` (`id`, `name`, `description`) VALUES (1,'Loops','Check your skills in loops'),(2,'Recursion','Check your recursive skills'),(3,'Exceptions control-flow','Check your skills in exceptions'),(4,'Exceptions checked-unchecked','Check your skills in exceptions');
/*!40000 ALTER TABLE `quizzes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `login`, `name`, `password`, `email`) VALUES (1,'Sara','Sara','123','sara@example.com'),(2,'Mike','Mike','123','mike@example.com'),(3,'Anton','Anton','123','anton@example.com'),(4,'Artem','Artem','123','artem@example.com'),(5,'testUser','I\'m test','123','test@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-21 18:04:27
