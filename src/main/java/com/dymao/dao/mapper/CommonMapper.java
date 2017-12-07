package com.dymao.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/3 20:34
 */
@Mapper
public interface CommonMapper {
    String getBannerId();
    String getCategoryId();
}
