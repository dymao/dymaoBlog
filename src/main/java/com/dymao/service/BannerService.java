package com.dymao.service;

import com.dymao.model.Banner;

import java.util.List;

/**
 * @author Mervin
 * @Description:
 * @date 2017/11/19 23:25
 */
public interface BannerService {

    Integer addBanner(Banner banner);

    Integer deleteByPrimaryKey(String id);

    Integer updateByPrimaryKeySelective(Banner banner);

    List<Banner> findBannerList();

    List<Banner> findAllBanner();

    Integer findAllBannerCount();

    Integer deleteByBannerIds(List<String> bannerIds);
}
