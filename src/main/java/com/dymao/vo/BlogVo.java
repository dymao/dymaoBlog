package com.dymao.vo;

import java.util.Date;

public class BlogVo {
    private String id;              // id主键

    private String userId;          // 用户id

    private String title;           // 博客标题

    private String contentShow;     // 博客摘要

    private String keyword;         // 博客关键字

    private String isPublic;        // 是否已发表

    private String deleted;         // 是否已删除

    private String categoryIdOne;   // 一级分类ID

    private String categoryOneName; // 一级分类名称

    private String categoryIdTwo;   // 二级分类ID

    private String categoryTwoName; // 二级分类名称

    private Integer viewNum;        // 浏览次数

    private Integer likeNum;        // 点赞次数

    private Integer treadNum;       // 踩的次数

    private String image;           // 博客缩略图地址

    private String isTransshipment; // 是否转载

    private String transshipmentUrl;// 转载地址

    private String isRecommend;     // 是否推荐

    private String isAudit;         // 是否已审核

    private String auditUserId;     // 审核员

    private String authority;       // 浏览权限

    private Date createTime;        // 创建时间

    private Date updateTime;        // 更新时间

    private String content;         // 博客正文

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContentShow() {
        return contentShow;
    }

    public void setContentShow(String contentShow) {
        this.contentShow = contentShow == null ? null : contentShow.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic == null ? null : isPublic.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    public String getCategoryIdOne() {
        return categoryIdOne;
    }

    public void setCategoryIdOne(String categoryIdOne) {
        this.categoryIdOne = categoryIdOne == null ? null : categoryIdOne.trim();
    }

    public String getCategoryIdTwo() {
        return categoryIdTwo;
    }

    public void setCategoryIdTwo(String categoryIdTwo) {
        this.categoryIdTwo = categoryIdTwo == null ? null : categoryIdTwo.trim();
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getTreadNum() {
        return treadNum;
    }

    public void setTreadNum(Integer treadNum) {
        this.treadNum = treadNum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getIsTransshipment() {
        return isTransshipment;
    }

    public void setIsTransshipment(String isTransshipment) {
        this.isTransshipment = isTransshipment == null ? null : isTransshipment.trim();
    }

    public String getTransshipmentUrl() {
        return transshipmentUrl;
    }

    public void setTransshipmentUrl(String transshipmentUrl) {
        this.transshipmentUrl = transshipmentUrl == null ? null : transshipmentUrl.trim();
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend == null ? null : isRecommend.trim();
    }

    public String getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit == null ? null : isAudit.trim();
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId == null ? null : auditUserId.trim();
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority == null ? null : authority.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCategoryOneName() {
        return categoryOneName;
    }

    public void setCategoryOneName(String categoryOneName) {
        this.categoryOneName = categoryOneName;
    }

    public String getCategoryTwoName() {
        return categoryTwoName;
    }

    public void setCategoryTwoName(String categoryTwoName) {
        this.categoryTwoName = categoryTwoName;
    }
}