package com.springboothc.demo.config;

import com.springboothc.demo.interceoter.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: springboothighercourse
 * @description: 拦截器配置
 * @author: zhijie
 * @create: 2019-04-10 14:11
 **/
//这是一个配置类
@Configuration
public class Inteceptor implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/api/v1/*/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
