package com.hrm.hrm_backend.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrm.hrm_backend.entity.Department;
import com.hrm.hrm_backend.vo.DepartmentTreeVO;

public interface DepartmentService extends IService<Department> {
    
    // 取得特定公司的部門樹狀結構
    List<DepartmentTreeVO> getDepartmentTree(Long companyId);

    // 新增或更新部門
    boolean saveOrUpdateDepartment(Department department);
}
