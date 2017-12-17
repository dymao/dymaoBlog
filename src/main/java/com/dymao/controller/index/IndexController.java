package com.dymao.controller.index;

import com.dymao.common.Utils.DateUtils;
import com.dymao.common.constants.Constant;
import com.dymao.model.Banner;
import com.dymao.service.BannerService;
import com.dymao.service.BlogService;
import com.dymao.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private BlogService blogService;
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("currentDate", DateUtils.getStringDate(new Date(),DateUtils.DATE_YYYY_MM_DD_WEEK));
        request.getSession().setAttribute("DailySentence","你是我人生中唯一的主角，我却只能是你故事中的一晃而过的路人甲");
        return "index";
    }
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/welcome")
    public String welcome(Model model, HttpServletRequest request, HttpServletResponse response) {

        //获取系统时间
        List<Banner> bannerList = bannerService.findBannerList();

        Map paramMap = new HashMap();
        paramMap.put("isPublic", Constant.BLOG_IS_PUBLIC_0);
        paramMap.put("isAudit",Constant.BLOG_IS_AUDIT_0);
        paramMap.put("deleted",Constant.DELETE_FLAG_0);
        List<BlogVo> blogList = blogService.selectBlogList(paramMap);

        model.addAttribute("bannerList",bannerList);

        model.addAttribute("blogList",blogList);
        return "welcome";
    }

}
