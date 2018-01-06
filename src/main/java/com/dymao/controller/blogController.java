package com.dymao.controller;

import com.dymao.common.Utils.DateUtils;
import com.dymao.common.constants.Constant;
import com.dymao.model.Banner;
import com.dymao.model.Blog;
import com.dymao.service.BannerService;
import com.dymao.service.BlogService;
import com.dymao.vo.BlogVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String list(Model model, String pageNum, String pageSize, String categoryIdOne,String categoryIdTwo) {
        if(StringUtils.isEmpty(pageNum) || !StringUtils.isNumeric(pageNum)){
            pageNum = "1";
        }
        if(StringUtils.isEmpty(pageSize) || !StringUtils.isNumeric(pageSize)){
            pageSize = "10";
        }
        Map paramMap = new HashMap();
        paramMap.put("isPublic", Constant.BLOG_IS_PUBLIC_0);
        paramMap.put("isAudit",Constant.BLOG_IS_AUDIT_0);
        paramMap.put("deleted",Constant.DELETE_FLAG_0);
        if(StringUtils.isNotBlank(categoryIdOne)){
            paramMap.put("categoryIdOne",categoryIdOne);
        }
        if(StringUtils.isNotBlank(categoryIdTwo)){
            paramMap.put("categoryIdTwo",categoryIdTwo);
        }
        PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<BlogVo> blogList = blogService.selectBlogList(paramMap);
        PageInfo<BlogVo> pageInfo = new PageInfo<BlogVo>(blogList);
        model.addAttribute("pageInfo",pageInfo);
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
        Map paramMap = new HashMap();
        paramMap.put("id",id);
        Blog preBlog = blogService.selectPreBlog(paramMap);
        Blog nextBlog = blogService.selectNextBlog(paramMap);
        model.addAttribute("preBlog",preBlog);
        model.addAttribute("nextBlog",nextBlog);
        // 更新浏览次数
        blogService.updateViewNumByPrimaryKey(paramMap);
        return "blog/blog-detail";
    }

}
