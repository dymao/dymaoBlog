package com.dymao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Mervin
 * @Description:
 * @date 2017/11/20 22:00
 */
@Controller
@RequestMapping("/video")
public class VideoController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String videoIndex(){

        return "/video/video";
    }
}
