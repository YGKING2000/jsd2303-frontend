package com.example.boot.controller;

import com.example.boot.mapper.UserMapper;
import com.example.boot.pojo.dto.UserLoginDTO;
import com.example.boot.pojo.dto.UserRegDTO;
import com.example.boot.pojo.entity.User;
import com.example.boot.pojo.vo.UserVO;
import com.example.boot.response.ResultVO;
import com.example.boot.response.StatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className UserController
 * @date 2023/05/30 09:37
 */

@RestController
@RequestMapping("/v1/users/")
public class UserController {
    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @PostMapping("reg")
    public ResultVO reg(@RequestBody @Validated UserRegDTO userRegDTO) {
        // 查询用户在数据库中是否已存在
        UserVO userVO = userMapper.selectByUsername(userRegDTO.getUsername());
        if (userVO != null) {
            return new ResultVO(405, "用户名已存在");
        }
        // 添加用户信息到数据库中
        User user = new User();
        BeanUtils.copyProperties(userRegDTO, user);
        // 对user对象里面的密码进行加密，加密之后会得到一个60个字符长度的密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated(new Date());
        userMapper.insert(user);
        // 当给客户端响应的是Java对象时SpringMVC框架会自动将Java对象转成Json格式的字符串，然后将字符串响应给客户端
        return new ResultVO(StatusCode.SUCCESS);
    }

    @Resource
    private AuthenticationManager manager;

    @PostMapping("login")
    public ResultVO login(@RequestBody @Validated UserLoginDTO userLoginDTO) {
        /*// 判断当前是否已有用户登录
        UserVO userVO = (UserVO) session.getAttribute("user");
        if (userVO != null) {
            return new ResultVO(404, "当前已有用户登录!");
        }
        // 查询用户在数据库中是否存在
        userVO = userMapper.selectByUsername(userLoginDTO.getUsername());
        if (userVO == null) {
            return new ResultVO(404, "用户名不存在!");
        }
        // 判断用户密码是否正确
        if (!userVO.getPassword().equals(userLoginDTO.getPassword())) {
            return new ResultVO(404, "密码错误!");
        }
        // 记录用户登录状态
        session.setAttribute("user", userVO);*/

        // 通过认证管理器启动Security的认证流程，返回认证结果对象
        Authentication result = manager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDTO.getUsername(), userLoginDTO.getPassword()
        ));
        // 将认证结果保存到Security上下文中，使得Security框架记住登录状态
        SecurityContextHolder.getContext().setAuthentication(result);
        // 代码执行到这里时代表登录成功，如果登录失败Security框架会抛出异常
        return new ResultVO(StatusCode.SUCCESS);
    }
}
