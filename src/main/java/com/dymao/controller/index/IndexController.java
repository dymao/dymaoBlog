package com.dymao.controller.index;

import com.dymao.common.Utils.DateUtils;
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
@RequestMapping(value = "/")
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
        request.getSession().setAttribute("currentDate", DateUtils.getStringDate(new Date(),DateUtils.DATE_YYYY_MM_DD_WEEK));
        request.getSession().setAttribute("DailySentence","你是我人生中唯一的主角，我却只能是你故事中的一晃而过的路人甲");
        return "index";
    }

}
