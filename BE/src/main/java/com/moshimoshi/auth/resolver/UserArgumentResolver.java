package com.moshimoshi.auth.resolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moshimoshi.auth.domain.AuthenticatedUser;
import com.moshimoshi.auth.utils.JwtProvider;
import com.moshimoshi.common.Define;
import com.moshimoshi.user.domain.User;
import com.moshimoshi.user.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean hasUsersType = User.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && hasUsersType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        String token = getToken(httpServletRequest);
        AuthenticatedUser authenticatedUser = getAuthenticatedUser(token);
        return userService.findByLoginId(authenticatedUser.getLoginId());
    }

    private String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return authorization.substring(7);
    }

    private AuthenticatedUser getAuthenticatedUser(String token) throws JsonProcessingException {
        Claims claims = jwtProvider.getClaims(token);
        String authenticatedUserJson = claims.get(Define.AUTHENTICATED, String.class);
        return objectMapper.readValue(authenticatedUserJson, AuthenticatedUser.class);
    }
}
