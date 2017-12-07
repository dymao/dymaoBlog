package com.dymao.controller.admin;

import com.dymao.common.constants.Constant;
import com.dymao.model.BlogCategory;
import com.dymao.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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

    @RequestMapping(value = "/toCategoryListPage",method = RequestMethod.GET)
    public String toCategoryListPage(Model model){
        List<BlogCategory> bolgCategoryOneLevelList = blogCategoryService.findCategoryList(Constant.BLOG_CATEGORY_LEVEL_1);
        model.addAttribute("categoryOneLevelList",bolgCategoryOneLevelList);
        return "admin/category/category";
    }
}
