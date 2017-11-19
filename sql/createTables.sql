CREATE  DATABASE  dymaoBlog;
USE `dymaoBlog`;

CREATE TABLE `friendlylink` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '标题',
  `url` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT  '链接地址',
  `sn` int(11) NOT NULL DEFAULT '1' COMMENT '序号',
  `create_time` datetime NOT NULL COMMENT ' 创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE friendlylink COMMENT '友情链接表';


CREATE TABLE `banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '标题',
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '图片名称',
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '链接地址',
  `sn` int(11) DEFAULT NULL COMMENT '序号',
  `deleted` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT '滚动图片表';


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