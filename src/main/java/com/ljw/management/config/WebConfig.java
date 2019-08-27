package com.ljw.management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebConfig
 * @Description:
 * @Author: 郎建伟
 * @Date: Created in 2019/8/25 12:47
 * @Version: 1.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/logOut")
                .excludePathPatterns("/admin/loginValid")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/fonts.nucleo/**")
                .excludePathPatterns("/vendor/**")
                .excludePathPatterns("/scss/**");
    }
}
