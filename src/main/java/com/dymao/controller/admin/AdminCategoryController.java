package com.dymao.controller.admin;

import com.dymao.common.constants.Constant;
import com.dymao.model.BlogCategory;
import com.dymao.service.BlogCategoryService;
import com.dymao.service.IdCreateService;
import com.dymao.vo.BaseMessage;
import com.dymao.vo.CategoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/6 21:48
 */
@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private BlogCategoryService blogCategoryService;

    @Autowired
    private IdCreateService idCreateService;

    @RequestMapping(value = "/toCategoryListPage",method = RequestMethod.GET)
    public String toCategoryListPage(Model model){
        List<BlogCategory> bolgCategoryOneLevelList = blogCategoryService.findCategoryList(Constant.BLOG_CATEGORY_LEVEL_1);

        List<CategoryInfo> bolgCategoryTwoLevelList = blogCategoryService.queryCategoryInfoList(Constant.BLOG_CATEGORY_LEVEL_2);
        model.addAttribute("categoryOneLevelList",bolgCategoryOneLevelList);
        model.addAttribute("bolgCategoryTwoLevelList",bolgCategoryTwoLevelList);
        model.addAttribute("categoryNum",blogCategoryService.queryCategoryInfoListCount(Constant.BLOG_CATEGORY_LEVEL_2));
        return "admin/category/category";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map addCategory(Model model,BlogCategory blogCategory){
        Map result = new HashMap();
        blogCategory.setId(idCreateService.getCategoryId());
        blogCategory.setLevel(Constant.BLOG_CATEGORY_LEVEL_2);
        int count = blogCategoryService.insert(blogCategory);
        result.put("count",count);
        result.put("blogCategory",blogCategory);
        return result;
    }

    @RequestMapping(value = "/del/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Map delBanner(@PathVariable String id){
        Map result  = new HashMap();
        Integer count = blogCategoryService.deleteByPrimaryKey(id);
        result.put("count",count);
        return result;
    }

    @RequestMapping(value = "/delBatch",method = RequestMethod.POST)
    @ResponseBody
    public Map delBatchCategory(String categoryIds){
        Map result  = new HashMap();
        List<String> categoryIdsIdList = Arrays.asList(categoryIds.split(","));
        Integer count = blogCategoryService.deleteByCategoryIds(categoryIdsIdList);
        result.put("count",count);
        return result;
    }

    @RequestMapping(value = "/toCategoryEditPage/{categoryId}",method = RequestMethod.GET)
    public String toCategoryEditPage(Model model, @PathVariable("categoryId") String id){
        List<BlogCategory> bolgCategoryOneLevelList = blogCategoryService.findCategoryList(Constant.BLOG_CATEGORY_LEVEL_1);
        BlogCategory blogCategory = blogCategoryService.selectByPrimaryKey(id);
        model.addAttribute("categoryOneLevelList",bolgCategoryOneLevelList);
        model.addAttribute("categoryInfo",blogCategory);
        return "admin/category/category-edit";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Map updateCategory(Model model,BlogCategory blogCategory){
        Map result = new HashMap();
        int count = blogCategoryService.updateByPrimaryKey(blogCategory);
        result.put("count",count);
        return result;
    }

    @RequestMapping(value = "/getBolgCategoryTwoLevelList",method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage getBolgCategoryTwoLevelList(Model model,String parentId){
        BaseMessage baseMessage = new BaseMessage();
        Map paramMap = new HashMap();
        paramMap.put("level",Constant.BLOG_CATEGORY_LEVEL_2);
        paramMap.put("parentId",parentId);
        List<BlogCategory> bolgCategoryTwoLevelList = blogCategoryService.findCategoryListByMap(paramMap);
        baseMessage.setReturnCode(200);
        baseMessage.setData(bolgCategoryTwoLevelList);
        baseMessage.setReturnMsg("加载成功");
        return baseMessage;
    }
}
