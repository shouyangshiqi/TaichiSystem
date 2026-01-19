package com.bsu.taichi.content.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Configuration
@MapperScan("com.bsu.taichi.mapper") // 把注解移到这里
public class MyBatisConfig {
}