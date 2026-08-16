package com.hrm.hrm_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrm.hrm_backend.entity.SysUser;

public interface SysUserService extends IService<SysUser> {

    // 登入
    SysUser login(String username, String password);

    // 根據帳號名稱查詢用戶
    SysUser getByUsername(String username);
}
