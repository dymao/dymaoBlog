package com.dymao.controller.admin;

import javax.servlet.http.HttpServletRequest;

import com.dymao.common.Utils.ImageTokenUtil;
import com.dymao.common.constants.Constant;
import com.dymao.model.AdminUser;
import com.dymao.model.User;
import com.dymao.service.UserManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminLoginController {

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private UserManagerService userManagerService;
	
	@RequestMapping(value="/toLoginPage")
	public String toLoginPage() {
		return "admin/login";
	}
	
    @RequestMapping(value="/login")
	public String login(HttpServletRequest request, Model model,String checkCode,String username,String password) {
		String resultPage = "redirect:/admin/toLoginPage";
		if(StringUtils.isEmpty(checkCode)){
			model.addAttribute("errorStatus", Constant.LOGIN_VRRFY_STATUS_3);
			return resultPage;
		}
		int checkResult = ImageTokenUtil.vrifyImageToken(checkCode,request);
		if(checkResult == 1 || checkResult == 2){ // 失效或错误
			model.addAttribute("errorStatus",checkResult+"");
			return resultPage;
		}
		User adminUser = new AdminUser();
		try{
			String authenticateResult = userManagerService.authenticate(adminUser);
			if(!StringUtils.equals(authenticateResult,Constant.LOGIN_VRRFY_STATUS_0)){
				model.addAttribute("errorStatus",authenticateResult);
				return resultPage;
			}
			userManagerService.initUser(adminUser,request);

		}catch (Exception e){

		}
		return "redirect:/admin";
	}
    
    @RequestMapping(value="/loginOut")
    public String loginOut(HttpServletRequest request) {
    	request.getSession().invalidate();
    	return "redirect:/admin/toLoginPage";
    }


}
