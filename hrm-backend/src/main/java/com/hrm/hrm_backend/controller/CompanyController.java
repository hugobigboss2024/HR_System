package com.hrm.hrm_backend.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrm.hrm_backend.dto.CompanyDTO;
import com.hrm.hrm_backend.dto.Result;
import com.hrm.hrm_backend.entity.Company;
import com.hrm.hrm_backend.service.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;

    // **後期再確定是否需要保留addCompany**
    @PostMapping
    public Result<String> addCompany(@Valid @RequestBody CompanyDTO dto){
        Company company = new Company();
        BeanUtils.copyProperties(dto, company);
        companyService.save(company);
        return Result.success("company added");
    }

    @PostMapping
    public Result<String> updateCompany(@Valid @RequestBody CompanyDTO dto){
        Company company = new Company();
        BeanUtils.copyProperties(dto, company);
        companyService.updateById(company);
        return Result.success("company updated");
    }

    @GetMapping("/{id}")
    public Result<Company> getById(@PathVariable Long id){
        return Result.success(companyService.getById(id));
    }

    @GetMapping("/page")
    public Result<Page<Company>> page (@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size){
        return Result.success(companyService.page(new Page<>(current, size)));
    }

    @DeleteMapping
    public Result<String> delete(@PathVariable Long id){
        companyService.removeById(id);
        return Result.success("deleted");
    }

    
}
