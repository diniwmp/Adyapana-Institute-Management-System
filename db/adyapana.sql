-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: adyapana
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `email` varchar(100) NOT NULL,
  `admin_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('dini@gmail.com','Dini','1234');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `maked_at` datetime NOT NULL,
  `students_s_no` int NOT NULL,
  `attendance_status_id` int NOT NULL,
  `classes_class_no` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_attendance_students1_idx` (`students_s_no`),
  KEY `fk_attendance_attendance_status1_idx` (`attendance_status_id`),
  KEY `fk_attendance_classes1_idx` (`classes_class_no`),
  CONSTRAINT `fk_attendance_attendance_status1` FOREIGN KEY (`attendance_status_id`) REFERENCES `attendance_status` (`id`),
  CONSTRAINT `fk_attendance_classes1` FOREIGN KEY (`classes_class_no`) REFERENCES `classes` (`class_no`),
  CONSTRAINT `fk_attendance_students1` FOREIGN KEY (`students_s_no`) REFERENCES `students` (`s_no`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (1,'2025-01-18 16:27:40',1,1,1),(2,'2025-01-18 17:59:18',6,1,3),(3,'2025-01-20 00:48:08',3,1,2);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance_status`
--

DROP TABLE IF EXISTS `attendance_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance_status`
--

LOCK TABLES `attendance_status` WRITE;
/*!40000 ALTER TABLE `attendance_status` DISABLE KEYS */;
INSERT INTO `attendance_status` VALUES (1,'Present'),(2,'Absent');
/*!40000 ALTER TABLE `attendance_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `class_no` int NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `subjects_sub_no` int NOT NULL,
  `teachers_t_no` int NOT NULL,
  `start_time` varchar(20) NOT NULL,
  `end_time` varchar(20) NOT NULL,
  `is_active` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`class_no`),
  KEY `fk_classes_subjects_idx` (`subjects_sub_no`),
  KEY `fk_classes_teachers1_idx` (`teachers_t_no`),
  CONSTRAINT `fk_classes_subjects` FOREIGN KEY (`subjects_sub_no`) REFERENCES `subjects` (`sub_no`),
  CONSTRAINT `fk_classes_teachers1` FOREIGN KEY (`teachers_t_no`) REFERENCES `teachers` (`t_no`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,'Mathematics-Mahesh',3,1,'Tuesday 5.30','Tuesday 7.30',1),(2,'English-Kavindu',3,1,'Monday 12PM','Monday 4PM',1),(3,'Chemistry - Thilina',2,4,'Friday 7PM','Friday 12PM',1),(4,'Physics - Rushani',4,2,'Wednesday 1PM','Wednesday 6PM',1);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoices`
--

DROP TABLE IF EXISTS `invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoices` (
  `invoice_id` int NOT NULL AUTO_INCREMENT,
  `yr` varchar(4) NOT NULL,
  `month` varchar(3) NOT NULL,
  `created_at` datetime NOT NULL,
  `students_s_no` int NOT NULL,
  `value` double NOT NULL,
  `classes_class_no` int NOT NULL,
  PRIMARY KEY (`invoice_id`),
  KEY `fk_invoices_students1_idx` (`students_s_no`),
  KEY `fk_invoices_classes1_idx` (`classes_class_no`),
  CONSTRAINT `fk_invoices_classes1` FOREIGN KEY (`classes_class_no`) REFERENCES `classes` (`class_no`),
  CONSTRAINT `fk_invoices_students1` FOREIGN KEY (`students_s_no`) REFERENCES `students` (`s_no`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoices`
--

LOCK TABLES `invoices` WRITE;
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
INSERT INTO `invoices` VALUES (1,'2025','01','2025-01-14 00:00:00',2,2000,2),(2,'2025','01','2025-01-14 19:53:09',1,2000,1),(3,'2025','01','2025-01-31 00:00:00',5,2000,2),(4,'2025','01','2025-02-01 00:00:00',3,2000,3),(5,'2025','01','2025-01-20 00:00:00',1,2000,2),(6,'2025','01','2025-01-20 00:00:00',6,2000,4),(7,'2025','01','2025-01-20 00:00:00',1,2000,4),(8,'2025','01','2025-01-20 00:00:00',3,2000,2),(9,'2025','01','2025-01-20 00:00:00',7,2000,2),(10,'2025','01','2025-01-20 00:00:00',2,2000,3),(17,'2025','01','2025-01-21 00:00:00',5,2000,3);
/*!40000 ALTER TABLE `invoices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `s_no` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `address` varchar(100) NOT NULL,
  `dob` date NOT NULL,
  `is_active` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`s_no`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'Chamodi','Wennappuwa','2004-02-25',1),(2,'Pasindu','Polonnaruwa','2002-01-04',1),(3,'Sachithra','Dankotuwa','2003-01-03',1),(5,'Rushira  ','Colombo 7','2003-01-21',1),(6,'Kaveesha','Panadura','2003-03-12',1),(7,'Pavithra','Kandy','2003-01-17',1);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjects` (
  `sub_no` int NOT NULL AUTO_INCREMENT,
  `description` varchar(60) NOT NULL,
  `price` double NOT NULL,
  `is_active` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`sub_no`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` VALUES (1,'Mathematics',2000,1),(2,'Chemistry',2000,1),(3,'English',2000,1),(4,'Physics',2000,1),(5,'Biology',2000,1),(6,'Anatomy',2000,1);
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachers` (
  `t_no` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `address` varchar(100) NOT NULL,
  `is_active` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`t_no`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES (1,'Mahesh','Monaragala',1),(2,'Rushani','Mathara',1),(3,'Kavindu','Nugegoda',1),(4,'Thilina','Kandy',1),(5,'Malindu','Ella',1),(6,'Pavindi','Ella',1);
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-21 20:58:01
