package com.hrm.hrm_backend.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrm.hrm_backend.entity.Position;
import com.hrm.hrm_backend.mapper.PositionMapper;
import com.hrm.hrm_backend.service.PositionService;

@Service
public class PositionServviceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {
    
}
