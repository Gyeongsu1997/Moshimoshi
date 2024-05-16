package com.moshimoshi.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moshimoshi.auth.domain.AuthenticatedUser;
import com.moshimoshi.auth.dto.TokenRefreshRequest;
import com.moshimoshi.auth.dto.TokenResponse;
import com.moshimoshi.auth.utils.JwtProvider;
import com.moshimoshi.common.Define;
import com.moshimoshi.common.exception.CommonException;
import com.moshimoshi.common.exception.ErrorCode;
import com.moshimoshi.user.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.HashMap;

@RequiredArgsConstructor
public class RefreshTokenFilter implements Filter {
    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (!"POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
            throw new CommonException(ErrorCode.METHOD_NOT_ALLOWED);
        }
        TokenRefreshRequest tokenRefreshRequest = objectMapper.readValue(httpServletRequest.getReader(), TokenRefreshRequest.class);
        String refreshToken = tokenRefreshRequest.getRefreshToken();
        jwtProvider.getClaims(refreshToken); // 유효한 토큰임을 검증
        AuthenticatedUser authenticatedUser = userService.findByRefreshToken(refreshToken);
        HashMap<String, Object> claims = new HashMap<>();
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
