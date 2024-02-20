-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bookmyride
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
-- Table structure for table `car_type`
--

DROP TABLE IF EXISTS `car_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_type` (
  `CAR_TYPE_ID` int NOT NULL AUTO_INCREMENT,
  `CAR_TYPE` varchar(45) NOT NULL,
  PRIMARY KEY (`CAR_TYPE_ID`),
  UNIQUE KEY `CAR_TYPE_UNIQUE` (`CAR_TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `declined_rides`
--

DROP TABLE IF EXISTS `declined_rides`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `declined_rides` (
  `DECLINED_RIDE_ID` int NOT NULL AUTO_INCREMENT,
  `DRIVER_ID` int NOT NULL,
  `RIDE_ID` int NOT NULL,
  PRIMARY KEY (`DECLINED_RIDE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driver` (
  `DRIVER_ID` int NOT NULL AUTO_INCREMENT,
  `USER_ID` int NOT NULL,
  `CAR_TYPE_ID` int NOT NULL,
  `CAR_MODEL` varchar(45) NOT NULL,
  `CAR_REG_NO` varchar(45) NOT NULL,
  `ACTIVATION_STATUS` enum('ACTIVE','DISABLED') NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`DRIVER_ID`),
  KEY `CARTYPE_DRIVER_idx` (`CAR_TYPE_ID`),
  KEY `USER_DRIVER_idx` (`USER_ID`),
  CONSTRAINT `CARTYPE_DRIVER` FOREIGN KEY (`CAR_TYPE_ID`) REFERENCES `car_type` (`CAR_TYPE_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `USER_DRIVER` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ride_bookings`
--

DROP TABLE IF EXISTS `ride_bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ride_bookings` (
  `RIDE_ID` int NOT NULL AUTO_INCREMENT,
  `USER_ID` int NOT NULL,
  `CAR_TYPE_ID` int NOT NULL,
  `NO_OF_HOURS` int NOT NULL,
  `CHARGES` int NOT NULL,
  `PICKUP_LOCATION` varchar(100) DEFAULT NULL,
  `PICKUP_ADDRESS` varchar(100) DEFAULT NULL,
  `PICKUP_TIME` datetime NOT NULL,
  `BOOKING_TIME` datetime NOT NULL,
  `BOOKING_STATUS` enum('PENDING','CONFIRMED','CANCELLED') NOT NULL,
  `DRIVER_ID` int DEFAULT NULL,
  `PAYMENT_STATUS` enum('PAID','NOT_PAID') NOT NULL,
  `PAYMENT_METHOD` enum('CASH','ONLINE') NOT NULL,
  PRIMARY KEY (`RIDE_ID`),
  KEY `USER_RIDEBOOKING_idx` (`USER_ID`),
  KEY `CARTYPE_RIDEBOOKING_idx` (`CAR_TYPE_ID`),
  KEY `DRIVER_RIDEBOOKING_idx` (`DRIVER_ID`),
  CONSTRAINT `CARTYPE_RIDEBOOKING` FOREIGN KEY (`CAR_TYPE_ID`) REFERENCES `car_type` (`CAR_TYPE_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `DRIVER_RIDEBOOKING` FOREIGN KEY (`DRIVER_ID`) REFERENCES `driver` (`DRIVER_ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `USER_RIDEBOOKING` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `USER_ID` int NOT NULL AUTO_INCREMENT,
  `EMAIL_ID` varchar(30) NOT NULL,
  `NAME` varchar(30) NOT NULL,
  `CONTACT_NO` bigint NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `ROLE` enum('USER','DRIVER','ADMIN') NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `EMAIL_ID_UNIQUE` (`EMAIL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-20 23:49:06
