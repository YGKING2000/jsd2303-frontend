package com.example.boot.pojo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className User
 * @date 2023/05/30 09:36
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Date created;
}
