package com.dymao.controller;

import com.dymao.common.Utils.ImageTokenUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Mervin
 * @Description:
 * @date 2017-12-19 22:44
 */
@Controller
@RequestMapping("/imageToken")
public class ImageTokenController {

    private final Logger logger = LoggerFactory.getLogger(ImageTokenController.class);
    @Autowired
    DefaultKaptcha captchaProducer;

    @RequestMapping(value = "/getImageToken")
    public void getKaptchaImage(HttpServletRequest request,HttpServletResponse response) {
        try {
            ImageTokenUtil.createImageToken(request,response,captchaProducer);
        }catch (Exception e){
            logger.error("创建验证码出错，错误 信息："+e);
        }

    }

}
