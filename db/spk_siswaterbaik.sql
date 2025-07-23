-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 23, 2025 at 05:11 PM
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
  `jenkel` varchar(10) DEFAULT NULL,
  `semester` varchar(10) DEFAULT NULL,
  `tahun_ajaran` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `alternatif`
--

INSERT INTO `alternatif` (`id_siswa`, `kode_siswa`, `nisn`, `nama_siswa`, `kelas`, `jenkel`, `semester`, `tahun_ajaran`) VALUES
(1, 'S001', '123123', 'ada', '2', 'Laki-Laki', 'Genap', '2025');

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
(1, 1, 1),
(2, 3, 0.888889);

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
(1, 'C001', 'rata-rata rapot', 0.25),
(2, 'C002', 'absen', 0.15),
(3, 'C003', 'sikap', 0.2),
(4, 'C004', 'ekstrakulikuler', 0.2),
(5, 'C005', 'keterampilan', 0.2);

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
(3, 'N003', 1, 3, 90),
(4, 'N004', 1, 4, 90),
(5, 'N005', 1, 5, 90),
(6, 'N006', 3, 1, 80),
(7, 'N007', 3, 2, 80),
(8, 'N008', 3, 3, 80),
(9, 'N009', 3, 4, 80),
(10, 'N010', 3, 5, 80);

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
(1, 1, 1, 1, 1, NULL),
(2, 1, 2, 2, 1, NULL),
(3, 1, 3, 3, 1, NULL),
(4, 1, 4, 4, 1, NULL),
(5, 1, 5, 5, 1, NULL),
(6, 3, 6, 1, 0.888889, NULL),
(7, 3, 7, 2, 0.888889, NULL),
(8, 3, 8, 3, 0.888889, NULL),
(9, 3, 9, 4, 0.888889, NULL),
(10, 3, 10, 5, 0.888889, NULL);

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
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3');

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
  MODIFY `id_siswa` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `hasil_akhir`
--
ALTER TABLE `hasil_akhir`
  MODIFY `id_hasil` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `kriteria`
--
ALTER TABLE `kriteria`
  MODIFY `id_kriteria` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `nilai_siswa`
--
ALTER TABLE `nilai_siswa`
  MODIFY `id_penilaian` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `saw`
--
ALTER TABLE `saw`
  MODIFY `id_saw` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

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
