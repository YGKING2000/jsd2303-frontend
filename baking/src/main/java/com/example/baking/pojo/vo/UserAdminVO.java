package com.example.baking.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className UserAdminVO
 * @date 2023/06/07 14:14
 */
@Data
public class UserAdminVO {
    private Long id;
    private String userName;
    private String nickName;
    private String imgUrl;
    private Boolean isAdmin;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
