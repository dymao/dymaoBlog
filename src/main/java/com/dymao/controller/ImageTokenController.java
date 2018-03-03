package com.dymao.controller;

import com.dymao.common.Utils.ImageTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mervin
 * @Description:
 * @date 2017-12-19 22:44
 */
@Controller
@RequestMapping("/imageToken")
public class ImageTokenController {

    private final Logger logger = LoggerFactory.getLogger(ImageTokenController.class);


    @RequestMapping(value = "/getImageToken")
    public void getKaptchaImage(HttpServletRequest request,HttpServletResponse response) {
        try {
            ImageTokenUtil.createImageToken(request,response);
        }catch (Exception e){
            logger.error("创建验证码出错，错误 信息："+e);
        }

    }

}
