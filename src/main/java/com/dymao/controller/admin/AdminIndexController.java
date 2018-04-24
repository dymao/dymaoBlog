package com.dymao.controller.admin;

import com.dymao.common.Utils.MapUtil;
import com.dymao.common.constants.Constant;
import com.dymao.common.constants.Dict;
import com.dymao.model.AdminUser;
import com.dymao.service.BannerService;
import com.dymao.service.BlogService;
import com.dymao.service.StatisticsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017/11/19 0:06
 */
@Controller
public class AdminIndexController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private StatisticsService statisticsService;
    /**
     * 后台首页
     * @return
     */
    @RequestMapping(value = "/admin")
    public String adminIndex(Model model, HttpServletRequest request, HttpServletResponse response) {
    	if(request.getSession().getAttribute("adminUser") == null) {
    		return "redirect:/admin/toLoginPage";
    	}
    	return "admin/index";
    }
    /**
     * 后台首页
     * @return
     */
    @RequestMapping(value = "/admin/welcome")
    public String adminWelcome(Model model, HttpServletRequest request, HttpServletResponse response) {
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Dict.ADMIN_USER);
        String userId = "";
        if(adminUser != null){
            userId = adminUser.getId();
        }
        model.addAttribute("isFirst","Y");
        if(!StringUtils.isBlank(userId)){
            Map paramMap  = new HashMap();
            paramMap.put("userid",userId);
            Map resultMap = statisticsService.getLastLoginTime(paramMap);
            if(resultMap != null && resultMap.get("loginTime") != null){
                model.addAttribute("isFirst","N");
                model.addAttribute("lastLoginTime", resultMap.get("loginTime"));
            }
        }
        // 查询统计数据
        Map statisMap = statisticsService.queryStatisNum();
        model.addAttribute("statisMap",statisMap);
        return "admin/welcome";
    }

    /**
     * 获取原创转载博客占比数据
     * @return
     */
    @RequestMapping(value = "/admin/getTranBlogNum")
    @ResponseBody
    public Map adminGetTranBlogNum(Model model, HttpServletRequest request, HttpServletResponse response) {
        Map resultMap = new HashMap();
        List<String> nameList = new ArrayList<>();
        List<Map> tranBlogNumList = statisticsService.getTransBlogNum();
        for(Map map: tranBlogNumList){
            nameList.add((String)map.get("name"));
        }
        resultMap.put("tranNameList", nameList);
        resultMap.put("tranBlogNumList", tranBlogNumList);
        return resultMap;
    }
    /**
     * 获取一级分类统计数据
     * @return
     */
    @RequestMapping(value = "/admin/getCategoryBlogNum")
    @ResponseBody
    public Map adminGetCategoryBlogNum(Model model, HttpServletRequest request, HttpServletResponse response) {
        Map resultMap = new HashMap();
        List<String> nameList = new ArrayList<>();
        List<Map> categoryBlogNumList = statisticsService.getCategoryBlogNum();
        for(Map map: categoryBlogNumList){
            nameList.add((String)map.get("name"));
        }
        resultMap.put("categoryNameList", nameList);
        resultMap.put("categoryBlogNumList", categoryBlogNumList);
        return resultMap;
    }

    /**
     * 获取近一年博客统计数据
     * @return
     */
    @RequestMapping(value = "/admin/getMonthBlogNum")
    @ResponseBody
    public Map adminMonthBlogNum(Model model, HttpServletRequest request, HttpServletResponse response) {
        Map resultMap = new HashMap();
        List<String> nameList = new ArrayList<>();
        List<Long> blogNumMonthList = new ArrayList<>();
        Map paramMap = new HashMap();
        paramMap.put("isPublic", Constant.BLOG_IS_PUBLIC_0);
        paramMap.put("isAudit",Constant.BLOG_IS_AUDIT_0);
        paramMap.put("deleted",Constant.DELETE_FLAG_0);
        List<Map> blogArchiveList = blogService.selectBlogArchiveList(paramMap);
        for(Map map: blogArchiveList){
            nameList.add((String)map.get("monthKey"));
            blogNumMonthList.add((Long) map.get("blogNum"));
        }
        resultMap.put("monthNameList", nameList);
        resultMap.put("monthBlogNumList", blogNumMonthList);
        return resultMap;
    }

    /**
     * 获取近一年访客统计数据
     * @return
     */
    @RequestMapping(value = "/admin/getMonthCustomerNum")
    @ResponseBody
    public Map adminMonthCustomerNum(Model model, HttpServletRequest request, HttpServletResponse response) {
        Map resultMap = new HashMap();
        List<String> nameList = new ArrayList<>();
        List<Long> monthCustomerNumList = new ArrayList<>();
        List<Map> monthCustomerList = statisticsService.getMonthCustomerNum();
        for(Map map: monthCustomerList){
            nameList.add((String)map.get("monthKey"));
            monthCustomerNumList.add((Long) map.get("userNum"));
        }
        resultMap.put("monthNameList", nameList);
        resultMap.put("monthCustomerNumList", monthCustomerNumList);
        return resultMap;
    }

}
