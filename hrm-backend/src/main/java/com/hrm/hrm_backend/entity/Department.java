package com.hrm.hrm_backend.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("hrm_dapartment")
public class Department {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long companyId;
    private String code;
    private String departmentName;
    private Integer status; // 1:啟用,0:停用
    private Long parentId; // 上級部門ID(0表示頂級)
    private String ancestors; // 多重上級部門鏈路，如 "0,1,5"

    @TableLogic
    private Integer isDeleted;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
}
