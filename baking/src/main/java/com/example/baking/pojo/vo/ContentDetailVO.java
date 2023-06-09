package com.example.baking.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className ContentDetailVO
 * @date 2023/06/06 15:23
 */
@Data
public class ContentDetailVO {
    private Long id;
    private String title;
    private String brief;
    private String videoUrl;
    private String imgUrl;
    private String content;
    private Integer type;
    private Integer viewCount;
    private Integer commentCount;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Integer userId;
    private String nickName;
    private String userImgUrl;
}
