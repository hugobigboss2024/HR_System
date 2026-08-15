package com.hrm.hrm_backend.service;

import com.hrm.hrm_backend.entity.SysUser;

public interface SysUserService {

    SysUser login(String username, String password);
    
}
