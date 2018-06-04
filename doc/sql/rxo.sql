/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 127.0.0.1:3306
 Source Schema         : rxo

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 04/06/2018 17:28:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `in_use` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sex_code` varchar(255) DEFAULT NULL,
  `super_master` bit(1) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `tenant_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account` VALUES ('97A2887D08EE423FB738C49EE39F93E5', NULL, b'1', NULL, b'1', 'admin', 'a29a95d42db20c30aa265394ed2f52346f53ce84f6b91e0b6a918e4ef754317e2d1cbbb6882ffd96', 'FEMALE', b'1', NULL, 'admin', NULL, '00000000000', '1');
COMMIT;

-- ----------------------------
-- Table structure for account_role_mapping
-- ----------------------------
DROP TABLE IF EXISTS `account_role_mapping`;
CREATE TABLE `account_role_mapping` (
  `id` varchar(255) NOT NULL,
  `account_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `tenant_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_role_mapping
-- ----------------------------
BEGIN;
INSERT INTO `account_role_mapping` VALUES ('00B6F359E795409988C2BF2F1B500D2C', '97A2887D08EE423FB738C49EE39F93E5', 'E6AC5CCEAFC24A18B5827DCD460D70C0', NULL, '1');
COMMIT;

-- ----------------------------
-- Table structure for dictionary_business
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_business`;
CREATE TABLE `dictionary_business` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `tenant_id` varchar(255) CHARACTER SET utf8 NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `code` varchar(255) COLLATE utf8_bin NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for dictionary_item
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_item`;
CREATE TABLE `dictionary_item` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `type_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `tenant_id` varchar(255) CHARACTER SET utf8 NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `code` varchar(255) COLLATE utf8_bin NOT NULL,
  `sort` int(2) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_type`;
CREATE TABLE `dictionary_type` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `business_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `tenant_id` varchar(255) CHARACTER SET utf8 NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `code` varchar(255) COLLATE utf8_bin NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `order_index` int(11) DEFAULT NULL,
  `parent_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `function` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resource
-- ----------------------------
BEGIN;
INSERT INTO `resource` VALUES ('001', 'gear', '系统管理', NULL, '-1', 'menu', '', NULL, 'systemMgr', '1');
INSERT INTO `resource` VALUES ('001001', NULL, '资源配置', 1, '001', 'page', 'index_v1.html', NULL, 'systemMgr.resource', '1');
INSERT INTO `resource` VALUES ('001002', NULL, '角色管理', 2, '001', 'page', 'admin/role.html', NULL, 'systemMgr.role', '1');
INSERT INTO `resource` VALUES ('001003', NULL, '用户管理', 3, '001', 'page', 'index_v1.html', NULL, 'systemMgr.account', '1');
INSERT INTO `resource` VALUES ('001004', NULL, '权限管理', 4, '001', 'page', 'index_v1.html', NULL, 'systemMgr.roleWithResource', '1');
INSERT INTO `resource` VALUES ('001005', NULL, '数据字典', 5, '001', 'page', 'admin/dict.html', NULL, 'systemMgr.dict', '1');
INSERT INTO `resource` VALUES ('002', 'gear', '关键词管理', NULL, '-1', 'menu', '', NULL, 'systemMgr', '1');
INSERT INTO `resource` VALUES ('002001', NULL, '关键词', 1, '002', 'page', 'business/dict/keyword_dict.html', NULL, 'keywordMgr.customdict', '1');
INSERT INTO `resource` VALUES ('002002', NULL, '同义词', 2, '002', 'page', 'business/dict/synonym_dict.html', NULL, 'keywordMgr.synonymdict', '1');
INSERT INTO `resource` VALUES ('002003', NULL, '敏感词', 3, '002', 'page', 'business/dict/sensitive_dcit.html', NULL, 'keywordMgr.sensitivedcit', '1');
INSERT INTO `resource` VALUES ('002004', NULL, '停止词', 4, '002', 'page', 'business/dict/stop_dict.html', NULL, 'keywordMgr.stopdict', '1');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `tenant_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES ('E6AC5CCEAFC24A18B5827DCD460D70C0', NULL, '系统管理员', '1');
COMMIT;

-- ----------------------------
-- Table structure for role_resource_mapping
-- ----------------------------
DROP TABLE IF EXISTS `role_resource_mapping`;
CREATE TABLE `role_resource_mapping` (
  `id` varchar(255) NOT NULL,
  `resource_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `tenant_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resource_mapping
-- ----------------------------
BEGIN;
INSERT INTO `role_resource_mapping` VALUES ('365713F83DA14030BB374423E97FBF52', '001001', 'E6AC5CCEAFC24A18B5827DCD460D70C0', NULL, '1');
INSERT INTO `role_resource_mapping` VALUES ('365713F83DA14030BB374423E97FBF53', '002001', 'E6AC5CCEAFC24A18B5827DCD460D70C0', NULL, '1');
INSERT INTO `role_resource_mapping` VALUES ('4665449B75A9440A8612D86F91068E81', '001003', 'E6AC5CCEAFC24A18B5827DCD460D70C0', NULL, '1');
INSERT INTO `role_resource_mapping` VALUES ('4665449B75A9440A8612D86F91068E82', '002003', 'E6AC5CCEAFC24A18B5827DCD460D70C0', NULL, '1');
INSERT INTO `role_resource_mapping` VALUES ('4787F421693549B3A1E680574879C30C', '001004', 'E6AC5CCEAFC24A18B5827DCD460D70C0', NULL, '1');
INSERT INTO `role_resource_mapping` VALUES ('4787F421693549B3A1E680574879C30d', '002004', 'E6AC5CCEAFC24A18B5827DCD460D70C0', NULL, '1');
INSERT INTO `role_resource_mapping` VALUES ('8D7A803F473A4BAAA74CAD92BFFD56E0', '001', 'E6AC5CCEAFC24A18B5827DCD460D70C0', NULL, '1');
INSERT INTO `role_resource_mapping` VALUES ('8D7A803F473A4BAAA74CAD92BFFD56E1', '002', 'E6AC5CCEAFC24A18B5827DCD460D70C0', NULL, '1');
INSERT INTO `role_resource_mapping` VALUES ('D8BA57D075C949DD97371F6B0BC0A781', '001002', 'E6AC5CCEAFC24A18B5827DCD460D70C0', NULL, '1');
INSERT INTO `role_resource_mapping` VALUES ('D8BA57D075C949DD97371F6B0BC0A782', '002002', 'E6AC5CCEAFC24A18B5827DCD460D70C0', NULL, '1');
INSERT INTO `role_resource_mapping` VALUES ('F6D6E010AF6F4D03B5E2FF29871E50F4', '001005', 'E6AC5CCEAFC24A18B5827DCD460D70C0', NULL, '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
