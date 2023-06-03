package com.example.baking.mapper;

import com.example.baking.pojo.entity.Content;
import com.example.baking.pojo.vo.ContentVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @date 2023/06/02 11:42
 */
@Repository
public interface ContentMapper {
    
    void insert(Content content);

    List<ContentVO> selectByTypeCreateBy(Integer type, Integer createBy);

    void deleteById(Integer id);
}   
