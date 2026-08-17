package com.hrm.hrm_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Long userId;
    private String userName;
    private String realName;

    /*
    public LoginResponse(String token, Long userId, String userName, String realName){
        this.token = token;
        this.userId = userId;
        this.userName = userName;
    }
    */
}
