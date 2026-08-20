package com.hrm.hrm_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hrm.hrm_backend.dto.Result;
import com.hrm.hrm_backend.entity.CostCenter;
import com.hrm.hrm_backend.service.CostCenterService;

@RestController
@RequestMapping("/cost-center")
public class CostCenterController {
    
    @Autowired
    private CostCenterService costCenterService;

    // 根據公司ID獲取成本中心列表
    @GetMapping("/list")
    public Result<List<CostCenter>> list(@RequestParam Long companyId,@RequestParam(required = false) String keyword){
        LambdaQueryWrapper<CostCenter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CostCenter::getCompanyId, companyId).and(StringUtils.hasText(keyword), w -> w.like(CostCenter::getCode, keyword).or().like(CostCenter::getName, keyword)).orderByAsc(CostCenter::getCode);
        return Result.success(costCenterService.list(wrapper));
    }

    @GetMapping("/{id}")
    public Result<CostCenter> getById(@PathVariable Long id){
        return Result.success(costCenterService.getById(id));
    }

    @PostMapping
    public Result<String> add(@RequestBody CostCenter costCenter){
        costCenterService.save(costCenter);
        return Result.success("added");
    }

    @PutMapping
    public Result<String> update(@RequestBody CostCenter costCenter){
        costCenterService.updateById(costCenter);
        return Result.success("updated");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id){
        costCenterService.removeById(id);
        return Result.success("deleted");
    }
}
