package com.dymao.dao.mapper;

import com.dymao.model.Banner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Banner banner);

    int insertSelective(Banner banner);

    Banner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Banner banner);

    int updateByPrimaryKey(Banner banner);

    List<Banner> findBannerList();
}