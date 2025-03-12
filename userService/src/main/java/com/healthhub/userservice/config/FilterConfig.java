package com.healthhub.userservice.config;



import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.healthhub.userservice.filter.JwtFilter;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class FilterConfig {

    @Autowired879io
    private JwtFilter jwtFilter;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterMethod() {
        FilterRegistrationBean<JwtFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(jwtFilter); // Use the injected JwtFilter bean
        filter.addUrlPatterns("/api/ai/*");
        return filter;
    }
}
