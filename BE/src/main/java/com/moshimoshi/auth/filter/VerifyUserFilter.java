package com.moshimoshi.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moshimoshi.auth.domain.AuthenticatedUser;
import com.moshimoshi.common.Define;
import com.moshimoshi.common.exception.BusinessException;
import com.moshimoshi.common.exception.ErrorCode;
import com.moshimoshi.auth.dto.LogInRequest;
import com.moshimoshi.auth.dto.LogInResponse;
import com.moshimoshi.user.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class VerifyUserFilter implements Filter {
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (!"POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
            throw new BusinessException(ErrorCode.METHOD_NOT_ALLOWED);
        }
        LogInRequest logInRequest = objectMapper.readValue(httpServletRequest.getReader(), LogInRequest.class);
        LogInResponse logInResponse = userService.login(logInRequest);
        request.setAttribute(Define.AUTHENTICATED, AuthenticatedUser.from(logInResponse));
        chain.doFilter(request, response);
    }
}
