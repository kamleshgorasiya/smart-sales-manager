-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 12, 2019 at 02:06 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smart_sales_manager`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(128) NOT NULL,
  `city` varchar(28) NOT NULL,
  `country` varchar(28) NOT NULL,
  `created` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `lat` varchar(14) NOT NULL,
  `longi` varchar(14) NOT NULL,
  `name` varchar(64) NOT NULL,
  `state` varchar(28) NOT NULL,
  `updated` bigint(20) DEFAULT NULL,
  `zipcode` varchar(8) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKda8tuywtf0gb6sedwk7la1pgi` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bookable`
--

DROP TABLE IF EXISTS `bookable`;
CREATE TABLE IF NOT EXISTS `bookable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `allowed_home_service` bit(1) DEFAULT NULL,
  `approx_time` int(11) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  `delivery_distance` int(11) DEFAULT NULL,
  `description` varchar(512) NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_allow_advance_booking` bit(1) DEFAULT NULL,
  `is_allow_to_book_appointment` bit(1) DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `service_charge` int(11) DEFAULT NULL,
  `service_tags` varchar(255) DEFAULT NULL,
  `title` varchar(128) NOT NULL,
  `updated` bigint(20) DEFAULT NULL,
  `business_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrpvfadgla0nlb5dujfj6s72xf` (`business_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookable`
--

INSERT INTO `bookable` (`id`, `allowed_home_service`, `approx_time`, `created`, `delivery_distance`, `description`, `is_active`, `is_allow_advance_booking`, `is_allow_to_book_appointment`, `name`, `service_charge`, `service_tags`, `title`, `updated`, `business_id`) VALUES
(1, b'0', 0, 1552393445290, 0, 'Shree Hans Export engaged with buy and sell diamonds', b'0', b'0', b'0', 'Shree Hans Export', 0, NULL, 'Faith in business', 0, 1),
(2, b'0', 0, 1552394118384, 0, 'Shree Hans Export engaged with buy and sell diamonds', b'0', b'0', b'0', 'Shree Hans Export', 0, NULL, 'Faith in business', 0, 1),
(3, b'0', 0, 1552394245623, 0, 'Shree Hans Export engaged with buy and sell diamonds', b'0', b'0', b'0', 'Shree Hans Export', 0, NULL, 'Faith in business', 0, 1),
(4, b'0', 0, 1552394586959, 0, 'Shree Hans Export engaged with buy and sell diamonds', b'0', b'0', b'0', 'Shree Hans Export', 0, NULL, 'Faith in business', 0, 1),
(5, b'0', 0, 1552394647808, 0, 'Shree Hans Export engaged with buy and sell diamonds', b'0', b'0', b'0', 'Shree Hans Export', 0, NULL, 'Faith in business', 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
CREATE TABLE IF NOT EXISTS `booking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `booking_status` varchar(255) DEFAULT NULL,
  `booking_time` bigint(20) NOT NULL,
  `created` bigint(20) DEFAULT NULL,
  `is_confirmed_by_employee` bit(1) DEFAULT NULL,
  `is_confirmed_by_user` bit(1) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `updated` bigint(20) DEFAULT NULL,
  `bookable_id` bigint(20) NOT NULL,
  `business_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtbhx4q4q4m1kv4muhynct3p81` (`bookable_id`),
  KEY `FKobmwu8k3vy9wipq6ts6hteggf` (`business_id`),
  KEY `FKn8ne879x0nwnn2uk9khsavonl` (`employee_id`),
  KEY `FKkgseyy7t56x7lkjgu3wah5s3t` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `business`
--

DROP TABLE IF EXISTS `business`;
CREATE TABLE IF NOT EXISTS `business` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(128) NOT NULL,
  `allowed_delivery` bit(1) DEFAULT NULL,
  `allowed_online_order` bit(1) DEFAULT NULL,
  `business_category` varchar(255) DEFAULT NULL,
  `business_tags` varchar(255) DEFAULT NULL,
  `city` varchar(28) NOT NULL,
  `country` varchar(28) NOT NULL,
  `created` bigint(20) DEFAULT NULL,
  `delivery_distance` int(11) DEFAULT NULL,
  `description` varchar(512) NOT NULL,
  `email` varchar(64) NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_advance_appointment_booking` bit(1) DEFAULT NULL,
  `is_book_appointment` bit(1) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `is_on` bit(1) DEFAULT NULL,
  `lat` varchar(14) NOT NULL,
  `longi` varchar(14) NOT NULL,
  `min_order_amount` int(11) DEFAULT NULL,
  `mobile` varchar(14) NOT NULL,
  `name` varchar(64) NOT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `state` varchar(28) NOT NULL,
  `title` varchar(128) NOT NULL,
  `updated` bigint(20) DEFAULT NULL,
  `zipcode` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ktb1t97n9aeupitfjsdpjcmuq` (`email`),
  UNIQUE KEY `UK_5xifx1oqkshgkova05tgsjy1k` (`mobile`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `business`
--

INSERT INTO `business` (`id`, `address`, `allowed_delivery`, `allowed_online_order`, `business_category`, `business_tags`, `city`, `country`, `created`, `delivery_distance`, `description`, `email`, `is_active`, `is_advance_appointment_booking`, `is_book_appointment`, `is_deleted`, `is_on`, `lat`, `longi`, `min_order_amount`, `mobile`, `name`, `owner_id`, `state`, `title`, `updated`, `zipcode`) VALUES
(1, 'Shop No : 2 Dhaduk nagar, varachha', b'1', b'1', 'Jwellary', 'diamond, jwellary ', 'surat', 'inidia', 0, 10, 'Shree Hans Export engaged with buy and sell diamonds', 'kamlesh@gmail.com', b'1', b'1', b'1', b'0', b'0', '0.124564', '0.124564', 100000, '919227350852', 'Shree Hans Export', 1, 'gujarat', 'Faith in business', 1552392852102, '145805'),
(2, 'Shop No : 2 Dhaduk nagar, varachha', b'1', b'1', 'Jwellary', 'diamond, jwellary ', 'surat', 'inidia', 1552392872663, 10, 'Shree Hans Export engaged with buy and sell diamonds', 'kamlesh@gmail.com1', b'1', b'1', b'1', b'0', b'0', '0.124564', '0.124564', 100000, '915227350852', 'Shree Hans Export', 1, 'gujarat', 'Faith in business', 0, '145805'),
(3, 'Shop No : 2 Dhaduk nagar, varachha', b'1', b'1', 'Jwellary', 'diamond, jwellary ', 'surat', 'inidia', 1552395189398, 10, 'Shree Hans Export engaged with buy and sell diamonds', 'kamleshs@gmail.com1', b'1', b'1', b'1', b'0', b'0', '0.124564', '0.124564', 100000, '915d227350852', 'Shree Hans Export', 1, 'gujarat', 'Faith in business', 0, '145805'),
(4, 'Shop No : 2 Dhaduk nagar, varachha', b'1', b'1', 'Jwellary', 'diamond, jwellary ', 'surat', 'inidia', 1552395318923, 10, 'Shree Hans Export engaged with buy and sell diamonds', 'kamleshgo@gmail.com1', b'1', b'1', b'1', b'0', b'0', '0.124564', '0.124564', 100000, '91527350852', 'Shree Hans Export', 1, 'gujarat', 'Faith in business', 0, '145805'),
(5, 'Shop No : 2 Dhaduk nagar, varachha', b'1', b'1', 'Jwellary', 'diamond, jwellary ', 'surat', 'inidia', 1552395376422, 10, 'Shree Hans Export engaged with buy and sell diamonds', 'kamleshg@gmail.com1', b'1', b'1', b'1', b'0', b'0', '0.124564', '0.124564', 100000, '915273508522', 'Shree Hans Export', 1, 'gujarat', 'Faith in business', 0, '145805'),
(6, 'Shop No : 2 Dhaduk nagar, varachha', b'1', b'1', 'Jwellary', 'diamond, jwellary ', 'surat', 'inidia', 1552395417568, 10, 'Shree Hans Export engaged with buy and sell diamonds', 'kamleshg@gmail.com', b'1', b'1', b'1', b'0', b'0', '0.124564', '0.124564', 100000, '915273508782', 'Shree Hans Export', 1, 'gujarat', 'Faith in business', 0, '145805');

-- --------------------------------------------------------

--
-- Table structure for table `business_category`
--

DROP TABLE IF EXISTS `business_category`;
CREATE TABLE IF NOT EXISTS `business_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL,
  `created` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5d5glyfb06ejpo3680am11qbr` (`category_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `business_id` bigint(20) NOT NULL,
  `created` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `updated` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
CREATE TABLE IF NOT EXISTS `password_reset_token` (
  `id` bigint(20) NOT NULL,
  `expiry_date` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g0guo4k8krgpwuagos61oc06j` (`token`),
  KEY `FK5lwtbncug84d4ero33v3cfxvl` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `description`, `name`) VALUES
(1, NULL, 'ADMIN'),
(2, NULL, 'USER'),
(3, NULL, 'EMPLOYEE');

-- --------------------------------------------------------

--
-- Table structure for table `specialist`
--

DROP TABLE IF EXISTS `specialist`;
CREATE TABLE IF NOT EXISTS `specialist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bookable_id` bigint(20) NOT NULL,
  `created` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `updated` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `created` bigint(20) DEFAULT NULL,
  `email` varchar(64) NOT NULL,
  `first_name` varchar(64) NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_email_verified` bit(1) DEFAULT NULL,
  `is_mobile_verified` bit(1) DEFAULT NULL,
  `last_name` varchar(64) NOT NULL,
  `mobile` varchar(14) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salary` bigint(20) NOT NULL,
  `updated` bigint(20) DEFAULT NULL,
  `username` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_cnjwxx5favk5ycqajjt17fwy1` (`mobile`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `age`, `created`, `email`, `first_name`, `is_deleted`, `is_active`, `is_email_verified`, `is_mobile_verified`, `last_name`, `mobile`, `password`, `salary`, `updated`, `username`) VALUES
(1, 30, 1552388819688, 'kamlesh@gmail.com', 'Kamlesh', b'0', b'1', b'0', b'0', 'Gorasiya', '919227350852', '$2a$10$eAGS6s5yLLq3JF599Bb16O3eSJn6Dp/2WuwLIXf5XrMgIrxKP1yH2', 16000, 0, 'admin11');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`),
  KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 2);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
