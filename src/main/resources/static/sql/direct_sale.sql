/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : direct_sale

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2019-01-06 23:34:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sale_user
-- ----------------------------
DROP TABLE IF EXISTS `sale_user`;
CREATE TABLE `sale_user` (
  `userId` int(7) NOT NULL auto_increment,
  `userName` varchar(32) default NULL COMMENT '真实姓名',
  `phoneNum` varchar(16) default NULL,
  `firstPwd` varchar(50) default NULL COMMENT '一级密码',
  `secondPwd` varchar(50) default NULL COMMENT '二级密码',
  `thirdPwd` varchar(50) default NULL COMMENT '三级密码',
  `userStatus` int(4) default '0',
  `treeSupId` int(7) default NULL COMMENT '树图中父节点',
  `treeLeft` int(7) default NULL,
  `treeRight` int(7) default NULL,
  `refereeId` int(7) default NULL COMMENT '推荐人ID',
  `puserId` int(7) default '0' COMMENT '报单中心ID',
  `joinMoney` int(8) default '0' COMMENT '加盟费',
  `totalIncome` int(9) default '0' COMMENT '总收入',
  `luckEnd` int(4) default '0' COMMENT '幸运分红奖0未结束1结束',
  `balance` int(9) default '0' COMMENT '余额',
  `coin` int(9) default '0' COMMENT '积分',
  `registeCore` int(4) default '0' COMMENT '0普通下线1报单中心',
  `registeCoreMoney` int(9) default '0' COMMENT '报单中心费',
  `cTime` datetime default NULL,
  `uTime` datetime default NULL,
  PRIMARY KEY  (`userId`),
  UNIQUE KEY `login_key` (`phoneNum`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_user
-- ----------------------------
INSERT INTO `sale_user` VALUES ('1', '王大明', '12345678901', '123', '123456', '654321', '0', null, '2', '3', null, '0', '390000', '0', '0', '35689', '5626', '1', '1000000', '2019-01-04 14:00:31', null);
INSERT INTO `sale_user` VALUES ('2', '王大锤', '12345678902', '123456', '123456', '111111', '1', '1', '7', '6', '1', '1', '2800', '0', '0', '0', '0', '0', null, '2019-01-04 15:09:33', '2019-01-04 16:48:41');
INSERT INTO `sale_user` VALUES ('3', '李静', '12345678903', '123456', '123456', '111111', '0', '1', null, '8', '1', '1', '490000', '0', '0', '0', '0', '0', null, '2019-01-04 15:13:23', null);
INSERT INTO `sale_user` VALUES ('6', '郭靖', '12345678904', '123456', '123456', '111111', '0', '2', null, null, '1', '1', '490000', '0', '0', '0', '0', '0', null, '2019-01-04 15:28:26', null);
INSERT INTO `sale_user` VALUES ('7', '黄蓉', '12345678905', '123456', '123456', '111111', '0', '2', null, '9', '1', '1', '490000', '0', '0', '0', '0', '0', null, '2019-01-04 15:29:15', null);
INSERT INTO `sale_user` VALUES ('8', '杨过', '12345678906', '123456', '123456', '111111', '0', '3', null, null, '1', '1', '490000', '0', '0', '0', '0', null, null, '2019-01-04 15:30:13', null);
INSERT INTO `sale_user` VALUES ('9', '王连', '21234567890', '123456', '123456', '111111', '0', '7', null, null, '2', '1', '490000', '0', '0', '0', '0', null, null, '2019-01-04 15:31:50', null);

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
  `adminId` int(7) NOT NULL auto_increment,
  `phoneNum` varchar(11) default NULL,
  `password` varchar(38) default NULL,
  `nickName` varchar(16) default NULL,
  `usable` int(4) default '0' COMMENT '0可用其他不可用',
  `sysRole` int(4) default '0',
  `cTime` datetime default NULL,
  `uTime` datetime default NULL,
  PRIMARY KEY  (`adminId`),
  UNIQUE KEY `login_name` (`phoneNum`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES ('1', '12', '123', '超级管理', '0', '0', '2018-12-27 15:34:57', '2018-12-26 11:23:19');
INSERT INTO `sys_admin` VALUES ('2', '122', '123', '张山2', '0', '1', '2018-12-27 10:46:29', '2018-12-27 10:46:29');
INSERT INTO `sys_admin` VALUES ('3', '123', '123', '张山3', '0', '1', '2018-12-27 10:46:39', '2018-12-27 10:46:39');
INSERT INTO `sys_admin` VALUES ('4', '124', '123', '张山4', '1', '0', '2018-12-27 10:46:55', '2018-12-27 10:46:55');
INSERT INTO `sys_admin` VALUES ('5', '125', '123', '张山5', '0', '0', '2018-12-27 10:47:06', '2018-12-27 10:47:06');
INSERT INTO `sys_admin` VALUES ('6', '126', '123', '张山6', '1', '0', '2018-12-27 10:47:17', '2018-12-27 10:47:17');
INSERT INTO `sys_admin` VALUES ('7', '127', '123', '张山7', '0', '1', '2018-12-27 10:47:28', '2018-12-27 10:47:28');
INSERT INTO `sys_admin` VALUES ('8', '128', '123', '张山8', '1', '0', '2018-12-27 10:47:39', '2018-12-27 10:47:39');
INSERT INTO `sys_admin` VALUES ('9', '129', '129', '张山9', '1', '1', '2018-12-27 10:47:52', '2018-12-27 10:47:52');
INSERT INTO `sys_admin` VALUES ('10', '1210', '1210', '张山10', '0', '1', '2018-12-27 10:48:05', '2018-12-27 10:48:05');
INSERT INTO `sys_admin` VALUES ('11', '1211', '123', '张山11', '0', '1', '2018-12-27 10:52:28', '2018-12-27 10:52:28');
INSERT INTO `sys_admin` VALUES ('12', '18637638956', '123456', '啊和的', '0', '1', '2018-12-27 14:39:39', null);
INSERT INTO `sys_admin` VALUES ('13', '18637638945', '123456', '超级管理员', '0', null, '2018-12-27 15:26:51', null);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `logId` int(7) NOT NULL auto_increment,
  `adminId` int(7) default NULL,
  `logInfo` varchar(150) default NULL,
  `cTime` datetime default NULL,
  PRIMARY KEY  (`logId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', '1', 'updaAdminUse_9', '2018-12-27 13:42:35');
INSERT INTO `sys_log` VALUES ('2', '1', 'addAdmin_18637638956', '2018-12-27 14:39:39');
INSERT INTO `sys_log` VALUES ('3', '1', 'addAdmin_18637638945', '2018-12-27 15:26:51');
INSERT INTO `sys_log` VALUES ('4', '1', 'updaUserUse_1', '2018-12-27 17:56:45');
INSERT INTO `sys_log` VALUES ('5', '1', 'updaUserUse_1', '2018-12-27 17:56:53');
INSERT INTO `sys_log` VALUES ('6', '1', 'updaUserUse_1', '2018-12-27 17:57:03');
INSERT INTO `sys_log` VALUES ('7', '1', 'updaUserUse_1', '2018-12-27 17:58:09');
INSERT INTO `sys_log` VALUES ('8', '1', 'updaUserUse_1', '2018-12-27 17:58:37');
INSERT INTO `sys_log` VALUES ('9', '1', 'updaUserUse_1', '2018-12-27 18:01:40');
INSERT INTO `sys_log` VALUES ('10', '1', 'updaUserUse_9', '2018-12-27 18:03:32');
INSERT INTO `sys_log` VALUES ('11', '1', 'updaUserUse_9', '2018-12-27 18:03:40');
INSERT INTO `sys_log` VALUES ('12', '1', 'updaUserUse_6', '2018-12-27 18:03:48');
INSERT INTO `sys_log` VALUES ('13', '1', 'updaUserUse_2', '2019-01-04 16:48:41');

-- ----------------------------
-- Table structure for user_income
-- ----------------------------
DROP TABLE IF EXISTS `user_income`;
CREATE TABLE `user_income` (
  `incomeId` int(8) NOT NULL auto_increment,
  `userId` int(7) default NULL,
  `ins` varchar(16) default NULL COMMENT '收益说明',
  `money` int(7) default NULL,
  `cTime` datetime default NULL,
  PRIMARY KEY  (`incomeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_income
-- ----------------------------
INSERT INTO `user_income` VALUES ('1', '1', '平衡奖', '1200', '2019-01-06 23:23:34');
INSERT INTO `user_income` VALUES ('2', '1', '管理奖', '2563', '2019-01-24 23:23:59');
INSERT INTO `user_income` VALUES ('3', '1', '静态分红', '5600', '2019-01-15 23:24:24');
