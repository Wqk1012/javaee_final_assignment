/*
Navicat MySQL Data Transfer

Source Server         : javaweb
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2025-01-01 11:54:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `author`
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of author
-- ----------------------------
INSERT INTO `author` VALUES ('1', '鲁迅');
INSERT INTO `author` VALUES ('2', '金庸');
INSERT INTO `author` VALUES ('3', '老舍');
INSERT INTO `author` VALUES ('4', '莫言');
INSERT INTO `author` VALUES ('5', '余华');

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `author_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `published_year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  KEY `category_id` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '呐喊', '1', '1', '1923');
INSERT INTO `book` VALUES ('2', '笑傲江湖', '2', '2', '1967');
INSERT INTO `book` VALUES ('3', '骆驼祥子', '3', '1', '1936');
INSERT INTO `book` VALUES ('4', '红高粱家族', '4', '3', '1986');
INSERT INTO `book` VALUES ('5', '活着', '5', '3', '1992');
INSERT INTO `book` VALUES ('6', '书剑恩仇录', '2', '2', '1955');
INSERT INTO `book` VALUES ('7', '边城', '1', '1', '1934');
INSERT INTO `book` VALUES ('8', '平凡的世界', '3', '1', '1986');
INSERT INTO `book` VALUES ('9', '白鹿原', '4', '3', '1993');
INSERT INTO `book` VALUES ('10', '许三观卖血记', '5', '3', '1995');

-- ----------------------------
-- Table structure for `borrowrecord`
-- ----------------------------
DROP TABLE IF EXISTS `borrowrecord`;
CREATE TABLE `borrowrecord` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  `borrow_date` date NOT NULL,
  `return_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrowrecord
-- ----------------------------
INSERT INTO `borrowrecord` VALUES ('1', '2', '1', '2024-01-01', '2024-01-15');
INSERT INTO `borrowrecord` VALUES ('2', '3', '2', '2024-01-03', '2024-12-31');
INSERT INTO `borrowrecord` VALUES ('3', '4', '3', '2024-01-05', '2024-01-20');
INSERT INTO `borrowrecord` VALUES ('4', '2', '4', '2024-01-10', null);
INSERT INTO `borrowrecord` VALUES ('5', '3', '5', '2024-01-12', '2024-01-22');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '文学');
INSERT INTO `category` VALUES ('2', '武侠');
INSERT INTO `category` VALUES ('3', '现代小说');
INSERT INTO `category` VALUES ('4', '历史');
INSERT INTO `category` VALUES ('5', '传记');

-- ----------------------------
-- Table structure for `reservation`
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  `reservation_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reservation
-- ----------------------------
INSERT INTO `reservation` VALUES ('1', '2', '6', '2024-01-11');
INSERT INTO `reservation` VALUES ('2', '3', '7', '2024-01-12');
INSERT INTO `reservation` VALUES ('3', '4', '8', '2024-01-15');
INSERT INTO `reservation` VALUES ('4', '2', '9', '2024-01-16');
INSERT INTO `reservation` VALUES ('5', '3', '10', '2024-01-17');
INSERT INTO `reservation` VALUES ('6', '4', '4', '2024-12-31');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `avatar_path` varchar(255) DEFAULT NULL COMMENT '头像路径',
  `email` varchar(100) NOT NULL COMMENT '邮箱地址',
  `role` enum('ADMIN','USER') NOT NULL COMMENT '用户角色',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '$2a$10$IOccgkluZt4FhEYDu3c5YeluNrr5OSCbNog7rE6RerTM0bj2vkSaS', '/avatars/admin.jpg', 'admin@example.com', 'ADMIN');
INSERT INTO `user` VALUES ('2', 'zhangsan', 'password123', '/avatars/zhangsan.jpg', 'zhangsan@example.com', 'USER');
INSERT INTO `user` VALUES ('3', 'lisi', 'password456', '/avatars/lisi.jpg', 'lisi@example.com', 'USER');
INSERT INTO `user` VALUES ('4', 'wangwu', 'password789', '/avatars/wangwu.jpg', 'wangwu@example.com', 'USER');
INSERT INTO `user` VALUES ('5', 'zhaoliu', 'password987', '/avatars/zhaoliu.jpg', 'zhaoliu@example.com', 'USER');
INSERT INTO `user` VALUES ('6', 'tianqi', 'password654', '/avatars/tianqi.jpg', 'tianqi@example.com', 'USER');
INSERT INTO `user` VALUES ('8', 'zzzz', '$2a$10$IOccgkluZt4FhEYDu3c5YeluNrr5OSCbNog7rE6RerTM0bj2vkSaS', 'D:\\img\\286660f0-83bb-42f2-88ec-1c26b0507556.jpg', '2472401292@qq.com', 'USER');
