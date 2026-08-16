package com.hrm.hrm_backend.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("sys_user")
public class SysUser {
    @TableId(type = IdType.AUTO)
    private Long userId;
    private String userName;
    private String password;
    private String realName;
    private String avatar;
    private String email;
    private String phone;
    private Integer status; // 1:正常, 0:禁用
    private Long employeeId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
