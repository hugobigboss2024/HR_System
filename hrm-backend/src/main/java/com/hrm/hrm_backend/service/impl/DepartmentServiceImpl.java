package com.hrm.hrm_backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrm.hrm_backend.entity.Department;
import com.hrm.hrm_backend.mapper.DepartmentMapper;
import com.hrm.hrm_backend.service.DepartmentService;
import com.hrm.hrm_backend.vo.DepartmentTreeVO;


@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    
    @Override
    public List<DepartmentTreeVO> getDepartmentTree(Long companyId){

        // 查出指定公司的所有部門
        LambdaQueryWrapper<Department> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(companyId != null, Department::getCompanyId, companyId).orderByAsc(Department::getId);
        List<Department> list = this.list(wrapper);

        // 轉為VO List
        List<DepartmentTreeVO> voList = list.stream().map(dept -> {
            DepartmentTreeVO vo = new DepartmentTreeVO();
            BeanUtils.copyProperties(dept, vo);
            return vo;
        }).collect(Collectors.toList());

        // 組裝成樹狀結構
        Map<Long, List<DepartmentTreeVO>> parentMap = voList.stream().collect(Collectors.groupingBy(DepartmentTreeVO::getParentId));
        voList.forEach(node -> node.setChildren(parentMap.getOrDefault(node.getId(), new ArrayList<>())));

        // 只返回頂層節點(parentId=0)
        return parentMap.getOrDefault(0L, new ArrayList<>());
       
    }

    @Override
    public boolean saveOrUpdateDepartment(Department department){

        // 
        if(department.getParentId() == null || department.getParentId() == 0L){
            department.setParentId(0L);
            department.setAncestors("0");
        }else{
            Department parent = this.getById(department.getParentId());
            if(parent != null){
                department.setAncestors(parent.getAncestors() + "," + parent.getId());
            }else{
                department.setAncestors("0");
            }
        }
        return this.saveOrUpdate(department);
    }
}
