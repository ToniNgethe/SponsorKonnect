-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 29, 2017 at 06:30 PM
-- Server version: 10.1.22-MariaDB
-- PHP Version: 7.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `konnect`
--

-- --------------------------------------------------------

--
-- Table structure for table `accountant`
--

CREATE TABLE `accountant` (
  `id` int(11) NOT NULL,
  `name` varchar(400) NOT NULL,
  `number` varchar(400) NOT NULL,
  `email` varchar(400) NOT NULL,
  `location` varchar(500) NOT NULL,
  `password` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accountant`
--

INSERT INTO `accountant` (`id`, `name`, `number`, `email`, `location`, `password`) VALUES
(1, 'Tonii', '0721827382', 'toni@gmail.com', 'nairobi', 'tonitoni'),
(2, 'GRace', '2132', 'dd@gmail.com', 'dfsidfnk', 'djfnsdjkfn');

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `name` varchar(300) DEFAULT NULL,
  `number` varchar(400) DEFAULT NULL,
  `email` varchar(200) NOT NULL,
  `location` varchar(400) DEFAULT NULL,
  `password` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `number`, `email`, `location`, `password`) VALUES
(1, 'admin', '0721827382', 'admin@gmail.com', 'admin', 'adminadmin'),
(2, 'grace', '0708343173', 'grace@gmail.com', 'nakuru', 'nakuru');

-- --------------------------------------------------------

--
-- Table structure for table `school`
--

CREATE TABLE `school` (
  `id` int(11) NOT NULL,
  `name` varchar(500) NOT NULL,
  `mode` varchar(500) NOT NULL,
  `means` varchar(500) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `school`
--

INSERT INTO `school` (`id`, `name`, `mode`, `means`, `date`) VALUES
(4, 'St Flevian Academy', 'private', 'Boarding', '2017-05-24'),
(5, 'Baricho Boys High School', 'private', 'Day&Boarding', '2017-05-24');

-- --------------------------------------------------------

--
-- Table structure for table `school_fees`
--

CREATE TABLE `school_fees` (
  `id` int(11) NOT NULL,
  `school` varchar(500) NOT NULL,
  `first_term` double NOT NULL,
  `second_term` double NOT NULL,
  `third_term` double NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `selected_school`
--

CREATE TABLE `selected_school` (
  `id` int(11) NOT NULL,
  `name` varchar(500) NOT NULL,
  `student` varchar(400) NOT NULL,
  `class` varchar(500) NOT NULL,
  `reg` varchar(500) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `SocialVisits`
--

CREATE TABLE `SocialVisits` (
  `id` int(11) NOT NULL,
  `stud_id` varchar(400) NOT NULL,
  `social_worker` varchar(400) NOT NULL,
  `background` varchar(1500) NOT NULL,
  `composition` varchar(1500) NOT NULL,
  `ethnicity` varchar(1500) NOT NULL,
  `health` varchar(1500) NOT NULL,
  `date` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SocialVisits`
--

INSERT INTO `SocialVisits` (`id`, `stud_id`, `social_worker`, `background`, `composition`, `ethnicity`, `health`, `date`) VALUES
(1, '3', 'f@gmail.com', 'Relevance:  the information included in the report should have a clear connection to the client’s presenting concern and/or the reason the social worker and agency are involved with the client.\r\nFocus on client strengths:  avoid preoccupation with pathology and family disorganization, personal weakness, and limitation.  Focus on what the client/family can do.  Successful intervention is built on client strength; the social assessment report must identify these strengths', 'Confidentiality and client access:  respect the client’s privacy.  Assume that the client may want to read the report and has a right to do so.  Do not include information that you would not want the client or family (or their lawyer) to read.\r\nObjectivity: select words that express your observations in an accurate and nonjudgmental manner. Do not present an opinion as if it were a fact.  Support your conclusions with data.', 'Confidentiality and client access:  respect the client’s privacy.  Assume that the client may want to read the report and has a right to do so.  Do not include information that you would not want the client or family (or their lawyer) to read.\r\nObjectivity: select words that express your observations in an accurate and nonjudgmental manner. Do not present an opinion as if it were a fact.  Support your conclusions with data', 'A social assessment report (often called a social history) focuses on and describes the social aspects of the clients functioning and their situation.\r\nSocial workers are particularly concerned about the match between client needs and the resources available to meet those needs', '');

-- --------------------------------------------------------

--
-- Table structure for table `social_worker`
--

CREATE TABLE `social_worker` (
  `id` int(11) NOT NULL,
  `name` varchar(400) NOT NULL,
  `number` varchar(400) NOT NULL,
  `email` varchar(490) NOT NULL,
  `location` varchar(400) NOT NULL,
  `password` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `social_worker`
--

INSERT INTO `social_worker` (`id`, `name`, `number`, `email`, `location`, `password`) VALUES
(1, 'faith', '073232323', 'f@gmail.com', 'nakuru', 'faithfaith'),
(2, 'Ken', '07228122', 'ken@gmail.com', 'nai', 'ken');

-- --------------------------------------------------------

--
-- Table structure for table `Sponsor`
--

CREATE TABLE `Sponsor` (
  `id` int(11) NOT NULL,
  `sponsor_id` varchar(500) NOT NULL,
  `name` varchar(500) NOT NULL,
  `mobile` varchar(500) NOT NULL,
  `email` varchar(500) NOT NULL,
  `means` varchar(500) NOT NULL,
  `type` varchar(500) NOT NULL,
  `company` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Sponsor`
--

INSERT INTO `Sponsor` (`id`, `sponsor_id`, `name`, `mobile`, `email`, `means`, `type`, `company`) VALUES
(34, 'qwer', 'qwqwq', '232', 'ds@gmail.com', 'email', 'primary', 'skdmk'),
(35, 'toni', 'SDJBHADJHBSDJH', '76372', 'D@gmail.com', 'phonenumber', 'secondary', 'fdksjhfjdfs');

-- --------------------------------------------------------

--
-- Table structure for table `sponsor_commits`
--

CREATE TABLE `sponsor_commits` (
  `id` int(11) NOT NULL,
  `sponsor_id` varchar(300) NOT NULL,
  `amount` double NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sponsor_commits`
--

INSERT INTO `sponsor_commits` (`id`, `sponsor_id`, `amount`, `date`) VALUES
(1, 'toni', 40000, '2017-05-13'),
(2, 'toni', 4300, '2017-05-13'),
(3, 'qwer', 5000, '2017-05-13'),
(4, 'toni', 1000, '2017-05-13'),
(5, 'qwer', 60000, '2017-05-13'),
(6, 'qwer', 60000, '2017-05-13'),
(7, 'qwer', 60000, '2017-05-13'),
(8, 'qwer', 60000, '2017-05-13'),
(9, 'toni', 5000, '2017-05-14');

-- --------------------------------------------------------

--
-- Table structure for table `sponsor_payments`
--

CREATE TABLE `sponsor_payments` (
  `id` int(11) NOT NULL,
  `sponsor_id` varchar(400) NOT NULL,
  `amount` double NOT NULL,
  `date` date NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sponsor_payments`
--

INSERT INTO `sponsor_payments` (`id`, `sponsor_id`, `amount`, `date`, `type`) VALUES
(4, 'toni', 50000, '2017-05-14', 0),
(6, 'toni', 300, '2017-05-14', 0),
(7, 'toni', 500, '2017-05-14', 1),
(8, 'qwer', 5000, '2017-05-16', 0);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(500) NOT NULL,
  `date` date NOT NULL,
  `image` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--


-- --------------------------------------------------------

--
-- Table structure for table `student_gurdian`
--

CREATE TABLE `student_gurdian` (
  `id` int(11) NOT NULL,
  `stud_id` varchar(100) NOT NULL,
  `name` varchar(400) NOT NULL,
  `occup` varchar(300) NOT NULL,
  `mobile` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_gurdian`
--

INSERT INTO `student_gurdian` (`id`, `stud_id`, `name`, `occup`, `mobile`) VALUES
(2, '2', 'Ngthe', 'Carpenter', '072198773');

-- --------------------------------------------------------

--
-- Table structure for table `student_parents`
--

CREATE TABLE `student_parents` (
  `id` int(11) NOT NULL,
  `stud_id` varchar(100) NOT NULL,
  `f_name` varchar(300) NOT NULL,
  `f_occup` varchar(300) NOT NULL,
  `f_mobile` varchar(400) NOT NULL,
  `m_name` varchar(200) NOT NULL,
  `m_occup` varchar(300) NOT NULL,
  `m_mobile` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_parents`
--

INSERT INTO `student_parents` (`id`, `stud_id`, `f_name`, `f_occup`, `f_mobile`, `m_name`, `m_occup`, `m_mobile`) VALUES
(4, '2', 'asdasd', 'sads', '3', 'wewe', 'sdad', '2');

-- --------------------------------------------------------

--
-- Table structure for table `student_personal`
--

CREATE TABLE `student_personal` (
  `id` int(11) NOT NULL,
  `stud_id` varchar(45) NOT NULL,
  `s_name` varchar(200) NOT NULL,
  `f_name` varchar(200) NOT NULL,
  `l_name` varchar(200) NOT NULL,
  `gender` varchar(300) NOT NULL,
  `number` varchar(300) NOT NULL,
  `location` varchar(390) NOT NULL,
  `status` varchar(300) NOT NULL,
  `age` varchar(10) NOT NULL,
  `dob` date NOT NULL,
  `added` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_personal`
--

INSERT INTO `student_personal` (`id`, `stud_id`, `s_name`, `f_name`, `l_name`, `gender`, `number`, `location`, `status`, `age`, `dob`, `added`) VALUES
(15, '3', 'grcae', 'grace', 'gracw', 'Female', '27386782678', 'dnujkdsnfjkd', 'stable', '33', '3013-12-12', '2017-04-25'),
(16, '2', 'toni', 'Toni', 'Ngethe', 'Male', '03823784', 'Nairobu', 'Stable', '22', '0012-09-12', '2017-05-14'),
(17, '6', 'dsjdbasjkdbsa', 'sjdndjksna', 'sadnasdknskad', 'Male', '7347863784', 'sajkndjkasd', 'Stable', '34', '0018-03-03', '2017-05-23');

-- --------------------------------------------------------

--
-- Table structure for table `student_school`
--

CREATE TABLE `student_school` (
  `id` int(11) NOT NULL,
  `stud_id` varchar(300) NOT NULL,
  `name` varchar(500) NOT NULL,
  `education_level` varchar(400) NOT NULL,
  `mode` varchar(200) NOT NULL,
  `type` varchar(200) NOT NULL,
  `class` varchar(200) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_school`
--

INSERT INTO `student_school` (`id`, `stud_id`, `name`, `education_level`, `mode`, `type`, `class`, `date`) VALUES
(2, '8', 'Menegai high skul', 'Secondary', 'Private', 'Boarding', '3', '2017-05-24');

-- --------------------------------------------------------

--
-- Table structure for table `student_sibling`
--

CREATE TABLE `student_sibling` (
  `id` int(11) NOT NULL,
  `stud_id` varchar(100) NOT NULL,
  `name` varchar(300) NOT NULL,
  `edu_level` varchar(400) NOT NULL,
  `school` varchar(40) NOT NULL,
  `age` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_sibling`
--

INSERT INTO `student_sibling` (`id`, `stud_id`, `name`, `edu_level`, `school`, `age`) VALUES
(1, '2', 'Clement', '1', 'St. Flevian', '12'),
(2, '2', 'Clement njuguna', '1', 'St. Flevian', '12');

-- --------------------------------------------------------

--
-- Table structure for table `student_sponsor`
--

CREATE TABLE `student_sponsor` (
  `id` int(11) NOT NULL,
  `student` varchar(400) NOT NULL,
  `sponsor` varchar(400) NOT NULL,
  `social` varchar(400) NOT NULL,
  `acc` varchar(400) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_sponsor`
--

INSERT INTO `student_sponsor` (`id`, `student`, `sponsor`, `social`, `acc`, `date`) VALUES
(5, '2', 'toni', 'f@gmail.com', 'toni@gmail.com', '2017-05-16'),
(6, '3', 'toni', 'f@gmail.com', 'toni@gmail.com', '2017-05-16');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accountant`
--
ALTER TABLE `accountant`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `school`
--
ALTER TABLE `school`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `school_fees`
--
ALTER TABLE `school_fees`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `selected_school`
--
ALTER TABLE `selected_school`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `SocialVisits`
--
ALTER TABLE `SocialVisits`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `social_worker`
--
ALTER TABLE `social_worker`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Sponsor`
--
ALTER TABLE `Sponsor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sponsor_commits`
--
ALTER TABLE `sponsor_commits`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sponsor_payments`
--
ALTER TABLE `sponsor_payments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_gurdian`
--
ALTER TABLE `student_gurdian`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_parents`
--
ALTER TABLE `student_parents`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_personal`
--
ALTER TABLE `student_personal`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_school`
--
ALTER TABLE `student_school`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_sibling`
--
ALTER TABLE `student_sibling`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_sponsor`
--
ALTER TABLE `student_sponsor`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accountant`
--
ALTER TABLE `accountant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `school`
--
ALTER TABLE `school`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `school_fees`
--
ALTER TABLE `school_fees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `selected_school`
--
ALTER TABLE `selected_school`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `SocialVisits`
--
ALTER TABLE `SocialVisits`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `social_worker`
--
ALTER TABLE `social_worker`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `Sponsor`
--
ALTER TABLE `Sponsor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table `sponsor_commits`
--
ALTER TABLE `sponsor_commits`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `sponsor_payments`
--
ALTER TABLE `sponsor_payments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `student_gurdian`
--
ALTER TABLE `student_gurdian`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `student_parents`
--
ALTER TABLE `student_parents`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `student_personal`
--
ALTER TABLE `student_personal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `student_school`
--
ALTER TABLE `student_school`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `student_sibling`
--
ALTER TABLE `student_sibling`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `student_sponsor`
--
ALTER TABLE `student_sponsor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
