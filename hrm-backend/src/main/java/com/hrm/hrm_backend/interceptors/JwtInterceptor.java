package com.hrm.hrm_backend.interceptors;

import com.hrm.hrm_backend.exception.BusinessException;
import com.hrm.hrm_backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    // 
    @Override
    public boolean preHandle(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        // 處理跨域預檢請求 (OPTIONS 請求直接放行)
        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            return true;
        }
        // 從Header獲取Token(約定Header名稱為Authorization或token)
        String token = request.getHeader("Authorization");
        // 如果Header裡面沒取到，嘗試獲取"token"Header
        if(!StringUtils.hasText(token)){
            token = request.getHeader("token");
        }
        // 判斷Token是否存在
        if(!StringUtils.hasText(token)){
            throw new BusinessException("Token is missing, please login first");
        }
        // 如果傳入的是Bearer token格式，進行截取
        if(token.startsWith("Bearer")){
            token = token.substring(7);
        }
        // 驗證Token是否有效/過期
        if(!jwtUtils.validateToken(token)){
            throw new BusinessException("Token is invalid or expired, please login again");
        }

        return true;
    }

    // 
    
}
