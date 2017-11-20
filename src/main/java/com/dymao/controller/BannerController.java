package com.dymao.controller;

import com.dymao.model.Banner;
import com.dymao.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Mervin
 * @Description:
 * @date 2017/11/19 23:36
 */
@RestController
@RequestMapping("/banner")
public class BannerController {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Banner addBanner(Model model, Banner banner){
        banner.setCreateTime(new Date());
        bannerService.addBanner(banner);
        return banner;
    }

    @RequestMapping(value = "/findBannerList",method = RequestMethod.GET)
    public List<Banner> findBannerList(Model model){
        return  bannerService.findBannerList();
    }

}
