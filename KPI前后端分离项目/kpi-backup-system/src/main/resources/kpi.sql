/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : kpi

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-01-23 02:08:59
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formssi_account
-- ----------------------------
INSERT INTO `formssi_account` VALUES ('1', 'smp', '9cfc4eaf37a0eabac81a26d58562c9d5', '2017-12-17 19:48:07', '2017-12-17 19:48:10', 'zhengxunsen@formssi.com');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formssi_import_flag
-- ----------------------------
INSERT INTO `formssi_import_flag` VALUES ('6', '', '2018-01-09 23:09:51', '2018', '2', 'smp');

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
  `normal_work` double(5,3) DEFAULT NULL,
  `program_serial_num` varchar(50) NOT NULL,
  `program_name` varchar(50) NOT NULL,
  `date` varchar(10) NOT NULL,
  `year` int(4) NOT NULL,
  `month` int(2) NOT NULL,
  `member_id` bigint(3) NOT NULL,
  `version` int(2) NOT NULL COMMENT '乐观锁',
  `department` varchar(50) NOT NULL,
  `work_type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formssi_kpi
-- ----------------------------
INSERT INTO `formssi_kpi` VALUES ('1832', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/24', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1833', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/11', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1834', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/01', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1835', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/02', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1836', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/05', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1837', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/06', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1838', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/07', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1839', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/08', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1840', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/09', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1841', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/12', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1842', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/13', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1843', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/14', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1844', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/22', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1845', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/23', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1846', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/26', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1847', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/27', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1848', '梁月华', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/28', '2018', '2', '1', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1849', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/24', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1850', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/11', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1851', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/01', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1852', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/02', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1853', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/05', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1854', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/06', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1855', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/07', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1856', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/08', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1857', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/09', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1858', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/12', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1859', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/13', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1860', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/14', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1861', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/22', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1862', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/23', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1863', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/26', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1864', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/27', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1865', '叶继萍', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/28', '2018', '2', '2', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1866', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/24', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1867', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/11', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1868', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/01', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1869', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/02', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1870', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/05', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1871', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/06', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1872', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/07', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1873', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/08', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1874', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/09', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1875', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/12', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1876', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/13', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1877', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/14', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1878', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/22', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1879', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/23', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1880', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/26', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1881', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/27', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1882', '马逸放', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/28', '2018', '2', '3', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1883', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/24', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1884', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/11', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1885', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/01', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1886', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/02', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1887', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/05', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1888', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/06', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1889', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/07', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1890', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/08', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1891', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/09', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1892', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/12', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1893', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/13', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1894', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/14', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1895', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/22', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1896', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/23', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1897', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/26', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1898', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/27', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1899', '丁琳Claudia', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/28', '2018', '2', '4', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1900', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/24', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1901', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/11', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1902', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/01', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1903', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/02', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1904', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/05', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1905', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/06', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1906', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/07', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1907', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/08', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1908', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/09', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1909', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/12', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1910', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/13', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1911', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/14', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1912', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/22', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1913', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/23', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1914', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/26', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1915', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/27', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1916', '董超', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/28', '2018', '2', '5', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1917', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/24', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1918', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/11', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1919', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/01', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1920', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/02', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1921', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/05', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1922', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/06', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1923', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/07', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1924', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/08', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1925', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/09', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1926', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/12', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1927', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/13', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1928', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/14', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1929', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/22', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1930', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/23', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1931', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/26', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1932', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/27', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1933', '林俊斌', 'AP', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-AP', '中银香港-香港-开放系统-销售流程管理系统-中级', '2018/02/28', '2018', '2', '6', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1934', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/24', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1935', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/11', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1936', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/01', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1937', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/02', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1938', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/05', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1939', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/06', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1940', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/07', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1941', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/08', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1942', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/09', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1943', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/12', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1944', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/13', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1945', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/14', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1946', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/22', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1947', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/23', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1948', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/26', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1949', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/27', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1950', '李一珉', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/28', '2018', '2', '7', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1951', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/24', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1952', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/11', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1953', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/01', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1954', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/02', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1955', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/05', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1956', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/06', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1957', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/07', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1958', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/08', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1959', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/09', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1960', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/12', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1961', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/13', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1962', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/14', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1963', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/22', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1964', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/23', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1965', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/26', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1966', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/27', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1967', '黄务强', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/28', '2018', '2', '8', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1968', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/24', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1969', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/11', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1970', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/01', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1971', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/02', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1972', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/05', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1973', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/06', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1974', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/07', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1975', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/08', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1976', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/09', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1977', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/12', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1978', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/13', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1979', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/14', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1980', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/22', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1981', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/23', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1982', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/26', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1983', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/27', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1984', '胡胜军', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/28', '2018', '2', '9', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1985', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/24', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1986', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/11', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1987', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/01', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1988', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/02', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1989', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/05', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1990', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/06', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1991', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/07', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1992', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/08', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1993', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/09', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1994', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/12', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1995', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/13', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1996', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/14', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1997', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/22', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1998', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/23', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('1999', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/26', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2000', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/27', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2001', '罗鹏', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/28', '2018', '2', '10', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2002', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/24', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2003', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/11', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2004', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/01', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2005', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/02', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2006', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/05', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2007', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/06', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2008', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/07', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2009', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/08', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2010', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/09', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2011', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/12', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2012', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/13', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2013', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/14', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2014', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/22', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2015', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/23', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2016', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/26', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2017', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/27', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2018', '谢云', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/28', '2018', '2', '11', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2019', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/24', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2020', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/11', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2021', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/01', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2022', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/02', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2023', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/05', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2024', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/06', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2025', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/07', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2026', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/08', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2027', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/09', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2028', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/12', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2029', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/13', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2030', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/14', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2031', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/22', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2032', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/23', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2033', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/26', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2034', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/27', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2035', '王先彭', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/28', '2018', '2', '12', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2036', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/24', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2037', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/11', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2038', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/01', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2039', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/02', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2040', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/05', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2041', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/06', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2042', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/07', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2043', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/08', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2044', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/09', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2045', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/12', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2046', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/13', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2047', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/14', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2048', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/22', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2049', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/23', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2050', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/26', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2051', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/27', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2052', '黄彦滨', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/28', '2018', '2', '13', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2053', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/24', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2054', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/11', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2055', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/01', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2056', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/02', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2057', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/05', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2058', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/06', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2059', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/07', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2060', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/08', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2061', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/09', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2062', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/12', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2063', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/13', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2064', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/14', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2065', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/22', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2066', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/23', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2067', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/26', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2068', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/27', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2069', '张洁', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/28', '2018', '2', '14', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2070', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/24', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2071', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/11', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2072', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/01', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2073', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/02', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2074', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/05', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2075', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/06', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2076', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/07', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2077', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/08', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2078', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/09', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2079', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/12', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2080', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/13', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2081', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/14', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2082', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/22', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2083', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/23', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2084', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/26', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2085', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/27', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2086', '郑训森', 'PR', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-PR', '中银香港-香港-开放系统-销售流程管理系统-初级', '2018/02/28', '2018', '2', '15', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2087', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/24', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2088', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/11', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2089', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/01', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2090', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/02', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2091', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/05', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2092', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/06', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2093', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/07', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2094', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/08', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2095', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/09', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2096', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/12', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2097', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/13', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2098', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/14', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2099', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/22', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2100', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/23', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2101', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/26', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2102', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/27', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2103', 'wkk', 'SA', null, null, null, '1.000', 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/28', '2018', '2', '31', '1', 'smp', 'NormalWork');
INSERT INTO `formssi_kpi` VALUES ('2108', 'wkk', 'SA', null, null, '0.500', null, 'BOCHK-HK-OPEN-SMP-SA', '中银香港-香港-开放系统-销售流程管理系统-高级', '2018/02/16', '2018', '2', '31', '1', 'smp', 'ExtraWork');

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formssi_kpi_balance
-- ----------------------------
INSERT INTO `formssi_kpi_balance` VALUES ('1', '1', '0', '梁月华', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('2', '2', '0', '叶继萍', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('3', '3', '0', '马逸放', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('4', '4', '0', '丁琳Claudia', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('5', '5', '0', '董超', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('6', '6', '0', '林俊斌', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('7', '7', '0', '李一珉', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('8', '8', '0', '黄务强', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('9', '9', '0', '胡胜军', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('10', '10', '0', '罗鹏', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('11', '11', '0', '谢云', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('12', '12', '0', '王先彭', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('13', '13', '0', '黄彦滨', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('14', '14', '0', '张洁', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('15', '15', '0', '郑训森', 'smp');
INSERT INTO `formssi_kpi_balance` VALUES ('16', '31', '10.5', 'wkk', 'smp');

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
INSERT INTO `formssi_kpi_style` VALUES ('2', 'smp', 'BOCHK-HK-OPEN-SMP-', '中银香港-香港-开放系统-销售流程管理系统-', '2017-12-19 20:41:09', '2018-01-22 23:07:16');
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formssi_member
-- ----------------------------
INSERT INTO `formssi_member` VALUES ('1', '梁月华', 'SA', 'smp', '2017-11-04 13:30:22', null);
INSERT INTO `formssi_member` VALUES ('2', '叶继萍', 'AP', 'smp', '2017-11-04 13:31:07', null);
INSERT INTO `formssi_member` VALUES ('3', '马逸放', 'PR', 'smp', '2017-11-04 13:32:35', null);
INSERT INTO `formssi_member` VALUES ('4', '丁琳Claudia', 'PR', 'smp', '2017-11-04 13:33:11', null);
INSERT INTO `formssi_member` VALUES ('5', '董超', 'PR', 'smp', '2017-11-04 13:33:28', null);
INSERT INTO `formssi_member` VALUES ('6', '林俊斌', 'AP', 'smp', '2017-11-04 13:33:51', null);
INSERT INTO `formssi_member` VALUES ('7', '李一珉', 'PR', 'smp', '2017-11-04 13:34:13', null);
INSERT INTO `formssi_member` VALUES ('8', '黄务强', 'PR', 'smp', '2017-11-04 13:34:37', null);
INSERT INTO `formssi_member` VALUES ('9', '胡胜军', 'PR', 'smp', '2017-11-04 13:35:07', null);
INSERT INTO `formssi_member` VALUES ('10', '罗鹏', 'PR', 'smp', '2017-11-04 13:35:57', null);
INSERT INTO `formssi_member` VALUES ('11', '谢云', 'PR', 'smp', '2017-11-04 13:36:23', null);
INSERT INTO `formssi_member` VALUES ('12', '王先彭', 'PR', 'smp', '2017-11-04 13:36:45', null);
INSERT INTO `formssi_member` VALUES ('13', '黄彦滨', 'PR', 'smp', '2017-11-04 13:37:54', null);
INSERT INTO `formssi_member` VALUES ('14', '张洁', 'SA', 'smp', '2017-11-04 13:37:57', null);
INSERT INTO `formssi_member` VALUES ('15', '郑训森', 'PR', 'smp', '2017-11-04 13:37:57', null);
INSERT INTO `formssi_member` VALUES ('31', 'wkk', 'SA', 'smp', null, '2018-01-06 14:19:12');

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
