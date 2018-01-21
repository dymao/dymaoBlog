package com.dymao.dao.mapper;

import com.dymao.model.SentiveWord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SentiveWordMapper {
    int deleteByPrimaryKey(String sentiveId);

    int insert(SentiveWord sentiveWord);

    SentiveWord selectByPrimaryKey(String sentiveId);

    int updateByPrimaryKey(SentiveWord sentiveWord);

    List<String> findAll(Map map);
}