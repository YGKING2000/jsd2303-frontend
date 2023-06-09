package com.example.baking.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className ContentManagementVO
 * @date 2023/06/02 19:14
 */
@Data
public class ContentManagementVO {
    private Long id;
    private String title;
    private String imgUrl;
    private String brief;
    private Integer type;
    private String categoryName;
    private Integer viewCount;
    private Integer commentCount;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
