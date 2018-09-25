-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.22-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema student
--

CREATE DATABASE IF NOT EXISTS student;
USE student;

--
-- Definition of table `fee_student`
--

DROP TABLE IF EXISTS `fee_student`;
CREATE TABLE `fee_student` (
  `ROLLNO` int(10) unsigned NOT NULL auto_increment,
  `NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `SEX` varchar(45) NOT NULL,
  `COURSE` varchar(45) NOT NULL,
  `FEE` int(10) unsigned NOT NULL,
  `PAID` int(10) unsigned NOT NULL,
  `DUE` int(10) unsigned NOT NULL,
  `ADDRESS` varchar(45) NOT NULL,
  `CONTACT` varchar(45) NOT NULL,
  PRIMARY KEY  USING BTREE (`ROLLNO`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `fee_student`
--

/*!40000 ALTER TABLE `fee_student` DISABLE KEYS */;
INSERT INTO `fee_student` (`ROLLNO`,`NAME`,`EMAIL`,`SEX`,`COURSE`,`FEE`,`PAID`,`DUE`,`ADDRESS`,`CONTACT`) VALUES 
 (1,'kamal','kamalparvez71@yahoo.com','male','Java',100000,100000,0,'DHAKA','01703777773'),
 (2,'Harun','hrcombination@gmail.com','male','Java',1000000,500000,500000,'UTARA','0124569566'),
 (4,'Rafi','rafi@gmail.com','male','Java',50000,30000,20000,'Azimpur','01785693524'),
 (5,'Ayesha','ayesha@gmail.com','female','Java',100000,100000,0,'Jigatola','017185555248');
/*!40000 ALTER TABLE `fee_student` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
