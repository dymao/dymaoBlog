package com.dymao.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author Mervin
 * @Description:
 * @date 2017/11/12 22:32
 */
@WebFilter(filterName = "druidStatFilter",urlPatterns = "/",
        initParams = {
            @WebInitParam(name = "exclusions",value ="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
        })
public class DruidStatFilter extends WebStatFilter {
}
