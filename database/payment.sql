-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 27, 2022 at 09:41 AM
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
(52, 1, 203000, 'ID7992023', 'Aug 26, 2022 9:39:23 PM'),
(53, 1, 20000, 'ID8902221', '27 Agt 2022 15:11:51'),
(54, 1, 100000, 'ID8902221', '27 Agt 2022 15:12:47'),
(55, 1, 20000, 'IDNF902211', '27 Agt 2022 15:20:29'),
(56, 1, 2000, 'ID8902221', '27 Agt 2022 15:21:10'),
(57, 1, 0, 'ID7992023', 'Aug 27, 2022 2:29:45 PM'),
(58, 1, 50000, 'ID7992023', 'Aug 27, 2022 2:30:02 PM'),
(59, 1, 20222, 'ID7992023', 'Aug 27, 2022 2:30:59 PM'),
(60, 1, 2030, 'ID7992023', 'Aug 27, 2022 2:31:54 PM'),
(61, 1, 1022, 'ID8902221', 'Aug 27, 2022 2:36:45 PM'),
(62, 1, 4055, 'ID8902221', 'Aug 27, 2022 2:39:18 PM');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(6) NOT NULL,
  `username` varchar(80) NOT NULL,
  `password` varchar(300) NOT NULL,
  `balance` int(50) NOT NULL,
  `photo_profile` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `balance`, `photo_profile`) VALUES
(1, 'Kevin Gilbert Toding', '$2y$10$y7H2W9Lm/gx3WWrUrpoDTOy6l9D.ZUw7S/Fk9.Q9YtK1I6y2fDaZS', 1382670, '1.png'),
(4, 'Nella', '$2y$10$w1E0/e8SwLETgBeUGvy7Le1xR5yJIU50SQFbLVp9Zaq0P0ieJU3WG', 3000000, 'nella.png'),
(5, 'eve', '$2y$10$gZu8v./AeNmWvLxWy/ZGveurS0WUgTkDqmu2P0t1W71nsUTjxVikq', 3000000, 'eve.png');

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
  MODIFY `transaction_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
