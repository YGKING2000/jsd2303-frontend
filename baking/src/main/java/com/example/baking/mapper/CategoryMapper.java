package com.example.baking.mapper;

import com.example.baking.pojo.vo.CategoryVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @date 2023/06/02 09:44
 */
@Repository
public interface CategoryMapper {
    List<CategoryVO> selectByType(Integer type);
}
