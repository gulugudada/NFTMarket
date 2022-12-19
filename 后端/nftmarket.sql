/*
 Navicat Premium Data Transfer

 Source Server         : xiaoxiaoguai
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : nftmarket

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 19/12/2022 15:56:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `createTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `lastTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '最近登录的时间',
  `lastLoginIp` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '最后登录的ip',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像URL',
  PRIMARY KEY (`id`, `account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (2, '19974712583', '小小怪', '69c5fcebaa65b560eaf06c3fbeb481ae44b8d618', '2022-09-13 12:48:11', NULL, '2022-12-09 16:13:27', '0:0:0:0:0:0:0:1', '/avatar/837cd9d2de90455cb674d3144baa7b00.jpg');
INSERT INTO `account` VALUES (3, '123456', '123456', '69c5fcebaa65b560eaf06c3fbeb481ae44b8d618', '2022-09-17 15:08:17', NULL, '2022-12-02 16:40:35', '0:0:0:0:0:0:0:1', '/avatar/390dc2e41e284bb7b70eb4c16d568e95.jpg');
INSERT INTO `account` VALUES (4, '123', '123', '69c5fcebaa65b560eaf06c3fbeb481ae44b8d618', '2022-09-27 13:23:41', NULL, '2022-10-06 14:19:15', '0:0:0:0:0:0:0:1', '/avatar/9f6aa117a9cb4ca9885588e3b5812328.jpg');

-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '操作账号',
  `actionId` int(12) NULL DEFAULT NULL COMMENT '操作id',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作类型（动态、关注、博客）',
  `actionType` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作类型（删除、关注、取关、收藏）',
  `actionTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`, `account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of action
-- ----------------------------
INSERT INTO `action` VALUES (1, '123', 11, 'blog', '点赞', '2022-11-06 01:19:27');

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '发布账号',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '博客内容',
  `thumbsUpCount` int(12) NULL DEFAULT 0 COMMENT '点赞数',
  `thumbsDownCount` int(12) NULL DEFAULT 0 COMMENT '踩的次数',
  `visits` int(12) NULL DEFAULT 0 COMMENT '查看次数',
  `createTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '发布时间',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '封面',
  PRIMARY KEY (`id`, `name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, 'hnucm_oj 算法分析与设计 练习13', '19974712583', 'hnucm_oj 算法分析与设计 练习13', 1, 1000, 1000, '2021-05-29 13:57:14', NULL);
INSERT INTO `blog` VALUES (2, 'Oracle 大型数据库技术 2021实验项目八-附加', '19974712583', 'Oracle 大型数据库技术 2021实验项目八-附加', 10, 2, 500, '2021-05-30 16:39:01', NULL);
INSERT INTO `blog` VALUES (3, 'hnucm_oj 算法分析与设计 练习14', '19974712583', 'hnucm_oj 算法分析与设计 练习14', 2, 0, 0, '2021-06-03 17:32:00', NULL);
INSERT INTO `blog` VALUES (4, 'hnucm_oj 算法分析与设计 练习15', '19974712583', 'hnucm_oj 算法分析与设计 练习15', 0, 0, 0, '2021-06-10 19:24:28', NULL);
INSERT INTO `blog` VALUES (5, 'hnucm_oj 算法分析与设计 练习16', '19974712583', 'hnucm_oj 算法分析与设计 练习16', 0, 0, 0, '2021-06-24 19:49:06', NULL);
INSERT INTO `blog` VALUES (6, '尚硅谷以太坊区块链学习（1）', '19974712583', '尚硅谷以太坊区块链学习（1）', 0, 0, 0, '2022-03-27 20:16:30', NULL);
INSERT INTO `blog` VALUES (7, '尚硅谷以太坊区块链学习（2）', '123', '尚硅谷以太坊区块链学习（2）', 0, 0, 0, '2022-03-24 16:09:18', NULL);
INSERT INTO `blog` VALUES (8, '尚硅谷以太坊区块链学习（3）', '19974712583', '尚硅谷以太坊区块链学习（3）', 0, 0, 0, '2022-04-04 01:46:44', NULL);
INSERT INTO `blog` VALUES (9, '尚硅谷以太坊区块链学习（4）', '19974712583', '尚硅谷以太坊区块链学习（4）', 0, 0, 0, '2022-04-04 21:09:27', NULL);
INSERT INTO `blog` VALUES (10, '尚硅谷以太坊区块链学习之NFT智能合约（6）', '19974712583', '尚硅谷以太坊区块链学习之NFT智能合约（6）', 0, 0, 0, '2022-04-16 23:16:42', NULL);
INSERT INTO `blog` VALUES (11, '尚硅谷以太坊区块链学习之Web3j（7）', '19974712583', '尚硅谷以太坊区块链学习之Web3j（7）', 3, 0, 0, '2022-04-17 21:36:34', NULL);
INSERT INTO `blog` VALUES (12, '软件测试之接口测试实验', '19974712583', '软件测试之接口测试实验', 0, 0, 0, '2022-05-13 20:56:40', NULL);
INSERT INTO `blog` VALUES (13, '推荐20个开源的不错前端低代码项目', '19974712583', '近几年，在技术领域低代码是比较热门的话题，比如阿里云推出了易搭，通过简单的拖拽、配置，即可完成业务应用的搭建，腾讯云则是推出了微搭，通过行业化模板、拖放式组件和可视化配置快速构建多端应用。低代码是基于可视化和模型驱动理念，结合云原生与多端体验技术，它能够在多数业务场景下实现大幅度的提效降本，为专业开发者提供了一种全新的高生产力开发范式。下面就来分享几个值得学习和使用的低代码开源项目，更深入地了解什么是低代码。', 0, 0, 1001, '2022-11-15 09:22:29', '/blog/cover/e278fe2e2d3748d4a2d28838a1b13ace.png');
INSERT INTO `blog` VALUES (14, 'Web大学生网页作业成品——环保垃圾分类网站设计与实现(HTML+CSS+JavaScript) web前端开发技术 web课程设计 网页规划与设计', '19974712583', '环境保护、 保护地球、 校园环保、垃圾分类、绿色家园、等网站的设计与制作。总结了一些学生网页制作的经验：一般的网页需要融入以下知识点：div+css布局、浮动、定位、高级css、表格、表单及验证、js轮播图、音频 视频 Flash的应用、ul li、下拉导航栏、鼠标划过效果等知识点，网页的风格主题也很全面：如爱好、风景、校园、美食、动漫、游戏、咖啡、音乐、家乡、电影、名人、商城以及个人主页等主题，学生、新手可参考下方页面的布局和设计和HTML源码（有用点赞） 一套A+的网', 0, 0, 1002, '2022-11-14 05:46:42', '/blog/cover/1668150833681.png');
INSERT INTO `blog` VALUES (15, '公司缺人自己搞了vue又搞koa，熬夜把架子搭起来', '19974712583', '如果有一天，人手紧缺，自己搞了前端还要搞服务端，今天我们把这个项目架子搭起来，让前端同学也可以轻松全栈开火。', 0, 0, 1003, '2022-11-08 13:18:52', '/blog/cover/919bb405dacb43bca5288423bfa643cc.png');
INSERT INTO `blog` VALUES (16, '优雅写代码的45个小技巧', '19974712583', '比如在项目中不同的类型的业务可能需要上传各种各样的附件，此时就可以定义好不同的一个附件的枚举，来区分不同业务的附件。不要在代码中直接写死，不定义枚举，代码阅读起来非常困难，直接看到数字都是懵逼的。。', 0, 0, 1004, '2022-11-06 03:45:00', '/blog/cover/8046ea57db754f67ad44c57060ab0755.png');
INSERT INTO `blog` VALUES (17, 'HTML小游戏6 —— 《高达战争》横版射击游戏（附完整源码）', '19974712583', '本节教程我会带大家使用 HTML 、CSS和 JS 来制作一个本节示例将会实现如下所示的效果：源码也可在文末进行获取。', 0, 0, 1005, '2022-11-11 13:29:26', '/blog/cover/e8b5a9e278ba40afa639a4f48adb68c0.png');

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `from` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `to` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sendTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES (3, '19974712583', '123', '123', '2022-11-23 01:16');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `collectId` int(12) NULL DEFAULT NULL COMMENT '收藏id',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '收藏的类型',
  `collectTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`, `account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '评论账号',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评论内容',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评论类型（博文、动态、评论）',
  `typeId` int(12) NULL DEFAULT NULL COMMENT '评论目标id',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`, `account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `owner` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `saleTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `information` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity
-- ----------------------------

-- ----------------------------
-- Table structure for concern
-- ----------------------------
DROP TABLE IF EXISTS `concern`;
CREATE TABLE `concern`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `concernAccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被关注账号',
  `concernLocation` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '关注位置（通过什么关注的）',
  `concernTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`id`, `account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of concern
-- ----------------------------
INSERT INTO `concern` VALUES (1, '123456', '19974712583', NULL, '2022-04-04 01:46:44');
INSERT INTO `concern` VALUES (2, '123456', '123', NULL, '2022-04-04 01:46:44');

-- ----------------------------
-- Table structure for dynamics
-- ----------------------------
DROP TABLE IF EXISTS `dynamics`;
CREATE TABLE `dynamics`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `createAccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '发布动态的账号',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '动态内容',
  `thumbsUpCount` int(12) NULL DEFAULT NULL COMMENT '点赞数',
  `thumbsDownCount` int(12) NULL DEFAULT NULL COMMENT '踩的次数',
  `visits` int(12) NULL DEFAULT NULL COMMENT '查看次数',
  `createTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '发布时间',
  `imgUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '如果有图片，就需要图片路径',
  PRIMARY KEY (`id`, `createAccount`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dynamics
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名',
  PRIMARY KEY (`id`, `roleName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '个人简介',
  `sex` char(3) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `visits` int(12) NULL DEFAULT 0 COMMENT '账号访问量',
  PRIMARY KEY (`id`, `account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, '19974712583', 'TA很懒，还没有添加简介', NULL, NULL, 100);
INSERT INTO `userinfo` VALUES (2, '123456', 'TA很懒，还没有添加简介', NULL, NULL, 22);
INSERT INTO `userinfo` VALUES (3, '123', 'TA很懒，还没有添加简介', NULL, NULL, 23);

SET FOREIGN_KEY_CHECKS = 1;
