package com.dymao.common.constants;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/3 21:16
 */
public class Constant {

    // 数据库记录删除状态
    public static final String DELETE_FLAG_0 = "0"; // 未删除
    public static final String DELETE_FLAG_1 = "1"; // 已删除

    // 记录显示状态
    public static final String SHOW_FLAG_0 = "0"; // 显示
    public static final String SHOW_FLAG_1 = "1"; // 不显示

    // 博客分类级别
    public static final Integer BLOG_CATEGORY_LEVEL_1 = 1; // 1类
    public static final Integer BLOG_CATEGORY_LEVEL_2 = 2; // 2类

    // 博客是否发表
    public static final String BLOG_IS_PUBLIC_0 = "0"; // 已发表
    public static final String BLOG_IS_PUBLIC_1 = "1"; // 未发表

    // 博客浏览权限
    public static final String BLOG_AUTHORITY_0 = "0"; // 所有人可见
    public static final String BLOG_AUTHORITY_1 = "1"; // 仅会员可见

    // 博客是否原创
    public static final String BLOG_IS_TRANSSHIPMENT_0 = "0"; // 原创
    public static final String BLOG_IS_TRANSSHIPMENT_1 = "1"; // 转载

    // 博客是否推荐
    public static final String BLOG_IS_RECOMMEND_0 = "0"; // 否
    public static final String BLOG_IS_RECOMMEND_1 = "1"; // 是

    // 博客是否已审核
    public static final String BLOG_IS_AUDIT_0 = "0"; // 0 已审核
    public static final String BLOG_IS_AUDIT_1 = "1"; // 1 未审核

    // 验证码验证返回状态 0：通过  1：验证码已失效  2：验证码错误
    public static final Integer IMAGE_VRIFY_CODE_STATUS_0 = 0; // 通过
    public static final Integer IMAGE_VRIFY_CODE_STATUS_1 = 1; // 验证码已失效
    public static final Integer IMAGE_VRIFY_CODE_STATUS_2 = 2; // 验证码错误

    // 登录返回状态 0：通过  1：验证码已失效  2：验证码错误
    public static final String LOGIN_VRRFY_STATUS_0 = "0"; // 通过
    public static final String LOGIN_VRRFY_STATUS_1 = "1"; // 验证码失效
    public static final String LOGIN_VRRFY_STATUS_2 = "2"; // 验证码错误
    public static final String LOGIN_VRRFY_STATUS_3 = "3"; // 验证码为空
    public static final String LOGIN_VRRFY_STATUS_4 = "4"; // 用户名或密码错误
    public static final String LOGIN_VRRFY_STATUS_5 = "5"; // 用户已注销
    public static final String LOGIN_VRRFY_STATUS_6 = "6"; // 用户已被冻结
    public static final String LOGIN_VRRFY_STATUS_7 = "7"; // 用户已被锁定

}