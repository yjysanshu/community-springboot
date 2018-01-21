package com.lxl.admin.config;

import com.lxl.admin.filters.SessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        SessionFilter sessionFilter = new SessionFilter();
        System.out.println("执行到了过滤器");
        registration.setFilter(sessionFilter);
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("sessionFilter");
        return registration;
    }
}
