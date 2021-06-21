/*
Navicat MySQL Data Transfer

Source Server         : localhos_3306
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : sims

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2021-06-21 23:29:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_bill
-- ----------------------------
DROP TABLE IF EXISTS `t_bill`;
CREATE TABLE `t_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `need_payment` decimal(10,2) DEFAULT NULL,
  `real_payment` decimal(10,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `profits` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bill
-- ----------------------------
INSERT INTO `t_bill` VALUES ('12', '65.00', '100.00', '2021-06-19 22:21:31', '41.00');
INSERT INTO `t_bill` VALUES ('13', '125.00', '1000.00', '2021-06-19 22:21:46', '27.00');
INSERT INTO `t_bill` VALUES ('14', '295.00', '1000.00', '2021-06-18 22:22:08', '96.00');
INSERT INTO `t_bill` VALUES ('15', '555.00', '10000.00', '2021-06-20 22:22:30', '161.00');
INSERT INTO `t_bill` VALUES ('16', '100.00', '1000.00', '2021-06-20 22:22:45', '44.00');
INSERT INTO `t_bill` VALUES ('17', '65.00', '1000.00', '2021-06-21 10:22:55', '41.00');
INSERT INTO `t_bill` VALUES ('18', '490.00', '10000.00', '2021-06-18 23:23:53', '163.00');
INSERT INTO `t_bill` VALUES ('19', '41.50', '500.00', '2021-06-20 22:24:18', '7.10');
INSERT INTO `t_bill` VALUES ('20', '195.00', '150.00', '2021-06-21 18:25:53', '51.00');

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `count` bigint(20) DEFAULT NULL,
  `prime_price` decimal(10,2) DEFAULT NULL,
  `sale_price` decimal(10,2) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `sid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `sid` (`sid`),
  CONSTRAINT `t_goods_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `t_supplier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES ('2', '辣条', '319', '3.60', '5.00', '零食', '7');
INSERT INTO `t_goods` VALUES ('3', '薯片', '420', '4.50', '6.50', '零食', '8');
INSERT INTO `t_goods` VALUES ('4', '冰红茶', '440', '3.40', '5.50', '饮料', '7');
INSERT INTO `t_goods` VALUES ('5', '面包', '20', '5.40', '6.00', '食物', '9');
INSERT INTO `t_goods` VALUES ('7', '牛奶', '729', '2.40', '6.50', '饮料', '9');
INSERT INTO `t_goods` VALUES ('8', '绿茶', '840', '2.20', '3.50', '饮料', '5');
INSERT INTO `t_goods` VALUES ('11', '饼干', '210', '2.20', '4.50', '零食', '16');
INSERT INTO `t_goods` VALUES ('12', '棒棒糖', '900', '1.20', '2.99', '零食', '15');
INSERT INTO `t_goods` VALUES ('16', '可乐', '630', '3.20', '4.50', '饮料', '15');

-- ----------------------------
-- Table structure for t_sales
-- ----------------------------
DROP TABLE IF EXISTS `t_sales`;
CREATE TABLE `t_sales` (
  `gid` bigint(20) DEFAULT NULL,
  `bid` bigint(20) DEFAULT NULL,
  `sales_count` bigint(20) DEFAULT NULL,
  KEY `gid` (`gid`),
  KEY `bid` (`bid`),
  CONSTRAINT `t_sales_ibfk_1` FOREIGN KEY (`gid`) REFERENCES `t_goods` (`id`),
  CONSTRAINT `t_sales_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `t_bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sales
-- ----------------------------
INSERT INTO `t_sales` VALUES ('7', '12', '10');
INSERT INTO `t_sales` VALUES ('8', '13', '10');
INSERT INTO `t_sales` VALUES ('8', '13', '10');
INSERT INTO `t_sales` VALUES ('4', '13', '10');
INSERT INTO `t_sales` VALUES ('5', '14', '5');
INSERT INTO `t_sales` VALUES ('3', '14', '10');
INSERT INTO `t_sales` VALUES ('7', '14', '10');
INSERT INTO `t_sales` VALUES ('8', '14', '10');
INSERT INTO `t_sales` VALUES ('2', '14', '10');
INSERT INTO `t_sales` VALUES ('2', '15', '100');
INSERT INTO `t_sales` VALUES ('4', '15', '10');
INSERT INTO `t_sales` VALUES ('8', '16', '10');
INSERT INTO `t_sales` VALUES ('7', '16', '10');
INSERT INTO `t_sales` VALUES ('7', '17', '10');
INSERT INTO `t_sales` VALUES ('4', '18', '10');
INSERT INTO `t_sales` VALUES ('3', '18', '50');
INSERT INTO `t_sales` VALUES ('4', '18', '20');
INSERT INTO `t_sales` VALUES ('8', '19', '10');
INSERT INTO `t_sales` VALUES ('7', '19', '1');
INSERT INTO `t_sales` VALUES ('3', '20', '10');
INSERT INTO `t_sales` VALUES ('2', '20', '20');
INSERT INTO `t_sales` VALUES ('5', '20', '5');

-- ----------------------------
-- Table structure for t_supplier
-- ----------------------------
DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE `t_supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `name_2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_supplier
-- ----------------------------
INSERT INTO `t_supplier` VALUES ('5', '1号有限公司', '广州', '17554413697');
INSERT INTO `t_supplier` VALUES ('6', '2号有限公司', '佛山', '19872581367');
INSERT INTO `t_supplier` VALUES ('7', '3号有限公司', '湛江', '14723658987');
INSERT INTO `t_supplier` VALUES ('8', '4号有限公司', '东莞', '12123132185');
INSERT INTO `t_supplier` VALUES ('9', '7号有限公司', '珠海', '11113432447');
INSERT INTO `t_supplier` VALUES ('10', '8号有限公司', '深圳', '11113432447');
INSERT INTO `t_supplier` VALUES ('11', '10号有限公司', '茂名', '14923658981');
INSERT INTO `t_supplier` VALUES ('12', '9号有限公司', '清远', '19633658981');
INSERT INTO `t_supplier` VALUES ('13', '5号有限公司', '肇庆', '18524658981');
INSERT INTO `t_supplier` VALUES ('14', '6号有限公司', '云浮', '14853658981');
INSERT INTO `t_supplier` VALUES ('15', '11号有限公司', '河源', '14785658981');
INSERT INTO `t_supplier` VALUES ('16', '12号有限公司', '阳江', '14796658981');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '123456');
