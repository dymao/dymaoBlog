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

    public static String SIGN_CODE_BANNER = "0"; // 博客分类
    public static String SIGN_CODE_BlOG_CATEGORY = "1"; // 博客分类

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private CommonMapper commonMapper;

    @Override
    public String getBannerId() {
        String bannerId = DateUtils.getStringDate(new Date(),DateUtils.DATE_YYYYMMDD);
        String seqId = commonMapper.getBannerId();
        if(seqId.length() < 3){
            seqId = StringUtils.leftPad(seqId,3,"0");
        }else if(seqId.length() > 3){
            seqId = StringUtils.substring(seqId,seqId.length()-3);
        }
        return bannerId + seqId + SIGN_CODE_BANNER;
    }

    @Override
    public String getCategoryId() {
        String bannerId = DateUtils.getStringDate(new Date(),DateUtils.DATE_YYYYMMDD);
        String seqId = commonMapper.getBannerId();
        if(seqId.length() < 3){
            seqId = StringUtils.leftPad(seqId,3,"0");
        }else if(seqId.length() > 3){
            seqId = StringUtils.substring(seqId,seqId.length()-3);
        }
        return bannerId + seqId + SIGN_CODE_BlOG_CATEGORY;
    }
}
