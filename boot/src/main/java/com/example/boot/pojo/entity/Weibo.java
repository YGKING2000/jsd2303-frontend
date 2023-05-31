package com.example.boot.pojo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className Weibo
 * @date 2023/05/31 09:26
 */
@Data
public class Weibo {
    private Integer id;
    private String content;
    private Date created;
    private Integer userId;
}
