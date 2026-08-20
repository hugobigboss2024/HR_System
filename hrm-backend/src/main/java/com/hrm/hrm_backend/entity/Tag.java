package com.hrm.hrm_backend.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("hrm_tag")
public class Tag {
    
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long companyId;
    private String code;
    private String name;
    private Long parentId;
    private String ancestors;
    private Integer status;

    @TableLogic
    private Integer isDeleted;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
}
