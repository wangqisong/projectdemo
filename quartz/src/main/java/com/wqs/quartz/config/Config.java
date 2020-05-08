package com.wqs.quartz.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/**
 * @author: wangqisong
 * @date: 2020/5/8
 * @Description:
 */
@Configuration
@ComponentScans(value = {@ComponentScan("com.wqs.quartz.job"),
        @ComponentScan("com.wqs.quartz.trigger")})
public class Config {
    
}
