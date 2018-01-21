package com.dymao.service;

import com.dymao.model.SentiveWord;

import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2018-01-17 00:54
 */
public interface SentiveWordService {

    int deleteByPrimaryKey(String sentiveId);

    int insert(SentiveWord sentiveWord);

    SentiveWord selectByPrimaryKey(String sentiveId);

    int updateByPrimaryKey(SentiveWord sentiveWord);

    List<String> findAll(Map map);
}
