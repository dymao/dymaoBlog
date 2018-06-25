package com.dymao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mervin
 * @Description:
 * @date 2018-01-10 00:36
 */
@Controller
@RequestMapping("/tools")
public class ToolsController {


    @RequestMapping("/onlinetools")
    public String toToolsIndexPage(){
        return "tools/online-tools";
    }
}
