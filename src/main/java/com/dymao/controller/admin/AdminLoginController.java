package com.dymao.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dymao.common.Utils.ImageTokenUtil;
import com.dymao.common.Utils.RSAUtils;
import com.dymao.common.constants.Constant;
import com.dymao.model.AdminUser;
import com.dymao.model.User;
import com.dymao.service.UserManagerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.PrintWriter;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminLoginController {

    private static final Logger logger = LoggerFactory.getLogger(AdminLoginController.class);

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private UserManagerService userManagerService;
	
	@RequestMapping(value="/toLoginPage")
	public String toLoginPage() {
		return "admin/login";
	}

	@RequestMapping(value="/getLoginPublicKey")
	@ResponseBody
	public Map getLoginPublicKey(HttpServletRequest request, HttpServletResponse response){

		RSAUtils rsa = new RSAUtils();
		//生成公钥和密钥
		Map<String,Object> keyMap = rsa.createKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyMap.get("publicKey");
		RSAPrivateKey privateKey = (RSAPrivateKey) keyMap.get("privateKey");
		//js通过模和公钥指数获取公钥对字符串进行加密，注意必须转为16进制
		//模
		String Modulus = publicKey.getModulus().toString(16);
		//公钥指数
		String Exponent = publicKey.getPublicExponent().toString(16);
		//私钥指数
		String private_exponent = privateKey.getPrivateExponent().toString();
		HttpSession session = request.getSession();
		//java中的模和私钥指数不需要转16进制，但是js中的需要转换为16进制
		session.setAttribute("Modulus", publicKey.getModulus().toString());
		session.setAttribute("private_exponent", private_exponent);
		//String strSet = Modulus+";"+Exponent;
		Map resultMap = new HashMap();
		resultMap.put("Modulus",Modulus);
		resultMap.put("Exponent",Exponent);
		return resultMap;
	}

	
    @PostMapping(value="/login")
	public String login(HttpServletRequest request, RedirectAttributes redirectAttributes, String checkCode, AdminUser adminUser) {
		String resultPage = "redirect:/admin/toLoginPage";
		if(StringUtils.isEmpty(checkCode)){
            redirectAttributes.addFlashAttribute("errorStatus", Constant.LOGIN_VRRFY_STATUS_3);
			return resultPage;
		}

		int checkResult = ImageTokenUtil.vrifyImageToken(checkCode,request);
		if(checkResult == 1 || checkResult == 2){ // 失效或错误
            redirectAttributes.addFlashAttribute("errorStatus",checkResult+"");
			return resultPage;
		}
		try{
			String authenticateResult = userManagerService.authenticate(adminUser,request);
			if(!StringUtils.equals(authenticateResult,Constant.LOGIN_VRRFY_STATUS_0)){
                redirectAttributes.addFlashAttribute("errorStatus",authenticateResult);
				return resultPage;
			}
			userManagerService.initUser(adminUser,request);

		}catch (Exception e){
            logger.error("登录验证出错：",e);
            redirectAttributes.addFlashAttribute("errorStatus",Constant.LOGIN_VRRFY_STATUS_8);
            return resultPage;
		}
		return "redirect:/admin";
	}
    
    @RequestMapping(value="/loginOut")
    public String loginOut(HttpServletRequest request) {
    	request.getSession().invalidate();
    	return "redirect:/admin/toLoginPage";
    }


}
