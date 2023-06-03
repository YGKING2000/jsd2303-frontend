package com.example.baking.controller;

import com.example.baking.mapper.ContentMapper;
import com.example.baking.pojo.dto.ContentDTO;
import com.example.baking.pojo.entity.Content;
import com.example.baking.response.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className ContentController
 * @date 2023/06/02 11:41
 */
@RestController
@RequestMapping("/v1/contents/")
public class ContentController {
    @Resource
    private ContentMapper mapper;
    
    @PostMapping("add-new")
    public ResultVO addNew(@RequestBody ContentDTO contentDTO) {
        Content content = new Content();
        BeanUtils.copyProperties(contentDTO, content);
        // 设置创建时间
        content.setCreateTime(new Date());
        mapper.insert(content);
        return ResultVO.ok();
    }
    
    @GetMapping("{type}/{createBy}/list")
    public ResultVO list(@PathVariable Integer type, @PathVariable Integer createBy) {
        return ResultVO.ok(mapper.selectByTypeCreateBy(type, createBy));
    }
    
    @GetMapping("{id}/delete")
    public ResultVO deleteById(@PathVariable Integer id) {
        mapper.deleteById(id);
        return ResultVO.ok();
    }
}
