package com.dymao.service;

import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2018-04-23 00:09
 */
public interface StatisticsService {
    Map queryStatisNum();
    Map getLastLoginTime(Map map);
    List<Map> getTransBlogNum();
    List<Map> getCategoryBlogNum();
    List<Map> getMonthCustomerNum();
}
