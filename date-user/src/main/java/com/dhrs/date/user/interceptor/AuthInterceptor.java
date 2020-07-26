package com.dhrs.date.user.interceptor;

import com.dhrs.date.common.utils.JwtUtil;
import com.dhrs.date.user.exception.AuthException;
import io.jsonwebtoken.Claims;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname AuthInterceptor
 * @Description TODO
 * @Date 2020/7/24 15:58
 * @Author 冷心影翼
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization) || !authorization.startsWith("Bearer ")) {
            throw new AuthException();
        }
        Claims claims = JwtUtil.parseJWT(authorization.substring(7));
        if (claims.isEmpty()) {
            throw new AuthException();
        }
        request.setAttribute("claims", claims);
        return true;
    }
}
