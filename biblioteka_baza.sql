/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 8.0.39 : Database - library
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `library`;

/*Table structure for table `author` */

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `author` */

insert  into `author`(`id`,`first_name`,`last_name`) values 
(1,'Jakov','Ignjatovic'),
(2,'Perl','Bak'),
(3,'Ernest','Hemingvej'),
(4,'Aristotel',''),
(5,'Imanuel','Kant'),
(6,'Dante','Aligijeri'),
(7,'Stiven','King'),
(8,'Piter','Straub'),
(9,'Platon',''),
(10,'Simo','Matavulj'),
(12,'Rej','Bredberi'),
(14,'Jovan','Sterija Popovic'),
(15,'Petar','Kocic'),
(17,'Sofokle',''),
(18,'Aristofan',''),
(19,'Branislav','Nusic'),
(20,'Gabrijel','Garsija Markes'),
(21,'Marko','Aurelije'),
(22,'Zan','Pol Sartr'),
(23,'Dzordz','Orvel'),
(24,'Euripid','');

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `printing_year` int unsigned DEFAULT NULL,
  `quantity` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `book` */

insert  into `book`(`id`,`title`,`printing_year`,`quantity`) values 
(1,'Za kim zvono zvoni',1961,6),
(2,'Sunce se ponovo radja',2004,1),
(3,'Veciti mladozenja',1895,4),
(4,'Obecanje',1991,1),
(5,'Dobra zemlja',1991,2),
(9,'Gozba',2018,1),
(10,'Metafizika',1979,4),
(11,'Farenhajt 451',2015,8),
(17,'Nikomahova etika',2003,3),
(18,'Kritika cistog uma',1978,4),
(19,'Pokondirena tikva',1989,11),
(21,'Kritika praktickog uma',1990,3),
(22,'Talisman',2019,3),
(25,'Boka i Bokelji',2001,1),
(26,'Antigona',2002,23),
(29,'Kritika moci sudjenja',1974,0),
(31,'Pakao',2001,5),
(32,'Zla zena',1989,9),
(34,'Autobiografija',1946,12),
(35,'Sto godina samoce',2009,6),
(36,'Zao cas',2003,9),
(37,'Meditacije',1899,2),
(41,'Hipolit',1967,0),
(42,'Sumnjivo lice',1987,5),
(43,'Ozaloscena porodica',1934,4),
(44,'1984',1976,14),
(45,'Zivotinjska Farma',2000,0),
(46,'Tvrdica',1953,11);

/*Table structure for table `loan` */

DROP TABLE IF EXISTS `loan`;

CREATE TABLE `loan` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `issuing_date` date NOT NULL,
  `return_date` date NOT NULL,
  `user_profile_id` int unsigned DEFAULT NULL,
  `book_id` int unsigned DEFAULT NULL,
  `valid` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_profile_id` (`user_profile_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`id`),
  CONSTRAINT `loan_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001076 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `loan` */

insert  into `loan`(`id`,`issuing_date`,`return_date`,`user_profile_id`,`book_id`,`valid`) values 
(1000001,'2025-04-26','2025-05-10',100000,1,0),
(1000002,'2025-04-01','2025-04-15',100002,11,0),
(1001012,'2025-03-12','2025-03-26',100004,18,0),
(1001013,'2025-05-03','2025-05-17',100008,37,0),
(1001014,'2025-05-03','2025-05-17',100008,21,0),
(1001015,'2025-05-03','2025-05-17',100002,31,0),
(1001016,'2025-05-03','2025-05-17',100008,5,0),
(1001017,'2025-05-03','2025-05-17',100008,34,0),
(1001018,'2025-05-03','2025-05-17',100008,9,0),
(1001019,'2025-05-03','2025-05-17',100004,11,0),
(1001020,'2025-05-04','2025-05-18',100008,11,0),
(1001021,'2025-05-04','2025-05-18',100010,10,0),
(1001022,'2025-05-05','2025-05-19',100005,9,0),
(1001023,'2025-05-05','2025-05-19',100001,26,0),
(1001024,'2025-05-05','2025-05-19',100008,9,0),
(1001025,'2025-05-05','2025-05-19',100008,26,0),
(1001026,'2025-05-05','2025-05-19',100008,10,0),
(1001027,'2025-05-13','2025-05-27',100008,41,1),
(1001028,'2025-05-14','2025-05-28',100008,10,0),
(1001029,'2025-05-14','2025-05-28',100008,10,0),
(1001030,'2025-05-14','2025-05-28',100008,10,0),
(1001031,'2025-05-14','2025-05-28',100000,26,0),
(1001032,'2025-05-14','2025-05-28',100002,37,0),
(1001033,'2025-05-14','2025-05-28',100004,29,0),
(1001034,'2025-05-16','2025-05-30',100016,32,0),
(1001035,'2025-05-16','2025-05-30',100016,29,0),
(1001036,'2025-05-16','2025-05-30',100016,22,0),
(1001037,'2025-05-16','2025-05-30',100016,36,0),
(1001038,'2025-05-16','2025-05-30',100016,34,0),
(1001039,'2025-05-16','2025-05-30',100000,34,0),
(1001040,'2025-05-16','2025-05-30',100000,18,0),
(1001041,'2025-05-16','2025-05-30',100000,17,1),
(1001042,'2025-05-16','2025-05-30',100000,5,1),
(1001043,'2025-05-16','2025-05-30',100004,45,0),
(1001044,'2025-05-16','2025-05-30',100004,9,0),
(1001045,'2025-05-16','2025-05-30',100002,32,0),
(1001046,'2025-05-16','2025-05-30',100016,46,0),
(1001047,'2025-05-16','2025-05-30',100008,10,1),
(1001048,'2025-05-16','2025-05-30',100002,3,0),
(1001049,'2025-05-16','2025-05-30',100016,3,0),
(1001050,'2025-05-20','2025-06-03',100016,44,0),
(1001051,'2025-05-20','2025-06-03',100016,17,0),
(1001052,'2025-05-20','2025-06-03',100010,17,0),
(1001053,'2025-05-20','2025-06-03',100010,9,0),
(1001054,'2025-05-20','2025-06-03',100010,46,0),
(1001055,'2025-05-20','2025-06-03',100000,46,1),
(1001056,'2025-05-20','2025-06-03',100002,44,0),
(1001057,'2025-05-20','2025-06-03',100016,9,0),
(1001058,'2025-05-20','2025-06-03',100016,29,1),
(1001059,'2025-05-20','2025-06-03',100016,42,1),
(1001060,'2025-05-20','2025-06-03',100004,18,0),
(1001061,'2025-05-20','2025-06-03',100008,29,1),
(1001062,'2025-05-25','2025-06-08',100010,34,0),
(1001063,'2025-05-25','2025-06-08',100010,32,0),
(1001064,'2025-05-27','2025-06-10',100002,26,0),
(1001065,'2025-05-27','2025-06-10',100016,32,1),
(1001066,'2025-05-27','2025-06-10',100010,37,0),
(1001067,'2025-05-27','2025-06-10',100010,44,0),
(1001068,'2025-05-27','2025-06-10',100002,25,0),
(1001069,'2025-05-28','2025-06-11',100004,1,1),
(1001070,'2025-05-28','2025-06-11',100002,31,0),
(1001071,'2025-05-29','2025-06-12',100008,17,1),
(1001072,'2025-05-30','2025-06-13',100008,9,1),
(1001073,'2025-06-01','2025-06-15',100000,45,1),
(1001074,'2025-06-02','2025-06-16',100004,45,1),
(1001075,'2025-06-02','2025-06-16',100010,26,1);

/*Table structure for table `user_profile` */

DROP TABLE IF EXISTS `user_profile`;

CREATE TABLE `user_profile` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(20) NOT NULL,
  `user_role` varchar(20) NOT NULL,
  PRIMARY KEY (`id`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=100019 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_profile` */

insert  into `user_profile`(`id`,`first_name`,`last_name`,`email`,`password`,`user_role`) values 
(100000,'Pera','Peric','pera.peric@gmail.com','1234','USER'),
(100001,'Ana','Anic','ana.anic@gmail.com','1234','ADMIN'),
(100002,'Lazar','Lazic','laza.lazic@yahoo.com','1234','USER'),
(100003,'Jelena','Jelenic','jelena@gmail.com','1234','ADMIN'),
(100004,'Ognjen','Ognjenovic','ognjeni@yahoo.com','123ed','USER'),
(100005,'Mika','Mikic','mika@gmail.com','1234','ADMIN'),
(100008,'Mitar','Visekruna','mitarv@proton.com','1234','USER'),
(100010,'Milos','Bojanovic','mikrobo@proton.com','1234','USER'),
(100016,'Jana','Janic','janajana@yahoo.com','1234','USER');

/*Table structure for table `writing` */

DROP TABLE IF EXISTS `writing`;

CREATE TABLE `writing` (
  `book_id` int unsigned NOT NULL,
  `author_id` int unsigned NOT NULL,
  PRIMARY KEY (`book_id`,`author_id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `writing_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE,
  CONSTRAINT `writing_ibfk_2` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `writing` */

insert  into `writing`(`book_id`,`author_id`) values 
(3,1),
(4,2),
(5,2),
(1,3),
(2,3),
(10,4),
(17,4),
(18,5),
(21,5),
(29,5),
(31,6),
(22,7),
(22,8),
(9,9),
(25,10),
(11,12),
(19,14),
(32,14),
(46,14),
(26,17),
(34,19),
(42,19),
(43,19),
(35,20),
(36,20),
(37,21),
(44,23),
(45,23),
(41,24);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
