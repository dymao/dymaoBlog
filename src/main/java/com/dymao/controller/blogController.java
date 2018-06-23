package com.dymao.controller;

import com.dymao.common.Utils.DateUtils;
import com.dymao.common.constants.Constant;
import com.dymao.model.Banner;
import com.dymao.model.Blog;
import com.dymao.service.BannerService;
import com.dymao.service.BlogService;
import com.dymao.vo.BaseMessage;
import com.dymao.vo.BlogVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.*;

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
    public String list(Model model, String pageNum, String pageSize, String categoryIdOne,
                       String categoryIdTwo,String searchWord,String archiveDate,
                       String labelId) {
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
        if(StringUtils.isNotBlank(searchWord)){
            paramMap.put("searchWord",searchWord);
            model.addAttribute("searchWord",searchWord);
        }
        if(StringUtils.isNotBlank(categoryIdOne)){
            paramMap.put("categoryIdOne",categoryIdOne);
        }
        if(StringUtils.isNotBlank(categoryIdTwo)){
            paramMap.put("categoryIdTwo",categoryIdTwo);
        }
        if(StringUtils.isNotBlank(archiveDate)){
            paramMap.put("archiveDate",archiveDate);
        }
        if(StringUtils.isNotBlank(labelId)){
            Map labelMap = new HashMap();
            labelMap.put("labelId",labelId);
            List<String> blogIdList = blogService.queryBlogIdByLabelId(labelMap);
            if(blogIdList != null && blogIdList.size() > 0){
                paramMap.put("list",blogIdList);
            }else{
                blogIdList = new ArrayList();
                blogIdList.add("1");
                paramMap.put("list",blogIdList); // 如果传了标签过来，而且查询不到记录，则new一个空数组，防止查询条件不生效
            }
        }
        PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<BlogVo> blogList = blogService.selectBlogList(paramMap);
        PageInfo<BlogVo> pageInfo = new PageInfo<BlogVo>(blogList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("totalNum",pageInfo.getTotal());
        return "blog/blog-list";
    }
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/detail/{id}")
    public String blogDetail(Model model, @PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {

        BlogVo blog = blogService.selectByPrimaryKey(id);
        if(blog == null || !StringUtils.equals(Constant.BLOG_IS_PUBLIC_0, blog.getIsPublic())
                || !StringUtils.equals(Constant.BLOG_IS_AUDIT_0, blog.getIsAudit())
                || !StringUtils.equals(Constant.DELETE_FLAG_0, blog.getDeleted())){
            return "blog/blog-detail";
        }
        model.addAttribute("blog",blog);
        Map paramMap = new HashMap();
        paramMap.put("id",id);
        BlogVo preBlog = blogService.selectPreBlog(paramMap);
        BlogVo nextBlog = blogService.selectNextBlog(paramMap);
        model.addAttribute("preBlog",preBlog);
        model.addAttribute("nextBlog",nextBlog);
        // 更新浏览次数
        blogService.updateViewNumByPrimaryKey(paramMap);
        return "blog/blog-detail";
    }

    /**
     * 点赞
     * @return
     */
    @RequestMapping(value = "/likeNum/{id}",method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage updateLikeNumByPrimaryKey(@PathVariable("id") String id, HttpServletRequest request) {
        BaseMessage result = new BaseMessage();
        BlogVo blog = blogService.selectByPrimaryKey(id);
        if(blog == null || !StringUtils.equals(Constant.BLOG_IS_PUBLIC_0, blog.getIsPublic())
                || !StringUtils.equals(Constant.BLOG_IS_AUDIT_0, blog.getIsAudit())
                || !StringUtils.equals(Constant.DELETE_FLAG_0, blog.getDeleted())){
            result.setReturnCode(201);
            result.setReturnMsg("文章不存在！");
            return result;
        }
        HttpSession session = request.getSession();
        String flag = (String)session.getAttribute("zan_"+id);
        if(StringUtils.isNotBlank(flag)){
            result.setReturnCode(202);
            result.setReturnMsg("您已赞过！");
            return result;
        }
        Map<String,String> paramMap = new HashMap<String,String>(1);
        paramMap.put("id",id);
        blogService.updateLikeNumByPrimaryKey(paramMap);
        session.setAttribute("zan_"+id,"YES");
        result.setReturnMsg("点赞成功");
        return result;
    }

    /**
     * 点赞
     * @return
     */
    @RequestMapping(value = "/treadNum/{id}",method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage updateTreadNumByPrimaryKey(@PathVariable("id") String id, HttpServletRequest request) {
        BaseMessage result = new BaseMessage();
        BlogVo blog = blogService.selectByPrimaryKey(id);
        if(blog == null || !StringUtils.equals(Constant.BLOG_IS_PUBLIC_0, blog.getIsPublic())
                || !StringUtils.equals(Constant.BLOG_IS_AUDIT_0, blog.getIsAudit())
                || !StringUtils.equals(Constant.DELETE_FLAG_0, blog.getDeleted())){
            result.setReturnCode(201);
            result.setReturnMsg("文章不存在！");
            return result;
        }
        HttpSession session = request.getSession();
        String flag = (String)session.getAttribute("cai_"+id);
        if(StringUtils.isNotBlank(flag)){
            result.setReturnCode(202);
            result.setReturnMsg("您已踩过！");
            return result;
        }
        Map<String,String> paramMap = new HashMap<String,String>(1);
        paramMap.put("id",id);
        blogService.updateTreadNumByPrimaryKey(paramMap);
        session.setAttribute("cai_"+id,"YES");
        result.setReturnMsg("踩成功");
        return result;
    }
}
