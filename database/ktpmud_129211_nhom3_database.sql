-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: ktpm_btl
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `day`
--

DROP TABLE IF EXISTS `day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `day` (
  `id_day` int NOT NULL AUTO_INCREMENT,
  `ma_giao_vien` varchar(6) NOT NULL,
  `ma_mon_hoc` varchar(4) NOT NULL,
  `ten_lop` varchar(3) NOT NULL,
  `nam_hoc` varchar(9) NOT NULL,
  `xoa` tinyint NOT NULL,
  PRIMARY KEY (`id_day`),
  KEY `FK_maGV_day_to_maGV_giao_vien_idx` (`ma_giao_vien`),
  KEY `FK_maMH_day_to_maMH_mon_hoc_idx` (`ma_mon_hoc`),
  KEY `FK_Lop_day_to_Lop_lop_idx` (`ten_lop`,`nam_hoc`),
  CONSTRAINT `FK_Lop_day_to_Lop_lop` FOREIGN KEY (`ten_lop`, `nam_hoc`) REFERENCES `lop` (`ten_lop`, `nam_hoc`) ON UPDATE CASCADE,
  CONSTRAINT `FK_maGV_day_to_maGV_giao_vien` FOREIGN KEY (`ma_giao_vien`) REFERENCES `giao_vien` (`ma_giao_vien`) ON UPDATE CASCADE,
  CONSTRAINT `FK_maMH_day_to_maMH_mon_hoc` FOREIGN KEY (`ma_mon_hoc`) REFERENCES `mon_hoc` (`ma_mon_hoc`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `day`
--

LOCK TABLES `day` WRITE;
/*!40000 ALTER TABLE `day` DISABLE KEYS */;
/*!40000 ALTER TABLE `day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diem`
--

DROP TABLE IF EXISTS `diem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diem` (
  `id_diem` int NOT NULL AUTO_INCREMENT,
  `ma_hoc_sinh` varchar(8) NOT NULL,
  `ma_mon_hoc` varchar(4) NOT NULL,
  `ma_loai_diem` varchar(4) NOT NULL,
  `nam_hoc` varchar(9) NOT NULL,
  `hoc_ky` varchar(2) NOT NULL,
  `diem_so` float NOT NULL,
  `xoa` tinyint DEFAULT NULL,
  PRIMARY KEY (`id_diem`),
  KEY `id_diem` (`id_diem`),
  KEY `FK_maHS_diem_to_maHS_hoc_sinh` (`ma_hoc_sinh`),
  KEY `FK_maLoaiDiem_diem_to_maLoaiDiem_loai_diem` (`ma_loai_diem`),
  KEY `FK_maMH_diem_to_maMH_mon_hoc` (`ma_mon_hoc`),
  CONSTRAINT `FK_maHS_diem_to_maHS_hoc_sinh` FOREIGN KEY (`ma_hoc_sinh`) REFERENCES `hoc_sinh` (`ma_hoc_sinh`) ON UPDATE CASCADE,
  CONSTRAINT `FK_maLoaiDiem_diem_to_maLoaiDiem_loai_diem` FOREIGN KEY (`ma_loai_diem`) REFERENCES `loai_diem` (`ma_loai_diem`) ON UPDATE CASCADE,
  CONSTRAINT `FK_maMH_diem_to_maMH_mon_hoc` FOREIGN KEY (`ma_mon_hoc`) REFERENCES `mon_hoc` (`ma_mon_hoc`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diem`
--

LOCK TABLES `diem` WRITE;
/*!40000 ALTER TABLE `diem` DISABLE KEYS */;
INSERT INTO `diem` VALUES (1,'HS000001','TH7','HS11','2020-2021','I',9,0),(2,'HS000001','TH7','GK','2020-2021','I',9.5,0),(3,'HS000002','TH7','HS11','2020-2021','I',8.5,0),(4,'HS000002','TH7','HS12','2020-2021','I',8,0),(5,'HS000001','TH7','HS12','2020-2021','I',8,0),(6,'HS000001','TH7','CK','2020-2021','I',9,0),(7,'HS000001','NV7','HS11','2020-2021','I',8,0),(8,'HS000001','NV7','HS12','2020-2021','I',7,0),(9,'HS000001','NV7','GK','2020-2021','I',8,0),(10,'HS000001','NV7','CK','2020-2021','I',7.5,0),(11,'HS000002','TH7','GK','2020-2021','I',9,0),(12,'HS000003','TH7','HS11','2020-2021','I',8,0),(13,'HS000003','TH7','HS12','2020-2021','I',9,0),(14,'HS000003','NV7','HS11','2020-2021','I',8,0),(15,'HS000003','NV7','GK','2020-2021','I',9,0),(16,'HS000003','NV7','CK','2020-2021','I',8,0),(17,'HS000005','NV7','HS11','2020-2021','I',8,0),(18,'HS000005','NV7','GK','2020-2021','I',7,0),(19,'HS000005','NV7','CK','2020-2021','I',8.5,0),(20,'HS000002','NV7','HS11','2020-2021','I',8,0),(21,'HS000002','NV7','GK','2020-2021','I',7,0),(22,'HS000002','NV7','CK','2020-2021','I',7,0),(23,'HS000005','TH6','HS11','2020-2021','I',8,0),(24,'HS000005','TH6','GK','2020-2021','I',8,0),(25,'HS000005','TH6','CK','2020-2021','I',9,0),(26,'HS000003','TH6','HS12','2020-2021','I',10,0),(27,'HS000003','TH6','HS11','2020-2021','I',8,0),(28,'HS000003','TH6','HS13','2020-2021','I',10,0),(29,'HS000003','TH6','GK','2020-2021','I',9,0),(30,'HS000005','TH6','HS12','2020-2021','I',9,0),(31,'HS000002','TH6','HS11','2020-2021','I',8.5,0),(32,'HS000002','TH6','GK','2020-2021','I',9,0),(33,'HS000002','TH6','CK','2020-2021','I',9,0),(34,'HS000005','NV7','HS12','2020-2021','I',10,0),(35,'HS000005','TH7','HS11','2020-2021','I',8,0),(36,'HS000005','NV7','HS13','2020-2021','I',9,0),(37,'HS000001','TH7','HS11','2020-2021','II',9,0),(38,'HS000001','TH7','GK','2020-2021','II',9,0),(39,'HS000001','TH7','CK','2020-2021','II',9,0),(40,'HS000008','TH7','HS11','2020-2021','I',8,0),(41,'HS000008','TH7','HS12','2020-2021','I',9,0);
/*!40000 ALTER TABLE `diem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giao_vien`
--

DROP TABLE IF EXISTS `giao_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giao_vien` (
  `id_giao_vien` int NOT NULL AUTO_INCREMENT,
  `ma_giao_vien` varchar(6) NOT NULL,
  `ten_giao_vien` varchar(45) NOT NULL,
  `gioi_tinh` varchar(4) NOT NULL,
  `ngay_sinh` date NOT NULL,
  `so_dien_thoai` varchar(11) DEFAULT NULL,
  `dia_chi` varchar(100) DEFAULT NULL,
  `chuc_vu` varchar(20) NOT NULL,
  `ten_dang_nhap` varchar(45) NOT NULL,
  `mat_khau` varchar(20) NOT NULL,
  `xoa` tinyint NOT NULL,
  PRIMARY KEY (`ma_giao_vien`),
  UNIQUE KEY `ma_giao_vien_UNIQUE` (`ma_giao_vien`),
  UNIQUE KEY `ten_dang_nhap_UNIQUE` (`ten_dang_nhap`),
  KEY `id_giao_vien` (`id_giao_vien`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giao_vien`
--

LOCK TABLES `giao_vien` WRITE;
/*!40000 ALTER TABLE `giao_vien` DISABLE KEYS */;
INSERT INTO `giao_vien` VALUES (1,'GV0001','Lê Thị Hiền','Nữ','1979-05-20','0916753479','Mai Động - Hoàng Mai - Hà Nội','Giáo viên giảng dạy','lethihien0001','Lethihien0001',0),(2,'GV0002','Lê Văn Bắc','Nam','1974-09-19','0975697689','Sài Đồng - Long Biên- Hà Nội','Hiệu trưởng','levanbac0002','Levanbac0002',0),(3,'GV0003','Vũ Hoài Anh','Nữ','1990-07-16','0917367812','Nguyễn Trãi - Thanh Xuân - Hà Nội','Giáo viên giảng dạy','vuhoaianh0003','Vuhoaianh0003',0),(4,'GV0004','Nguyễn Thị Huệ','Nữ','1981-03-18','0913678932','Xuân Đỉnh - Nam Từ Liêm - Hà Nội','Giáo viên giảng dạy','nguyenthihue0004','Nguyenthihue0004',0),(5,'GV0005','Hoàng Thị Lan','Nữ','1989-09-18','0923721381','Phủ Lý - Hà Nam','Giáo viên giảng dạy','hoangthilan0005','Hoangthilan0005',0),(6,'GV0006','Vũ Huy Hoàng','Nam','1980-08-19','0312398136','Thuận Thành - Bắc Ninh','Giáo viên giảng dạy','vuhuyhoang0006','vuhuyhoang0006',0),(7,'GV0007','Nguyễn Thu Hà','Nữ','1978-07-18','0986788978','Hoàng Mai - Hà Nội ','Hiệu phó','nguyenthuha0007','Nguyenthuha0007',0),(8,'GV8879','Nguyễn Văn C','Nam','2001-01-15','0931238981','đâsd','Giáo viên giảng dạy','dfsafas','sdasdasd',1);
/*!40000 ALTER TABLE `giao_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hanh_kiem`
--

DROP TABLE IF EXISTS `hanh_kiem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hanh_kiem` (
  `id_hanh_kiem` int NOT NULL AUTO_INCREMENT,
  `ma_hoc_sinh` varchar(8) NOT NULL,
  `nam_hoc` varchar(9) NOT NULL,
  `hoc_ky` varchar(2) NOT NULL,
  `loi_vi_pham` varchar(100) DEFAULT NULL,
  `nghi_co_phep` int DEFAULT NULL,
  `nghi_khong_phep` int DEFAULT NULL,
  `xoa` tinyint NOT NULL,
  PRIMARY KEY (`id_hanh_kiem`),
  KEY `FK_maHS_diem_to_maHS_hoc_sinh_idx` (`ma_hoc_sinh`),
  CONSTRAINT `FK_maHS_hanh_kiem_to_maHS_hoc_sinh` FOREIGN KEY (`ma_hoc_sinh`) REFERENCES `hoc_sinh` (`ma_hoc_sinh`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hanh_kiem`
--

LOCK TABLES `hanh_kiem` WRITE;
/*!40000 ALTER TABLE `hanh_kiem` DISABLE KEYS */;
INSERT INTO `hanh_kiem` VALUES (1,'HS000001','2020-2021','I','Đi học muộn ',2,0,0),(2,'HS000002','2020-2021','I','Mất trật tự trong giờ',3,0,0),(5,'HS000003','2020-2021','I','Mất trật tự',0,0,0);
/*!40000 ALTER TABLE `hanh_kiem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoc`
--

DROP TABLE IF EXISTS `hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoc` (
  `id_hoc` int NOT NULL AUTO_INCREMENT,
  `ten_lop` varchar(3) NOT NULL,
  `nam_hoc` varchar(9) NOT NULL,
  `ma_hoc_sinh` varchar(8) NOT NULL,
  `xoa` tinyint NOT NULL,
  PRIMARY KEY (`id_hoc`),
  KEY `FK_maHS_hoc_to_maHS_hoc_sinh_idx` (`ma_hoc_sinh`),
  KEY `FK_Lop_hoc_to_Lop_lop` (`ten_lop`,`nam_hoc`),
  CONSTRAINT `FK_Lop_hoc_to_Lop_lop` FOREIGN KEY (`ten_lop`, `nam_hoc`) REFERENCES `lop` (`ten_lop`, `nam_hoc`) ON UPDATE CASCADE,
  CONSTRAINT `FK_maHS_hoc_to_maHS_hoc_sinh` FOREIGN KEY (`ma_hoc_sinh`) REFERENCES `hoc_sinh` (`ma_hoc_sinh`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoc`
--

LOCK TABLES `hoc` WRITE;
/*!40000 ALTER TABLE `hoc` DISABLE KEYS */;
INSERT INTO `hoc` VALUES (1,'7A3','2020-2021','HS000001',0),(2,'7A3','2020-2021','HS000002',0),(8,'8A1','2021-2022','HS000001',0),(9,'7A3','2020-2021','HS000003',0),(10,'7A3','2020-2021','HS000005',0),(11,'7A3','2020-2021','HS000008',0);
/*!40000 ALTER TABLE `hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoc_sinh`
--

DROP TABLE IF EXISTS `hoc_sinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoc_sinh` (
  `id_hoc_sinh` int NOT NULL AUTO_INCREMENT,
  `ma_hoc_sinh` varchar(8) NOT NULL,
  `ten_hoc_sinh` varchar(45) DEFAULT NULL,
  `gioi_tinh` varchar(4) DEFAULT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `dia_chi` varchar(100) DEFAULT NULL,
  `ten_phu_huynh` varchar(45) DEFAULT NULL,
  `so_dien_thoai` varchar(11) DEFAULT NULL,
  `xoa` tinyint DEFAULT NULL,
  PRIMARY KEY (`ma_hoc_sinh`),
  UNIQUE KEY `ma_hoc_sinh_UNIQUE` (`ma_hoc_sinh`),
  KEY `id_hoc_sinh` (`id_hoc_sinh`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoc_sinh`
--

LOCK TABLES `hoc_sinh` WRITE;
/*!40000 ALTER TABLE `hoc_sinh` DISABLE KEYS */;
INSERT INTO `hoc_sinh` VALUES (1,'HS000001','Đào Huy Chiến','Nam','2001-08-18','Đặng Xá - Gia Lâm - Hà Nội','Nguyễn Thị Minh Hương','0888281036',0),(2,'HS000002','Đặng Quang Anh','Nam','2001-02-20','Xuân Đỉnh - Tây Hồ - Hà Nội','Nguyễn Thị Vân ','0232138234',0),(3,'HS000003','Nguyễn Văn Dũng','Nam','2001-02-05','Kim Sơn - Gia Lâm - Hà Nội','Nguyễn Văn Hoàng','0938213345',0),(18,'HS000005','Vũ Thu Huyền','Nữ','2001-09-19','Thanh Xuân Trung - Thanh Xuân - Hà Nội','Nguyễn Thị Lan','0987736671',0),(19,'HS000008','Nguyễn Văn A','Nam','2001-09-18','Hà Nội','Nguyễn Văn B','0984571892',0);
/*!40000 ALTER TABLE `hoc_sinh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai_diem`
--

DROP TABLE IF EXISTS `loai_diem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loai_diem` (
  `id_loai_diem` int NOT NULL AUTO_INCREMENT,
  `ma_loai_diem` varchar(4) NOT NULL,
  `ten_loai_diem` varchar(20) NOT NULL,
  `trong_so` int NOT NULL,
  `xoa` tinyint DEFAULT NULL,
  PRIMARY KEY (`ma_loai_diem`),
  KEY `id_loai_diem` (`id_loai_diem`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai_diem`
--

LOCK TABLES `loai_diem` WRITE;
/*!40000 ALTER TABLE `loai_diem` DISABLE KEYS */;
INSERT INTO `loai_diem` VALUES (6,'CK','Điểm thi cuối kỳ ',3,0),(5,'GK','Điểm thi giữa kỳ ',2,0),(1,'HS11','Điểm hệ số 1 lần 1',1,0),(2,'HS12','Điểm hệ số 1 lần 2',1,0),(3,'HS13','Điểm hệ số 1 lần 3',1,0);
/*!40000 ALTER TABLE `loai_diem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lop`
--

DROP TABLE IF EXISTS `lop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lop` (
  `id_lop` int NOT NULL AUTO_INCREMENT,
  `ten_lop` varchar(3) NOT NULL,
  `nam_hoc` varchar(9) NOT NULL,
  `si_so` int NOT NULL,
  `ma_gv_chu_nhiem` varchar(6) DEFAULT NULL,
  `xoa` tinyint DEFAULT NULL,
  PRIMARY KEY (`ten_lop`,`nam_hoc`),
  KEY `id_lop` (`id_lop`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lop`
--

LOCK TABLES `lop` WRITE;
/*!40000 ALTER TABLE `lop` DISABLE KEYS */;
INSERT INTO `lop` VALUES (6,'6A1','2020-2021',0,'GV0003',0),(7,'6A2','2020-2021',0,'GV0007',0),(10,'6A3','2020-2021',0,'GV0005',0),(8,'7A1','2020-2021',0,'GV0006',0),(1,'7A1','2021-2022',0,'GV0003',0),(2,'7A3','2020-2021',5,'GV0001',0),(3,'8A1','2021-2022',1,'GV0004',0),(9,'8A2','2020-2021',0,'',0),(11,'8A3','2020-2021',0,'GV0004',0);
/*!40000 ALTER TABLE `lop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mon_hoc`
--

DROP TABLE IF EXISTS `mon_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mon_hoc` (
  `id_mon_hoc` int NOT NULL AUTO_INCREMENT,
  `ma_mon_hoc` varchar(4) NOT NULL,
  `ten_mon` varchar(30) NOT NULL,
  `trong_so` int NOT NULL,
  `khoi` int NOT NULL,
  `xoa` tinyint NOT NULL,
  PRIMARY KEY (`ma_mon_hoc`),
  KEY `id_mon_hoc` (`id_mon_hoc`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mon_hoc`
--

LOCK TABLES `mon_hoc` WRITE;
/*!40000 ALTER TABLE `mon_hoc` DISABLE KEYS */;
INSERT INTO `mon_hoc` VALUES (7,'HH7','Hóa Học 7',1,7,0),(5,'NV7','Ngữ Văn 7',2,7,0),(3,'NV8','Ngữ Văn 8',2,8,0),(6,'TA7','Tiếng Anh 7',1,7,0),(4,'TA9','Tiếng Anh 9',1,9,0),(1,'TH6','Toán Học 6',2,6,0),(2,'TH7','Toán Học 7',2,7,0),(8,'TH8','Toán Học 8',1,8,0);
/*!40000 ALTER TABLE `mon_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tai_khoan`
--

DROP TABLE IF EXISTS `tai_khoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tai_khoan` (
  `id_tai_khoan` int NOT NULL AUTO_INCREMENT,
  `ten_dang_nhap` varchar(45) NOT NULL,
  `mat_khau` varchar(20) NOT NULL,
  `vai_tro` varchar(15) NOT NULL,
  `ma_giao_vien` varchar(6) NOT NULL,
  `xoa` tinyint NOT NULL,
  PRIMARY KEY (`ten_dang_nhap`),
  UNIQUE KEY `ten_dang_nhap_UNIQUE` (`ten_dang_nhap`) /*!80000 INVISIBLE */,
  KEY `id_tai_khoan` (`id_tai_khoan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tai_khoan`
--

LOCK TABLES `tai_khoan` WRITE;
/*!40000 ALTER TABLE `tai_khoan` DISABLE KEYS */;
/*!40000 ALTER TABLE `tai_khoan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-19  8:38:32
