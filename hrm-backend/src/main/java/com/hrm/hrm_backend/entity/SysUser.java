package com.hrm.hrm_backend.entity;

import lombok.Data;

@Data
public class SysUser {
    private Long userId;
    private String userName;
    private String password;
}
