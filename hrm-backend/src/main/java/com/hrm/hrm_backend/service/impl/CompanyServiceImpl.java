package com.hrm.hrm_backend.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrm.hrm_backend.entity.Company;
import com.hrm.hrm_backend.mapper.CompanyMapper;
import com.hrm.hrm_backend.service.CompanyService;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

}
