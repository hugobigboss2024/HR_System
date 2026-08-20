package com.hrm.hrm_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.hrm.hrm_backend.dto.Result;
import com.hrm.hrm_backend.entity.Position;
import com.hrm.hrm_backend.service.PositionService;

@RestController
@RequestMapping("/position")
public class PositionController {
    
    @Autowired
    private PositionService positionService;

    // 分頁或清單查詢
    @GetMapping("/list")
    public Result<List<Position>> list(@RequestParam Long companyId){
        LambdaQueryWrapper<Position> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Position::getCompanyId, companyId).eq(Position::getStatus, 1);
        return Result.success(positionService.list(wrapper));
    }

    @PostMapping
    public Result<String> add(@RequestBody Position position){
        positionService.save(position);
        return Result.success("added");
    }

    @PutMapping
    public Result<String> update(@RequestBody Position position){
        positionService.updateById(position);
        return Result.success("updated");
    }

    @DeleteMapping("/{id]")
    public Result<String> delete(@RequestBody Long id){
        positionService.removeById(id);
        return Result.success("deleted");
    }
}
