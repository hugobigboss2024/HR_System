package com.hrm.hrm_backend.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.hrm_backend.dto.DepartmentDTO;
import com.hrm.hrm_backend.dto.Result;
import com.hrm.hrm_backend.entity.Department;
import com.hrm.hrm_backend.service.DepartmentService;
import com.hrm.hrm_backend.vo.DepartmentTreeVO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;

    // 獲取樹狀部門結構
    @GetMapping("/tree")
    public Result<List<DepartmentTreeVO>> getTree(@RequestParam(required = false) Long companyId){
        return Result.success(departmentService.getDepartmentTree(companyId));
    }

    @PostMapping
    public Result<String> addDepartment(@Valid @RequestBody DepartmentDTO dto){
        Department department = new Department();
        BeanUtils.copyProperties(dto, department);
        departmentService.saveOrUpdateDepartment(department);
        return Result.success("department added");
    }

    @PutMapping
    public Result<String> updateDepartment(@Valid @RequestBody DepartmentDTO dto){
        Department department = new Department();
        BeanUtils.copyProperties(dto, department);
        departmentService.saveOrUpdateDepartment(department);
        return Result.success("department updated");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteDepartment(@PathVariable Long id){
        departmentService.removeById(id);
        return Result.success("department deleted");
    }
}
