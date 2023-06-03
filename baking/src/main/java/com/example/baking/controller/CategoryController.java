package com.example.baking.controller;

import com.example.baking.mapper.CategoryMapper;
import com.example.baking.response.ResultVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className CategoryController
 * @date 2023/06/02 09:39
 */
@RestController
@RequestMapping("/v1/categories/")
public class CategoryController {
    @Resource
    private CategoryMapper mapper;
    
    /**
     * @description 根据一级分类ID查询其二级分类
     * @return com.example.baking.response.ResultVO
     * @param type Integer
     * @author YGKING
     * @date 2023/06/02 09:44:19
     */
    @RequestMapping("/{type}/sub")
    public ResultVO sub(@PathVariable Integer type) {
        return ResultVO.ok(mapper.selectByType(type));
    }
}
