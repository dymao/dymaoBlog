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

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private CommonMapper commonMapper;

    @Override
    public String getBannerId() {
        String bannerId = DateUtils.getStringDate(new Date(),DateUtils.DATE_YYYYMMDD);
        String seqId = commonMapper.getBannerId();
        if(seqId.length() < 4){
            seqId = StringUtils.leftPad(seqId,4,"0");
        }
        return bannerId + seqId;
    }
}
