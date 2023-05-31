package com.example.boot.controller;

import com.example.boot.mapper.WeiboMapper;
import com.example.boot.pojo.dto.WeiboDTO;
import com.example.boot.pojo.entity.Weibo;
import com.example.boot.pojo.vo.WeiboIndexVO;
import com.example.boot.response.ResultVO;
import com.example.boot.response.StatusCode;
import com.example.boot.security.CustomUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className WeiboController
 * @date 2023/05/31 09:23
 */
@RestController
@RequestMapping("/v1/weibo/")
public class WeiboController {
    @Resource
    private WeiboMapper weiboMapper;

    @PostMapping("insert")
    public ResultVO insert(@RequestBody WeiboDTO weiboDTO, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Weibo weibo = new Weibo();
        BeanUtils.copyProperties(weiboDTO, weibo);
        weibo.setUserId(userDetails.getId());
        weibo.setCreated(new Date());
        weiboMapper.insert(weibo);
        return new ResultVO(StatusCode.SUCCESS);
    }

    @GetMapping("")
    public ResultVO weiboList() {
        List<WeiboIndexVO> list = weiboMapper.selectAllWeibo();
        return new ResultVO(StatusCode.SUCCESS, list);
    }

    @PostMapping("{id}/delete")
    // 当前登录用户必须拥有ADMIN权限才能调用该方法进行删除
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResultVO delete(@PathVariable Integer id) {
        weiboMapper.deleteById(id);
        return new ResultVO(StatusCode.SUCCESS);
    }
}
