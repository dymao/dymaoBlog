package com.dymao.controller;

import com.dymao.common.Utils.DateUtils;
import com.dymao.common.constants.Constant;
import com.dymao.model.Banner;
import com.dymao.model.Blog;
import com.dymao.service.BannerService;
import com.dymao.service.BlogService;
import com.dymao.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
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
@RequestMapping(value = "/blog")
public class blogController {

    @Autowired
    private BlogService blogService;
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return "blog/blog-list";
    }
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/detail/{id}")
    public String blogDetail(Model model, @PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {

        Blog blog = blogService.selectByPrimaryKey(id);
        model.addAttribute("blog",blog);
        //获取系统时间
        return "blog/blog-detail";
    }

}
