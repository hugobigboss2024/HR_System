package com.hrm.hrm_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrm.hrm_backend.entity.SysUser;
import com.hrm.hrm_backend.exception.BusinessException;
import com.hrm.hrm_backend.mapper.SysUserMapper;
import com.hrm.hrm_backend.service.SysUserService;

@Service
public class SysUserServiceimpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SysUser login(String username, String password) {
        SysUser user = sysUserMapper.findByUsername(username);
        if (user == null) {
            throw new BusinessException("User not found");
        }

        // 驗證密碼
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException("Invalid password");
        }

        return user;
    }

    @Override
    public SysUser getByUsername(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName, username);
        return this.getOne(queryWrapper);
    }

}
