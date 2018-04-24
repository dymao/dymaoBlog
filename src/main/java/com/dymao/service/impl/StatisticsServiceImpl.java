package com.dymao.service.impl;

import com.dymao.dao.mapper.StatisticsMapper;
import com.dymao.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2018-04-23 00:10
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public Map getLastLoginTime(Map map) {
        return statisticsMapper.getLastLoginTime(map);
    }

    @Override
    public List<Map> getTransBlogNum() {
        return statisticsMapper.getTransBlogNum();
    }

    @Override
    public List<Map> getCategoryBlogNum() {
        return statisticsMapper.getCategoryBlogNum();
    }
    @Override
    public List<Map> getMonthCustomerNum() {
        return statisticsMapper.getMonthCustomerNum();
    }

    @Override
    public Map queryStatisNum() {
        return statisticsMapper.queryStatisNum();
    }

}
