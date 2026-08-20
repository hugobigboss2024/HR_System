package com.hrm.hrm_backend.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TagTreeVO {
    
    private Long id;
    private Long companyId;
    private String code;
    private String name;
    private Long parentId;
    private Integer status;
    private LocalDateTime createTime;

    // 子標籤列表
    private List<TagTreeVO> children = new ArrayList<>();
}
