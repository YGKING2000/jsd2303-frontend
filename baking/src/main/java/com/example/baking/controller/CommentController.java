package com.example.baking.controller;

import com.example.baking.mapper.CommentMapper;
import com.example.baking.mapper.ContentMapper;
import com.example.baking.pojo.dto.CommentDTO;
import com.example.baking.pojo.entity.Comment;
import com.example.baking.response.ResultVO;
import com.example.baking.response.StatusCode;
import com.example.baking.security.CustomUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className CommentController
 * @date 2023/06/07 10:26
 */
@RestController
@RequestMapping("/v1/comments")
public class CommentController {
    @Resource
    private CommentMapper mapper;
    
    @Resource
    private ContentMapper contentMapper;
    
    @PostMapping("/add-new")
    public ResultVO addNew(@RequestBody CommentDTO commentDTO, @AuthenticationPrincipal CustomUserDetails details) {
        if (details == null) {
            return new ResultVO(StatusCode.NOT_LOGIN);
        }
        contentMapper.updateCommentCount(commentDTO.getContentId());
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setUserId(details.getId());
        comment.setCreateTime(new Date());
        mapper.insert(comment);
        return ResultVO.ok();
    }
    
    @GetMapping("/{contentId}/list")
    public ResultVO list(@PathVariable Integer contentId) {
        return ResultVO.ok(mapper.selectByContentId(contentId));
    }
}
