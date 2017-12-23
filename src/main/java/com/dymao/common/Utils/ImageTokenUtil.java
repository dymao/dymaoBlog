package com.dymao.common.Utils;

import com.dymao.common.constants.Constant;
import com.dymao.common.constants.Dict;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @author Mervin
 * @Description:
 * @date 2017-12-19 23:42
 */
public class ImageTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageTokenUtil.class);

    // 验证码失效时间：2两分钟
    public static final Long VRIFY_IMAGE_CODE_TIME_OUT = 2*60*1000L;

    /**
     * 创建图片验证码
     * @param request
     * @param response
     * @param captchaProducer
     * @throws IOException
     */
    public static void createImageToken(HttpServletRequest request, HttpServletResponse response,
                                     DefaultKaptcha captchaProducer) throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        //生产验证码字符串并保存到session中
        String createText = captchaProducer.createText();
        logger.debug("生成的图形验证码是："+createText);
        request.getSession().setAttribute(Dict.IMAGE_VRIFY_CODE, createText);
        request.getSession().setAttribute(Dict.IMAGE_VRIFY_CODE_TIME,new Date().getTime());
        //使用生成的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
        BufferedImage challenge = captchaProducer.createImage(createText);
        ImageIO.write(challenge, "jpg", jpegOutputStream);
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 验证图片验证码
     * @param checkCode
     * @param request
     * @return 0：通过  1：验证码已失效  2：验证码错误
     */
    public static int vrifyImageToken(String checkCode,HttpServletRequest request){
        HttpSession session = request.getSession();
        Object VrifyCodeObj = session.getAttribute(Dict.IMAGE_VRIFY_CODE) ; //验证码对象
        if(VrifyCodeObj == null){
           return Constant.IMAGE_VRIFY_CODE_STATUS_1;
        }
        String imageVrifyCode = VrifyCodeObj.toString();
        logger.debug("生成的图形验证码是："+imageVrifyCode+",上送的验证码是："+checkCode);
        Date now = new Date();
        Long codeTime = Long.valueOf(session.getAttribute(Dict.IMAGE_VRIFY_CODE_TIME)+"");
        if(StringUtils.isEmpty(checkCode) || imageVrifyCode == null || !(checkCode.equalsIgnoreCase(imageVrifyCode))) {
            return Constant.IMAGE_VRIFY_CODE_STATUS_2;
        } else if ((now.getTime()-codeTime) > VRIFY_IMAGE_CODE_TIME_OUT) {
            return Constant.IMAGE_VRIFY_CODE_STATUS_1;
        }else {
            session.removeAttribute("simpleCaptcha");
            return Constant.IMAGE_VRIFY_CODE_STATUS_0;
        }
    }
}
