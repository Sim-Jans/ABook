package com.account.ABook.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerConfig())
                .addPathPatterns("/**")
                .excludePathPatterns("/kk")
                .excludePathPatterns("/login"); //로그인 쪽은 예외처리를 한다.
    }
}
