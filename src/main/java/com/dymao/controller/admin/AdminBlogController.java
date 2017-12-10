package com.dymao.controller.admin;

import com.dymao.common.constants.Config;
import com.dymao.model.Blog;
import com.dymao.service.BlogService;
import com.dymao.vo.BlogVo;
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

    @RequestMapping(value = "/toBlogListPage", method = RequestMethod.GET)
    public String toBlogListPage(Model model){
        List<BlogVo> blogVoList = blogService.selectBlogList(new HashMap());
        model.addAttribute("blogVoList",blogVoList);
        return "/admin/blog/blogList";
    }

    /**
     * 跳转到博客新增页
     * @return
     */
    @RequestMapping(value = "/toAddPage",method = RequestMethod.GET)
    public String toAdminBlogAdd() {
        return "admin/blog/blog-add";
    }


    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    @ResponseBody
    public Map uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        Map result  = new HashMap();
        String fileName = "";
       /* String contentType = file.getContentType();
        String originalFilename = file.getOriginalFilename();
        String imgSuffix = originalFilename.substring(originalFilename.indexOf("."));
        String fileName = StringUtil.getCharAndNumr(20)+imgSuffix;
        String filePath = request.getSession().getServletContext().getRealPath(Config.BANNER_IMAGE_PAHT);
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {

        }*/
        result.put("url",Config.BANNER_IMAGE_PAHT+fileName);
        return result;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Blog addBanner(Model model, Blog blog){

        return blog;
    }

    /**
     * 跳转到博客修改页
     * @return
     */
    @RequestMapping(value = "/toEditPage/{id}",method = RequestMethod.GET)
    public String toAdminBannerEdit(@PathVariable String id,Model model) {
        Blog blog = blogService.selectByPrimaryKey(id);
        model.addAttribute("blog",blog);
        return "admin/blog/blog-edit";
    }

    /**
     * 博客修改
     * @param model
     * @param blog
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Blog updateBanner(Model model, Blog blog){
        blog.setCreateTime(new Date());
        blogService.updateByPrimaryKey(blog);
        return blog;
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
}
