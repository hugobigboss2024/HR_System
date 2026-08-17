package com.hrm.hrm_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrm.hrm_backend.dto.RegisterDTO;
import com.hrm.hrm_backend.entity.SysUser;

import jakarta.validation.Valid;

public interface SysUserService extends IService<SysUser> {

    // 登入
    SysUser login(String username, String password);

    // 根據帳號名稱查詢用戶
    SysUser getByUsername(String username);

    // 註冊新用戶
    void register(RegisterDTO registerDTO);
}
