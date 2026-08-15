package com.hrm.hrm_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hrm.hrm_backend.entity.SysUser;
import com.hrm.hrm_backend.mapper.SysUserMapper;
import com.hrm.hrm_backend.service.SysUserService;


@Service
public class SysUserServiceimpl implements SysUserService {
    
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SysUser login(String username, String password) {
        SysUser user = sysUserMapper.findByUsername(username);
        if(user == null){
            return null;
        }

        // 驗證密碼
        if(!passwordEncoder.matches(password, user.getPassword())){
            return null;
        }

        return user;
    }

    
}
