package com.example.baking.mapper;

import com.example.baking.pojo.entity.User;
import com.example.baking.pojo.vo.UserAdminVO;
import com.example.baking.pojo.vo.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @date 2023/06/01 09:28
 */
@Repository
public interface UserMapper {
    UserVO selectByUserName(String userName);

    void insert(User user);

    void update(User user);

    List<UserAdminVO> select();

    void deleteById(Long id);
}
