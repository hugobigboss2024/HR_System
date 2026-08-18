package com.hrm.hrm_backend.config;

import com.hrm.hrm_backend.interceptors.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
        // 攔截所有請求
        .addPathPatterns("/**")
        // 排除不需要驗證Token的開放接口
        .excludePathPatterns(
            "auth/login",
            "/auth/register",
            "/error"  // Spring預設錯誤路徑
        );
    }

    
    
}
