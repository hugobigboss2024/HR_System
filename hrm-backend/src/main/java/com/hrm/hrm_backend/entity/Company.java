package com.hrm.hrm_backend.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("hrm_company")
public class Company {
    
    @TableId(type = IdType.AUTO)
    private Long id;

    private String companyLogo;
    private String systemDisplayName;
    private String brName;
    private String brNo;
    private String employerEmpfId;
    private String phoneNumber;
    private String emailAddress;
    private String companyAddress;
    private String vendorBrNo;
    private Integer status; // 1:啟用,0:停用

    @TableLogic
    private Integer isDeleted; // 邏輯刪除(0:未刪,1:已刪)

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
