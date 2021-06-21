package com.bo.sims.configuration;


import com.bo.sims.interceptor.loginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 1、编写一个拦截器实现HandlerInterceptor接口
 * 2、拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors）
 * 3、指定拦截规则【如果是拦截所有，静态资源也会被拦截】
 *
 * @author Bo
 * @create 2021-06-18 13:22
 */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new loginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin", "/admin/login", "/css/**", "/images/**", "/js/**", "/lib/**");
    }
}

