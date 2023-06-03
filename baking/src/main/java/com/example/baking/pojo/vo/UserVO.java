package com.example.baking.pojo.vo;

import lombok.Data;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className UserVo
 * @date 2023/06/01 09:29
 */
@Data
public class UserVO {
    private Long id;
    private String nickName;
    private String password;
    private Integer isAdmin;
    private String imgUrl;
}
