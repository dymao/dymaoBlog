package com.dymao.dao.mapper;

import com.dymao.model.AccessLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccessLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccessLog record);

    AccessLog selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(AccessLog record);
}