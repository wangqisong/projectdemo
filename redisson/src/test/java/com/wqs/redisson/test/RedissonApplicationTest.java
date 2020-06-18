package com.wqs.redisson.test;

import com.wqs.redisson.RedissonApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedissonApplication.class})
public class RedissonApplicationTest {

    @Autowired
    private Config config;

    @Test
    public void test(){
        RedissonClient client = Redisson.create(config);
        RKeys keys = client.getKeys();
        Iterable<String> keys1 = keys.getKeys();
        Iterator<String> iterator = keys1.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
