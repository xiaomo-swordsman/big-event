package com.xiaomo.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

// 测试类添加该注解，那么单元测试方法执行前，会先初始化spring容器
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet(){
        // 向redis中 存入一个键值对
        stringRedisTemplate.opsForValue().set("name","xiaomo");
    }

    @Test
    public void testGet(){
        // 向redis中 存入一个键值对
        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

}
