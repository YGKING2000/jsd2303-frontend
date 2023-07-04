package com.example.baking.controller;

import com.example.baking.mapper.UserMapper;
import com.example.baking.pojo.dto.UserLoginDTO;
import com.example.baking.pojo.dto.UserRegDTO;
import com.example.baking.pojo.dto.UserUpdateDTO;
import com.example.baking.pojo.entity.User;
import com.example.baking.pojo.vo.UserVO;
import com.example.baking.response.ResultVO;
import com.example.baking.response.StatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className UserController
 * @date 2023/06/01 09:27
 */
@RestController
@RequestMapping("/v1/users/")
public class UserController {
    @Resource
    private UserMapper mapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthenticationManager manager;

    @PostMapping("reg")
    public ResultVO reg(@RequestBody UserRegDTO userRegDTO) {
        UserVO userVO = mapper.selectByUserName(userRegDTO.getUserName());
        if (userVO != null) {
            return new ResultVO(StatusCode.USERNAME_ALREADY_EXISTS);
        }
        User user = new User();
        BeanUtils.copyProperties(userRegDTO, user);
        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(new Date());
        // 默认设置账号不是管理员权限
        user.setIsAdmin(0);
        // 设置账号默认头像
        user.setImgUrl("/imgs/ikun.jpg");
        mapper.insert(user);
        return ResultVO.ok();
    }

    @PostMapping("login")
    public ResultVO login(@RequestBody UserLoginDTO userLoginDTO) {
        Authentication result = manager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDTO.getUserName(), userLoginDTO.getPassword()
        ));
        // 将认证结果保存到Security上下文中，使得Security框架记住登录状态
        SecurityContextHolder.getContext().setAuthentication(result);
        System.out.println(result.getPrincipal());
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return ResultVO.ok(result.getPrincipal());
    }

    @GetMapping("logout")
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    @PostMapping("update")
    public ResultVO update(@RequestBody UserUpdateDTO userUpdateDTO) {
        User user = new User();
        BeanUtils.copyProperties(userUpdateDTO, user);
        mapper.update(user);
        return ResultVO.ok();
    }
    
    @GetMapping("/list")
    public ResultVO list() {
        return ResultVO.ok(mapper.select());
    }
    
    @PostMapping("/{id}/{isAdmin}/change")
    public ResultVO changeIsAdmin(@PathVariable Long id, @PathVariable Integer isAdmin) {
        User user = new User();
        user.setId(id);
        user.setIsAdmin(isAdmin);
        System.out.println(user);
        mapper.update(user);
        return ResultVO.ok();
    }
    
    @PostMapping("/{id}/delete")
    public ResultVO delete(@PathVariable Long id) {
        mapper.deleteById(id);
        return ResultVO.ok();    
    }
}


