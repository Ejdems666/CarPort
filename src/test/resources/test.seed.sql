-- MySQL dump 10.13  Distrib 5.6.31, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: cba_carport_new
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
-- Dumping data for table `assembly_material`
--

INSERT INTO `assembly_material` (`id`, `name`, `price`, `stock`) VALUES (1,'screw',1,1000);

--
-- Dumping data for table `carport`
--

INSERT INTO `carport` (`id`, `name`, `default_price`, `profit_from_materials`, `default_width`, `thumbnail_id`, `frame_id`, `roof_tile_id`, `default_length`, `description`) VALUES (1,'carport1',10000,50,400,2,1,1,800,'desc');

--
-- Dumping data for table `frame`
--

INSERT INTO `frame` (`id`, `upper_pillar_material_id`, `lower_pillar_material_id`, `roof_plank_material_id`, `vertical_pillar_material_id`, `vertical_pillar_distance`, `roof_plank_distance`) VALUES (1,1,2,4,4,200,50),(2,1,2,4,4,200,50);

--
-- Dumping data for table `material`
--

INSERT INTO `material` (`id`, `name`, `width`, `height`) VALUES (1,'upper frame pillar',30,5),(2,'lower frame pillar',30,30),(3,'holding vertical pillar',35,35),(4,'roof plank',10,5);

--
-- Dumping data for table `material_dependency`
--

INSERT INTO `material_dependency` (`id`, `material_id`, `assembly_material_id`, `amount_per_unit`) VALUES (1,3,1,5);

--
-- Dumping data for table `material_length`
--

INSERT INTO `material_length` (`id`, `material_id`, `length`, `price`, `stock`) VALUES (1,1,800,100,100),(2,2,800,100,100),(3,3,250,100,100),(4,4,400,100,100),(12,1,400,100,100);

--
-- Dumping data for table `picture`
--

INSERT INTO `picture` (`id`, `url`, `carport_id`) VALUES (2,'carport1.jpeg',NULL);

--
-- Dumping data for table `roof_tile`
--

INSERT INTO `roof_tile` (`id`, `name`, `width`, `length`, `price`, `stock`) VALUES (1,'basic tile',50,50,10,1000);

--
-- Dumping data for table `user`
--

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-14 18:56:09
