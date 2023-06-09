package com.example.baking.controller;

import com.example.baking.mapper.BannerMapper;
import com.example.baking.response.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className BannerController
 * @date 2023/05/31 17:20
 */
@RestController
@RequestMapping("/v1/banners")
public class BannerController {
    @Resource
    private BannerMapper mapper;

    @GetMapping("/")
    public ResultVO select() {
        return ResultVO.ok(mapper.select());
    }
    
    @GetMapping("/admin")
    public ResultVO admin() {
        return ResultVO.ok(mapper.selectForAdmin());
    }

    @PostMapping("/{id}/delete")
    public ResultVO delete(@PathVariable Long id) {
        mapper.deleteById(id);
        return ResultVO.ok();
    }
}
