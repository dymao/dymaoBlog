package com.dymao.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminLoginController {
	
	
	@RequestMapping(value="/toLoginPage")
	public String toLoginPage() {
		return "admin/login";
	}
	
    @RequestMapping(value="/login")
	public String login(HttpServletRequest request) {
		request.getSession().setAttribute("userId", "mmmmm");
		if(request.getSession().getAttribute("userId") == null) {
			return "redirect:/admin/toLoginPage";
		}
		return "redirect:/admin";
	}
    
    @RequestMapping(value="/loginOut")
    public String loginOut(HttpServletRequest request) {
    	request.getSession().invalidate();
    	return "redirect:/admin/toLoginPage";
    }
}
