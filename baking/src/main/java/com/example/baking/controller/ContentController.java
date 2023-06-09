package com.example.baking.controller;

import com.example.baking.mapper.ContentMapper;
import com.example.baking.pojo.dto.ContentDTO;
import com.example.baking.pojo.entity.Content;
import com.example.baking.pojo.vo.ContentEditVO;
import com.example.baking.response.ResultVO;
import com.example.baking.response.StatusCode;
import com.example.baking.security.CustomUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className ContentController
 * @date 2023/06/02 11:41
 */
@RestController
@RequestMapping("/v1/contents")
public class ContentController {
    @Value("${filePath}")
    private String filePath;
    @Resource
    private ContentMapper mapper;

    @PostMapping("/add-new")
    public ResultVO addNew(@RequestBody ContentDTO contentDTO, @AuthenticationPrincipal CustomUserDetails details) {
        Content content = new Content();
        BeanUtils.copyProperties(contentDTO, content);
        if (contentDTO.getId() == null) {
            // 设置创建时间
            content.setCreateTime(new Date());
            mapper.insert(content);
        } else {
            content.setUpdateTime(new Date());
            content.setUpdateBy(details.getId());
            mapper.update(content);
        }
        return ResultVO.ok();
    }

    @GetMapping("/{type}/management")
    public ResultVO list(@PathVariable Integer type, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            return new ResultVO(StatusCode.NOT_LOGIN);
        }
        return ResultVO.ok(mapper.selectByType(type, userDetails.getId()));
    }

    @GetMapping("/{id}/edit")
    public ResultVO getEdit(@PathVariable Long id) {
        return ResultVO.ok(mapper.selectByIdForEdit(id));
    }

    @PostMapping("/{id}/delete")
    public ResultVO delete(@PathVariable Long id) {
        ContentEditVO contentEditVO = mapper.selectByIdForEdit(id);
        // 删除封面图片
        new File(filePath + contentEditVO.getImgUrl()).delete();
        // 判断是否含有视频并作删除操作
        if (contentEditVO.getType() == 2) {
            new File(filePath + contentEditVO.getVideoUrl()).delete();
        }
        mapper.deleteById(id);
        return ResultVO.ok();
    }
    
    @GetMapping("/{type}/{categoryId}/index")
    public ResultVO index(@PathVariable Integer type, @PathVariable Long categoryId) {
        return ResultVO.ok(mapper.selectByTypeAndCategoryId(type, categoryId));
    }
    
    @GetMapping("/{type}/list")
    public ResultVO list(@PathVariable Integer type) {
        return ResultVO.ok(mapper.selectByTypeForList(type));
    }
    
    @GetMapping("/{id}/detail")
    public ResultVO detail(@PathVariable Long id) {
        mapper.updateViewCountById(id);
        return ResultVO.ok(mapper.selectByIdForDetail(id));
    }
    
    @GetMapping("/{userId}/{id}/others")
    public ResultVO others(@PathVariable Integer userId, @PathVariable Long id) {
        return ResultVO.ok(mapper.selectOthersByUserId(userId, id));
    }
    
    @GetMapping("/hot")
    public ResultVO hot() {
        return ResultVO.ok(mapper.selectHotOrderByViewCount());
    }
    
    @GetMapping("/{keyword}/search")
    public ResultVO search(@PathVariable String keyword) {
        return ResultVO.ok(mapper.selectByKeyword(keyword));
    }
    
    @GetMapping("/{type}/admin")
    public ResultVO admin(@PathVariable Integer type) {
        return ResultVO.ok(mapper.selectByTypeForAdmin(type));
    }
}
