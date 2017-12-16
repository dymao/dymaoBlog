package com.dymao.common.constants;

import com.dymao.common.Utils.DateUtils;

import java.io.File;
import java.util.Date;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/3 23:10
 */
public class Config {
    //轮播图存储地址
    public static final String BANNER_IMAGE_PATH = "images" + File.separator + "banner" + File.separator;

    public static final String BLOG_IMAGE_BASE_PATH = "images" + File.separator + "blog" + File.separator;

    //博文缩略图存储地址
    public static final String BLOG__TITLE_IMAGE_PATH = BLOG_IMAGE_BASE_PATH+"titile"+ File.separator + DateUtils.getStringDate(new Date(),DateUtils.DATE_YYYYMMDD)+ File.separator;

    //博文正文图存储地址
    public static final String BLOG__CONTENT_IMAGE_PATH = BLOG_IMAGE_BASE_PATH+"content"+ File.separator + DateUtils.getStringDate(new Date(),DateUtils.DATE_YYYYMMDD)+ File.separator;
}
