package com.example.baking.security;

import com.example.baking.mapper.UserMapper;
import com.example.baking.pojo.vo.UserVO;
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
        UserVO userVO = userMapper.selectByUserName(username);
        // 判断用户是否存在
        if (userVO == null) {
            return null;
        }
        String role = userVO.getIsAdmin() == 1 ? "ADMIN" : "USER";
        List<GrantedAuthority> list = AuthorityUtils.createAuthorityList(role);
        return new CustomUserDetails(userVO.getId(), userVO.getNickName(), userVO.getImgUrl(),
                userVO.getIsAdmin(), username, userVO.getPassword(), list);
    }
}
