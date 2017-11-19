package com.dymao.model;

import java.util.Date;

public class Blog {
    private Integer id;

    private Integer userId;

    private String title;

    private String contentShow;

    private String keyword;

    private Integer ispublic;

    private Integer deleted;

    private Integer category;

    private Integer view;

    private Integer type;

    private String image;

    private Date createTime;

    private Integer zhuanzai;

    private String zhuanzaiurl;

    private Integer editortype;

    private Integer level;

    private Integer showside;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getIspublic() {
        return ispublic;
    }

    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getZhuanzai() {
        return zhuanzai;
    }

    public void setZhuanzai(Integer zhuanzai) {
        this.zhuanzai = zhuanzai;
    }

    public String getZhuanzaiurl() {
        return zhuanzaiurl;
    }

    public void setZhuanzaiurl(String zhuanzaiurl) {
        this.zhuanzaiurl = zhuanzaiurl == null ? null : zhuanzaiurl.trim();
    }

    public Integer getEditortype() {
        return editortype;
    }

    public void setEditortype(Integer editortype) {
        this.editortype = editortype;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getShowside() {
        return showside;
    }

    public void setShowside(Integer showside) {
        this.showside = showside;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}