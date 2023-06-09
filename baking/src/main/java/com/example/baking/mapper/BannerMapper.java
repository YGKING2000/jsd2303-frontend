package com.example.baking.mapper;

import com.example.baking.pojo.vo.BannerAdminVO;
import com.example.baking.pojo.vo.BannerVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @date 2023/05/31 17:20
 */
@Repository
public interface BannerMapper {
    List<BannerVO> select();

    List<BannerAdminVO> selectForAdmin();

    void deleteById(Long id);
}
