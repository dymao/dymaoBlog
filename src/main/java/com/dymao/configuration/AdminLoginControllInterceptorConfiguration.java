package com.dymao.configuration;

import com.dymao.interceptor.AdminLoginControllInterceptor;
import com.dymao.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Mervin
 * @Description:
 * @date 2018-01-01 21:55
 */
@Configuration
public class AdminLoginControllInterceptorConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminLoginControllInterceptor()).addPathPatterns("/admin/**");
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
