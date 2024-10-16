package com.tang.bigevent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void setString() {
        redisTemplate.opsForValue().set("username","tang3");
        redisTemplate.opsForValue().set("id","1",10, TimeUnit.SECONDS);
    }

    @Test
    public void getString(){
        System.out.println(redisTemplate.opsForValue().get("username"));
    }
}
