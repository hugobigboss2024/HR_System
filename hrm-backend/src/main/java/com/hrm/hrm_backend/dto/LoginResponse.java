package com.hrm.hrm_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    // 登入成功回應
    private String token;
    private String tokenType = "Bearer";
    private Long userId;
    private String userName;

    public LoginResponse(String token, Long userId, String userName){
        this.token = token;
        this.userId = userId;
        this.userName = userName;
    }
}
