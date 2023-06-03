package com.example.baking.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className MybatisConfig
 * @date 2023/05/31 17:27
 */
@Configuration
@MapperScan("com.example.baking.mapper")
public class MybatisConfig {
}
