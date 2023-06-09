package com.example.baking.pojo.vo;

import lombok.Data;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className ContentEditVO
 * @date 2023/06/05 11:47
 */
@Data
public class ContentEditVO {
    private Long id;
    private String title;
    private String imgUrl;
    private String videoUrl;
    private Integer type;
    private Long categoryId;
    private String content;
    private Integer createBy;
}
