package com.dymao.controller.admin;

import com.dymao.common.constants.Config;
import com.dymao.common.constants.Constant;
import com.dymao.common.constants.Dict;
import com.dymao.model.AdminUser;
import com.dymao.model.Blog;
import com.dymao.model.BlogCategory;
import com.dymao.model.Label;
import com.dymao.service.BlogCategoryService;
import com.dymao.service.BlogService;
import com.dymao.service.IdCreateService;
import com.dymao.service.LabelService;
import com.dymao.vo.BaseMessage;
import com.dymao.vo.BlogVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String toBlogListPage(String pageNum, String pageSize,Model model){
        if(StringUtils.isEmpty(pageNum) || !StringUtils.isNumeric(pageNum)){
            pageNum = "1";
        }
        if(StringUtils.isEmpty(pageSize) || !StringUtils.isNumeric(pageSize)){
            pageSize = "10";
        }
        PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        Map paramMap = new HashMap();
        paramMap.put("deleted",Constant.DELETE_FLAG_0);
        List<BlogVo> blogVoList = blogService.selectBlogList(paramMap);
        PageInfo<BlogVo> pageInfo = new PageInfo<BlogVo>(blogVoList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("totalNum",pageInfo.getTotal());
       // model.addAttribute("blogVoList",blogVoList);
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
    @Transactional
    public BaseMessage addBlog(Model model, Blog blog,String[] labels,HttpServletRequest request){
        BaseMessage baseMessage = new BaseMessage();
        int count = 0;
        Date now = new Date();
        blog.setUpdateTime(now);
        blog.setIsAudit(Constant.BLOG_IS_AUDIT_1); // 无论是新增还是修改，状态都应该是待审核


        if(StringUtils.isEmpty(blog.getId())){// 新增
            blog.setId(idCreateService.getBlogId());
            blog.setDeleted(Constant.DELETE_FLAG_0);
            blog.setIsPublic(Constant.BLOG_IS_PUBLIC_0);

            HttpSession session = request.getSession();
            AdminUser adminUser = (AdminUser) session.getAttribute(Dict.ADMIN_USER);
            if(adminUser != null){
                blog.setUserId(adminUser.getId());
            }
            blog.setViewNum(0);
            blog.setLikeNum(0);
            blog.setTreadNum(0);
            blog.setIsRecommend(Constant.BLOG_IS_RECOMMEND_0);

            blog.setCreateTime(now);
            count = blogService.insert(blog);
        }else{   // 修改
            count = blogService.updateByPrimaryKey(blog);
            blogService.delBlogLabels(blog.getId());// 删除之前所有标签
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("blogId", blog.getId());
        if(labels != null && labels.length > 0){
            for (String label: labels) {
                map.put("labelId", label);
                    blogService.saveBlogLabels(map);
            }
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
        BlogVo blog = blogService.selectByPrimaryKey(id);
        model.addAttribute("blog",blog);
        // 查询二级分类
        Map paramMap = new HashMap();
        paramMap.put("level",Constant.BLOG_CATEGORY_LEVEL_2);
        paramMap.put("parentId",blog.getCategoryIdOne());
        List<BlogCategory> bolgCategoryTwoLevelList = blogCategoryService.findCategoryListByMap(paramMap);

        model.addAttribute("bolgCategoryTwoLevelList",bolgCategoryTwoLevelList);

        // 查询所有标签
        List<Label> labelList = labelService.findAllLabel(new HashMap());
        model.addAttribute("labelList",labelList);

        return "admin/blog/blog-edit";
    }

    /**
     * 博客删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id}",method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Map delBanner(@PathVariable String id){
        Map result  = new HashMap();
        Integer count = blogService.deleteByPrimaryKey(id);
        blogService.delBlogLabels(id);// 删除之前所有标签
        result.put("count",count);
        return result;
    }

    @RequestMapping(value = "/recommend/{id}",method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage recommendBlog(@PathVariable String id){
        BaseMessage baseMessage = new BaseMessage();

        BlogVo blog = blogService.selectByPrimaryKey(id);
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

        BlogVo blog = blogService.selectByPrimaryKey(id);
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
