package com.example.boot.security;

import com.example.boot.mapper.UserMapper;
import com.example.boot.pojo.vo.UserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className UserDetailServiceImpl
 * @date 2023/05/30 15:44
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO userVO = userMapper.selectByUsername(username);
        // 判断用户是否存在
        if (userVO == null) {
            return null;
        }
        String role = username.endsWith("胡歌") ? "ADMIN" : "USER";
        List<GrantedAuthority> list = AuthorityUtils.createAuthorityList(role);
        return new CustomUserDetails(userVO.getId(), userVO.getNickname(), username, userVO.getPassword(), list);
        /*// 如果用户输入的密码和数据库中查询到的密码不一致，则会抛出异常
        return User.builder()
                .username(username).password(userVO.getPassword())
                .disabled(false)// 是否禁用
                .accountLocked(false)// 是否锁定
                .accountExpired(false)// 登录是否过期
                .credentialsExpired(false)// 登录凭证是否过期
                .authorities("权限")// 授权，授予当前登录用户的权限
                .build();// 创建账号*/
    }
}
