package com.dymao.listener;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author Mervin
 * @Description:
 * @date 2018-04-22 20:05
 */
@WebListener
public class OnlineCountListener implements HttpSessionListener {

    private static final Logger logger = LoggerFactory.getLogger(OnlineCountListener.class);

    @Override
    public synchronized  void sessionCreated(HttpSessionEvent event) {
       // logger.debug("【HttpSessionListener监听器】count++ 增加");
        String onLineCount = (String)event.getSession().getServletContext().getAttribute("onLineCount");
        if(StringUtils.isBlank(onLineCount)){
            onLineCount = "0";
        }
        onLineCount = (Integer.valueOf(onLineCount) + 1) + "";
        event.getSession().getServletContext().setAttribute("onLineCount", onLineCount);
    }

    @Override
    public synchronized  void sessionDestroyed(HttpSessionEvent event) {
        String onLineCount = (String)event.getSession().getServletContext().getAttribute("onLineCount");
        if(StringUtils.isBlank(onLineCount)){
            onLineCount = "1";
        }
        onLineCount = (Integer.valueOf(onLineCount) - 1) + "";
        event.getSession().getServletContext().setAttribute("onLineCount", onLineCount);
    }
}
