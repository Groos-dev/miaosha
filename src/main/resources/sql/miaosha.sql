/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : localhost:3306
 Source Schema         : miaosha

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : 65001

 Date: 14/01/2023 19:18:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `price` double(10, 0) NOT NULL DEFAULT 0,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `sales` int(11) NOT NULL DEFAULT 0,
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1612700360024285185 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (1612700343783940096, '小米10S', 1999, '国产之光小米手机晓龙888', 133, '/imge/1.jpg');

-- ----------------------------
-- Table structure for item_stock
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock`  (
  `id` bigint(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `stock` int(11) NOT NULL DEFAULT 0,
  `item_id` bigint(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `item_id_index`(`item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_stock
-- ----------------------------
INSERT INTO `item_stock` VALUES (00000000006, 38, 1612700343783940096);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` bigint(11) NOT NULL DEFAULT 0,
  `item_id` bigint(11) NOT NULL DEFAULT 0,
  `item_price` double NOT NULL DEFAULT 0,
  `amount` bigint(11) NOT NULL DEFAULT 0,
  `order_price` double NOT NULL DEFAULT 0,
  `promo_id` bigint(11) NOT NULL DEFAULT 0,
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '1，刚创建  2，创建成功 3，创建失败',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('2018111800000000', 9, 6, 0, 1, 0, 0, 1);
INSERT INTO `order_info` VALUES ('2018111800000100', 9, 6, 800, 1, 800, 0, 1);
INSERT INTO `order_info` VALUES ('2018111800000200', 9, 6, 800, 1, 800, 0, 1);
INSERT INTO `order_info` VALUES ('2018111900000300', 9, 6, 800, 1, 800, 0, 1);
INSERT INTO `order_info` VALUES ('2018111900000400', 9, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2018122200000500', 21, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019012300000600', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019012300000700', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019021000000800', 15, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019021000000900', 15, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022200001000', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022200001100', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022200001200', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022300001300', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400001400', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400001500', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400001600', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400001700', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400001800', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400001900', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400002000', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400002100', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400002200', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400002300', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400002400', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400002500', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400002600', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400002700', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400002800', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019022400002900', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019030200003000', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019030200003100', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019030200003200', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019030200003300', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019030200003400', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019030300003500', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019030300003600', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019030900003700', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019030900003800', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019030900003900', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019030900004000', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2019030900004100', 22, 6, 100, 1, 100, 1, 1);
INSERT INTO `order_info` VALUES ('2023011300004200', 1613506659179909120, 1612700343783940096, 1999, 10, 19990, 0, 1);
INSERT INTO `order_info` VALUES ('2023011300004300', 1613506659179909120, 1612700343783940096, 1999, 10, 19990, 0, 1);
INSERT INTO `order_info` VALUES ('2023011300004400', 1613506659179909120, 1612700343783940096, 1899, 10, 18990, 1, 1);
INSERT INTO `order_info` VALUES ('2023011300004500', 1613506659179909120, 1612700343783940096, 1999, 1, 1999, 0, 1);
INSERT INTO `order_info` VALUES ('2023011400004600', 1613506659179909120, 1612700343783940096, 1899, 1, 2, 1, 1);
INSERT INTO `order_info` VALUES ('2023011400004700', 1613506659179909120, 1612700343783940096, 1899, 1, 2, 1, 1);
INSERT INTO `order_info` VALUES ('2023011400004800', 1613506659179909120, 1612700343783940096, 1899, 1, 2, 1, 1);
INSERT INTO `order_info` VALUES ('2023011400004900', 1613506659179909120, 1612700343783940096, 1899, 1, 1899, 1, 2);

-- ----------------------------
-- Table structure for promo
-- ----------------------------
DROP TABLE IF EXISTS `promo`;
CREATE TABLE `promo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `start_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `item_id` bigint(20) NOT NULL DEFAULT 0,
  `promo_item_price` double NOT NULL DEFAULT 0,
  `end_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promo
-- ----------------------------
INSERT INTO `promo` VALUES (1, '小米10S促销', '2023-01-10 12:09:27', 1612700343783940096, 1899, '2023-01-21 12:09:36');

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `current_value` bigint(11) NOT NULL DEFAULT 0,
  `step` bigint(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sequence_info
-- ----------------------------
INSERT INTO `sequence_info` VALUES ('order_info', 50, 1);

-- ----------------------------
-- Table structure for stock_log
-- ----------------------------
DROP TABLE IF EXISTS `stock_log`;
CREATE TABLE `stock_log`  (
  `stock_log_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `item_id` bigint(11) NOT NULL DEFAULT 0,
  `amount` int(11) NOT NULL DEFAULT 0,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '//1表示初始状态，2表示下单扣减库存成功，3表示下单回滚',
  PRIMARY KEY (`stock_log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock_log
-- ----------------------------
INSERT INTO `stock_log` VALUES ('05bcc80c65c74dc9969762fe63e58248', 6, 1, 2);
INSERT INTO `stock_log` VALUES ('0c5021c2ca944e21a989b0d114d285bb', 1612700343783940096, 1, 2);
INSERT INTO `stock_log` VALUES ('1151e433d0b84e6d93a0e20091071ebf', 6, 1, 1);
INSERT INTO `stock_log` VALUES ('1392bff227564b439903a70521429bee', 6, 1, 2);
INSERT INTO `stock_log` VALUES ('291a6da023da401cab8f44deec29cb4b', 1612700343783940096, 1, 2);
INSERT INTO `stock_log` VALUES ('3feff068ed8e4b91a757cce927bf0915', 6, 1, 2);
INSERT INTO `stock_log` VALUES ('51ac037d3cbd4177a93e728d633d87e5', 6, 1, 2);
INSERT INTO `stock_log` VALUES ('6a92c50c0c644475b03b476ecf16deae', 6, 1, 2);
INSERT INTO `stock_log` VALUES ('6b5d7a909c1846aa879ab7e13acbc9ea', 6, 1, 2);
INSERT INTO `stock_log` VALUES ('6b792aee5f574a8b9ad011e8e3962d04', 6, 1, 2);
INSERT INTO `stock_log` VALUES ('6d605e997fd44e75aff4d8642fa62265', 1612700343783940096, 1, 2);
INSERT INTO `stock_log` VALUES ('777621f3639f45a08f0c7cc75863c2f4', 6, 1, 2);
INSERT INTO `stock_log` VALUES ('7e84ccb5a2024f7f8a469aba4f5930ac', 6, 1, 2);
INSERT INTO `stock_log` VALUES ('8428d20ff6ab480291d63c457cd17afa', 6, 1, 2);
INSERT INTO `stock_log` VALUES ('912bf7696f6c4814bcd23a7409bff2a5', 6, 1, 2);
INSERT INTO `stock_log` VALUES ('b68146fe6a994596879b48fdfb84d304', 1612700343783940096, 1, 2);
INSERT INTO `stock_log` VALUES ('df8cbfc3b153422397f12317cbe3c810', 6, 1, 2);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `gender` tinyint(4) NOT NULL DEFAULT 0 COMMENT '//1代表男性，2代表女性',
  `age` bigint(11) NOT NULL DEFAULT 0,
  `telphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `register_mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '//byphone,bywechat,byalipay',
  `third_party_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `telphone_unique_index`(`telphone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1613506659179909121 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, '第一个用户', 1, 30, '13521234859', 'byphone', '');
INSERT INTO `user_info` VALUES (15, 'teambition', 1, 20, '1312345678', 'byphone', '');
INSERT INTO `user_info` VALUES (20, '82030', 1, 1, '11111122', 'byphone', '');
INSERT INTO `user_info` VALUES (21, 'hzl', 1, 31, '13671573214', 'byphone', '');
INSERT INTO `user_info` VALUES (22, 'testuser', 1, 20, '13562514273', 'byphone', '');
INSERT INTO `user_info` VALUES (24, '', 0, 0, '', '', '');
INSERT INTO `user_info` VALUES (1613506659179909120, '曾薪', 1, 18, '18723897797', 'byphone', NULL);

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `encrpt_password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `user_id` bigint(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1613506659242823681 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES (1, 'ddlsjfjfjfjlf', 1);
INSERT INTO `user_password` VALUES (9, '4QrcOUm6Wau+VuBX8g+IPg==', 15);
INSERT INTO `user_password` VALUES (11, 'xMpCOKC5I4INzFCab3WEmw==', 20);
INSERT INTO `user_password` VALUES (12, '4QrcOUm6Wau+VuBX8g+IPg==', 21);
INSERT INTO `user_password` VALUES (13, '4QrcOUm6Wau+VuBX8g+IPg==', 22);
INSERT INTO `user_password` VALUES (1613503426092904448, 'c0Hs/UeeSc37X4k+GQBjFg==', 1613503426017406976);
INSERT INTO `user_password` VALUES (1613506659242823680, 'lueSGJZetyySpUndWjMBEg==', 1613506659179909120);

SET FOREIGN_KEY_CHECKS = 1;
