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



CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `title` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '博客标题',
  `content` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '博客内容',
  `content_show` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '博客前段显示内容',
  `keyword` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '关键词',
  `ispublic` int(11) NOT NULL COMMENT '是否发表',
  `deleted` int(11) NOT NULL COMMENT '是否已删除',
  `category` int(11) NOT NULL COMMENT '分类的id',
  `view` int(11) NOT NULL DEFAULT '0' COMMENT '浏览次数',
  `type` int(11) NOT NULL DEFAULT '0' ,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '缩略图地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `zhuanzai` int(11) NOT NULL DEFAULT '0',
  `zhuanzaiurl` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `editortype` int(11) NOT NULL DEFAULT '0',
  `level` int(11) NOT NULL DEFAULT '0',
  `showside` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `blog_userid_foregignkey` (`user_id`),
  KEY `blog_category_foregignkey` (`category`),
  CONSTRAINT `blog_category_foregignkey` FOREIGN KEY (`category`) REFERENCES `blogcategory` (`id`)
  #CONSTRAINT `blog_userid_foregignkey` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT '博客文章表';

CREATE TABLE `blogcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT '分类标签';