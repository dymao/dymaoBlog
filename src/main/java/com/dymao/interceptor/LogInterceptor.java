package com.dymao.interceptor;


import com.dymao.common.Utils.AccessLoggerUtil;
import com.dymao.common.constants.Dict;
import com.dymao.dao.mapper.AccessLogMapper;
import com.dymao.model.AccessLog;
import com.dymao.model.AdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mervin
 * @Description:
 * @date 2018-04-10 21:40
 */
public class LogInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //把整个log中的参数，交给logUtil来获取，并返回log对象
        AccessLog log = null;
        try {
            log = AccessLoggerUtil.getLog(request);
        }catch (Exception e){
            logger.error("logger",e.getMessage());
        }
        request.setAttribute(AccessLoggerUtil.LOG_OPERATE,log);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception exception) throws Exception {
        //返回视图时，插入操作日志
        AccessLogMapper logMapper = getMapper(AccessLogMapper.class,httpServletRequest);
        AccessLog log = (AccessLog) httpServletRequest.getAttribute(AccessLoggerUtil.LOG_OPERATE);
        if(log == null){
            logger.warn("日志信息为空",log);
        }else{
            if(exception != null){
                String returnMsg = exception.getMessage();
                log.setReturnMsg(returnMsg);
            }
            logMapper.insert(log);
        }
    }
    private <T> T getMapper(Class<T> clazz,HttpServletRequest request)
    {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }
}
