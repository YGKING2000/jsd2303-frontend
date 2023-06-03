package com.example.baking.controller;

import com.example.baking.mapper.BannerMapper;
import com.example.baking.response.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className BannerController
 * @date 2023/05/31 17:20
 */
@RestController
@RequestMapping("/v1/banners/")
public class BannerController {
    @Resource
    private BannerMapper mapper;

    @GetMapping("")
    public ResultVO select() {
        return ResultVO.ok(mapper.select());
    }
}
