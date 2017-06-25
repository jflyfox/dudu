/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : dudu

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2017-06-26 01:28:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `key` varchar(64) NOT NULL COMMENT '键',
  `value` varchar(1000) NOT NULL COMMENT '值',
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '类型',
  `sort` int(11) NOT NULL DEFAULT '10' COMMENT '排序号',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `update_time` varchar(24) DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(24) DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='系统配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '系统参数', 'systemParam', '0', '', '0', '10', '1', '2017-04-27 08:32:34', '1', '2016-12-17 22:32:35', '1');
INSERT INTO `sys_config` VALUES ('2', 'API参数', 'apiParam', '0', null, '0', '11', '1', '2016-12-17 22:33:41', '1', '2016-12-17 22:33:41', '1');
INSERT INTO `sys_config` VALUES ('4', '版权', 'copyright', '&copy;FLY的狐狸 版权所有', '', '1', '10', '1', '2017-05-09 02:03:36', '1', '2016-12-17 23:07:21', '1');
INSERT INTO `sys_config` VALUES ('5', 'API是否开启', 'API.FLAG', 'true', null, '2', '110', '1', '2016-12-17 23:12:26', '1', '2016-12-17 23:12:26', '1');
INSERT INTO `sys_config` VALUES ('6', 'ip黑名单，逗号分隔', 'API.IP.BLACK', '127.0.0.122,localhost22', null, '2', '111', '1', '2016-12-17 23:16:29', '1', '2016-12-17 23:16:29', '1');
INSERT INTO `sys_config` VALUES ('7', '支持的版本，逗号分隔', 'API.VERSIONS', '1.0.0,1.0.1', null, '2', '112', '1', '2016-12-17 23:17:00', '1', '2016-12-17 23:17:00', '1');
INSERT INTO `sys_config` VALUES ('8', '登陆验证是否开启', 'API.LOGIN.VALID', 'true', null, '2', '114', '1', '2016-12-17 23:17:23', '1', '2016-12-17 23:17:23', '1');
INSERT INTO `sys_config` VALUES ('9', '站点参数', 'siteParam', '0', null, '0', '12', '1', '2016-12-31 16:27:12', '1', '2016-12-31 16:27:12', '1');
INSERT INTO `sys_config` VALUES ('10', '多站点标示', 'SITE.MULTI.FLAG', 'false', null, '9', '211', '1', '2016-12-31 16:28:02', '1', '2016-12-31 16:28:02', '1');
INSERT INTO `sys_config` VALUES ('11', '站点根目录', 'SITE.TEMPLATE.PATH', '/template/', null, '9', '212', '1', '2016-12-31 16:28:43', '1', '2016-12-31 16:28:43', '1');
INSERT INTO `sys_config` VALUES ('12', 'Session站点列表', 'SITE.SESSION.SITES', 'sites', null, '9', '213', '1', '2016-12-31 16:30:17', '1', '2016-12-31 16:30:17', '1');
INSERT INTO `sys_config` VALUES ('13', 'Session站点', 'SITE.SESSION.SITE', 'site', null, '9', '214', '1', '2016-12-31 16:30:38', '1', '2016-12-31 16:30:38', '1');
INSERT INTO `sys_config` VALUES ('14', 'API参数加密是否开启', 'API.PARAM.ENCRYPT', 'true', null, '2', '115', '1', '2017-03-17 23:17:23', '1', '2017-03-17 23:17:23', '1');
INSERT INTO `sys_config` VALUES ('15', '文件备份参数', 'backupParam', '0', null, '0', '10', '1', '2017-04-05 03:49:21', '1', '2017-04-05 03:49:21', '1');
INSERT INTO `sys_config` VALUES ('16', '文件备份类型名称', 'backup.name', 'filemanger', null, '15', '10', '1', '2017-04-05 03:50:12', '1', '2017-04-05 03:50:12', '1');
INSERT INTO `sys_config` VALUES ('17', '文件系统备份路径', 'backup.filemanger.path', 'D:\\test', null, '15', '10', '1', '2017-04-05 03:50:42', '1', '2017-04-05 03:50:42', '1');
INSERT INTO `sys_config` VALUES ('18', '阿里云存储bucketname', 'backup.oss.bucketname', 'jflyfox', null, '15', '10', '1', '2017-04-05 22:58:05', '1', '2017-04-05 22:58:05', '1');
INSERT INTO `sys_config` VALUES ('19', '阿里云存储endpoint', 'backup.oss.endpoint', 'http://oss-cn-beijing.aliyuncs.com', null, '15', '10', '1', '2017-04-05 22:59:01', '1', '2017-04-05 22:59:01', '1');
INSERT INTO `sys_config` VALUES ('20', '阿里云存储ID', 'backup.oss.id', '', null, '15', '10', '1', '2017-04-05 22:59:24', '1', '2017-04-05 22:59:24', '1');
INSERT INTO `sys_config` VALUES ('21', '阿里云存储KEY', 'backup.oss.key', '', null, '15', '10', '1', '2017-04-05 22:59:51', '1', '2017-04-05 22:59:51', '1');

-- ----------------------------
-- Table structure for `sys_department`
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parentid` int(11) DEFAULT '0' COMMENT '上级机构',
  `name` varchar(32) NOT NULL COMMENT '部门/11111',
  `code` varchar(128) DEFAULT NULL COMMENT '机构编码',
  `sort` int(11) DEFAULT '0' COMMENT '序号',
  `linkman` varchar(64) DEFAULT NULL COMMENT '联系人',
  `linkman_no` varchar(32) DEFAULT NULL COMMENT '联系人电话',
  `remark` varchar(128) DEFAULT NULL COMMENT '机构描述',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `update_time` varchar(24) DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(24) DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10007 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', '0', '系统承建单位', 'A001', '99', 'system', '15888888888', '系统承建单位', '1', '2017-05-09 21:29:58', '1', '2016-06-06 06:06:06', '1');
INSERT INTO `sys_department` VALUES ('9', '0', '注册用户', 'B001', '97', '无人', '15888888888', '', '1', '2017-04-28 02:39:49', '1', '2017-04-28 02:39:49', '1');
INSERT INTO `sys_department` VALUES ('10', '0', '第三方用户', 'B001', '90', '无人', '15888888888', '', '1', '2017-04-28 02:40:01', '1', '2017-04-28 02:40:01', '1');
INSERT INTO `sys_department` VALUES ('10001', '0', 'FLY的狐狸', 'ABC000', '100', '', '', '', '1', '2017-04-28 01:16:43', '1', '2016-07-31 18:12:30', '1');
INSERT INTO `sys_department` VALUES ('10002', '10001', '开发部', 'ABC001', '101', null, null, null, '1', '2016-07-31 18:15:29', '1', '2016-07-31 18:15:29', '1');
INSERT INTO `sys_department` VALUES ('10003', '10001', '财务部', 'ABC003', '103', '', '', '', '1', '2017-04-28 00:58:41', '1', '2016-07-31 18:16:06', '1');
INSERT INTO `sys_department` VALUES ('10004', '10001', '人事部', 'ABC004', '104', null, null, null, '1', '2016-07-31 18:16:30', '1', '2016-07-31 18:16:30', '1');
INSERT INTO `sys_department` VALUES ('10005', '10002', '测试部', 'ABC0011', '101', null, null, null, '1', '2016-07-31 18:16:52', '1', '2016-07-31 18:16:52', '1');
INSERT INTO `sys_department` VALUES ('10006', '0', '123', '123', '123', '', '', '', '1', '2017-05-09 23:43:36', '1', '2017-05-09 23:43:36', '1');

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) NOT NULL COMMENT '名称',
  `type` varchar(64) NOT NULL COMMENT '类型',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `update_time` varchar(24) DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(24) DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_SYS_DICT_TYPE` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=utf8 COMMENT='数据字典主表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '日志配置', 'systemLog', null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict` VALUES ('10001', '目录类型', 'folderType', null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict` VALUES ('10002', '文章类型', 'articleType', null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict` VALUES ('10003', '友情链接类型', 'friendlyLinkType', null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict` VALUES ('10004', '栏目类型', 'materialType', null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');

-- ----------------------------
-- Table structure for `sys_dict_detail`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_type` varchar(64) NOT NULL COMMENT '数据字典类型',
  `name` varchar(256) DEFAULT NULL COMMENT '名称',
  `code` varchar(32) DEFAULT NULL COMMENT '代码',
  `sort` varchar(32) DEFAULT NULL COMMENT '排序号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `status` varchar(32) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `content` varchar(256) DEFAULT NULL COMMENT '内容',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `update_time` varchar(24) DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(24) DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10108 DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES ('10001', 'folderType', '目录', '1', '1', null, '1', null, '', '1', '2017-05-09 16:00:13', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10002', 'folderType', 'a标签', '2', '2', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10003', 'folderType', 'a标签target', '3', '3', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10004', 'folderType', 'html标签', '4', '4', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10011', 'articleType', '正常', '1', '1', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10012', 'articleType', '预览', '2', '2', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10013', 'articleType', '程序', '3', '3', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10021', 'friendlyLinkType', '友情链接', null, '1', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10022', 'friendlyLinkType', '关于我们', null, '2', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10032', 'materialType', '文章', '1', '1', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10033', 'materialType', '图片', '2', '2', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10034', 'materialType', '视频', '3', '3', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10035', 'materialType', '其他', '9', '9', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10036', 'materialType', '栏目', '6', '6', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10051', 'systemLog', 'sys_dict', '数据字典主表', '1', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10052', 'systemLog', 'sys_dict_detail', '数据字典', '2', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10053', 'systemLog', 'sys_menu', '菜单管理', '3', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10054', 'systemLog', 'sys_department', '组织机构', '4', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10055', 'systemLog', 'sys_user', '用户管理', '5', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10056', 'systemLog', 'sys_user_role', '用户角色授权', '6', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10057', 'systemLog', 'sys_role', '角色管理', '7', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10058', 'systemLog', 'sys_role_folder', '角色目录授权', '8', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10059', 'systemLog', 'sys_role_menu', '角色菜单授权', '9', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10060', 'systemLog', 'tb_folder', '目录管理', '10', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10061', 'systemLog', 'tb_article', '文章管理', '11', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10062', 'systemLog', 'tb_articlelike', '喜欢的文章管理', '12', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10063', 'systemLog', 'tb_comment', '评论管理', '13', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10064', 'systemLog', 'tb_tags', '标签管理', '14', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10065', 'systemLog', 'tb_contact', '联系人', '15', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10066', 'systemLog', 'tb_error', '错误管理', '16', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10067', 'systemLog', 'tb_friendlylink', '友情链接', '17', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10068', 'systemLog', 'tb_pageview', '访问量统计', '18', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('10107', 'systemLog', 'tb_site', '站点管理', '19', null, '1', null, null, '1', '2015-05-06 15:18:59', '1', '2015-05-06 15:18:59', '1');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `log_type` int(11) NOT NULL COMMENT '类型',
  `oper_object` varchar(64) DEFAULT NULL COMMENT '操作对象',
  `oper_table` varchar(64) NOT NULL COMMENT '操作表',
  `oper_id` int(11) DEFAULT '0' COMMENT '操作主键',
  `oper_type` varchar(64) DEFAULT NULL COMMENT '操作类型',
  `oper_remark` varchar(100) DEFAULT NULL COMMENT '操作备注',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `update_time` varchar(24) DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(24) DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10027 DEFAULT CHARSET=utf8 COMMENT='日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', '1', '访问量统计', 'tb_pageview', '1', '添加', null, '1', '2017-03-22 01:04:10', '1', '2017-03-22 01:04:10', '1');
INSERT INTO `sys_log` VALUES ('2', '2', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-03-22 01:04:10', '1', '2017-03-22 01:04:10', '1');
INSERT INTO `sys_log` VALUES ('3', '2', '组织机构', 'sys_department', '10007', '添加', null, '1', '2017-05-10 00:37:51', '1', '2017-05-10 00:37:51', '1');
INSERT INTO `sys_log` VALUES ('4', '2', '组织机构', 'sys_department', '10007', '更新', null, '1', '2017-05-10 00:38:02', '1', null, '0');
INSERT INTO `sys_log` VALUES ('5', '2', '组织机构', 'sys_department', '10007', '删除', null, '1', '2017-05-10 00:38:22', '1', '2017-05-10 00:38:22', '1');
INSERT INTO `sys_log` VALUES ('6', '1', '用户管理', 'sys_user', '1', '登出', null, '1', '2017-05-10 01:24:35', '1', '2017-05-10 01:24:35', '1');
INSERT INTO `sys_log` VALUES ('7', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-05-10 01:24:48', '1', '2017-05-10 01:24:48', '1');
INSERT INTO `sys_log` VALUES ('8', '2', '联系人', 'tb_contact', '10001', '添加', null, '1', '2017-05-10 01:32:22', '1', '2017-05-10 01:32:22', '1');
INSERT INTO `sys_log` VALUES ('9', '2', '联系人', 'tb_contact', '10002', '添加', null, '1', '2017-05-10 01:33:35', '1', '2017-05-10 01:33:35', '1');
INSERT INTO `sys_log` VALUES ('10', '2', '联系人', 'tb_contact', '10001', '更新', null, '1', '2017-05-10 01:34:40', '1', null, '0');
INSERT INTO `sys_log` VALUES ('11', '2', '联系人', 'tb_contact', '10003', '添加', null, '1', '2017-05-10 01:34:53', '1', '2017-05-10 01:34:53', '1');
INSERT INTO `sys_log` VALUES ('12', '2', '联系人', 'tb_contact', '10003', '删除', null, '1', '2017-05-10 01:34:55', '1', '2017-05-10 01:34:55', '1');
INSERT INTO `sys_log` VALUES ('13', '2', '菜单管理', 'sys_menu', '10', '添加', null, '1', '2017-05-10 01:36:03', '1', '2017-05-10 01:36:03', '1');
INSERT INTO `sys_log` VALUES ('10001', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-18 01:28:14', '1', '2017-06-18 01:28:14', '1');
INSERT INTO `sys_log` VALUES ('10002', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-18 11:38:09', '1', '2017-06-18 11:38:09', '1');
INSERT INTO `sys_log` VALUES ('10003', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-20 00:22:50', '1', '2017-06-20 00:22:50', '1');
INSERT INTO `sys_log` VALUES ('10004', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-24 01:10:31', '1', '2017-06-24 01:10:31', '1');
INSERT INTO `sys_log` VALUES ('10005', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-24 23:23:03', '1', '2017-06-24 23:23:03', '1');
INSERT INTO `sys_log` VALUES ('10006', '1', '用户管理', 'sys_user', '1', '登出', null, '1', '2017-06-24 23:25:40', '1', '2017-06-24 23:25:40', '1');
INSERT INTO `sys_log` VALUES ('10007', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-25 00:13:40', '1', '2017-06-25 00:13:40', '1');
INSERT INTO `sys_log` VALUES ('10008', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-25 00:13:58', '1', '2017-06-25 00:13:58', '1');
INSERT INTO `sys_log` VALUES ('10009', '1', '用户管理', 'sys_user', '1', '登出', null, '1', '2017-06-25 00:19:02', '1', '2017-06-25 00:19:02', '1');
INSERT INTO `sys_log` VALUES ('10010', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-25 00:27:05', '1', '2017-06-25 00:27:05', '1');
INSERT INTO `sys_log` VALUES ('10011', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-25 00:27:06', '1', '2017-06-25 00:27:06', '1');
INSERT INTO `sys_log` VALUES ('10012', '1', '用户管理', 'sys_user', '1', '登出', null, '1', '2017-06-25 00:29:41', '1', '2017-06-25 00:29:41', '1');
INSERT INTO `sys_log` VALUES ('10013', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-25 00:40:38', '1', '2017-06-25 00:40:38', '1');
INSERT INTO `sys_log` VALUES ('10014', '1', '用户管理', 'sys_user', '1', '登出', null, '1', '2017-06-25 00:41:17', '1', '2017-06-25 00:41:17', '1');
INSERT INTO `sys_log` VALUES ('10015', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-25 00:42:57', '1', '2017-06-25 00:42:57', '1');
INSERT INTO `sys_log` VALUES ('10016', '1', '用户管理', 'sys_user', '1', '登出', null, '1', '2017-06-25 01:03:10', '1', '2017-06-25 01:03:10', '1');
INSERT INTO `sys_log` VALUES ('10017', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-25 01:03:19', '1', '2017-06-25 01:03:19', '1');
INSERT INTO `sys_log` VALUES ('10018', '1', '用户管理', 'sys_user', '1', '登出', null, '1', '2017-06-25 01:10:59', '1', '2017-06-25 01:10:59', '1');
INSERT INTO `sys_log` VALUES ('10019', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-25 01:11:15', '1', '2017-06-25 01:11:15', '1');
INSERT INTO `sys_log` VALUES ('10020', '1', '用户管理', 'sys_user', '1', '登出', null, '1', '2017-06-25 01:23:24', '1', '2017-06-25 01:23:24', '1');
INSERT INTO `sys_log` VALUES ('10021', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-25 01:23:36', '1', '2017-06-25 01:23:36', '1');
INSERT INTO `sys_log` VALUES ('10022', '1', '用户管理', 'sys_user', '1', '登出', null, '1', '2017-06-25 02:00:13', '1', '2017-06-25 02:00:13', '1');
INSERT INTO `sys_log` VALUES ('10023', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-25 02:00:24', '1', '2017-06-25 02:00:24', '1');
INSERT INTO `sys_log` VALUES ('10024', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-25 23:28:02', '1', '2017-06-25 23:28:02', '1');
INSERT INTO `sys_log` VALUES ('10025', '1', '用户管理', 'sys_user', '1', '登出', null, '1', '2017-06-26 00:36:23', '1', '2017-06-26 00:36:23', '1');
INSERT INTO `sys_log` VALUES ('10026', '1', '用户管理', 'sys_user', '1', '登入', null, '1', '2017-06-26 00:36:33', '1', '2017-06-26 00:36:33', '1');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parentid` int(11) NOT NULL DEFAULT '0' COMMENT '父id',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '名称/11111',
  `urlkey` varchar(256) DEFAULT NULL COMMENT '菜单key',
  `url` varchar(256) DEFAULT NULL COMMENT '链接地址',
  `status` int(11) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `type` int(11) DEFAULT '1' COMMENT '类型//select/1,菜单,2,a标签,3,a标签_blank,4,外部url',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `level` int(11) DEFAULT '1' COMMENT '级别',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `update_time` varchar(24) DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(24) DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '首页', 'home', '/admin/home', '1', '1', '10', '1', '1', '2015-04-27 17:28:06', '1', '2015-04-27 17:28:06', '1');
INSERT INTO `sys_menu` VALUES ('2', '0', '系统管理', 'system_root', null, '1', '0', '190', '1', '1', '2015-04-27 17:28:06', '1', '2015-04-27 17:28:06', '1');
INSERT INTO `sys_menu` VALUES ('3', '2', '组织机构', 'department', '/system/department/list', '1', '1', '191', '2', '1', '2015-04-27 17:28:06', '1', '2015-04-27 17:28:25', '1');
INSERT INTO `sys_menu` VALUES ('4', '2', '用户管理', 'user', '/system/user/list', '1', '1', '192', '2', '1', '2015-04-27 17:28:06', '1', '2015-04-27 17:28:46', '1');
INSERT INTO `sys_menu` VALUES ('5', '2', '角色管理', 'role', '/system/role/list', '1', '1', '194', '2', '1', '2015-04-27 17:28:06', '1', '2015-04-27 17:29:13', '1');
INSERT INTO `sys_menu` VALUES ('6', '2', '菜单管理', 'menu', '/system/menu/list', '1', '1', '196', '2015', '1', '1', '2', '2015-04-27 17:29:43', '1');
INSERT INTO `sys_menu` VALUES ('7', '2', '数据字典', 'dict', '/system/dict/list', '1', '1', '197', '2', '1', '2015-04-27 17:28:06', '1', '2015-04-27 17:30:05', '1');
INSERT INTO `sys_menu` VALUES ('8', '2', '参数配置', 'config', '/system/config/list', '1', '1', '198', '2', '1', '2015-04-27 17:28:06', '1', '2016-12-17 23:34:13', '1');
INSERT INTO `sys_menu` VALUES ('9', '2', '日志管理', 'log', '/system/log/list', '1', '1', '199', '2', '1', '2015-04-27 17:28:06', '1', '2016-01-03 18:09:18', '1');
INSERT INTO `sys_menu` VALUES ('10', '0', '联系人管理', 'contact', '/admin/contact/list', '1', '1', '110', '1', '1', '2017-05-10 01:36:03', '1', '2017-05-10 01:36:03', '1');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '名称/11111/',
  `status` int(11) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `remark` text COMMENT '说明//textarea',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `update_time` varchar(24) DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(24) DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '测试角色', '1', '1', null, '1', '2016-03-31 23:41:59', '1', '2016-03-31 23:41:59', '1');
INSERT INTO `sys_role` VALUES ('2', '测试2', '2', '1231', '1231', '1', '2017-05-08 22:36:23', '1', '2017-05-08 22:33:23', '1');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `roleid` int(11) NOT NULL COMMENT '角色id',
  `menuid` int(11) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('9', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('10', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('11', '2', '3');
INSERT INTO `sys_role_menu` VALUES ('12', '2', '4');
INSERT INTO `sys_role_menu` VALUES ('13', '2', '5');
INSERT INTO `sys_role_menu` VALUES ('14', '2', '6');
INSERT INTO `sys_role_menu` VALUES ('15', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('16', '2', '8');
INSERT INTO `sys_role_menu` VALUES ('17', '2', '9');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT 'UUID',
  `username` varchar(32) NOT NULL COMMENT '登录名/11111',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `realname` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `departid` int(11) DEFAULT '0' COMMENT '部门/11111/dict',
  `usertype` int(11) DEFAULT '2' COMMENT '类型//select/1,管理员,2,普通用户,3,前台用户,4,第三方用户,5,API用户',
  `status` int(11) DEFAULT '10' COMMENT '状态',
  `thirdid` varchar(200) DEFAULT NULL COMMENT '第三方ID',
  `endtime` varchar(32) DEFAULT NULL COMMENT '结束时间',
  `email` varchar(64) DEFAULT NULL COMMENT 'email',
  `tel` varchar(32) DEFAULT NULL COMMENT '手机号',
  `address` varchar(32) DEFAULT NULL COMMENT '地址',
  `title_url` varchar(200) DEFAULT NULL COMMENT '头像地址',
  `remark` varchar(1000) DEFAULT NULL COMMENT '说明',
  `theme` varchar(64) DEFAULT 'default' COMMENT '主题',
  `back_site_id` int(11) DEFAULT '0' COMMENT '后台选择站点ID',
  `create_site_id` int(11) DEFAULT '1' COMMENT '创建站点ID',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `update_time` varchar(24) DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(24) DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', null, 'admin', 'Iuy8AguV5T7Vm08po+I4Gg==', '系统管理员', '1', '1', '10', null, null, 'zcool321@sina.com', '123', null, null, '时间是最好的老师，但遗憾的是——最后他把所有的学生都弄死了', 'flat', '5', '1', '3', '2017-03-19 20:41:25', '1', '2017-03-19 20:41:25', '1');
INSERT INTO `sys_user` VALUES ('2', null, 'testapi', 'EY3JNDE7nu8=', 'api测试', '1', '5', '10', null, null, null, null, null, null, null, 'default', '0', '1', '1', '2017-03-19 20:41:25', '1', '2017-03-19 20:41:25', '1');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` int(11) NOT NULL COMMENT '用户id',
  `roleid` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_contact`
-- ----------------------------
DROP TABLE IF EXISTS `tb_contact`;
CREATE TABLE `tb_contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) NOT NULL COMMENT '姓名',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT 'Email',
  `addr` varchar(256) DEFAULT NULL COMMENT '地址',
  `birthday` varchar(32) DEFAULT NULL COMMENT '生日',
  `remark` varchar(256) DEFAULT NULL COMMENT '说明',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `update_time` varchar(24) DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(24) DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8 COMMENT='联系人';

-- ----------------------------
-- Records of tb_contact
-- ----------------------------
INSERT INTO `tb_contact` VALUES ('10001', '张三', '13112587412', 'zhangsan@qq.com', '上海市张三村三号路五楼2单元444', '1991-05-10', '美女', '1', '2017-05-10 01:34:40', '1', '2017-05-10 01:32:22', '1');
INSERT INTO `tb_contact` VALUES ('10002', '李四', '15812345678', 'lisi@sina.com', '北京市李四家', '2007-05-10', '小孩子', '1', '2017-05-10 01:33:35', '1', '2017-05-10 01:33:35', '1');
