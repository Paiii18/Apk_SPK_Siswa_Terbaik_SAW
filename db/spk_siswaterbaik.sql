-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 18, 2025 at 01:30 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spk_siswaterbaik`
--

-- --------------------------------------------------------

--
-- Table structure for table `alternatif`
--

CREATE TABLE `alternatif` (
  `id_siswa` int NOT NULL,
  `kode_siswa` varchar(20) DEFAULT NULL,
  `nisn` varchar(50) NOT NULL,
  `nama_siswa` varchar(100) DEFAULT NULL,
  `kelas` varchar(20) DEFAULT NULL,
  `jenkel` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `alternatif`
--

INSERT INTO `alternatif` (`id_siswa`, `kode_siswa`, `nisn`, `nama_siswa`, `kelas`, `jenkel`) VALUES
(1, 'S001', '0038512253', 'Elsa Nur Fitri', 'XII IPA 1', 'Laki-Laki'),
(2, 'S002', '0054013663', 'Defita Syida', 'XII IPA', 'Perempuan'),
(3, 'S003', '0056358072', 'Alika Salwan', 'XII IPA 1', 'Laki-Laki'),
(4, 'S004', '006913470', 'Chakra Naruto', 'XII IPA 1', 'Laki-Laki');

-- --------------------------------------------------------

--
-- Table structure for table `hasil_akhir`
--

CREATE TABLE `hasil_akhir` (
  `id_hasil` int NOT NULL,
  `id_siswa` int DEFAULT NULL,
  `skor_akhir` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hasil_akhir`
--

INSERT INTO `hasil_akhir` (`id_hasil`, `id_siswa`, `skor_akhir`) VALUES
(6, 3, 0.9833),
(7, 1, 0.925),
(8, 2, 0.9195);

-- --------------------------------------------------------

--
-- Table structure for table `kriteria`
--

CREATE TABLE `kriteria` (
  `id_kriteria` int NOT NULL,
  `kode_kriteria` varchar(20) DEFAULT NULL,
  `nama_kriteria` varchar(100) DEFAULT NULL,
  `bobot_kriteria` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `kriteria`
--

INSERT INTO `kriteria` (`id_kriteria`, `kode_kriteria`, `nama_kriteria`, `bobot_kriteria`) VALUES
(1, 'K001', 'Nilai Rata-Rata Rapot', 0.25),
(2, 'K002', 'Absensi', 0.15),
(3, 'K003', 'Sikap', 0.2),
(4, 'K004', 'Ekstrakurikuler', 0.2),
(5, 'K005', 'Keterampilan', 0.2);

-- --------------------------------------------------------

--
-- Table structure for table `nilai_siswa`
--

CREATE TABLE `nilai_siswa` (
  `id_penilaian` int NOT NULL,
  `kode` varchar(50) NOT NULL,
  `id_siswa` int DEFAULT NULL,
  `id_kriteria` int DEFAULT NULL,
  `nilai` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `nilai_siswa`
--

INSERT INTO `nilai_siswa` (`id_penilaian`, `kode`, `id_siswa`, `id_kriteria`, `nilai`) VALUES
(1, 'N001', 1, 1, 90),
(2, 'N002', 1, 2, 90),
(3, 'N003', 1, 3, 70),
(4, 'N004', 1, 4, 70),
(6, 'N005', 1, 5, 70),
(7, 'N006', 2, 1, 70),
(8, 'N007', 2, 2, 90),
(9, 'N008', 2, 3, 80),
(10, 'N009', 2, 4, 80),
(11, 'N010', 2, 5, 70),
(12, 'N011', 3, 1, 90),
(13, 'N012', 3, 2, 80),
(14, 'N013', 3, 3, 80),
(15, 'N014', 3, 4, 80),
(16, 'N015', 3, 5, 80);

-- --------------------------------------------------------

--
-- Table structure for table `saw`
--

CREATE TABLE `saw` (
  `id_saw` int NOT NULL,
  `id_siswa` int DEFAULT NULL,
  `id_penilaian` int DEFAULT NULL,
  `id_kriteria` int DEFAULT NULL,
  `nilai_normalisasi` float DEFAULT NULL,
  `id_hasil` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `saw`
--

INSERT INTO `saw` (`id_saw`, `id_siswa`, `id_penilaian`, `id_kriteria`, `nilai_normalisasi`, `id_hasil`) VALUES
(28, 3, 12, 1, 1, 6),
(29, 3, 13, 2, 1, 6),
(30, 3, 14, 3, 0.875, 6),
(31, 3, 15, 4, 0.875, 6),
(32, 3, 16, 5, 0.875, 6),
(33, 1, 1, 1, 0.7778, 7),
(34, 1, 2, 2, 1, 7),
(35, 1, 3, 3, 1, 7),
(36, 1, 4, 4, 1, 7),
(37, 1, 6, 5, 0.875, 7),
(38, 2, 7, 1, 1, 8),
(39, 2, 8, 2, 0.8889, 8),
(40, 2, 9, 3, 1, 8),
(41, 2, 10, 4, 1, 8),
(42, 2, 11, 5, 1, 8);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_users` int NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_users`, `username`, `password`) VALUES
(1, 'Admin', 'e64b78fc3bc91bcbc7dc232ba8ec59e0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alternatif`
--
ALTER TABLE `alternatif`
  ADD PRIMARY KEY (`id_siswa`),
  ADD UNIQUE KEY `kode_siswa` (`kode_siswa`,`nisn`);

--
-- Indexes for table `hasil_akhir`
--
ALTER TABLE `hasil_akhir`
  ADD PRIMARY KEY (`id_hasil`),
  ADD KEY `id_siswa` (`id_siswa`);

--
-- Indexes for table `kriteria`
--
ALTER TABLE `kriteria`
  ADD PRIMARY KEY (`id_kriteria`);

--
-- Indexes for table `nilai_siswa`
--
ALTER TABLE `nilai_siswa`
  ADD PRIMARY KEY (`id_penilaian`),
  ADD KEY `id_siswa` (`id_siswa`),
  ADD KEY `id_kriteria` (`id_kriteria`);

--
-- Indexes for table `saw`
--
ALTER TABLE `saw`
  ADD PRIMARY KEY (`id_saw`),
  ADD KEY `id_siswa` (`id_siswa`),
  ADD KEY `id_penilaian` (`id_penilaian`),
  ADD KEY `id_kriteria` (`id_kriteria`),
  ADD KEY `id_hasil` (`id_hasil`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_users`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alternatif`
--
ALTER TABLE `alternatif`
  MODIFY `id_siswa` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `hasil_akhir`
--
ALTER TABLE `hasil_akhir`
  MODIFY `id_hasil` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `kriteria`
--
ALTER TABLE `kriteria`
  MODIFY `id_kriteria` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `nilai_siswa`
--
ALTER TABLE `nilai_siswa`
  MODIFY `id_penilaian` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `saw`
--
ALTER TABLE `saw`
  MODIFY `id_saw` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_users` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hasil_akhir`
--
ALTER TABLE `hasil_akhir`
  ADD CONSTRAINT `hasil_akhir_ibfk_1` FOREIGN KEY (`id_siswa`) REFERENCES `alternatif` (`id_siswa`) ON DELETE CASCADE;

--
-- Constraints for table `nilai_siswa`
--
ALTER TABLE `nilai_siswa`
  ADD CONSTRAINT `nilai_siswa_ibfk_1` FOREIGN KEY (`id_siswa`) REFERENCES `alternatif` (`id_siswa`) ON DELETE CASCADE,
  ADD CONSTRAINT `nilai_siswa_ibfk_2` FOREIGN KEY (`id_kriteria`) REFERENCES `kriteria` (`id_kriteria`) ON DELETE CASCADE;

--
-- Constraints for table `saw`
--
ALTER TABLE `saw`
  ADD CONSTRAINT `saw_ibfk_1` FOREIGN KEY (`id_siswa`) REFERENCES `alternatif` (`id_siswa`) ON DELETE CASCADE,
  ADD CONSTRAINT `saw_ibfk_2` FOREIGN KEY (`id_penilaian`) REFERENCES `nilai_siswa` (`id_penilaian`) ON DELETE CASCADE,
  ADD CONSTRAINT `saw_ibfk_3` FOREIGN KEY (`id_kriteria`) REFERENCES `kriteria` (`id_kriteria`) ON DELETE CASCADE,
  ADD CONSTRAINT `saw_ibfk_4` FOREIGN KEY (`id_hasil`) REFERENCES `hasil_akhir` (`id_hasil`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
