package com.moshimoshi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moshimoshi.auth.filter.*;
import com.moshimoshi.auth.resolver.UserArgumentResolver;
import com.moshimoshi.auth.utils.JwtProvider;
import com.moshimoshi.user.service.UserService;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements WebMvcConfigurer {
    private final ObjectMapper objectMapper;
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserArgumentResolver(jwtProvider, objectMapper, userService));
    }

    @Bean
    public FilterRegistrationBean<Filter> exceptionHandlingFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new ExceptionHandlingFilter(objectMapper));
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> verifyUserFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new VerifyUserFilter(objectMapper, userService));
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/api/auth/login");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> issueTokenFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new IssueTokenFilter(jwtProvider, objectMapper, userService));
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/api/auth/login");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> refreshTokenFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new RefreshTokenFilter(jwtProvider, objectMapper, userService));
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/api/auth/refresh");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> authorizationFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AuthorizationFilter(jwtProvider, objectMapper));
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }
}
