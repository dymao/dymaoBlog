package com.dymao.service;

import com.dymao.model.Banner;

import java.util.List;

/**
 * @author Mervin
 * @Description:
 * @date 2017/11/19 23:25
 */
public interface BannerService {

    public Integer addBanner(Banner banner);

    public List<Banner> findBannerList();
}
