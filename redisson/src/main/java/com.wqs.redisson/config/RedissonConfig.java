package com.wqs.redisson.config;

import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public Config config(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://172.18.18.160:6379").setPassword("123456");
        return config;
    }
}
