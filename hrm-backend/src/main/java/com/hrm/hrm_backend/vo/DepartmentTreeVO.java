package com.hrm.hrm_backend.vo;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DepartmentTreeVO {
    
    private Long id;
    private Long companyId;
    private String code;
    private String departmentName;
    private Integer status;
    private Long parentId;
    private LocalDateTime createTime;

    // 子部門列表
    private List<DepartmentTreeVO> children = new ArrayList<>();

    
}
