CREATE  DATABASE  dymaoBlog;
USE `dymaoBlog`;

/*****************************************
由于mysql和oracle不太一样，不支持直接的sequence，所以需要创建一张table来模拟sequence的功能
 *****************************************/
/* 1. 创建--Sequence 管理表*/
DROP TABLE IF EXISTS sequence;
CREATE TABLE sequence (
  seq_name VARCHAR(50) NOT NULL  COMMENT '序列名称',
  current_value INT NOT NULL COMMENT '当前值',
  increment INT NOT NULL DEFAULT 1 COMMENT '步长',
  PRIMARY KEY (seq_name)
) ENGINE=InnoDB;

/* 2. 创建--取当前值的函数*/
DROP FUNCTION IF EXISTS currval;
DELIMITER $
CREATE FUNCTION currval(seqName VARCHAR(50))
  RETURNS INTEGER
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
  SQL SECURITY DEFINER
  COMMENT ''
  BEGIN
    DECLARE value INTEGER;
    SET value = 0;
    SELECT current_value INTO value
    FROM sequence
    WHERE seq_name = seqName;
    RETURN value;
  END
$
DELIMITER ;

/* 3. 创建--取下一个值的函数*/
DROP FUNCTION IF EXISTS nextval;
DELIMITER $
CREATE FUNCTION nextval (seqName VARCHAR(50))
  RETURNS INTEGER
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
  SQL SECURITY DEFINER
  COMMENT ''
  BEGIN
    UPDATE sequence
    SET current_value = current_value + increment
    WHERE seq_name = seqName;
    RETURN currval(seqName);
  END
$
DELIMITER ;

/* 4. 创建--更新当前值的函数*/
DROP FUNCTION IF EXISTS setval;
DELIMITER $
CREATE FUNCTION setval (seq_name VARCHAR(50), value INTEGER)
  RETURNS INTEGER
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
  SQL SECURITY DEFINER
  COMMENT ''
  BEGIN
    UPDATE sequence
    SET current_value = value
    WHERE name = seq_name;
    RETURN currval(seq_name);
  END
$
DELIMITER ;

CREATE TABLE `adminuser` (
  `id` varchar(32) NOT NULL COMMENT '用户ID',
  `login_id` varchar(50) NOT NULL COMMENT '登录ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '登录密码',
  `user_status` varchar(1) NOT NULL DEFAULT '0' COMMENT '用户登录状态',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机号码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COLLATE=utf8_unicode_ci;

CREATE TABLE `banner` (
  `id` varchar(12) COLLATE utf8_unicode_ci NOT NULL COMMENT 'id',
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '标题',
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '图片名称',
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '链接地址',
  `showFlag` varchar(1) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '显示状态 0：显示 1：不显示',
  `deleted` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '是否已删除 0：未删 1：已删除',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='滚动图片表';

CREATE TABLE `blog` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `user_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户id',
  `title` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '博客标题',
  `content` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '博客内容',
  `content_show` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '博客前段显示内容',
  `keyword` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '关键词',
  `is_public` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '是否发表  0:已发表  1：未发表',
  `deleted` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '是否已删除 0：未删除 1： 已删除',
  `category_id_one` varchar(12) COLLATE utf8_unicode_ci NOT NULL COMMENT '分类的id',
  `category_id_two` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `view_num` int(11) NOT NULL DEFAULT '0' COMMENT '浏览次数',
  `like_num` int(11) NOT NULL DEFAULT '0' COMMENT '点赞人数',
  `tread_num` int(11) NOT NULL DEFAULT '0' COMMENT '踩的人数',
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '缩略图地址',
  `is_transshipment` varchar(1) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '是否转载 0：原创 1：转载',
  `transshipment_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '转载源地址',
  `is_recommend` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '是否推荐 0：否  1： 是',
  `is_audit` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '1' COMMENT '是否审核 1：未审核  0： 已审核',
  `audit_user_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核员ID',
  `authority` varchar(1) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '浏览权限 0：所有人可见  1：仅会员可见',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `blog_userid_foregignkey` (`user_id`),
  KEY `blog_category_foregignkey` (`category_id_one`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='博客文章表';

CREATE TABLE `blogcategory` (
  `id` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT '分类名称',
  `level` smallint(1) NOT NULL COMMENT '分类级别',
  `parentId` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '上级分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='分类标签';

CREATE TABLE `friendlylink` (
  `id` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '标题',
  `url` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '链接地址',
  `sn` int(11) NOT NULL DEFAULT '1' COMMENT '序号',
  `create_time` datetime NOT NULL COMMENT ' 创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='友情链接表';

CREATE TABLE `label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label_name` varchar(15) NOT NULL,
  `desc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

CREATE TABLE `message` (
  `id` varchar(32) NOT NULL COMMENT '留言主键id',
  `nick_name` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `content` varchar(2000) NOT NULL,
  `create_time` datetime NOT NULL,
  `reply_userId` varchar(32) DEFAULT NULL,
  `reply_content` varchar(1000) DEFAULT NULL,
  `reply_time` datetime DEFAULT NULL,
  `showFlag` varchar(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `sentiveword` (
  `SENTIVE_ID` varchar(10) NOT NULL,
  `SENTIVE_CONTENT` varchar(30) NOT NULL,
  PRIMARY KEY (`SENTIVE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sequence` (
  `seq_name` varchar(50) NOT NULL COMMENT '序列名称',
  `current_value` int(11) NOT NULL COMMENT '当前值',
  `increment` int(11) NOT NULL DEFAULT '1' COMMENT '步长',
  PRIMARY KEY (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建视图
DROP VIEW IF EXISTS `past_12_month_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `past_12_month_view` AS select date_format(curdate(),'%Y-%m') AS `month` union select date_format((curdate() - interval 1 month),'%Y-%m') AS `month` union select date_format((curdate() - interval 2 month),'%Y-%m') AS `month` union select date_format((curdate() - interval 3 month),'%Y-%m') AS `month` union select date_format((curdate() - interval 4 month),'%Y-%m') AS `month` union select date_format((curdate() - interval 5 month),'%Y-%m') AS `month` union select date_format((curdate() - interval 6 month),'%Y-%m') AS `month` union select date_format((curdate() - interval 7 month),'%Y-%m') AS `month` union select date_format((curdate() - interval 8 month),'%Y-%m') AS `month` union select date_format((curdate() - interval 9 month),'%Y-%m') AS `month` union select date_format((curdate() - interval 10 month),'%Y-%m') AS `month` union select date_format((curdate() - interval 11 month),'%Y-%m') AS `month`;

-- 博客标签关系表
CREATE TABLE `blog_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_id` varchar(32) NOT NULL COMMENT '博客id',
  `label_id` int(11) NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8  COLLATE=utf8_unicode_ci COMMENT='博客标签关系表';

-- 访问日志记录表
CREATE TABLE `access_log` (
  `id` INT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `url` VARCHAR(150) NOT NULL  COMMENT  '请求URL',
  `userid` VARCHAR(32)  COMMENT '用户ID',
  `devicetype` VARCHAR(20) COMMENT '设备类型',
  `channeltype` VARCHAR(1) COMMENT '访问渠道 0：前端， 1：后台',
  `sessionId` VARCHAR(100) COMMENT 'sessionID',
  `ip` VARCHAR(50) COMMENT '访问者IP',
  `status` VARCHAR(10) COMMENT '访问状态',
  `returnMsg` VARCHAR(100) COMMENT '返回信息',
  `accesstime` datetime DEFAULT NULL COMMENT '访问时间'
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='访问日志记录表';
