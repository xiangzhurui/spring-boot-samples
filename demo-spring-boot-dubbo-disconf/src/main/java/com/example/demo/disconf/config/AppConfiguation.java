package com.example.demo.disconf.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiangzhurui
 * @version 2018/8/10 10:39
 */
@Configuration
@MapperScan("com.example.demo.disconf.dao.mapper")
public class AppConfiguation {
}
