package com.example.baking.mapper;

import com.example.baking.pojo.entity.Comment;
import com.example.baking.pojo.vo.CommentVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @date 2023/06/07 10:26
 */
@Repository
public interface CommentMapper {

    void insert(Comment comment);

    List<CommentVO> selectByContentId(Integer contentId);
}
