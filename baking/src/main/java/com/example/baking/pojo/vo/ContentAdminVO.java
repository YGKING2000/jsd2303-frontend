package com.example.baking.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className ContentAdminVO
 * @date 2023/06/07 16:34
 */
@Data
public class ContentAdminVO {
    private Long id;
    private String title;
    private String imgUrl;
    private String brief;
    private String categoryName;
    private Integer viewCount;
    private Integer commentCount;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
