/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : kpi

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-02-08 22:01:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `formssi_account`
-- ----------------------------
DROP TABLE IF EXISTS `formssi_account`;
CREATE TABLE `formssi_account` (
  `account_id` bigint(5) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formssi_account
-- ----------------------------
INSERT INTO `formssi_account` VALUES ('2', 'wkk', '1b5e8c9fb9388e383c9ceeb4a38b4a1e', '2018-01-30 23:18:38', '2018-01-30 23:18:38', 'wankangkang@formssi.com');
INSERT INTO `formssi_account` VALUES ('3', 'test', '4de0fe9cf9f47ce5af891d6182d6c4b7', '2018-02-06 21:59:41', '2018-02-06 21:59:41', 'zhengxunsen@formssi.com');

-- ----------------------------
-- Table structure for `formssi_import_flag`
-- ----------------------------
DROP TABLE IF EXISTS `formssi_import_flag`;
CREATE TABLE `formssi_import_flag` (
  `flag_id` bigint(5) NOT NULL AUTO_INCREMENT,
  `import_flag` bit(1) NOT NULL COMMENT '判断导入条件',
  `import_time` datetime DEFAULT NULL,
  `year` int(4) NOT NULL,
  `month` int(2) NOT NULL,
  `department` varchar(200) NOT NULL,
  PRIMARY KEY (`flag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formssi_import_flag
-- ----------------------------

-- ----------------------------
-- Table structure for `formssi_kpi`
-- ----------------------------
DROP TABLE IF EXISTS `formssi_kpi`;
CREATE TABLE `formssi_kpi` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `member_name` varchar(30) NOT NULL,
  `rank` varchar(10) NOT NULL,
  `staff_id` varchar(10) DEFAULT NULL,
  `sort_sign` varchar(10) DEFAULT NULL,
  `extra_work` double(5,3) DEFAULT NULL,
  `normal_work` double(5,3) DEFAULT '1.000',
  `program_serial_num` varchar(50) NOT NULL,
  `program_name` varchar(50) NOT NULL,
  `date` varchar(10) NOT NULL,
  `year` int(4) NOT NULL,
  `month` int(2) NOT NULL,
  `member_id` bigint(3) NOT NULL,
  `version` int(2) NOT NULL COMMENT '乐观锁',
  `department` varchar(50) NOT NULL,
  `work_type` varchar(50) NOT NULL,
  `version2` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2982 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formssi_kpi
-- ----------------------------

-- ----------------------------
-- Table structure for `formssi_kpi_balance`
-- ----------------------------
DROP TABLE IF EXISTS `formssi_kpi_balance`;
CREATE TABLE `formssi_kpi_balance` (
  `kpi_balance_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL,
  `kpi_balance` double DEFAULT NULL,
  `member_name` varchar(50) NOT NULL,
  `department` varchar(30) NOT NULL,
  PRIMARY KEY (`kpi_balance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formssi_kpi_balance
-- ----------------------------
INSERT INTO `formssi_kpi_balance` VALUES ('16', '31', '14.875', 'wkk', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('17', '32', '0', 'test', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('20', '35', '0', '蠢蒙', 'wkk');

-- ----------------------------
-- Table structure for `formssi_kpi_style`
-- ----------------------------
DROP TABLE IF EXISTS `formssi_kpi_style`;
CREATE TABLE `formssi_kpi_style` (
  `style_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `department` varchar(50) NOT NULL,
  `program_serial_num_prefix` varchar(100) NOT NULL,
  `program_name_prefix` varchar(100) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`style_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formssi_kpi_style
-- ----------------------------
INSERT INTO `formssi_kpi_style` VALUES ('1', 'default', 'BOCHK-HK-OPEN-SMP-', '中银香港-香港-开放系统-销售流程管理系统-', '2017-12-18 23:37:57', '2017-12-18 23:38:00');
INSERT INTO `formssi_kpi_style` VALUES ('2', 'smp', 'BOCHK-HK-OPEN-SMP-11', '中银香港-香港-开放系统-销售流程管理系统-', '2017-12-19 20:41:09', '2018-01-29 22:33:06');
INSERT INTO `formssi_kpi_style` VALUES ('7', 'string', 'fadfasdg', 'asdfasd', '2018-01-23 02:02:12', '2018-01-23 02:03:03');

-- ----------------------------
-- Table structure for `formssi_member`
-- ----------------------------
DROP TABLE IF EXISTS `formssi_member`;
CREATE TABLE `formssi_member` (
  `member_id` bigint(5) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `membername` varchar(30) NOT NULL COMMENT '姓名',
  `rank` varchar(10) DEFAULT NULL COMMENT '级别',
  `department` varchar(30) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formssi_member
-- ----------------------------
INSERT INTO `formssi_member` VALUES ('31', 'wkk', 'SA', 'smp', null, '2018-01-06 14:19:12');
INSERT INTO `formssi_member` VALUES ('32', 'test', 'T1', 'smp', '2018-01-30 11:00:01', '2018-01-30 11:00:01');
INSERT INTO `formssi_member` VALUES ('35', '蠢蒙', 'T1', 'wkk', '2018-01-30 23:28:21', '2018-01-30 23:28:21');

-- ----------------------------
-- Table structure for `holiday`
-- ----------------------------
DROP TABLE IF EXISTS `holiday`;
CREATE TABLE `holiday` (
  `holiday_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `holiday_type` varchar(30) NOT NULL,
  `holiday_date` varchar(30) NOT NULL,
  `year` int(4) NOT NULL,
  `month` int(2) NOT NULL,
  `title` varchar(30) NOT NULL,
  `vacation` int(2) DEFAULT NULL,
  PRIMARY KEY (`holiday_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of holiday
-- ----------------------------
INSERT INTO `holiday` VALUES ('1', 'holiday', '2018/01/01', '2018', '1', '元旦', '1');
INSERT INTO `holiday` VALUES ('2', 'holiday', '2018/02/15', '2018', '2', '除夕', '1');
INSERT INTO `holiday` VALUES ('3', 'holiday', '2018/02/16', '2018', '2', '春节', '1');
INSERT INTO `holiday` VALUES ('4', 'holiday', '2018/02/19', '2018', '2', '休', '1');
INSERT INTO `holiday` VALUES ('5', 'holiday', '2018/02/20', '2018', '2', '休', '1');
INSERT INTO `holiday` VALUES ('6', 'holiday', '2018/02/21', '2018', '2', '休', '1');
INSERT INTO `holiday` VALUES ('7', 'workday', '2018/02/24', '2018', '2', '班', '0');
INSERT INTO `holiday` VALUES ('8', 'workday', '2018/02/11', '2018', '2', '班', '0');
INSERT INTO `holiday` VALUES ('9', 'holiday', '2018/04/05', '2018', '4', '清明', '1');
INSERT INTO `holiday` VALUES ('10', 'holiday', '2018/04/06', '2018', '4', '休', '1');
INSERT INTO `holiday` VALUES ('11', 'workday', '2018/04/08', '2018', '4', '班', '0');
INSERT INTO `holiday` VALUES ('12', 'workday', '2018/04/28', '2018', '4', '班', '0');
INSERT INTO `holiday` VALUES ('13', 'holiday', '2018/04/30', '2018', '4', '休', '1');
INSERT INTO `holiday` VALUES ('14', 'holiday', '2018/05/01', '2018', '5', '劳动节', '1');
INSERT INTO `holiday` VALUES ('15', 'holiday', '2018/06/18', '2018', '6', '端午节', '1');
INSERT INTO `holiday` VALUES ('16', 'holiday', '2018/09/24', '2018', '9', '中秋节', '1');
INSERT INTO `holiday` VALUES ('17', 'workday', '2018/09/29', '2018', '9', '班', '0');
INSERT INTO `holiday` VALUES ('18', 'workday', '2018/09/30', '2018', '9', '班', '0');
INSERT INTO `holiday` VALUES ('19', 'holiday', '2018/10/01', '2018', '10', '国庆节', '1');
INSERT INTO `holiday` VALUES ('20', 'holiday', '2018/10/02', '2018', '10', '休', '1');
INSERT INTO `holiday` VALUES ('21', 'holiday', '2018/10/03', '2018', '10', '休', '1');
INSERT INTO `holiday` VALUES ('22', 'holiday', '2018/10/04', '2018', '10', '休', '1');
INSERT INTO `holiday` VALUES ('23', 'holiday', '2018/10/05', '2018', '10', '休', '1');
