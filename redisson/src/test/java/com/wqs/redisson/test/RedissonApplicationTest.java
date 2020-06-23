package com.wqs.redisson.test;

import com.wqs.redisson.RedissonApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedissonApplication.class})
public class RedissonApplicationTest {

    /*@Autowired
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

    }*/

    @Autowired
    private RedissonClient client;

    @Autowired
    @Qualifier("rc")
    private RedissonReactiveClient reactiveClient;

    @Test
    public void syncTest(){
        RAtomicLong myLong = client.getAtomicLong("mylong");
        boolean b = myLong.compareAndSet(222, 401);
    }

    @Test
    public void  asyncTest(){
        RAtomicLong mylong = client.getAtomicLong("mylong");
        RFuture<Boolean> future = mylong.compareAndSetAsync(222, 4);
        future.whenComplete((aBoolean, throwable) ->{
            Assert.assertEquals(aBoolean,false);
            System.out.println("==============");
        });
        RLiveObjectService liveObjectService = client.getLiveObjectService();

    }
}
