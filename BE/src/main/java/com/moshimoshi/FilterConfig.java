package com.moshimoshi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moshimoshi.auth.utils.JwtProvider;
import com.moshimoshi.filter.AuthorizationFilter;
import com.moshimoshi.filter.ExceptionHandlingFilter;
import com.moshimoshi.filter.JwtFilter;
import com.moshimoshi.filter.VerifyUserFilter;
import com.moshimoshi.user.service.UserService;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<Filter> exceptionHandlingFilter(ObjectMapper objectMapper) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new ExceptionHandlingFilter(objectMapper));
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> verifyUserFilter(ObjectMapper objectMapper, UserService userService) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new VerifyUserFilter(objectMapper, userService));
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/api/login");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> jwtFilter(JwtProvider jwtProvider, ObjectMapper objectMapper, UserService userService) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtFilter(jwtProvider, objectMapper, userService));
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/api/login");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> authorizationFilter(JwtProvider jwtProvider, ObjectMapper objectMapper) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AuthorizationFilter(jwtProvider, objectMapper));
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
