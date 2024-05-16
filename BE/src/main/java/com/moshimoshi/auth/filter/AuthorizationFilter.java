package com.moshimoshi.auth.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moshimoshi.auth.domain.AuthenticatedUser;
import com.moshimoshi.auth.utils.JwtProvider;
import com.moshimoshi.common.Define;
import com.moshimoshi.common.exception.CommonException;
import com.moshimoshi.common.exception.ErrorCode;
import com.moshimoshi.user.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class AuthorizationFilter implements Filter {
    private final String[] allowedURIs = new String[] {"/api/users/signup", "/api/auth/login", "/api/auth/refresh", "/api/threads*"};
    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (isAllowedUris(httpServletRequest.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }
        if (!isTokenExist(httpServletRequest)) {
            throw new CommonException(ErrorCode.ACCESS_TOKEN_NOT_EXIST);
        }
        try {
            String token = getToken(httpServletRequest);
            AuthenticatedUser authenticatedUser = getAuthenticatedUser(token);
            authorize(httpServletRequest.getRequestURI(), authenticatedUser);
            log.info("[{}] logged in", authenticatedUser.getLoginId());
            chain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            throw new CommonException(ErrorCode.ACCESS_TOKEN_EXPIRED);
        }
    }

    private boolean isAllowedUris(String requestURI) {
        return PatternMatchUtils.simpleMatch(allowedURIs, requestURI);
    }

    private boolean isTokenExist(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        return authorization != null && authorization.startsWith("Bearer ");
    }

    private String getToken(HttpServletRequest httpServletRequest){
        String authorization = httpServletRequest.getHeader("Authorization");
        return authorization.substring(7);
    }

    private AuthenticatedUser getAuthenticatedUser(String token) throws JsonProcessingException {
        Claims claims = jwtProvider.getClaims(token);
        String authenticatedUserJson = claims.get(Define.AUTHENTICATED, String.class);
        return objectMapper.readValue(authenticatedUserJson, AuthenticatedUser.class);
    }

    private void authorize(String requestURI, AuthenticatedUser authenticatedUser) {
        if (PatternMatchUtils.simpleMatch("*/admin*", requestURI) && !Role.ADMIN.equals(authenticatedUser.getRole())){
            throw new CommonException(ErrorCode.FORBIDDEN);
        }
    }
}
