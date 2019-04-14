/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : question

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-04-14 17:39:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_city
-- ----------------------------
DROP TABLE IF EXISTS `tb_city`;
CREATE TABLE `tb_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `artistic_grade` varchar(255) DEFAULT NULL,
  `cultural_grade` varchar(255) DEFAULT NULL,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP,
  `type` varchar(255) DEFAULT '0' COMMENT '0不分文理 1文 2理',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_city
-- ----------------------------
INSERT INTO `tb_city` VALUES ('1', '辽宁', '50', '50', '2019-04-01 19:21:10', '1');
INSERT INTO `tb_city` VALUES ('2', '吉林', '50', '50', '2019-04-01 19:21:10', '1');
INSERT INTO `tb_city` VALUES ('3', '黑龙江', '50', '50', '2019-04-01 19:21:10', '0');
INSERT INTO `tb_city` VALUES ('5', '河北', '50', '50', '2019-04-01 19:21:10', '0');
INSERT INTO `tb_city` VALUES ('6', '山东', '50', '50', '2019-04-01 19:21:10', '1');
INSERT INTO `tb_city` VALUES ('7', '山西', '50', '50', '2019-04-01 19:21:10', '0');
INSERT INTO `tb_city` VALUES ('11', '内蒙古', '50', '50', '2019-04-11 08:26:06', '0');
INSERT INTO `tb_city` VALUES ('12', '辽宁', '50', '50', '2019-04-11 08:27:19', '2');
INSERT INTO `tb_city` VALUES ('13', '湖南', '50', '50', '2019-04-11 08:28:26', '1');
INSERT INTO `tb_city` VALUES ('14', '吉林', '50', '50', '2019-04-11 08:28:50', '2');
INSERT INTO `tb_city` VALUES ('15', '山东', '50', '50', '2019-04-11 08:29:01', '2');
INSERT INTO `tb_city` VALUES ('16', '湖南', '50', '50', '2019-04-11 08:29:30', '2');

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `cotent` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_message
-- ----------------------------

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `r_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tb_permission_rol_id` (`r_id`),
  CONSTRAINT `tb_permission_rol_id` FOREIGN KEY (`r_id`) REFERENCES `tb_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES ('1', 'update', '1');
INSERT INTO `tb_permission` VALUES ('2', 'delete', '1');
INSERT INTO `tb_permission` VALUES ('3', 'create', '2');
INSERT INTO `tb_permission` VALUES ('4', 'find', '2');
INSERT INTO `tb_permission` VALUES ('5', 'create', '1');
INSERT INTO `tb_permission` VALUES ('6', 'find', '1');

-- ----------------------------
-- Table structure for tb_roles
-- ----------------------------
DROP TABLE IF EXISTS `tb_roles`;
CREATE TABLE `tb_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `tb_role_user_id` (`u_id`),
  CONSTRAINT `tb_role_user_id` FOREIGN KEY (`u_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_roles
-- ----------------------------
INSERT INTO `tb_roles` VALUES ('1', 'admin', '1', '2019-04-01 11:15:10');
INSERT INTO `tb_roles` VALUES ('2', 'customer', '2', '2019-04-01 11:15:28');
INSERT INTO `tb_roles` VALUES ('3', 'admin', '3', '2019-04-14 17:25:25');
INSERT INTO `tb_roles` VALUES ('4', 'admin', '4', '2019-04-14 17:27:29');

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL COMMENT '艺术成绩#文化成绩',
  `total_grade` double(255,0) DEFAULT NULL,
  `c_id` int(11) DEFAULT NULL,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `tb_student_city_id` (`c_id`),
  CONSTRAINT `tb_student_city_id` FOREIGN KEY (`c_id`) REFERENCES `tb_city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES ('1', '张家豪', '100#100', '100', '1', '2019-04-01 19:22:51');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '2015136327', '410439', '2019-04-01 01:34:58');
INSERT INTO `tb_user` VALUES ('2', '123', '123', '2019-04-07 15:58:47');
INSERT INTO `tb_user` VALUES ('3', '20151363272', '123456', '2019-04-14 17:25:25');
INSERT INTO `tb_user` VALUES ('4', '20151363272', '410439', '2019-04-14 17:27:29');
