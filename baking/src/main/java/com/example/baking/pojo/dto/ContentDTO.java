package com.example.baking.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className ContentDTO
 * @date 2023/06/02 11:50
 */
@Data
public class ContentDTO {
    private String title;
    private String imgUrl;
    private String videoUrl;
    private String brief;
    private Long type;
    private Long categoryId;
    private Long createBy;
    private String content;
}
