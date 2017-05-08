/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : videoworld

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-08 23:24:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mpdy_source`
-- ----------------------------
DROP TABLE IF EXISTS `mpdy_source`;
CREATE TABLE `mpdy_source` (
  `id` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mpdy_source
-- ----------------------------
INSERT INTO `mpdy_source` VALUES ('6796', 'ds', 'http://maopu.tv/ds/6796.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('6861', 'ds', 'http://maopu.tv/ds/6861.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('6945', 'ds', 'http://maopu.tv/ds/6945.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('6947', 'ds', 'http://maopu.tv/ds/6947.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7344', 'ds', 'http://maopu.tv/ds/7344.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7469', 'ds', 'http://maopu.tv/ds/7469.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7475', 'ds', 'http://maopu.tv/ds/7475.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7477', 'ds', 'http://maopu.tv/ds/7477.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7485', 'ds', 'http://maopu.tv/ds/7485.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7492', 'ds', 'http://maopu.tv/ds/7492.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7493', 'ds', 'http://maopu.tv/ds/7493.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7494', 'ds', 'http://maopu.tv/ds/7494.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7495', 'ds', 'http://maopu.tv/ds/7495.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7496', 'ds', 'http://maopu.tv/ds/7496.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7497', 'ds', 'http://maopu.tv/ds/7497.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7545', 'ds', 'http://maopu.tv/ds/7545.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7638', 'dy', 'http://maopu.tv/dy/7638.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7639', 'dy', 'http://maopu.tv/dy/7639.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7640', 'dy', 'http://maopu.tv/dy/7640.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7641', 'dy', 'http://maopu.tv/dy/7641.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7642', 'dy', 'http://maopu.tv/dy/7642.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7643', 'dy', 'http://maopu.tv/dy/7643.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7644', 'dy', 'http://maopu.tv/dy/7644.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7645', 'dy', 'http://maopu.tv/dy/7645.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7646', 'dy', 'http://maopu.tv/dy/7646.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7647', 'dy', 'http://maopu.tv/dy/7647.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7648', 'dy', 'http://maopu.tv/dy/7648.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7649', 'dy', 'http://maopu.tv/dy/7649.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7650', 'dy', 'http://maopu.tv/dy/7650.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7651', 'dy', 'http://maopu.tv/dy/7651.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7652', 'dy', 'http://maopu.tv/dy/7652.html', '1', null);
INSERT INTO `mpdy_source` VALUES ('7653', 'dy', 'http://maopu.tv/dy/7653.html', '1', null);

-- ----------------------------
-- Table structure for `phdy_hot`
-- ----------------------------
DROP TABLE IF EXISTS `phdy_hot`;
CREATE TABLE `phdy_hot` (
  `id` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `date` int(8) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phdy_hot
-- ----------------------------

-- ----------------------------
-- Table structure for `phdy_new`
-- ----------------------------
DROP TABLE IF EXISTS `phdy_new`;
CREATE TABLE `phdy_new` (
  `id` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `date` int(8) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phdy_new
-- ----------------------------

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
