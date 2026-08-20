package com.hrm.hrm_backend.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrm.hrm_backend.entity.CostCenter;
import com.hrm.hrm_backend.mapper.CostCenterMapper;
import com.hrm.hrm_backend.service.CostCenterService;

@Service
public class CostCenterServiceImpl extends ServiceImpl<CostCenterMapper, CostCenter> implements CostCenterService {
    
}
