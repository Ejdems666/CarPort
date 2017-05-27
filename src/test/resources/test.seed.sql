-- MySQL dump 10.13  Distrib 5.6.31, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: cba_carport
-- ------------------------------------------------------
-- Server version	5.6.31

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
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `surname`, `created_at`, `status`, `type`, `email`, `password`, `salt`) VALUES (1,'Adam','Becvar','2017-05-16',1,1,'becvar.adam666@gmail.com','6a3fc5ba8d76615a2065f1c63ca2287949b78e9116e42bb1a6aa3c415e79726a','320927329287754881270722666944861037179');

--
-- Dumping data for table `material`
--

INSERT INTO `material` (`id`, `width`, `height`, `name`, `description`) VALUES (1,5,30,'upper frame pillar',''),(2,30,30,'lower frame pillar',''),(3,20,20,'holding vertical pillar',''),(4,10,5,'roof plank','');

--
-- Dumping data for table `picture`
--

INSERT INTO `picture` (`id`, `url`, `carport_id`) VALUES (2,'carport1.jpeg',NULL);

--
-- Dumping data for table `carport`
--

INSERT INTO `carport` (`id`, `name`, `default_price`, `profit_from_materials`, `default_width`, `default_length`, `description`, `thumbnail_id`, `frame_id`, `roof_tile_id`) VALUES (1,'carport1',10000,50,400,800,'desc',2,1,1);

--
-- Dumping data for table `material_length`
--

INSERT INTO `material_length` (`id`, `length`, `price`, `stock`, `material_id`) VALUES (1,800,100,100,1),(2,800,100,100,2),(3,300,100,100,3),(4,400,100,100,4),(12,400,100,100,1);

--
-- Dumping data for table `material_dependency`
--

INSERT INTO `material_dependency` (`id`, `material_id`, `assembly_material_id`, `amount_per_unit`) VALUES (1,3,1,5),(2,1,1,2),(3,2,2,10);

--
-- Dumping data for table `frame`
--

INSERT INTO `frame` (`id`, `upper_pillar_material_id`, `lower_pillar_material_id`, `vertical_pillar_material_id`, `roof_plank_material_id`, `vertical_pillar_distance`, `roof_plank_distance`, `vertical_pillar_front_reserve`, `vertical_pillar_back_reserve`) VALUES (1,1,2,3,4,310,50,100,130),(2,1,2,3,4,200,50,120,130);

--
-- Dumping data for table `purchase`
--

INSERT INTO `purchase` (`id`, `final_price`, `ordered_on`, `customer_id`) VALUES (2,12174,'2017-05-24',NULL);

--
-- Dumping data for table `roof_tile_dependency`
--


--
-- Dumping data for table `roof_tile`
--

INSERT INTO `roof_tile` (`id`, `name`, `width`, `width_overlap`, `length_overlap`, `length`, `price`, `stock`, `description`) VALUES (1,'basic tile',50,0,0,50,10,1000,'');

--
-- Dumping data for table `purchase_carport`
--

INSERT INTO `purchase_carport` (`id`, `carport_id`, `frame_width`, `frame_length`, `purchase_id`, `price`, `pdf_catalogue`) VALUES (1,1,400,800,2,6087,NULL),(2,1,400,800,2,6087,NULL);

--
-- Dumping data for table `assembly_material`
--

INSERT INTO `assembly_material` (`id`, `name`, `price`, `stock`, `description`) VALUES (1,'screw',1,1000,'screw desc'),(2,'different screw',2,1000,'diff s desc'),(3,'new screw',1,10000,'some normal description');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-26  1:07:38
