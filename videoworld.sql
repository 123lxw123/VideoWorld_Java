/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : videoworld

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-08 01:04:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `phdy_source`
-- ----------------------------
DROP TABLE IF EXISTS `phdy_source`;
CREATE TABLE `phdy_source` (
  `id` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `date` int(8) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phdy_source
-- ----------------------------

-- ----------------------------
-- Table structure for `ygdy_classical`
-- ----------------------------
DROP TABLE IF EXISTS `ygdy_classical`;
CREATE TABLE `ygdy_classical` (
  `id` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `date` int(8) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ygdy_classical
-- ----------------------------

-- ----------------------------
-- Table structure for `ygdy_hot`
-- ----------------------------
DROP TABLE IF EXISTS `ygdy_hot`;
CREATE TABLE `ygdy_hot` (
  `id` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `date` int(8) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ygdy_hot
-- ----------------------------

-- ----------------------------
-- Table structure for `ygdy_source`
-- ----------------------------
DROP TABLE IF EXISTS `ygdy_source`;
CREATE TABLE `ygdy_source` (
  `id` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `date` int(8) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ygdy_source
-- ----------------------------

-- ----------------------------
-- Table structure for `ygdy_source_detail`
-- ----------------------------
DROP TABLE IF EXISTS `ygdy_source_detail`;
CREATE TABLE `ygdy_source_detail` (
  `url` varchar(255) NOT NULL,
  `id` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `images` text,
  `content` mediumtext,
  `links` mediumtext,
  `date` int(8) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ygdy_source_detail
-- ----------------------------
