package com.moshimoshi.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moshimoshi.common.exception.BusinessException;
import com.moshimoshi.common.dto.ErrorResponse;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class ExceptionHandlingFilter implements Filter {
    private final ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (BusinessException e) {
            setErrorResponse((HttpServletResponse) response, e);
        } catch (Exception e) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void setErrorResponse(HttpServletResponse httpServletResponse, BusinessException e) throws IOException {
        httpServletResponse.setStatus(e.getHttpStatus().value());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        String json = objectMapper.writeValueAsString(ErrorResponse.from(e));
        httpServletResponse.getWriter().write(json);
    }
}