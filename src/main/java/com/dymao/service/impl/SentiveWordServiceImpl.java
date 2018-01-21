package com.dymao.service.impl;

import com.dymao.dao.mapper.SentiveWordMapper;
import com.dymao.model.SentiveWord;
import com.dymao.service.SentiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2018-01-17 00:55
 */
@Service
public class SentiveWordServiceImpl implements SentiveWordService {


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private SentiveWordMapper sentiveWordMapper;

    @Override
    public int deleteByPrimaryKey(String sentiveId) {
        return sentiveWordMapper.deleteByPrimaryKey(sentiveId);
    }

    @Override
    public int insert(SentiveWord sentiveWord) {
        return sentiveWordMapper.insert(sentiveWord);
    }

    @Override
    public SentiveWord selectByPrimaryKey(String sentiveId) {
        return sentiveWordMapper.selectByPrimaryKey(sentiveId);
    }

    @Override
    public int updateByPrimaryKey(SentiveWord sentiveWord) {
        return sentiveWordMapper.updateByPrimaryKey(sentiveWord);
    }

    @Override
    public List<String> findAll(Map map) {
        return sentiveWordMapper.findAll(map);
    }
}
