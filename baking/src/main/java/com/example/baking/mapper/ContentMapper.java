package com.example.baking.mapper;

import com.example.baking.pojo.entity.Content;
import com.example.baking.pojo.vo.*;
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

    List<ContentManagementVO> selectByType(Integer type, Long createBy);

    void deleteById(Long id);

    ContentEditVO selectByIdForEdit(Long id);

    void update(Content content);

    List<ContentIndexVO> selectByTypeAndCategoryId(Integer type, Long categoryId);

    List<ContentIndexVO> selectByTypeForList(Integer type);

    ContentDetailVO selectByIdForDetail(Long id);

    List<ContentSimpleVO> selectOthersByUserId(Integer userId, Long id);

    void updateViewCountById(Long id);

    List<ContentSimpleVO> selectHotOrderByViewCount();

    List<ContentIndexVO> selectByKeyword(String keyword);

    List<ContentAdminVO> selectByTypeForAdmin(Integer type);

    void updateCommentCount(Long contentId);
}   
