# FilesUpAndDownloadWebApp
SpringBoot小项目实战

# 成功概览

主要练习Web框架及逻辑，对于前端界面并没有过度在意

## 登录

登录失败重新跳回该页面

![image-20210226224158471](https://gitee.com/DengSchoo374/img/raw/master/images/image-20210226224158471.png)

## 展示自己文件信息界面

用户只能查看自己的文件列表

![image-20210226224256879](https://gitee.com/DengSchoo374/img/raw/master/images/image-20210226224256879.png)

## 上传文件

选择文件

![image-20210226224517857](https://gitee.com/DengSchoo374/img/raw/master/images/image-20210226224517857.png)

上传文件

![image-20210226224542619](https://gitee.com/DengSchoo374/img/raw/master/images/image-20210226224542619.png)

## 在线打开

![image-20210226225210746](https://gitee.com/DengSchoo374/img/raw/master/images/image-20210226225210746.png)

可以在线浏览相应的文件内容

## 删除

删除FileId为2是文件

![image-20210226225139324](https://gitee.com/DengSchoo374/img/raw/master/images/image-20210226225139324.png)

## 下载

点击即可下载对应文件，每点击下载一次，下载次数会增一。

# 技术栈

- Spring Boot
- Mybatis做持久层
- thymeleaf做模板引擎渲染，ajax做局部刷新
- lombok
- SSM架构

# 需求

- 用户登录展示所有的文件，文件如果是图片则在页面中显示图片

- 完成文件的==下载==和==在线打开==(在线打开不计算下载次数)

- 在一张页面中完成文件的上传功能，上传的目录要求根据日期每天创建一个文件夹（文件夹统一命名为"yyyy-MM-dd")上传完成后要跳转到查询所有页面。

# 项目规范

1. 项目命名规则： file_xxxx 如 file_input

2. 包名统一

3. 项目中的webroot

   1. 建立files文件夹为上传下载的总文件夹

      更具日期每天创建不同的文件夹 每天上传文件放入不同的文件夹中 `files/2020-2-3`

   2. 两个页面login.html来做用户登录，showAllFIles做文件展示和上传下载文件

4. 项目统一使用UTF-8

# 数据表设计

```sql
/*
 Navicat Premium Data Transfer

 Source Server         : localTestMySQL8.0
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : files

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 25/02/2021 21:43:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_files
-- ----------------------------
DROP TABLE IF EXISTS `t_files`;
CREATE TABLE `t_files`  (
  `id` int(8) NOT NULL,
  `oldFileNames` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `newFileNames` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ext` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isImg` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `downCounts` int(6) NULL DEFAULT NULL,
  `dateTime` datetime(0) NULL DEFAULT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`userId`) USING BTREE,
  CONSTRAINT `userid` FOREIGN KEY (`userId`) REFERENCES `t_users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_files
-- ----------------------------

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users`  (
  `id` int(11) NOT NULL,
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES (1, 'DengSchoo', '123456');
INSERT INTO `t_users` VALUES (2, 'ZhangSan', '654321');

SET FOREIGN_KEY_CHECKS = 1;

```

