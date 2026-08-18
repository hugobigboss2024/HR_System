package com.hrm.hrm_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentDTO {
    
    private Long id;

    @NotBlank(message = "Company ID not null")
    private Long companyId;

    private String code;

    @NotBlank(message = "Department Name not null")
    private String departmentNmae;

    private Integer status;
    private Long parentId = 0L; // 預設為頂級部門
}
