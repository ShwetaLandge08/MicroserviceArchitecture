package com.practice.StudentService.config;

import com.practice.StudentService.filter.JWTFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public FilterRegistrationBean<JWTFilter> jwtFilterBean() {
        FilterRegistrationBean<JWTFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new JWTFilter());
        //filterFilterRegistrationBean.addUrlPatterns("/api/user/*");
        filterFilterRegistrationBean.addUrlPatterns("/api/student/user/update");
        filterFilterRegistrationBean.addUrlPatterns("/api/student/user/delete");
        filterFilterRegistrationBean.addUrlPatterns("/api/student/student/*");
        return filterFilterRegistrationBean;
    }
}
