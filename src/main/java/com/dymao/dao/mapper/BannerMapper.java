package com.dymao.dao.mapper;

import com.dymao.model.Banner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Banner banner);

    int insertSelective(Banner banner);

    Banner selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Banner banner);

    int updateByPrimaryKey(Banner banner);

    List<Banner> findBannerList();

    List<Banner> findAllBanner();

    Integer findAllBannerCount();


    Integer deleteByBannerIds(List<String> bannerIds);
}