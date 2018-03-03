package com.dymao.controller.index;

import com.dymao.common.Utils.DateUtils;
import com.dymao.common.constants.Constant;
import com.dymao.model.Banner;
import com.dymao.model.Blog;
import com.dymao.model.FriendlyLink;
import com.dymao.model.Label;
import com.dymao.service.*;
import com.dymao.vo.BlogVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private LabelService labelService;

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
        Locale localeCN = Locale.SIMPLIFIED_CHINESE;
        request.getSession().setAttribute("currentDate", DateUtils.getStringDate(new Date(),DateUtils.DATE_YYYY_MM_DD_WEEK,localeCN));

        ServletContext application = request.getServletContext();
        String DailySentence = (String)application.getAttribute("DailySentence");
        if(StringUtils.isBlank(DailySentence)){
            application.setAttribute("DailySentence","锲而舍之，朽木不折；锲而不舍，金石可镂");
        }

        List<FriendlyLink> friendlinkList = (List<FriendlyLink>)application.getAttribute("friendlinkList");
        if(friendlinkList == null){
            friendlinkList = friendLinkService.findFriendlinkList(new HashMap<>());
            application.setAttribute("friendlinkList",friendlinkList);
        }

        //获取轮播图列表
        List<Banner> bannerList = bannerService.findBannerList();

        Map paramMap = new HashMap();
        paramMap.put("isPublic", Constant.BLOG_IS_PUBLIC_0);
        paramMap.put("isAudit",Constant.BLOG_IS_AUDIT_0);
        paramMap.put("deleted",Constant.DELETE_FLAG_0);
        List<BlogVo> blogList = blogService.selectBlogList(paramMap);

        model.addAttribute("bannerList",bannerList);

        model.addAttribute("blogList",blogList);

        return "index";
    }
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/welcome")
    public String welcome(Model model, HttpServletRequest request, HttpServletResponse response) {

        //获取轮播图列表
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

    @RequestMapping("/rightDataList")
    @ResponseBody
    public Map getHotBlogList(){
        Map resultMap = new HashMap();

        // 查询热门博客
        Map paramMap = new HashMap();
        paramMap.put("isPublic", Constant.BLOG_IS_PUBLIC_0);
        paramMap.put("isAudit",Constant.BLOG_IS_AUDIT_0);
        paramMap.put("deleted",Constant.DELETE_FLAG_0);
        List<Blog> hotBlogList = blogService.selectHotBlogList(paramMap);
        resultMap.put("hotBlogList",hotBlogList);

       /* // 查询最新留言
        paramMap.clear();
        paramMap.put("showflag", Constant.SHOW_FLAG_0);
        paramMap.put("limitNum", Integer.valueOf(5));
        List<Message> messageList = messageService.findAllByCondition(paramMap);
        resultMap.put("newMessageList",messageList);*/

        List<Label> labelList = labelService.findAllLabel(new HashMap());

        resultMap.put("labelList",labelList);
        return resultMap;
    }

}
