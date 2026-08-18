package com.hrm.hrm_backend.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hrm.hrm_backend.entity.Company;

@Mapper
public interface CompanyMapper extends BaseMapper<Company> {
    
}
