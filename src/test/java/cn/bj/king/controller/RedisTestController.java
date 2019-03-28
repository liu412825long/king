package cn.bj.king.controller;

import cn.bj.king.KingApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisTestController extends KingApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void redisTest(){
        redisTemplate.opsForValue().set("name","liuhailong",10, TimeUnit.SECONDS);
        String name=(String)redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
    @Test
    public void redisTest1(){
        redisTemplate.opsForHash().put("account","name","liuhailong");
    }

}
