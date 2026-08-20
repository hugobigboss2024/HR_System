package com.hrm.hrm_backend.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrm.hrm_backend.entity.Tag;
import com.hrm.hrm_backend.mapper.TagMapper;
import com.hrm.hrm_backend.service.TagService;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    
}
