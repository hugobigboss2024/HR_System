package com.hrm.hrm_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.hrm.hrm_backend.entity.SysUser;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    // 根據帳號名稱查詢用戶
    SysUser findByUsername(String username);

}
