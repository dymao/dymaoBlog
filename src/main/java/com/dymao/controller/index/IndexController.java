package com.dymao.controller.index;

import com.dymao.DateUtils;
import com.dymao.model.Banner;
import com.dymao.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author Mervin
 * @Description:
 * @date 2017/11/19 0:06
 */
@Controller
public class IndexController {

    @Autowired
    private BannerService bannerService;
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
        //获取系统时间
        List<Banner> bannerList = bannerService.findBannerList();
        model.addAttribute("bannerList",bannerList);
        model.addAttribute("currentDate", DateUtils.getStringData(new Date(),DateUtils.DATE_YYYY_MM_DD_WEEK));

        return "index";
    }

}
