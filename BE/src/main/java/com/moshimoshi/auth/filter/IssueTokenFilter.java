package com.moshimoshi.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moshimoshi.auth.domain.AuthenticatedUser;
import com.moshimoshi.auth.dto.TokenResponse;
import com.moshimoshi.auth.utils.JwtProvider;
import com.moshimoshi.common.Define;
import com.moshimoshi.user.service.UserService;
import jakarta.servlet.*;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class IssueTokenFilter implements Filter {
    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) request.getAttribute(Define.AUTHENTICATED);
        Map<String, Object> claims = new HashMap<>();
        String authenticatedUserJson = objectMapper.writeValueAsString(authenticatedUser);
        claims.put(Define.AUTHENTICATED, authenticatedUserJson);
        TokenResponse tokenResponse = jwtProvider.createJwt(claims);
        userService.updateRefreshToken(authenticatedUser.getLoginId(), tokenResponse.getRefreshToken());
        String jwtJson = objectMapper.writeValueAsString(tokenResponse);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jwtJson);
    }
}
