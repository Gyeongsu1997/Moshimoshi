package com.moshimoshi.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moshimoshi.user.dto.LoginRequest;
import com.moshimoshi.user.dto.LoginResponse;
import com.moshimoshi.user.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class VerifyUserFilter implements Filter {
    public static final String AUTHENTICATED = "authenticated";
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (!"POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
        }
        LoginRequest loginRequest = objectMapper.readValue(httpServletRequest.getReader(), LoginRequest.class);
        LoginResponse loginResponse = userService.login(loginRequest);
        request.setAttribute(AUTHENTICATED, AuthenticatedUser.from(loginResponse));
        chain.doFilter(request, response);
    }
}
