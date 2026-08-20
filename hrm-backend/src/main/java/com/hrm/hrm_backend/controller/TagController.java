package com.hrm.hrm_backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hrm.hrm_backend.dto.Result;
import com.hrm.hrm_backend.entity.Tag;
import com.hrm.hrm_backend.service.TagService;
import com.hrm.hrm_backend.vo.TagTreeVO;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;
    
    // 查詢Tag列表
    @GetMapping("/list")
    public Result<List<Tag>> list(@RequestParam Long companyId){
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getCompanyId, companyId).orderByAsc(Tag::getId);
        return Result.success(tagService.list(wrapper));
    }
    
    // 查詢標籤樹狀結構
    @GetMapping("/tree")
    public Result<List<TagTreeVO>> tree(@RequestParam Long companyId){
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getCompanyId, companyId).orderByAsc(Tag::getId);
        List<Tag> list = tagService.list(wrapper);

        // Entity 轉 VO
        List<TagTreeVO> voList = list.stream().map(tag -> {
            TagTreeVO vo = new TagTreeVO();
            BeanUtils.copyProperties(tag, vo);
            return vo;
        }).collect(Collectors.toList());

        // 組裝樹狀結構
        Map<Long, List<TagTreeVO>> parentMap = voList.stream().collect(Collectors.groupingBy(TagTreeVO::getParentId));

        for (TagTreeVO node : voList) {
            List<TagTreeVO> children = parentMap.get(node.getId());
            node.setChildren(children != null ? children : new ArrayList<>());
        }

        // 返回頂層標籤 (parentId = 0)
        return Result.success(parentMap.getOrDefault(0L, new ArrayList<>()));
    }

    @PostMapping
    public Result<String> add(@RequestBody Tag tag){
        if (tag.getParentId() == null){
            tag.setParentId(0L);
        }
        tagService.save(tag);
        return Result.success("added");
    }

    @PutMapping
    public Result<String> update(@RequestBody Tag tag){
        tagService.updateById(tag);
        return Result.success("updated");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id){
        tagService.removeById(id);
        return Result.success("deleted");
    }
}
