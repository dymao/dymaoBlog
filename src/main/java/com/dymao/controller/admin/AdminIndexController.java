package com.dymao.controller.admin;

import com.dymao.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mervin
 * @Description:
 * @date 2017/11/19 0:06
 */
@Controller
public class AdminIndexController {


    @Autowired
    private BannerService bannerService;
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
        return "admin/welcome";
    }

}
