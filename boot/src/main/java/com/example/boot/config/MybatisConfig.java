package com.example.boot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className MybatisConfig
 * @date 2023/05/30 09:38
 */
@Configuration
@MapperScan("com.example.boot.mapper")
public class MybatisConfig {
}
