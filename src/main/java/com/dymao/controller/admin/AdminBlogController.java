package com.dymao.controller.admin;

import com.dymao.common.constants.Config;
import com.dymao.common.constants.Constant;
import com.dymao.model.Blog;
import com.dymao.model.BlogCategory;
import com.dymao.model.Label;
import com.dymao.service.BlogCategoryService;
import com.dymao.service.BlogService;
import com.dymao.service.IdCreateService;
import com.dymao.service.LabelService;
import com.dymao.vo.BaseMessage;
import com.dymao.vo.BlogVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/9 11:06
 */
@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogCategoryService blogCategoryService;

    @Autowired
    private IdCreateService idCreateService;

    @Autowired
    private LabelService labelService;

    @RequestMapping(value = "/toBlogListPage", method = RequestMethod.GET)
    public String toBlogListPage(Model model){
        Map paramMap = new HashMap();
        paramMap.put("deleted",Constant.DELETE_FLAG_0);
        List<BlogVo> blogVoList = blogService.selectBlogList(paramMap);
        model.addAttribute("blogVoList",blogVoList);
        return "admin/blog/blogList";
    }

    /**
     * 跳转到博客新增页
     * @return
     */
    @RequestMapping(value = "/toAddPage",method = RequestMethod.GET)
    public String toAdminBlogAdd(Model model) {
        // 查询一级分类列表
        List<BlogCategory> categoryOneLevelList = blogCategoryService.findCategoryList(Constant.BLOG_CATEGORY_LEVEL_1);
        model.addAttribute("categoryOneLevelList",categoryOneLevelList);
        // 查询所有标签
        List<Label> labelList = labelService.findAllLabel(new HashMap());
        model.addAttribute("labelList",labelList);
        return "admin/blog/blog-add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage addBlog(Model model, Blog blog,String[] label){
        BaseMessage baseMessage = new BaseMessage();
        int count = 0;
        Date now = new Date();
        blog.setUpdateTime(now);
        blog.setIsAudit(Constant.BLOG_IS_AUDIT_1); // 无论是新增还是修改，状态都应该是待审核


        if(StringUtils.isEmpty(blog.getId())){// 新增
            blog.setId(idCreateService.getBlogId());
            blog.setDeleted(Constant.DELETE_FLAG_0);
            blog.setIsPublic(Constant.BLOG_IS_PUBLIC_0);
            blog.setUserId("admin");
            blog.setViewNum(0);
            blog.setLikeNum(0);
            blog.setTreadNum(0);
            blog.setIsRecommend(Constant.BLOG_IS_RECOMMEND_0);

            blog.setCreateTime(now);
            count = blogService.insert(blog);
        }else{   // 修改
            count = blogService.updateByPrimaryKey(blog);
        }

        if(count <=0){
            baseMessage.setReturnCode(201);
            baseMessage.setReturnMsg("保存失败");
        }
        return baseMessage;
    }

    /**
     * 跳转到博客修改页
     * @return
     */
    @RequestMapping(value = "/toEditPage/{id}",method = RequestMethod.GET)
    public String toAdminBlogEdit(@PathVariable String id,Model model) {
        // 查询一级分类列表
        List<BlogCategory> categoryOneLevelList = blogCategoryService.findCategoryList(Constant.BLOG_CATEGORY_LEVEL_1);
        model.addAttribute("categoryOneLevelList",categoryOneLevelList);
        Blog blog = blogService.selectByPrimaryKey(id);
        model.addAttribute("blog",blog);
        // 查询二级分类
        Map paramMap = new HashMap();
        paramMap.put("level",Constant.BLOG_CATEGORY_LEVEL_2);
        paramMap.put("parentId",blog.getCategoryIdOne());
        List<BlogCategory> bolgCategoryTwoLevelList = blogCategoryService.findCategoryListByMap(paramMap);

        model.addAttribute("bolgCategoryTwoLevelList",bolgCategoryTwoLevelList);
        return "admin/blog/blog-edit";
    }

    /**
     * 博客删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Map delBanner(@PathVariable String id){
        Map result  = new HashMap();
        Integer count = blogService.deleteByPrimaryKey(id);
        result.put("count",count);
        return result;
    }

    /*@RequestMapping(value = "/delBatch",method = RequestMethod.POST)
    @ResponseBody
    public Map delBatchBanner(String bannerIds){
        Map result  = new HashMap();
        List<String> idList = Arrays.asList(bannerIds.split(","));
        Integer count = blogService.deleteByBannerIds(idList);
        result.put("count",count);
        return result;
    }*/

    @RequestMapping(value = "/recommend/{id}",method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage recommendBlog(@PathVariable String id){
        BaseMessage baseMessage = new BaseMessage();

        Blog blog = blogService.selectByPrimaryKey(id);
        if(blog == null || Constant.DELETE_FLAG_1.equals(blog.getDeleted())){
            baseMessage.setReturnCode(201);
            baseMessage.setReturnMsg("博客不存在，请检查！");
            return  baseMessage;
        }
        if(Constant.BLOG_IS_RECOMMEND_1.equals(blog.getIsRecommend())){
            baseMessage.setReturnCode(201);
            baseMessage.setReturnMsg("该博客已是推荐状态，不能重复推荐！");
            return  baseMessage;
        }
        Blog newBlog = new Blog();
        newBlog.setId(id);
        newBlog.setIsRecommend(Constant.BLOG_IS_RECOMMEND_1);
        Date now = new Date();
        newBlog.setUpdateTime(now);
        int count = blogService.updateByPrimaryKey(newBlog);
        if(count <=0){
            baseMessage.setReturnCode(201);
            baseMessage.setReturnMsg("保存失败");
        }
        return baseMessage;
    }

    @RequestMapping(value = "/audit/{id}",method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage auditBlog(@PathVariable String id){
        BaseMessage baseMessage = new BaseMessage();

        Blog blog = blogService.selectByPrimaryKey(id);
        if(blog == null || Constant.DELETE_FLAG_1.equals(blog.getDeleted())){
            baseMessage.setReturnCode(201);
            baseMessage.setReturnMsg("博客不存在，请检查！");
            return  baseMessage;
        }
        if(Constant.BLOG_IS_AUDIT_0.equals(blog.getIsAudit())){
            baseMessage.setReturnCode(201);
            baseMessage.setReturnMsg("该博客已是审核通过状态，不需要审核！");
            return  baseMessage;
        }
        Blog newBlog = new Blog();
        newBlog.setId(id);
        newBlog.setIsAudit(Constant.BLOG_IS_AUDIT_0);
        Date now = new Date();
        newBlog.setUpdateTime(now);
        int count = blogService.updateByPrimaryKey(newBlog);
        if(count <=0){
            baseMessage.setReturnCode(201);
            baseMessage.setReturnMsg("保存失败");
        }
        return baseMessage;
    }
}
