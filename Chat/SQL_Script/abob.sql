
create database abob;
use abob;

CREATE TABLE `member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `m_id` varchar(20) DEFAULT NULL,
  `m_pw` varchar(20) DEFAULT NULL,
  `m_name` varchar(20) DEFAULT NULL,
  `m_sex` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `cost` int DEFAULT NULL,
  `cook_time` int DEFAULT NULL,
  `food_type` varchar(10) DEFAULT NULL,
  `str_id` int DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `str_id` (`str_id`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`str_id`) REFERENCES `store` (`str_id`)
);

CREATE TABLE `store` (
  `str_id` int NOT NULL AUTO_INCREMENT,
  `str_name` varchar(20) DEFAULT NULL,
  `loc` varchar(20) DEFAULT NULL,
  `loc_num` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`str_id`)
);

INSERT INTO `menu` VALUES (1,'부리또',4000,600,'양식',5),(2,'불닭마요',3700,420,'한식',5),(3,'짜장면',4500,600,'중식',5),(4,'밥버거',4000,300,'한식',5),
(5,'즉석떡볶이',6000,900,'한식',5),(6,'치즈돈가스',6000,900,'일식',3),(7,'불고기',6000,780,'한식',3),(8,'돼지김치찌개',6000,600,'한식',3),(9,'미트볼스파게티',6000,840,'양식',3),
(10,'떡갈비',6000,780,'한식',3),(11,'숯불직화구이',6000,600,'한식',9) ,(12,'우동',4000,480,'일식',9),(13,'떡라면',3000,360,'한식',9),(14,'김치볶음밥',4000,600,'한식',9),(15,'로제스파게티',5000,600,'양식',9),
(16,'순대국',6000,480,'한식',1),(17,'김밥',2500,300,'한식',1),(18,'회덮밥',5500,420,'한식',1),(19,'치즈스테이크',4000,600,'양식',1),(20,'설렁탕',5500,300,'한식',1),
(21,'치킨샐러드',5000,120,'양식',7),(22,'해물짬뽕',6000,780,'중식',7),(23,'쫄면',4000,300,'한식',7),(24,'빠네크림파스타',6000,720,'양식',7),(25,'닭볶음탕',6000,360,'한식',7);



INSERT INTO `store` VALUES (5,'비전타워 식당상가','비전타워',''),(3,'비전타워 학생식당','비전타워',''),(9,'예술대학1 학생식당','예술대학1',''),(1,'가천관 지하 푸드코트','가천관',''),(7,'교육대학 학생식당','교육대학','');


