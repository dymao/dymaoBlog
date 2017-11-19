package com.dymao.controller;

import com.dymao.dao.mapper.ProductDao;
import com.dymao.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mervin
 * @Description:
 * @date 2017/11/12 22:18
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ProductDao productDao;

    @RequestMapping("query")
    public String  getProductList(Model model){
        List<Product>  list = productDao.findByName("");

        model.addAttribute("products", list);
        return "index";
    }
}
