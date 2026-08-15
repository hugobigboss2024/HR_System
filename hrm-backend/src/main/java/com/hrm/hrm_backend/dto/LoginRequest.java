package com.hrm.hrm_backend.dto;

import lombok.Data;

@Data
public class LoginRequest {
    // 登入請求參數
    private String username;
    private String password;
}
