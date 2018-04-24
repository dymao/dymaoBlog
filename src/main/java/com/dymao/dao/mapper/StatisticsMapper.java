package com.dymao.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/3 20:34
 */
@Mapper
public interface StatisticsMapper {
    Map queryStatisNum();
    Map getLastLoginTime(Map map);
    List<Map> getTransBlogNum();
    List<Map> getCategoryBlogNum();
    List<Map> getMonthCustomerNum();
}
