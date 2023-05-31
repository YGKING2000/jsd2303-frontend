package com.example.boot.mapper;

import com.example.boot.pojo.entity.Weibo;
import com.example.boot.pojo.vo.WeiboIndexVO;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @date 2023/05/31 09:25
 */
public interface WeiboMapper {
    void insert(Weibo weibo);

    List<WeiboIndexVO> selectAllWeibo();

    void deleteById(Integer id);
}
