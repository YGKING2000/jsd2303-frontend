package com.example.boot.mapper;

import com.example.boot.pojo.entity.User;
import com.example.boot.pojo.vo.UserVO;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @date 2023/05/30 09:38
 */
public interface UserMapper {
    UserVO selectByUsername(String username);

    void insert(User user);
}
