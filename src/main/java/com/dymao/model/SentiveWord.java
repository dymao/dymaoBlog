package com.dymao.model;

public class SentiveWord {

    private String sentiveId; // 敏感词ID

    private String sentiveContent; // 敏感词

    public String getSentiveId() {
        return sentiveId;
    }

    public void setSentiveId(String sentiveId) {
        this.sentiveId = sentiveId == null ? null : sentiveId.trim();
    }

    public String getSentiveContent() {
        return sentiveContent;
    }

    public void setSentiveContent(String sentiveContent) {
        this.sentiveContent = sentiveContent == null ? null : sentiveContent.trim();
    }
}