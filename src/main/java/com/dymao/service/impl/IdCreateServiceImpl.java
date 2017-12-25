package com.dymao.service.impl;

import com.dymao.common.Utils.DateUtils;
import com.dymao.dao.mapper.CommonMapper;
import com.dymao.service.IdCreateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/3 18:54
 */
@Service
public class IdCreateServiceImpl implements IdCreateService {

    public static String SIGN_CODE_BANNER = "0";        // 博客分类
    public static String SIGN_CODE_BlOG_CATEGORY = "1"; // 博客分类
    public static String SIGN_CODE_FRIEND_LINK = "2";   // 友情链接
    public static String SIGN_CODE_BLOG_ID = "3";       // 博客ID
    public static String SIGN_CODE_MESSAGE_ID = "4";    // 留言ID

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private CommonMapper commonMapper;

    @Override
    public String getBannerId() {
        String dateStr = DateUtils.getStringDate(new Date(),DateUtils.DATE_YYMMDD);
        String seqId = commonMapper.getBannerId();
        if(seqId.length() < 5){
            seqId = StringUtils.leftPad(seqId,5,"0");
        }else if(seqId.length() > 5){
            seqId = StringUtils.substring(seqId,seqId.length()-5);
        }
        return dateStr + seqId + SIGN_CODE_BANNER;
    }

    @Override
    public String getCategoryId() {
        String dateStr = DateUtils.getStringDate(new Date(),DateUtils.DATE_YYMMDD);
        String seqId = commonMapper.getCategoryId();
        if(seqId.length() < 5){
            seqId = StringUtils.leftPad(seqId,5,"0");
        }else if(seqId.length() > 5){
            seqId = StringUtils.substring(seqId,seqId.length()-5);
        }
        return dateStr + seqId + SIGN_CODE_BlOG_CATEGORY;
    }

    @Override
    public String getFriendlinkId() {
        String dateStr = DateUtils.getStringDate(new Date(),DateUtils.DATE_YYMMDD);
        String seqId = commonMapper.getFriendlinkId();
        if(seqId.length() < 5){
            seqId = StringUtils.leftPad(seqId,5,"0");
        }else if(seqId.length() > 5){
            seqId = StringUtils.substring(seqId,seqId.length()-5);
        }
        return dateStr + seqId + SIGN_CODE_FRIEND_LINK;
    }

    @Override
    public String getBlogId() {
        String dateStr = DateUtils.getStringDate(new Date(),DateUtils.DATE_YYMMDD);
        String seqId = commonMapper.getBlogId();
        if(seqId.length() < 25){
            seqId = StringUtils.leftPad(seqId,25,"0");
        }else if(seqId.length() > 25){
            seqId = StringUtils.substring(seqId,seqId.length()-5);
        }
        return dateStr + seqId + SIGN_CODE_BLOG_ID;
    }

    @Override
    public String getMessageId() {
        String dateStr = DateUtils.getStringDate(new Date(),DateUtils.DATE_YYMMDD);
        String seqId = commonMapper.getMessageId();
        if(seqId.length() < 25){
            seqId = StringUtils.leftPad(seqId,25,"0");
        }else if(seqId.length() > 25){
            seqId = StringUtils.substring(seqId,seqId.length()-5);
        }
        return dateStr + seqId + SIGN_CODE_MESSAGE_ID;
    }
}
