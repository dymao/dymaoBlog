package com.dymao.service.impl;

import com.dymao.dao.mapper.BannerMapper;
import com.dymao.model.Banner;
import com.dymao.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mervin
 * @Description:
 * @date 2017/11/19 23:27
 */
@Service
public class BannerServiceImpl implements BannerService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private BannerMapper  bannerMapper;

    @Override
    public Integer addBanner(Banner banner) {
        return bannerMapper.insert(banner);
    }

    @Override
    public Integer deleteByPrimaryKey(String id) {
        return bannerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Banner banner) {
        return bannerMapper.updateByPrimaryKeySelective(banner);
    }

    @Override
    public List<Banner> findBannerList() {
        return bannerMapper.findBannerList();
    }

    @Override
    public List<Banner> findAllBanner() {
        return bannerMapper.findAllBanner();
    }

    @Override
    public Integer findAllBannerCount() {
        return bannerMapper.findAllBannerCount();
    }
}
