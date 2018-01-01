package com.dymao.interceptor;


import com.dymao.common.constants.Dict;
import com.dymao.model.AdminUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mervin
 * @Description:
 * @date 2018-01-01 21:40
 */
public class AdminLoginControllInterceptor implements HandlerInterceptor {

    private List<String> excludePath = new ArrayList<String>();

    public AdminLoginControllInterceptor(){
        super();
        excludePath.add("/admin/toLoginPage");
        excludePath.add("/admin/getLoginPublicKey");
        excludePath.add("/admin/login");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestURI = request.getRequestURI();
        if(excludePath.contains(requestURI)){
            return true;
        }
        AdminUser adminUser = (AdminUser)request.getSession().getAttribute(Dict.ADMIN_USER);
        if (adminUser == null){
            response.sendRedirect("/admin/toLoginPage");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception exception) throws Exception {

    }
}
