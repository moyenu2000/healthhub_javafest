// package com.healthhub.ai_service.config;


// import com.healthhub.ai_service.filter.JwtFilter;

// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class FilterConfig {

//     @Bean
//     public FilterRegistrationBean<JwtFilter> jwtFilterMethod() {

//         FilterRegistrationBean<JwtFilter> filter= new FilterRegistrationBean<JwtFilter>();
//         filter.setFilter(new JwtFilter());

// //        provide endpoints which needs to be restricted.
// //        All Endpoints would be restricted if unspecified
//         filter.addUrlPatterns("/api/ai/*");
//         return filter;
//     }
// }

package com.healthhub.ai_service.config;

import com.healthhub.ai_service.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class FilterConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterMethod() {
        FilterRegistrationBean<JwtFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(jwtFilter); // Use the injected JwtFilter bean
        filter.addUrlPatterns("/api/ai/*");
        return filter;
    }
}
