-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 26, 2022 at 05:09 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `payment`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_id` varchar(10) NOT NULL,
  `name` varchar(300) NOT NULL,
  `package_name` varchar(300) NOT NULL,
  `image` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `name`, `package_name`, `image`) VALUES
('ID68372D', 'Dribble', 'Dribble Pro', 'dribble.png'),
('ID7992023', 'Spotify', 'Spotify Premium', 'spotify.png'),
('ID8902221', 'YouTube', 'Youtube Premium', 'youtube.png'),
('IDNF902211', 'Netflix', 'Netflix Premium', 'netflix.png');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` int(6) NOT NULL,
  `user_id` int(6) NOT NULL,
  `total` int(50) NOT NULL,
  `product_id` varchar(20) NOT NULL,
  `date` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `user_id`, `total`, `product_id`, `date`) VALUES
(38, 1, 20000, 'ID68372D', '26 Agt 2022 20:36:13'),
(39, 1, 30010, 'ID68372D', '26 Agt 2022 20:36:39'),
(40, 1, 20088, 'ID68372D', '26 Agt 2022 20:37:07'),
(41, 1, 20000, 'IDNF902211', '26 Agt 2022 20:37:31'),
(42, 1, 2000, 'IDNF902211', '26 Agt 2022 20:37:57'),
(46, 1, 20000, 'ID7992023', '26 Agt 2022 20:51:11'),
(47, 1, 200000, 'ID7992023', '26 Agt 2022 20:55:21'),
(48, 1, 200000, 'ID7992023', '26 Agt 2022 20:57:42'),
(49, 1, 50555, 'ID7992023', '26 Agt 2022 20:58:57'),
(50, 1, 50000, 'ID7992023', '26 Agt 2022 21:00:19'),
(51, 1, 50000, 'ID7992023', '26 Agt 2022 21:02:32'),
(52, 1, 203000, 'ID7992023', 'Aug 26, 2022 9:39:23 PM');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(6) NOT NULL,
  `username` varchar(80) NOT NULL,
  `balance` int(50) NOT NULL,
  `photo_profile` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `balance`, `photo_profile`) VALUES
(1, 'Kevin Gilbert Toding', 1601999, '1.png'),
(3, 'Kevin Gilbert', 290000, '2.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
