package com.dhrs.date.discuss.config;

import com.dhrs.date.discuss.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname UserWebConfig
 * @Description TODO
 * @Date 2020/7/26 14:54
 * @Author 冷心影翼
 */
@Configuration
public class UserWebConfig implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor)
//                .addPathPatterns("/**").excludePathPatterns("/user/member/guest/**");
    }
}
