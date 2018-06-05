package com.refactoring.rxo.springmvc.log;

import com.refactoring.rxo.springmvc.log.interceptor.LogHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author fei.yao
 * @create 2018-01-03 14:38
 **/
@Configuration
public class LoggerConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogHandlerInterceptor()).addPathPatterns("/**");
    }
}
