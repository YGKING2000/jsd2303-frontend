package com.example.baking.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className ContentSimpleVO
 * @date 2023/06/06 16:39
 */
@Data
public class ContentSimpleVO {
    private Long id;
    private String imgUrl;
    private String title;
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date createTime;
    private Integer viewCount;
}
